import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.lang.Integer;



public class MathService implements Runnable
{
    private Socket s;
    private Scanner in;
    private PrintWriter out;
        
    public MathService (Socket insocket)
    {
        s = insocket;
    }
    
    public void run()
    {
        try
        {
            try
            {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doOperation();
            }
            finally
            {
                s.close();
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
    
    public void doOperation() throws IOException
    {
            String userCommand = in.nextLine();
            if (userCommand.equals("QUIT")) return;
            else preformOperation(userCommand);
    }
    
    public void preformOperation(String userCommand)
    {
        if (!properInput(userCommand)){return;}
        String operation = userCommand.substring(0,userCommand.indexOf(' '));
        double calculatedvalue; // the value that will be calculated from the operations
        if (operation.equals("INV"))
        {
            int invertable = Integer.parseInt(userCommand.substring(userCommand.indexOf(' ')+1));
            calculatedvalue = (1.0/invertable);
            out.println(calculatedvalue);
            out.flush();
            return;
        }
        else if (operation.equals("ADD"))
        {
            return;
        }
        else if (operation.equals("SUB"))
        {
            return;
        }
        else if (operation.equals("MUL"))
        {
            return;
        }
        else if (operation.equals("DIV"))//////MAKE THIS NOT EQUALS
        {
            out.println(operation +" is not a valid command");
            out.flush();
            return;
        }
        //the operation for dividing
        
    }
    
    public boolean properInput(String command)
    {       
        out.println(command);
        out.flush();
        int spacecount = 0; // the number of spaces in the argument
        for (int i=0; i<command.length();i++){
            out.println(spacecount);
            out.flush();
            if (command.charAt(i)==' ')spacecount++; // count all of the spaces
        }
        if(spacecount > 2 || spacecount < 1)//if the number of spaces is greater then 2, or less then 1 a syntax error has occured
        {
            out.println("Incorrect Syntax plese enter commands as follows : Command peramiters");
            out.flush();
            return false;
        }
        //String integer1 = command.substring(command.indexOf(' '),command.indexOf(command.indexOf(' ')));
        String integer2 = command.substring(command.indexOf(' ')+1);
        out.println(integer2);
        out.flush();
        try
        {
            //Integer firstarg = new Integer(integer1);
            Integer secondarg = new Integer(integer2);
        }
        catch (NumberFormatException exception)//if the values input are not proper integer values then a syntax error has occured
        {
            out.println("please enter integer values");
            out.flush();
            return false;
        }
        return true;
    }
        
}
