import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BlackJackAlertBox {
	public static void display(String title, String message,
			Runnable onExitAction) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label messageLabel = new Label(message);
		Button confirmButton = new Button("OK");
		confirmButton.setOnAction(e -> {
			window.close();
			onExitAction.run();
		});
		
		VBox root = new VBox(10);
		root.getChildren().addAll(messageLabel, confirmButton);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.sizeToScene();
		window.setResizable(false);
		window.show();
	}
}
