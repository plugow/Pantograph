package mainPackage;



import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;




public class Main extends Application {
//    private int angleValue1;
//    private int angleValue11;
//    private int angleValue2;
//    private int angleValue22;
//    private int angleValue3;
//    private int angleValue33;
//    private int angleValue4;
//    private int xValue;
//    private int yValue;
//    private int zValue;
//    private double[] thetaValue=new double[3];

    @Override
    public void start(Stage startWindow) throws Exception {

        MainModel.getInstance().currentStage().setOnCloseRequest(event -> Platform.exit());

        Parent root2 = FXMLLoader.load(getClass().getResource("../ViewPackage/startWindowStyle.fxml"));
        Parent root3 = FXMLLoader.load(getClass().getResource("../ViewPackage/previewStyle.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../ViewPackage/mainWindowStyle.fxml"));



        // set startWindow
        startWindow.setTitle("Pantograph");
        startWindow.setScene(new Scene(root3, 340, 200));
        startWindow.show();

        //Pause for Preview
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> startWindow.setScene(new Scene(root2, 340, 200)));
        delay.play();

        MainModel.getInstance().currentStage().setTitle("Pantograph");
        MainModel.getInstance().currentStage().setScene(new Scene(root, 900, 600));

//
//        thetaValue=InversKin.inverse(0,-350,503);
//        angleValue1=(int) Math.toDegrees(thetaValue[0]);
//        angleValue2=(int) Math.toDegrees(thetaValue[1]);
//        angleValue3=(int) Math.toDegrees(thetaValue[2]);
//        System.out.println( Math.toDegrees(thetaValue[0]));
//        System.out.println( Math.toDegrees(thetaValue[1]));
//        System.out.println( Math.toDegrees(thetaValue[2]));
//
//        System.out.println( Math.round(Math.toDegrees(thetaValue[0])));
//        System.out.println( Math.round(Math.toDegrees(thetaValue[1])));
//        System.out.println( Math.round(Math.toDegrees(thetaValue[2])));
//
//
//        System.out.println( (int)Math.toDegrees(thetaValue[0]));
//        System.out.println( (int)Math.toDegrees(thetaValue[1]));
//        System.out.println( (int)Math.toDegrees(thetaValue[2]));


    }






    public static void main(String[] args) {
        launch(args);
    }
}
