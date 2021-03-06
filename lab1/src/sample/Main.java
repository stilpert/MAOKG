package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    Group root = new Group();
    Scene scene = new Scene(root, 600, 400);

    Rectangle rectangle = new Rectangle(50, 50, 500, 300);
    root.getChildren().add(rectangle);
    rectangle.setFill(Color.DARKRED);;
    scene.setFill(Color.OLIVE);

    Line line1 = new Line(50, 150, 550, 150);
    line1.setStroke(Color.YELLOW);
    line1.setStrokeWidth(10);
    root.getChildren().add(line1);

    Line line2 = new Line(50, 250, 550, 250);
    line2.setStroke(Color.YELLOW);
    line2.setStrokeWidth(10);
    root.getChildren().add(line2);

    Line line3 = new Line(300, 50, 300, 150);
    line3.setStroke(Color.YELLOW);
    line3.setStrokeWidth(10);
    root.getChildren().add(line3);

    Line line4 = new Line(300, 250, 300, 350);
    line4.setStroke(Color.YELLOW);
    line4.setStrokeWidth(10);
    root.getChildren().add(line4);

    Line line5 = new Line(200, 150, 200, 250);
    line5.setStroke(Color.YELLOW);
    line5.setStrokeWidth(10);
    root.getChildren().add(line5);

    Line line6 = new Line(400, 150, 400, 250);
    line6.setStroke(Color.YELLOW);
    line6.setStrokeWidth(10);
    root.getChildren().add(line6);


    primaryStage.setScene(scene);
    primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}