package ClientServerMulticlient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Nicolai Bach Woller on 14-09-2017.
 */
public class ClientThread implements Runnable
{
    private MulticlientChat app;
    private Socket socket;
    private PrintWriter outputPrintWriter;
    boolean running;

    public ClientThread(MulticlientChat app, int port, String ip)
    {
        this.app = app;
        try
        {
            socket = new Socket(ip, port);
            outputPrintWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            running = true;
            while (running)
            {
                if (inputReader.ready())
                {
                    app.receive(inputReader.readLine() + "\n");
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    public void sendMessage(String message)
    {
        outputPrintWriter.println(message);
    }

    public void stop()
    {
        running = false;
        try
        {
            socket.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
