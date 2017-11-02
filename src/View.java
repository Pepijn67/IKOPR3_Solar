
import javafx.scene.SubScene;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

public class View extends SubScene{
    
    private Sphere sun;
    private Sphere earth;
    private Sphere moon;
    private Sphere test;
    private StackPane moonPane;
    private StackPane stackPane;

    public View() {
        super (new Group(), 1080, 920, true, SceneAntialiasing.BALANCED);
        createShapes();
        ((Group)this.getRoot()).getChildren().addAll(stackPane);
    }

    private void createShapes() {
        sun = new Sphere(80);
        earth = new Sphere(50);
        moon = new Sphere(30);
        test = new Sphere(100);



        PhongMaterial greenMaterial = new PhongMaterial();
        PhongMaterial redMaterial = new PhongMaterial();
        PhongMaterial blackMaterial = new PhongMaterial();
        PhongMaterial beigeMaterial = new PhongMaterial();
        
        redMaterial.setDiffuseColor (Color.RED);
        greenMaterial.setDiffuseColor (Color.GREEN);
        blackMaterial.setDiffuseColor(Color.BLACK);
        beigeMaterial.setDiffuseColor(Color.BEIGE);

        sun.setMaterial(redMaterial);
        earth.setMaterial (greenMaterial);
        moon.setMaterial (beigeMaterial);
        test.setMaterial(blackMaterial);

        moonPane = new StackPane();
        moonPane.translateXProperty().bind(earth.translateXProperty());
        moonPane.translateYProperty().bind(earth.translateYProperty());
        moonPane.getChildren().add(moon);

        stackPane = new StackPane();
        stackPane.getChildren().addAll(sun, earth, moonPane);




    }


    public Sphere getSun() {
        return sun;
    }

    public Sphere getTest() {
        return test;
    }

    public Sphere getEarth() {
        return earth;
    }

    public Sphere getMoon() {
        return moon;
    }
}
