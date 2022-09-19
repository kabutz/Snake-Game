/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication11;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Familijo VI Ste
 */
public class JavaFXApplication11 extends Application {

    ArrayList<Circle> tails = new ArrayList<Circle>();
    private static int PREFFERED_WIDTH = 800;
    private static int PREFFERED_HEIGHT = 800;
    private static int RADIUS = 10;
    private Pane root;
    private Circle food;
    private Random random;
    private Circle snake;
    public int POINTS = 1;
    Text score = new Text();

    private void newFood() {

        food = new Circle();
        int hranaX = random.nextInt(PREFFERED_WIDTH / 20) * 20;
        int hranaY = random.nextInt(PREFFERED_HEIGHT / 20) * 20;
        food.setCenterX(hranaX);
        food.setCenterY(hranaY);
        food.setRadius(RADIUS);
        food.setFill(Color.RED);
        root.getChildren().add(food);

    }

    private void Tail(double X, double Y) {
        Circle tail = new Circle();

        tail.setCenterX(snake.getCenterX() + X * POINTS);
        tail.setCenterY(snake.getCenterY() + Y * POINTS);
        tail.setRadius(RADIUS);
        tail.setFill(Color.GREEN);
        root.getChildren().add(tail);
        tails.add(tail);
    }

    private void Point(int X, int Y) {
        if (snake.getCenterX() == food.getCenterX() && snake.getCenterY() == food.getCenterY()) {
            root.getChildren().remove(food);
            
            
            newFood();
            
            
            Tail(X, Y);
            POINTS += 1;
            score.setText("Score : "+String.valueOf(POINTS-1));
        }

    }

    private void Snake() {
        snake = new Circle(PREFFERED_HEIGHT / 2, PREFFERED_WIDTH / 2, RADIUS);
        snake.setFill(Color.GREEN);
        root.getChildren().add(snake);
        

    }

    @Override

    public void start(Stage primaryStage) {

        root = new Pane();
        root.setPrefSize(PREFFERED_WIDTH, PREFFERED_HEIGHT);
        random = new Random();
        BorderPane pane = new BorderPane();
        Snake();
        newFood();
        pane.setCenter(root);
        
        pane.setTop(score);
        Scene scene = new Scene(pane);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.W) {

                snake.setCenterY(snake.getCenterY() - 20);

                Point(0, 20);
                for (int i = 1; i <= POINTS; i++) {
                    Circle c = tails.get(i-1);
                    c.setRadius(RADIUS);
                    c.setCenterY(snake.getCenterY() + 20 * i);
                    c.setCenterX(snake.getCenterX());

                }

            } else if (code == KeyCode.S) {
                snake.setCenterY(snake.getCenterY() + 20);
                Point(0, -20);
                for (int i = 1; i <= POINTS; i++) {
                    Circle c = tails.get(i-1);
                    c.setRadius(RADIUS);
                    c.setCenterY(snake.getCenterY() - 20 * i);
                    c.setCenterX(snake.getCenterX());
                }
            } else if (code == KeyCode.A) {

                snake.setCenterX(snake.getCenterX() - 20);

                Point(20, 0);
                for (int i = 1; i <= POINTS; i++) {
                    Circle c = tails.get(i-1);
                    c.setRadius(RADIUS);
                    c.setCenterX(snake.getCenterX() + 20 * i);
                    c.setCenterY(snake.getCenterY());
                }
            } else if (code == KeyCode.D) {
                snake.setCenterX(snake.getCenterX() + 20);
                Point(-20, 0);
                for (int i = 1; i <= POINTS; i++) {
                    Circle c = tails.get(i-1);
                    c.setRadius(RADIUS);
                    c.setCenterX(snake.getCenterX() - 20 * i);
                    c.setCenterY(snake.getCenterY());
                }

            }

        });

        primaryStage.setTitle("SNAKE GAME");
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
