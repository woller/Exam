package ClientServerMulticlient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Nicolai Bach Woller on 14-09-2017.
 */
public class ClientHandler implements Runnable
{
    private ServerThread serverThread;
    private Socket socket;
    boolean running = true;

    public ClientHandler(Socket socket, ServerThread serverThread)
    {
        this.socket = socket;
        this.serverThread = serverThread;
    }

    @Override
    public void run()
    {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            running = true;
            while (running)
            {
                if (input.ready())
                {
                    serverThread.broadcast(input.readLine());
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }


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
