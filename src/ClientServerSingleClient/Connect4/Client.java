package ClientServerSingleClient.Connect4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Nicolai Bach Woller on 12-09-2017.
 */
public class Client implements Runnable
{
    private String ip;
    private int port;
    private Connect4App app;
    private boolean running; //to indicate whether the Client is running and listening
    private Socket socket;
    private PrintWriter output;

    public Client(String ip, int port, Connect4App app)
    {
        this.ip = ip;
        this.port = port;
        this.app = app;
        running = false;
    }

    public void run()
    {
        running = true;
        try
        {
            socket = new Socket(ip, port);
            app.setConnectionStatus(true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            while (running)
            {

                if (input.ready())
                {
                    String receivedMessage = input.readLine();
                    if (receivedMessage.equals("end"))
                    {
                        app.stop();
                        endGame();
                    } else
                    {
                        System.out.println(receivedMessage);
                        app.receiveNetworkMove(Integer.parseInt(receivedMessage));
                    }
                }
            }
        }catch (Exception e)
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
        app.setConnectionStatus(false);
        try
        {
            socket.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
