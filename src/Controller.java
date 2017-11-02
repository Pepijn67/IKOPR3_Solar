

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Controller {

    private View view;
    private PerspectiveCamera camera;
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;


    private Rotate rx, ry;

    public Controller(View view) {
        this.view = view;
        rx = new Rotate();
        rx.setAxis(Rotate.X_AXIS);
        ry = new Rotate();
        ry.setAxis(Rotate.Y_AXIS);
        view.getRoot().getTransforms().addAll(rx, ry);

        createAndSetCamera();

        view.getSun().setOnMousePressed(me
                -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });

        view.getSun().setOnMouseDragged(me
                -> {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);
            ry.setAngle(ry.getAngle() - mouseDeltaX * 2.0);
            rx.setAngle(rx.getAngle() + mouseDeltaY * 2.0);
        });
        //decleration of objects
        Ellipse ellipseEarth = new Ellipse(); //aarde rond de zon
        Ellipse ellipseMoon = new Ellipse(); // maan rond de aarde
        Ellipse ellipseTest = new Ellipse(); // test rond de zon
        PathTransition transitionEarth = new PathTransition();
        PathTransition transitionMoon = new PathTransition();

        //create two ellipse shapes to be used as a path later
        ellipseEarth.setRadiusX(view.getSun().getBoundsInLocal().getWidth() / 2.0 + 1.01671388 * 170); //160 / 2.0 + 1.01671388 * 170, 160 = 2x de straal van de zon, x / 2.0 + 1.01671388 * 170 = formule om een schijne rechte lijn door de ellipse te laten gaan. https://www.google.nl/search?q=x+%2F+2.0+%2B+1.01671388+*+170&oq=x+%2F+2.0+%2B+1.01671388+*+170&aqs=chrome..69i57.19319j0j4&sourceid=chrome&ie=UTF-8
        ellipseEarth.setRadiusY(view.getSun().getBoundsInLocal().getHeight() / 2.0 + 1.01671388 * 170);
        ellipseEarth.setVisible(false);


        ellipseMoon.setRadiusX(view.getEarth().getBoundsInLocal().getWidth() / 2.0 + 1.01671388 * 70);
        ellipseMoon.setRadiusY(view.getEarth().getBoundsInLocal().getHeight() / 2.0 + 1.01671388 * 70);
        ellipseMoon.setVisible(false);

        transitionEarth.setPath(ellipseEarth); //set the path for the transition to follow
        transitionEarth.setNode(view.getEarth());//Which object should follow it
        transitionEarth.setInterpolator(Interpolator.LINEAR); //sets the speed for the animation (linear = same as every time)
        transitionEarth.setDuration(Duration.seconds(10.000017421)); //how long should one transition take (full circle)
        transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); //updates the Rotate value at a regular interval(at this settings), otherwise it will specifies the upright orientation of the node(earth in this case)
        transitionEarth.setCycleCount(Timeline.INDEFINITE); //how long should it play

        transitionMoon.setPath(ellipseMoon); //set the path for the transition to follow
        transitionMoon.setNode(view.getMoon()); //which object should follow it
        transitionMoon.setInterpolator(Interpolator.LINEAR); // sets the speed for the animation (linear = same as every time)
        transitionMoon.setDuration(Duration.seconds(1.000017421)); //how long should one transition take (full circle)
        transitionMoon.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); //updates the Rotate value at a regular interval(at this settings), otherwise it will specifies the upright orientation of the node(Moon in this case)
        transitionMoon.setCycleCount(Timeline.INDEFINITE); //how long should it play
        //start the transisions
        transitionEarth.play();
        transitionMoon.play();









    }

    private void createAndSetCamera() {
        camera = new PerspectiveCamera(true);
        camera.setFarClip(20000);
        camera.setTranslateZ(-2500);

        view.setCamera(camera);
    }

    public PerspectiveCamera getCamera() {
        return (camera);
    }

}
