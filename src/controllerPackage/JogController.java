package controllerPackage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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


    // togglegroup for set way to move
    final private ToggleGroup group = new ToggleGroup();

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
    private String checkMode;

    //region timer an timertask declaration and implementaion
    private Timer timer = new Timer();
    private TimerTask plusTask1 = new PlusTimerTask1();
    private TimerTask minusTask1 = new MinusTimerTask1();
    private TimerTask plusTask2 = new PlusTimerTask2();
    private TimerTask minusTask2 = new MinusTimerTask2();
    private TimerTask plusTask3 = new PlusTimerTask3();
    private TimerTask minusTask3 = new MinusTimerTask3();

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


    }

    private class PlusTimerTask1 extends TimerTask {

        public void run() {
            //System.out.println(angleValue1);
            angleValue1+=1;
            if(checkMode.equals("simulation")){
            if(angleValue11==angleValue1-step){
                MainModel.getInstance().currentValue1().setText(Integer.toString(angleValue1));
                angleValue11=angleValue1;
            }}
            MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,255);
        }
    }

    private class MinusTimerTask1 extends TimerTask {
        public void run() {

            angleValue1-=1;
            if(checkMode.equals("simulation")){
            if(angleValue11==angleValue1+step){
                MainModel.getInstance().currentValue1().setText(Integer.toString(angleValue1));
                angleValue11=angleValue1;
            }}
            MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,255);
        }
    }

    private class PlusTimerTask2 extends TimerTask {

        public void run() {
            //System.out.println(angleValue2);
            angleValue2+=1;
            if(checkMode.equals("simulation")){
                if(angleValue22==angleValue2-step){
                    MainModel.getInstance().currentValue2().setText(Integer.toString(angleValue2));
                    angleValue22=angleValue2;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,255);
        }
    }

    private class MinusTimerTask2 extends TimerTask {
        public void run() {

            angleValue2-=1;
            if(checkMode.equals("simulation")){
                if(angleValue22==angleValue2+step){
                    MainModel.getInstance().currentValue2().setText(Integer.toString(angleValue2));
                    angleValue22=angleValue2;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,255);
        }
    }

    private class PlusTimerTask3 extends TimerTask {

        public void run() {
            //System.out.println(angleValue3);
            angleValue3+=1;

            if(checkMode.equals("simulation")){
                if(angleValue33==angleValue3-step){
                    MainModel.getInstance().currentValue3().setText(Integer.toString(angleValue3));
                    angleValue33=angleValue3;
                }}
            MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,255);
        }
    }

    private class MinusTimerTask3 extends TimerTask {
        public void run() {

            angleValue3-=1;
            if(checkMode.equals("simulation")){
                if(angleValue33==angleValue3+step){
                    MainModel.getInstance().currentValue3().setText(Integer.toString(angleValue3));
                    angleValue33=angleValue3;
                }}
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
    }

    // set every servo's position on 90 degrees
    @FXML private void calibrationButtonClicked(){
        MainModel.getInstance().currentLink().sendToneMessage(1,90,0);
        MainModel.getInstance().currentLink().sendToneMessage(2,90,0);
        MainModel.getInstance().currentLink().sendToneMessage(3,90,0);
        MainModel.getInstance().currentLink().sendToneMessage(4,90,0);
        angleValue1=90;
        angleValue2=90;
        angleValue3=90;
        angleValue4=90;

    }

    // end effector open/close
    @FXML private void effectorButtonClicked(){
        MainModel.getInstance().currentLink().sendToneMessage(4,angleValue4,0);
    }


    // jog buttons
    // first jog buttons
    @FXML private void firstMinusClicked(){
        System.out.println(angleValue1);
        angleValue1-=1;

        MainModel.getInstance().currentValue1().setText(Integer.toString(angleValue1));
        MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,0);

    }
    @FXML private void firstPlusClicked(){
        angleValue1+=1;
        System.out.println(angleValue1);
        MainModel.getInstance().currentLink().sendToneMessage(1,angleValue1,0);
        MainModel.getInstance().currentValue1().setText(Integer.toString(angleValue1));

    }

    //second buttons
    @FXML private void secondMinusClicked(){
        System.out.println(angleValue2);
        angleValue2-=1;
        MainModel.getInstance().currentValue2().setText(Integer.toString(angleValue2));
        MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,0);

    }
    @FXML private void secondPlusClicked(){
        angleValue2+=1;
        System.out.println(angleValue2);
        MainModel.getInstance().currentValue2().setText(Integer.toString(angleValue2));
        MainModel.getInstance().currentLink().sendToneMessage(2,angleValue2,0);

    }

    //third buttons
    @FXML private void thirdMinusClicked(){
        System.out.println(angleValue3);
        angleValue3-=1;
        MainModel.getInstance().currentValue3().setText(Integer.toString(angleValue3));


        MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,0);

    }
    @FXML private void thirdPlusClicked(){
        angleValue3+=1;
        System.out.println(angleValue3);

        MainModel.getInstance().currentValue3().setText(Integer.toString(angleValue3));
        MainModel.getInstance().currentLink().sendToneMessage(3,angleValue3,0);

    }



    // Cartesian buttons
    // first buttons
    @FXML private void xMinusClicked(){

    }
    @FXML private void xPlusClicked(){

    }
    //second buttons
    @FXML private void yMinusClicked(){

    }
    @FXML private void yPlusClicked(){

    }
    //third buttons
    @FXML private void zMinusClicked(){

    }
    @FXML private void zPlusClicked(){

    }



    // action when mouse is pressed
    // first servo
    @FXML private  void firstMinusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        angleValue11=angleValue1;
        checkMode =MainModel.getInstance().currentLabel().getText();
        minusTask1 = new MinusTimerTask1();
        timer = new Timer();
        timer.scheduleAtFixedRate(minusTask1, 500, velocity);


    }

    @FXML private void firstMinusReleased(){
        minusTask1.cancel();
        timer.cancel();
        timer.purge();

    }

    @FXML private void firstPlusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        angleValue11=angleValue1;
        checkMode =MainModel.getInstance().currentLabel().getText();
        plusTask1 = new PlusTimerTask1();
        timer = new Timer();
        timer.scheduleAtFixedRate(plusTask1, 500, velocity);

    }

    @FXML private void firstPlusReleased(){
        plusTask1.cancel();
        timer.cancel();
        timer.purge();

    }


    // second servo
    @FXML private  void secondMinusPressed() {
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        checkMode =MainModel.getInstance().currentLabel().getText();
        angleValue22=angleValue2;
        minusTask2 = new MinusTimerTask2();
        timer = new Timer();
        timer.scheduleAtFixedRate(minusTask2, 500, velocity);

    }

    @FXML private void secondMinusReleased(){
        minusTask2.cancel();
        timer.cancel();
        timer.purge();

    }

    @FXML private void secondPlusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        checkMode =MainModel.getInstance().currentLabel().getText();
        angleValue22=angleValue2;
        plusTask2 = new PlusTimerTask2();
        timer = new Timer();
        timer.scheduleAtFixedRate(plusTask2, 500, velocity);

    }

    @FXML private void secondPlusReleased(){
        plusTask2.cancel();
        timer.cancel();
        timer.purge();

    }


    // third servo
    @FXML private  void thirdMinusPressed() {
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        checkMode =MainModel.getInstance().currentLabel().getText();
        angleValue33=angleValue3;
        minusTask3 = new MinusTimerTask3();
        timer = new Timer();
        timer.scheduleAtFixedRate(minusTask3, 500, velocity);

    }

    @FXML private void thirdMinusReleased(){
        minusTask3.cancel();
        timer.cancel();
        timer.purge();

    }

    @FXML private void thirdPlusPressed(){
        velocityInit=(int)velocitySlider.getValue();
        velocity=101-velocityInit;
        step=(velocityInit+ helpStep)/10;
        checkMode =MainModel.getInstance().currentLabel().getText();
        angleValue33=angleValue3;
        plusTask3 = new PlusTimerTask3();
        timer = new Timer();
        timer.scheduleAtFixedRate(plusTask3, 500, velocity);

    }

    @FXML private void thirdPlusReleased(){
        plusTask3.cancel();
        timer.cancel();
        timer.purge();

    }





}
