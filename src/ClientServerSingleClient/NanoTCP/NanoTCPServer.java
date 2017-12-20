package ClientServerSingleClient.NanoTCP;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Nicolai Bach Woller on 05-09-2017.
 * Server for the chat client - start this one first
 */
public class NanoTCPServer
{
    static final int port = 1337;

    public static void main(String[] args)
    {
        try
        {
            System.out.println("Server: Listening for connections");
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept(); //blokerer og venter på nogen opretter forbindelse
            System.out.println("Connection established");
            Common common = new Common(socket, "bye");
            common.start();
            serverSocket.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
