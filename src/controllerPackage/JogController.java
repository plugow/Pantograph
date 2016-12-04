package controllerPackage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mainPackage.ForwardKin;
import mainPackage.InversKin;
import mainPackage.MainModel;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class JogController implements Initializable{

    //region  FXML buttons sliders
    @FXML RadioButton jogRadio;
    @FXML RadioButton cartesianRadio;

    @FXML Button firstMinus;
    @FXML Button firstPlus;
    @FXML Button secondMinus;
    @FXML Button secondPlus;
    @FXML Button thirdMinus;
    @FXML Button thirdPlus;

    @FXML Button xMinus;
    @FXML Button xPlus;
    @FXML Button yMinus;
    @FXML Button yPlus;
    @FXML Button zMinus;
    @FXML Button zPlus;

    @FXML Slider velocitySlider;
    //endregion

    //region data declaration
    final private ToggleGroup group = new ToggleGroup();
    private ForwardKin forwardKin=new ForwardKin();
    private int velocity;
    private int velocityInit;
    private int step;
    private int helpStep =20;
    private int angleValue1;
    private int angleValue11;
    private int angleValue2;
    private int angleValue22;
    private int angleValue3;
    private int angleValue33;
    private int angleValue4;
    private int xValue;
    private int yValue;
    private int zValue;
    private int xxValue;
    private int yyValue;
    private int zzValue;
    private boolean isOpened;



    //endregion

    // initialize method
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        angleValue1=90;
        angleValue2=90;
        angleValue3=90;
        angleValue4=90;


        jogRadio.setToggleGroup(group);
        jogRadio.setSelected(true);
        cartesianRadio.setToggleGroup(group);
        xMinus.setDisable(true);
        xPlus.setDisable(true);
        yMinus.setDisable(true);
        yPlus.setDisable(true);
        zMinus.setDisable(true);
        zPlus.setDisable(true);
        velocitySlider.valueProperty().addListener((obs, oldval, newVal) ->
                velocitySlider.setValue(newVal.intValue()));
        isOpened =true;


    }

    //region timers for jog move
    private Timer timer = new Timer();
    private TimerTask plusTask1 = new PlusTimerTask1();
    private TimerTask minusTask1 = new MinusTimerTask1();
    private TimerTask plusTask2 = new PlusTimerTask2();
    private TimerTask minusTask2 = new MinusTimerTask2();
    private TimerTask plusTask3 = new PlusTimerTask3();
    private TimerTask minusTask3 = new MinusTimerTask3();





    private class PlusTimerTask1 extends TimerTask {

        public void run() {
            //System.out.println(angleValue1);
            angleValue1+=1;
            if(MainModel.getInstance().isCheckMode()){
                if(angleValue11==angleValue1-step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    angleValue11=angleValue1;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,255);
        }
    }

    private class MinusTimerTask1 extends TimerTask {
        public void run() {

            angleValue1-=1;
            if(MainModel.getInstance().isCheckMode()){
                if(angleValue11==angleValue1+step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    angleValue11=angleValue1;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,255);
        }
    }


    private class PlusTimerTask2 extends TimerTask {

        public void run() {

            angleValue2+=1;
            if(MainModel.getInstance().isCheckMode()){
                if(angleValue22==angleValue2-step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    angleValue22=angleValue2;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,255);
        }
    }

    private class MinusTimerTask2 extends TimerTask {
        public void run() {

            angleValue2-=1;
            if(MainModel.getInstance().isCheckMode()){
                if(angleValue22==angleValue2+step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    angleValue22=angleValue2;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,255);
        }
    }

    private class PlusTimerTask3 extends TimerTask {

        public void run() {
            //System.out.println(angleValue3);
            angleValue3+=1;

            if(MainModel.getInstance().isCheckMode()){
                if(angleValue33==angleValue3-step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    angleValue33=angleValue3;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,255);
        }
    }

    private class MinusTimerTask3 extends TimerTask {
        public void run() {

            angleValue3-=1;
            if(MainModel.getInstance().isCheckMode()){
                if(angleValue33==angleValue3+step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    angleValue33=angleValue3;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,255);
        }
    }

    //endregion

    //region timers for cartesian move
    private TimerTask plusTaskX = new PlusTimerTaskX();
    private TimerTask minusTaskX = new MinusTimerTaskX();
    private TimerTask plusTaskY = new PlusTimerTaskY();
    private TimerTask minusTaskY = new MinusTimerTaskY();
    private TimerTask plusTaskZ= new PlusTimerTaskZ();
    private TimerTask minusTaskZ = new MinusTimerTaskZ();

    private class PlusTimerTaskX extends TimerTask {

        public void run() {
            //System.out.println(angleValue1);
            xValue+=1;
            double[] thetaValue;
            thetaValue=InversKin.inverse(xValue,yValue,zValue);
            angleValue1=(int) Math.round(Math.toDegrees(thetaValue[0]));
            angleValue2=(int) Math.round(Math.toDegrees(thetaValue[1]));
            angleValue3=(int) Math.round(Math.toDegrees(thetaValue[2]));
            if(MainModel.getInstance().isCheckMode()){
                if(xxValue==xValue-step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    xxValue=xValue;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,255);
            MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,255);
            MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,255);
        }
    }

    private class MinusTimerTaskX extends TimerTask {
        public void run() {
            //System.out.println(angleValue1);
            xValue-=1;
            double[] thetaValue;
            thetaValue=InversKin.inverse(xValue,yValue,zValue);
            angleValue1=(int) Math.round(Math.toDegrees(thetaValue[0]));
            angleValue2=(int) Math.round(Math.toDegrees(thetaValue[1]));
            angleValue3=(int) Math.round(Math.toDegrees(thetaValue[2]));
            if(MainModel.getInstance().isCheckMode()){
                if(xxValue==xValue+step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    xxValue=xValue;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,255);
            MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,255);
            MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,255);
        }
    }


    private class PlusTimerTaskY extends TimerTask {

        public void run() {
            //System.out.println(angleValue1);
            yValue+=1;
            double[] thetaValue;
            thetaValue=InversKin.inverse(xValue,yValue,zValue);
            angleValue1=(int) Math.round(Math.toDegrees(thetaValue[0]));
            angleValue2=(int) Math.round(Math.toDegrees(thetaValue[1]));
            angleValue3=(int) Math.round(Math.toDegrees(thetaValue[2]));
            if(MainModel.getInstance().isCheckMode()){
                if(yyValue==yValue-step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    yyValue=yValue;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,255);
            MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,255);
            MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,255);
        }
    }

    private class MinusTimerTaskY extends TimerTask {
        public void run() {
            //System.out.println(angleValue1);
            yValue-=1;
            double[] thetaValue;
            thetaValue=InversKin.inverse(xValue,yValue,zValue);
            angleValue1=(int) Math.round(Math.toDegrees(thetaValue[0]));
            angleValue2=(int) Math.round(Math.toDegrees(thetaValue[1]));
            angleValue3=(int) Math.round(Math.toDegrees(thetaValue[2]));
            if(MainModel.getInstance().isCheckMode()){
                if(yyValue==yValue+step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    yyValue=yValue;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,255);
            MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,255);
            MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,255);
        }
    }

    private class PlusTimerTaskZ extends TimerTask {

        public void run() {
            //System.out.println(angleValue1);
            zValue+=1;
            double[] thetaValue;
            thetaValue=InversKin.inverse(xValue,yValue,zValue);
            angleValue1=(int) Math.round(Math.toDegrees(thetaValue[0]));
            angleValue2=(int) Math.round(Math.toDegrees(thetaValue[1]));
            angleValue3=(int) Math.round(Math.toDegrees(thetaValue[2]));
            if(MainModel.getInstance().isCheckMode()){
                if(zzValue==zValue-step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    zzValue=zValue;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,255);
            MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,255);
            MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,255);
        }
    }

    private class MinusTimerTaskZ extends TimerTask {
        public void run() {
            //System.out.println(angleValue1);
            zValue-=1;
            double[] thetaValue;
            thetaValue=InversKin.inverse(xValue,yValue,zValue);
            angleValue1=(int) Math.round(Math.toDegrees(thetaValue[0]));
            angleValue2=(int) Math.round(Math.toDegrees(thetaValue[1]));
            angleValue3=(int) Math.round(Math.toDegrees(thetaValue[2]));
            if(MainModel.getInstance().isCheckMode()){
                if(zzValue==zValue+step){
                    MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
                    zzValue=zValue;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,255);
            MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,255);
            MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,255);
        }
    }
    //endregion


    // select way to move
    // JOG
    @FXML private void jogRadioClicked(){
        xMinus.setDisable(true);
        xPlus.setDisable(true);
        yMinus.setDisable(true);
        yPlus.setDisable(true);
        zMinus.setDisable(true);
        zPlus.setDisable(true);

        firstMinus.setDisable(false);
        firstPlus.setDisable(false);
        secondMinus.setDisable(false);
        secondPlus.setDisable(false);
        thirdMinus.setDisable(false);
        thirdPlus.setDisable(false);




    }
    //CARTESIAN
    @FXML private void cartesianRadioClicked(){
        firstMinus.setDisable(true);
        firstPlus.setDisable(true);
        secondMinus.setDisable(true);
        secondPlus.setDisable(true);
        thirdMinus.setDisable(true);
        thirdPlus.setDisable(true);

        xMinus.setDisable(false);
        xPlus.setDisable(false);
        yMinus.setDisable(false);
        yPlus.setDisable(false);
        zMinus.setDisable(false);
        zPlus.setDisable(false);
        float[][] results;
        results=forwardKin.forward((float) Math.toRadians(angleValue1),(float) Math.toRadians(angleValue2),(float) Math.toRadians(angleValue3));
        xValue= (int) results[3][0];
        yValue= (int) results[3][1];
        zValue= (int) results[3][2];

    }

    // set every servo's position on 90 degrees
    @FXML private void calibrationButtonClicked(){
        MainModel.getInstance().currentLink().sendToneMessage(1,90,200);
        MainModel.getInstance().currentLink().sendToneMessage(2,90,200);
        MainModel.getInstance().currentLink().sendToneMessage(3,90,200);
        //MainModel.getInstance().currentLink().sendToneMessage(4,90,200);
        angleValue1=90;
        angleValue2=90;
        angleValue3=90;
        //angleValue4=90;
        xValue= 0;
        yValue= -350;
        zValue= 513;
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);


    }

    // end effector open/close
    @FXML private void effectorButtonClicked(){
        if(isOpened){
            angleValue4=118;
            MainModel.getInstance().currentLink().sendToneMessage(4,angleValue4,200);
            isOpened=false;
            System.out.println("effector closed");
            MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        }
        else{angleValue4=90;
            MainModel.getInstance().currentLink().sendToneMessage(4,angleValue4,200);
            isOpened=true;
            System.out.println("effector opened");
            MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        }

    }


    //region jog buttons
    // jog buttons
    // first jog buttons
    @FXML private void firstMinusClicked(){
        System.out.println(angleValue1);
        angleValue1-=1;


        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);

        MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,0);


    }
    @FXML private void firstPlusClicked(){
        angleValue1+=1;
        System.out.println(angleValue1);
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,0);


    }

    //second buttons
    @FXML private void secondMinusClicked(){
        System.out.println(angleValue2);
        angleValue2-=1;

        MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,0);


        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);

    }
    @FXML private void secondPlusClicked(){
        angleValue2+=1;
        System.out.println(angleValue2);
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,0);

    }

    //third buttons
    @FXML private void thirdMinusClicked(){
        System.out.println(angleValue3);
        angleValue3-=1;
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);


        MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,0);

    }
    @FXML private void thirdPlusClicked(){
        angleValue3+=1;
        System.out.println(angleValue3);
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,0);

    }
    //endregion

    //region Cartesian buttons
    // Cartesian buttons
    // first buttons
    @FXML private void xMinusClicked(){
        xValue-=1;

        setXyzPosition();



    }
    @FXML private void xPlusClicked(){
        xValue+=1;

        setXyzPosition();
    }
    //second buttons
    @FXML private void yMinusClicked(){
        yValue-=1;

        setXyzPosition();

    }
    @FXML private void yPlusClicked(){
        yValue+=1;

        setXyzPosition();

    }
    //third buttons
    @FXML private void zMinusClicked(){
        zValue-=1;

       setXyzPosition();
    }
    @FXML private void zPlusClicked(){
        zValue+=1;

        setXyzPosition();
    }
    //endregion

    //region joint pressed and released buttons
    // action when mouse is pressed
    // first servo
    @FXML private  void firstMinusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        angleValue11=angleValue1;

        minusTask1 = new MinusTimerTask1();
        timer = new Timer();
        timer.scheduleAtFixedRate(minusTask1, 500, velocity);


    }

    @FXML private void firstMinusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        minusTask1.cancel();
        timer.cancel();
        timer.purge();

    }

    @FXML private void firstPlusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        angleValue11=angleValue1;

        plusTask1 = new PlusTimerTask1();
        timer = new Timer();
        timer.scheduleAtFixedRate(plusTask1, 500, velocity);

    }

    @FXML private void firstPlusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        plusTask1.cancel();
        timer.cancel();
        timer.purge();

    }


    // second servo
    @FXML private  void secondMinusPressed() {
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        angleValue22=angleValue2;
        minusTask2 = new MinusTimerTask2();
        timer = new Timer();
        timer.scheduleAtFixedRate(minusTask2, 500, velocity);

    }

    @FXML private void secondMinusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        minusTask2.cancel();
        timer.cancel();
        timer.purge();

    }

    @FXML private void secondPlusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        angleValue22=angleValue2;
        plusTask2 = new PlusTimerTask2();
        timer = new Timer();
        timer.scheduleAtFixedRate(plusTask2, 500, velocity);

    }

    @FXML private void secondPlusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        plusTask2.cancel();
        timer.cancel();
        timer.purge();

    }


    // third servo
    @FXML private  void thirdMinusPressed() {
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        angleValue33=angleValue3;
        minusTask3 = new MinusTimerTask3();
        timer = new Timer();
        timer.scheduleAtFixedRate(minusTask3, 500, velocity);

    }

    @FXML private void thirdMinusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        minusTask3.cancel();
        timer.cancel();
        timer.purge();

    }

    @FXML private void thirdPlusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        angleValue33=angleValue3;
        plusTask3 = new PlusTimerTask3();
        timer = new Timer();
        timer.scheduleAtFixedRate(plusTask3, 500, velocity);

    }

    @FXML private void thirdPlusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        plusTask3.cancel();
        timer.cancel();
        timer.purge();

    }
    //endregion

    //region xyz pressed and released buttons
    @FXML private  void xMinusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        xxValue=xValue;

        minusTaskX = new MinusTimerTaskX();
        timer = new Timer();
        timer.scheduleAtFixedRate(minusTaskX, 500, velocity);


    }

    @FXML private void xMinusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        minusTaskX.cancel();
        timer.cancel();
        timer.purge();

    }

    @FXML private void xPlusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        xxValue=xValue;

        plusTaskX = new PlusTimerTaskX();
        timer = new Timer();
        timer.scheduleAtFixedRate(plusTaskX, 500, velocity);

    }

    @FXML private void xPlusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        plusTaskX.cancel();
        timer.cancel();
        timer.purge();

    }


    // second servo
    @FXML private  void yMinusPressed() {
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        yyValue=yValue;
        minusTaskY = new MinusTimerTaskY();
        timer = new Timer();
        timer.scheduleAtFixedRate(minusTaskY, 500, velocity);

    }

    @FXML private void yMinusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        minusTaskY.cancel();
        timer.cancel();
        timer.purge();

    }

    @FXML private void yPlusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        yyValue=yValue;
        plusTaskY = new PlusTimerTaskY();
        timer = new Timer();
        timer.scheduleAtFixedRate(plusTaskY, 500, velocity);

    }

    @FXML private void yPlusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        plusTaskY.cancel();
        timer.cancel();
        timer.purge();

    }


    // third servo
    @FXML private  void zMinusPressed() {
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        zzValue=zValue;
        minusTaskZ = new MinusTimerTaskZ();
        timer = new Timer();
        timer.scheduleAtFixedRate(minusTaskZ, 500, velocity);

    }

    @FXML private void zMinusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        minusTaskZ.cancel();
        timer.cancel();
        timer.purge();

    }

    @FXML private void zPlusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        zzValue=zValue;
        plusTaskZ = new PlusTimerTaskZ();
        timer = new Timer();
        timer.scheduleAtFixedRate(plusTaskZ, 500, velocity);

    }

    @FXML private void zPlusReleased(){
        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
        plusTaskZ.cancel();
        timer.cancel();
        timer.purge();

    }
    //endregion

    private void setXyzPosition(){
        double[] thetaValue;
        thetaValue=InversKin.inverse(xValue,yValue,zValue);
        angleValue1=(int) Math.round(Math.toDegrees(thetaValue[0]));
        angleValue2=(int) Math.round(Math.toDegrees(thetaValue[1]));
        angleValue3=(int) Math.round(Math.toDegrees(thetaValue[2]));
        System.out.println(angleValue1);
        System.out.println(angleValue2);
        System.out.println(angleValue3);

        MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,255);
        MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,255);
        MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,255);

        MainModel.getInstance().getIntegerList().setAll(angleValue1,angleValue2,angleValue3,angleValue4);
    }





}
