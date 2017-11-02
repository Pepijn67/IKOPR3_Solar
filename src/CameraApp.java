

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Group;

public class CameraApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        View view = new View();
        Controller controller = new Controller(view);

        SouthPane southPane = new SouthPane(controller);

        BorderPane bpane = new BorderPane();
        bpane.setCenter(view);
        bpane.setBottom(southPane);
        bpane.setStyle("-fx-background-color: black");

        Scene scene = new Scene(bpane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Solar System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
