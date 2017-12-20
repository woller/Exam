package ClientServerSingleClient.NanoTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Nicolai Bach Woller on 05-09-2017.
 * Common containing all the shared code between the server and the client
 */
public class Common
{
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private BufferedReader keyboard;
    private String exitPhrase;

    public Common(Socket socket, String exitPhrase)
    {
        try
        {
            this.socket = socket;
            this.exitPhrase = exitPhrase;
            this.output = new PrintWriter(socket.getOutputStream(), true);
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.keyboard = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void start()
    {
        try
        {
            while (true)
            {
                if (keyboard.ready()) //hvis tastaturet er klart sendes en besked
                {
                    output.println(keyboard.readLine());
                }

                if (input.ready())
                {
                    String text = input.readLine();
                    if (text.equals(exitPhrase)) //close command
                    {
                        output.println("bye");
                        System.out.println("Interrupted: " + text);
                        break;
                    }
                    System.out.println(text);
                }
            }
            socket.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
