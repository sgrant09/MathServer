import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MathClient
{
    public static void main (String []args) throws IOException
    {
        final int MATH_PORT = 8888;
        Socket s = new Socket("localhost", MATH_PORT);
        InputStream instream = s.getInputStream();
        OutputStream outstream = s.getOutputStream();
        Scanner in = new Scanner (instream);
        PrintWriter out = new PrintWriter(outstream);
        
        String command = "INV 5\n";
        String response = "";
        System.out.println("sending command ...");
        out.print(command);
        out.flush();
        while(in.hasNext())
        {
            response = in.nextLine();
            System.out.println("reciving : " + response);
        }
        s.close();
    }
}