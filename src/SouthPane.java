

import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Label;

public class SouthPane extends HBox {

    private Label labelC, labelX, labelY, labelZ;

    public SouthPane(Controller controller) {
        setAlignment(Pos.CENTER);
        setSpacing(10);
        createLabels(controller.getCamera());
        getChildren().addAll(labelC, labelX, labelY, labelZ);        
    }

    private void createLabels(PerspectiveCamera camera) {
        labelC = new Label("camera ");
        labelX = new Label("X: " + (int) camera.getTranslateX());
        labelY = new Label("Y: " + (int) camera.getTranslateY());
        labelZ = new Label("Z: " + (int) camera.getTranslateZ());

        camera.translateXProperty().addListener(ov
                -> labelX.setText("X: " + (int) camera.getTranslateX()));

        camera.translateYProperty().addListener(ov
                -> labelY.setText("Y: " + (int) camera.getTranslateY()));

        camera.translateZProperty().addListener(ov
                -> labelZ.setText("Z: " + (int) camera.getTranslateZ()));

        labelX.setOnScroll(e
                -> {
            camera.setTranslateX(camera.getTranslateX()
                    + e.getDeltaY() / 8);
        });

        labelY.setOnScroll(e
                -> {
            camera.setTranslateY(camera.getTranslateY()
                    + e.getDeltaY() / 8);
        });

        labelZ.setOnScroll(e
                -> {
            camera.setTranslateZ(camera.getTranslateZ()
                    + e.getDeltaY() / 8);
        });

    }
}
