package ClientServerSingleClient.NanoTCP;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Nicolai Bach Woller on 05-09-2017.
 * Client for the chat server - start this one second
 */
public class NanoTCPClient
{
    static final int port = 1337;
    static final String ip = "localhost";

    public static void main(String[] args)
    {
        try
        {
            Socket socket = new Socket(ip, port);
            System.out.println("Connection established");
            Common clientCommon = new Common(socket, "bye");
            clientCommon.start();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
