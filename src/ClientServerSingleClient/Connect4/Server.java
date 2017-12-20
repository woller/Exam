package ClientServerSingleClient.Connect4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Nicolai Bach Woller on 12-09-2017.
 */
public class Server implements Runnable//TODO: Make a Core-class containing all the shared code
{
    private ServerSocket serverSocket;
    private Socket socket;
    private Connect4App app;
    private boolean running; //to indicate whether the Server is running and listening
    private PrintWriter output;
    private BufferedReader input;

    public Server(int port, Connect4App connect4App)
    {
        try
        {
            this.app = connect4App;
            serverSocket = new ServerSocket(port);
            running = false;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        running = true;
        try
        {
            socket = serverSocket.accept();
            //When connection is established, construct all IO-objects
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            while (running)
            {

                if (input.ready())
                {
                    String receivedMessage = input.readLine();
                    if (receivedMessage.equals("end"))
                    {
                        app.stop();
                        running = false;
                    } else
                    {
                        System.out.println(receivedMessage);
                        app.receiveNetworkMove(Integer.parseInt(receivedMessage));
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void sendMove(int column)
    {
        output.println(column);
    }

    public void endGame()
    {
        if (socket.isConnected())
        {
            output.println("end");
        }
        running = false;
        try
        {
            app.setServerStatus(false, InetAddress.getLocalHost().toString());
        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        try
        {
            socket.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
