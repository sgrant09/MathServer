import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MathServer
{
    public static void main (String []args) throws IOException
    {
        final int MATH_PORT = 8888;
        ServerSocket mathserve = new ServerSocket(MATH_PORT);
        System.out.println("Waitng for a client to connect....");
        
        while (true)
        {
            Socket s = mathserve.accept();
            System.out.println("Client connected!!");
            MathService service = new MathService(s);
            Thread t = new Thread(service);
            t.start();
        }
    }
}
