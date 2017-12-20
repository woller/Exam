package ClientServerMulticlient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 * @author Jon Eikholm (jone@kea.dk) Network preparation
 */
public class MulticlientChat extends Application {

    private static final int TILE_SIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;

    //GUI part
    TextArea mainChat;

    // network part
    private Button startServerButton = new Button("Start Server");
    private Button connectToServerButton = new Button("Connect to Server");
    private TextField statusLabel = new TextField();
    private TextField gameOverLabel = new TextField();
    private String messageStyle =  "; -fx-text-fill: white";
    public final int serverPortNumber = 1337;
    private TextField ipEntry = new TextField("127.0.0.1");
    private TextField portEntry = new TextField(serverPortNumber+"");
    private Label ipLabel = new Label("IP");
    private Pane root;
    private Socket socket;

    //Client
    private ClientThread client;

    //Server
    private ServerThread server;

    @Override
    public void stop(){
        System.out.println("app was closed...");
        if (server != null)
        {
            server.stop();
        } else if (client != null)
        {
            client.stop();
        }
    }

    private void clientConnectToServer(){
        System.out.println("connecting to server...");
        client = new ClientThread(this, Integer.parseInt(portEntry.getText()), ipEntry.getText());
        Thread thread = new Thread(client);
        thread.start();

    }

    private void startServer(){
        System.out.println("starting server...");
        server = new ServerThread(this, serverPortNumber);
        Thread thread = new Thread(server);
        thread.start();
        try
        {
            String ip = InetAddress.getLocalHost().toString();
            setServerStatus(true, ip);

        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        }

    }

    private void send(String message)
    {
        if (client != null)
        {
            client.sendMessage(message);
        } else if (server != null)//eller skulle man med en server oprette både server og klient?
        {
            server.sendMessage(message);
        }
    }

    public void receive(String message){
        Platform.runLater(() -> mainChat.appendText(message));
        // possibly add further code
    }

    // Exercise: Call this method to display server status
    public void setServerStatus(boolean isRunning, String hostAddress){
        Platform.runLater(() -> {
            if(isRunning){
                statusLabel.setText("Server is Running ");
                ipLabel.setText(hostAddress);
                statusLabel.setStyle("-fx-background-color: cornflowerblue" + messageStyle);
            }else{
                statusLabel.setText("Server is OFF");
                statusLabel.setStyle("-fx-background-color: darkgray" + messageStyle);
            }
        });
    }

    // Exercise: Call this method to display connection status
    public void setConnectionStatus(boolean isConnected){
        Platform.runLater(() -> {
            if (isConnected)
            {
                statusLabel.setText("Connected.");
                statusLabel.setStyle("-fx-background-color: green" + messageStyle);

            } else
            {
                statusLabel.setText("Not connected.");
                statusLabel.setStyle("-fx-background-color: red" + messageStyle);
            }
        });
    }

    private Parent createContent() {
        root = new VBox();// Pane();
        root.setPrefSize(600,300);
        //root.setBackground(new Background(BackgroundFill));
        root.setStyle("-fx-background-color: blue");
        HBox networkPanel = makeNetworkPanel();

        TextField userName = new TextField();
        mainChat = new TextArea();
        userName.setPromptText("enter your name");
        TextField userInput = new TextField();
        userInput.setOnAction(event -> {
            System.out.println("you entered..."  + userInput.getText());
            String message = userName.getText() + ": " + userInput.getText() + "\n";
            send(message);
            userInput.clear();
        });
        userInput.setPromptText("enter message for all");


        mainChat.setPrefSize(580, 200);
        root.getChildren().addAll(networkPanel, statusLabel, userName,  mainChat, userInput);
        return root;
    }

    private HBox makeNetworkPanel()
    {
        HBox networkPanel = new HBox();
        ipEntry.setPromptText("add server ip");
        portEntry.setPromptText("add server port");

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton serverOn = makeServerRadioButton(networkPanel, toggleGroup);
        RadioButton clientOn = makeClientRadioButton(networkPanel, toggleGroup);

        networkPanel.getChildren().addAll(serverOn, clientOn);
        configureStatusLabel();
        configureGameOverLabel();
        startServerButton.setOnAction(event -> startServer());
        connectToServerButton.setOnAction(event -> clientConnectToServer());

        return networkPanel;
    }



    private RadioButton makeServerRadioButton(HBox networkPanel, ToggleGroup toggleGroup)
    {
        RadioButton serverOn = new RadioButton("Server");
        serverOn.setStyle("-fx-text-fill:white");
        serverOn.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue)
                toggleClientServer(networkPanel, newValue);
        });
        serverOn.setToggleGroup(toggleGroup);
        return serverOn;
    }

    private RadioButton makeClientRadioButton(HBox networkPanel, ToggleGroup toggleGroup)
    {
        RadioButton clientOn = new RadioButton("Client");
        clientOn.setStyle("-fx-text-fill:white");
        clientOn.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue)
                toggleClientServer(networkPanel, !newValue);
        });
        clientOn.setToggleGroup(toggleGroup);
        return clientOn;
    }


    private void configureStatusLabel()
    {
        statusLabel.setMinWidth(TILE_SIZE * (COLUMNS + 1));
        statusLabel.setMinHeight(30);
        String style = "-fx-background-color:green; -fx-text-fill: white; -fx-font-weight: 200";
        statusLabel.setStyle(style);
        statusLabel.setAlignment(Pos.CENTER);

        ipLabel.setStyle(messageStyle);
    }

    private void configureGameOverLabel()
    {
        gameOverLabel.setTranslateY(ROWS * 11);
        gameOverLabel.setMinWidth(TILE_SIZE * (COLUMNS + 1));
        gameOverLabel.setMinHeight(30);
        String style = "-fx-background-color:sandybrown; -fx-text-fill: red; -fx-font-weight: 200";
        gameOverLabel.setStyle(style);
        gameOverLabel.setAlignment(Pos.CENTER);
        ipLabel.setStyle(messageStyle);
    }

    private void toggleClientServer(HBox networkPanel, Boolean isServer)
    {
        if(isServer){
            ipEntry.setVisible(false);

            networkPanel.getChildren().add(startServerButton);
            networkPanel.getChildren().add(ipLabel);
            networkPanel.getChildren().remove(ipEntry);
            networkPanel.getChildren().remove(portEntry);
            networkPanel.getChildren().remove(connectToServerButton);
        }else{
            ipEntry.setVisible(true);
            networkPanel.getChildren().remove(startServerButton);
            networkPanel.getChildren().remove(ipLabel);
            networkPanel.getChildren().add(ipEntry);
            networkPanel.getChildren().add(portEntry);
            networkPanel.getChildren().add(connectToServerButton);

        }
    }



    // end of network code.








    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }



    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}