import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application{
	
	
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		
		Scene scene = new Scene(pane, 250, 400, Color.rgb(100, 100, 101));
		
		Button btn = new Button("Begin To Draw");
		btn.relocate(70, 200);
		pane.getChildren().add(btn);
		
		
		btn.setOnAction(e -> {
						
			ActualMenu menu = new ActualMenu();
			pane.getChildren().add(menu);
			
			Stage newWindow = new Stage();
			newWindow.setScene(scene);
			newWindow.setTitle("Paint");
			
			newWindow.initModality(Modality.WINDOW_MODAL);
			newWindow.initOwner(primaryStage);	
			newWindow.show();

		});
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Main Menu");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
		
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	

}
