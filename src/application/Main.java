package application;
	

import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import network.ReadThread;
import network.WriteThread;


public class Main extends Application {
	private String name = "";
	
	private Socket socket;
	@Override
	public void start(Stage primaryStage) {
		try {
			//Start the network connection to the server
			
			//get the text from the input stream and assign it to chat text.
			socket = new Socket("192.168.0.20",4444);
			System.out.println("Connection successful");
			
			
			VBox root = new VBox();
			Scene scene = new Scene(root,800,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Label clabel = new Label("Chat");
			TextArea chat = new TextArea();
			
			chat.setPrefWidth(200);
			chat.setWrapText(true);
			chat.setEditable(false);
						
			new ReadThread(socket, chat).start();
			
			root.getChildren().addAll(clabel,chat, buildInputArea(chat));
			primaryStage.setTitle("This is a test");
			primaryStage.setOnShown(k ->{
				final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(primaryStage);
                VBox dialogVbox = new VBox(20);
                
                HBox inputs = new HBox();
                TextField inpt = new TextField();
                
                Button btn = new Button("Set Name");
                btn.setOnAction(l->{
                	this.name = inpt.getText();
                	chat.setText("Welcome to McCoygleChat " + name + "\n");
                	dialog.close();
                });
                
                inputs.getChildren().addAll(inpt,btn);
                dialogVbox.getChildren().add(inputs);
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
			});
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private HBox buildInputArea(TextArea chat)
	{
		HBox hbox = new HBox();
		TextField txtField = new TextField();
		Button btnSend = new Button("Send");
		btnSend.setOnAction( k -> {
			StringBuilder output = new StringBuilder();
			output.append("[").append(name).append("] :  ").append(txtField.getText()).append("\n");
			new WriteThread(socket,  name).run(output.toString());
			chat.appendText(output.toString());
			txtField.setText("");
			
			
			Platform.runLater(()->{
				txtField.requestFocus();
			});
		});
		hbox.getChildren().addAll(txtField,btnSend);
		Platform.runLater(() ->{
			btnSend.requestFocus();
		});
		return hbox;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
