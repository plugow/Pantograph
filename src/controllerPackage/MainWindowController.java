package controllerPackage;


import javafx.collections.ListChangeListener;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mainPackage.ForwardKin;
import org.math.plot.Plot3DPanel;
import org.zu.ardulink.protocol.IProtocol;
import mainPackage.MainModel;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MainWindowController implements Initializable{
    private int power=IProtocol.LOW;
    private Stage jogWindow=new Stage();
    private SwingNode swingNode = new SwingNode();
    @FXML Label informationBar;
    @FXML Button ledOn;
    @FXML Pane pane;

    private float pi=(float)Math.PI;
    //private Plot3DPanel plot3=new Plot3DPanel();
    private ForwardKin forwardKin=new ForwardKin();
    //private float[][] results=new float[4][3];
    private double[] x = new double[5];
    private double[] y = new double[5];
    private double[] z = new double[5];
    private float alfa1=pi/2;
    private float alfa2=pi/2;
    private float alfa3=pi/2;
    private ReentrantLock lock = new ReentrantLock();
    List<String> lista;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainModel.getInstance().currentLabel().textProperty().addListener((observable, oldValue, newValue) -> informationBar.setText(newValue));


        MainModel.getInstance().currentValue1().textProperty().addListener((observable, oldValue, newValue) -> {
            lock.lock();
            try {
                alfa1=(float)Math.toRadians(Integer.parseInt(newValue));
                setForwardKin(alfa1,alfa2,alfa3);
            } finally {
                lock.unlock();
            }

        });

        MainModel.getInstance().currentValue2().textProperty().addListener((observable, oldValue, newValue) -> {
            lock.lock();
            try {
                alfa2=(float)Math.toRadians(Integer.parseInt(newValue));
                setForwardKin(alfa1,alfa2,alfa3);
            } finally {
                lock.unlock();
            }

        });

        MainModel.getInstance().currentValue3().textProperty().addListener((observable, oldValue, newValue) -> {
            lock.lock();
            try {
                alfa3=(float)Math.toRadians(Integer.parseInt(newValue));
                setForwardKin(alfa1,alfa2,alfa3);
            } finally {
                lock.unlock();
            }


        });

        MainModel.getInstance().getLabelList().addListener((ListChangeListener.Change<? extends String> c)-> {
            c.next();
            //c.reset();


           if (c.wasReplaced())updatePlot(MainModel.getInstance().getLabelList());

            //c.reset();


                }
        );




        setForwardKin(alfa1,alfa2,alfa3);
        pane.getChildren().add(swingNode);





    }


    @FXML private void ledOnClicked() {

        power = IProtocol.HIGH;
        MainModel.getInstance().currentLink().sendPowerPinSwitch(3,power);


    }

    @FXML private void ledOffClicked() {
        power = IProtocol.LOW;

        MainModel.getInstance().currentLink().sendPowerPinSwitch(3,power);

    }


    @FXML private void jogOperationClicked() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../ViewPackage/jogWindowStyle.fxml"));
        jogWindow.setTitle("Pantograph");
        jogWindow.setScene(new Scene(root, 400, 400));
        jogWindow.show();


    }

    @FXML private void disconnectButtonClicked(){

    }


    private void setForwardKin(float theta1, float theta2, float theta3){
        float[][] results;
        results=forwardKin.forward(theta1,theta2,theta3);
        x[1]=results[0][0];
        y[1]=results[0][1];
        z[1]=results[0][2];

        x[2]=results[1][0];
        y[2]=results[1][1];
        z[2]=results[1][2];

        x[3]=results[2][0];
        y[3]=results[2][1];
        z[3]=results[2][2];

        x[4]=results[3][0];
        y[4]=results[3][1];
        z[4]=results[3][2];

        Plot3DPanel plot3=new Plot3DPanel();

        plot3.addLinePlot("plot", Color.BLACK, x, y,z);
        plot3.addScatterPlot("plot2",Color.BLUE,x,y,z);
        plot3.setFixedBounds(0,-900,900);
        plot3.setFixedBounds(1,-900,900);
        plot3.setFixedBounds(2,0,1000);
        plot3.setPreferredSize(new Dimension(500,500));

        swingNode.setContent(plot3);

    }


    private void updatePlot(List<String> list){
        System.out.println(list.get(0)+ list.get(1) +list.get(2));


    }











}
