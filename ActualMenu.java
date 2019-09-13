

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import javafx.scene.canvas.*;

public class ActualMenu extends Pane {

	Pane gPane;
	Canvas canvas;
	GraphicsContext gc;

	ScrollBar scroll;
	ScrollBar scroll2;
	ScrollBar scroll3;
	ScrollBar brushSize;

	public ActualMenu() {
		
		gPane = new Pane();

		Scene scene = new Scene(gPane,200,400);

		canvasSetup();
	}

	public void canvasSetup() {


		//Creating a setting the canvas
		canvas = new Canvas(200,200);
		gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.rgb(255, 254, 254));
		gc.fillRect(0, 0, 200, 200);

		getChildren().add(canvas);
		canvas.relocate(19, 150);

		Button save = new Button("Save Color");
		getChildren().add(save);
		save.relocate(160, 80);

		Button saveImage = new Button("Save Image");
		getChildren().add(saveImage);
		saveImage.relocate(160, 110);

		scroll= new ScrollBar();
		scroll.setMin(0);
		scroll.setMax(250);
		getChildren().add(scroll);
		scroll.relocate(30, 50);

		scroll2= new ScrollBar();
		scroll2.setMin(0);
		scroll2.setMax(250);
		getChildren().add(scroll2);
		scroll2.relocate(30, 80);

		scroll3= new ScrollBar();
		scroll3.setMin(0);
		scroll3.setMax(250);
		getChildren().add(scroll3);
		scroll3.relocate(30, 110);
		
		brushSize = new ScrollBar();
		brushSize.setMin(1);
		brushSize.setMax(15);
		brushSize.setValue(5);
		getChildren().add(brushSize);
		brushSize.relocate(30, 20);

		save.setOnAction(e ->{
			
			System.out.println("Saving Color...");

			displaySample(getRed(),getGreen(),getBlue());

			createCircle(getRed(),getGreen(),getBlue(),getSize());

		});

		saveImage.setOnAction(e ->{
			
			System.out.println("Saving File...");

			saveImage();

		});

		createCircle(0,0,0,getSize());

	}

	public void saveImage() {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(
				new ExtensionFilter("(*.png)", "*.png"));

		Window mainStage = gPane.getScene().getWindow();

		File file = chooser.showSaveDialog(mainStage);

		if(file != null) {
			try {
				WritableImage writableImage = new WritableImage(200, 200);
				canvas.snapshot(null, writableImage);
				RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
				ImageIO.write(renderedImage, "png", file);
			} catch (IOException ex) {

			}
			System.out.println("Save");
		}
	}



	public void createCircle(int r, int g, int b, int size) {

		setOnMouseDragged(e -> {

			gc.fillOval(e.getX() - 22, e.getY() - 150, size, size);
			gc.setFill(Color.rgb(r,g,b));

		});
	}

	public void displaySample(int r, int g, int b) {
		
		int counter = 0;
		
		if(counter == 0) {

			Circle circle = new Circle();
			circle.setFill(Color.rgb(r, g, b));
			circle.relocate(200, 50);
			circle.setRadius(20);

			getChildren().add(circle);
			counter++;

		}else {

			getChildren().clear();
			counter--;

		}
	}

	public int getRed() {
		return (int)scroll.getValue();
	}

	public int getGreen() {
		return (int)scroll2.getValue();
	}

	public int getBlue() {
		return (int)scroll3.getValue();
	}

	
	public int getSize() {
		return (int)brushSize.getValue();
	}
}
