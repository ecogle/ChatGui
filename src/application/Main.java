package application;
	


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			Scene scene = new Scene(root,800,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Label clabel = new Label("Chat");
			TextArea chat = new TextArea();
			
			chat.setPrefWidth(200);
			chat.setWrapText(true);
			chat.setEditable(false);
			chat.setText("This is a test");
			
			HBox hbox = new HBox();
			
			TextField txtField = new TextField();
			Button btnSend = new Button("Send");
			btnSend.setOnAction( k -> {
				chat.appendText("\n");
				chat.appendText(txtField.getText());
				txtField.setText("");
			});
			hbox.getChildren().addAll(txtField,btnSend);
			root.getChildren().addAll(clabel,chat, hbox);
			primaryStage.setTitle("This is a test");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//SystemTray.getSystemTray();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
