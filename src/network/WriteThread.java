package network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class WriteThread {

	private PrintStream writer;    
    private String name; 
    
    public WriteThread(Socket socket, String name) {
                
        this.name = name;
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintStream(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run( String txt) {
 
/*        Console console = System.console();
 
        String userName = console.readLine("\nEnter your name: ");
        
        writer.println(userName);
 
        String text;
 
        do {
            text = console.readLine("[" + userName + "]: ");
            writer.println(text);
 
        } while (!text.equals("bye"));
 
        try {
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    	*/
    	
	    	writer.append(txt);
	    	writer.flush();
    	
    	
    }
}
