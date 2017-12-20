package ClientServerMulticlient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by Nicolai Bach Woller on 14-09-2017.
 * Simpelt TCP multiklient chat program
 * TODO: Mangler en ordentlig stop-metode.
 */
public class ServerThread implements Runnable
{
    private MulticlientChat app;
    private boolean running;
    private ServerSocket serverSocket;
    private HashMap<Socket, PrintWriter> connectedClients;

    public ServerThread(MulticlientChat app, int port)
    {
        this.app = app;
        try
        {
            serverSocket = new ServerSocket(port); //kan refaktoreres til short hand senere
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        connectedClients = new HashMap<>();
    }

    @Override
    public void run()
    {
        running = true;
        while (running)
        {
            System.out.println("I'm listening!");
            try //lytter og blokerer
            {
                Socket socket = serverSocket.accept();
                System.out.println("Har fået en ven!");
                PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true);
                connectedClients.put(socket, outputWriter);
                ClientHandler clientHandler = new ClientHandler(socket, this);
                Thread thread = new Thread(clientHandler);
                thread.start();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void broadcast(String message)
    {
        for (Socket socket : connectedClients.keySet())
        {
            PrintWriter output = connectedClients.get(socket);
            output.println(message);
        }
        app.receive(message + "\n");
    }

    public void stop() //burde jeg iterere gennem mit HashMap og lukke alle sockets? Og burde jeg lade være med at lave auto-close?
    {
        running = false;
        for (Socket socket : connectedClients.keySet())
        {
            try
            {
                socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message)
    {
        broadcast(message);
    }
}
