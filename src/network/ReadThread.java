package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javafx.scene.control.TextArea;

public class ReadThread extends Thread{

	private BufferedReader reader;
    private TextArea txtArea; 
 
    public ReadThread(Socket socket, TextArea txtArea ) {
        this.txtArea = txtArea;
 
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run() {
    	txtArea.appendText("\n");
        while (true) {
            try {
                String response = reader.readLine();
                
                txtArea.appendText(response);
                txtArea.appendText("\n");
                // prints the username after displaying the server's message
                
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
