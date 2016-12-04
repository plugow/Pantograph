package controllerPackage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mainPackage.MainModel;
import org.zu.ardulink.Link;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;


public class StartWindowController implements Initializable{

    private Link link= MainModel.getInstance().currentLink();
    @FXML public Label label;
    @FXML private Button connectButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("włączanie");
    }


    @FXML private void checkButtonClicked(){
        System.out.println("wyjdź");
        exit();
    }

    @FXML private void connectButtonClicked(){
        System.out.println("connect");
        List<String> portList;
        try {

            portList = link.getPortList();
            if(portList != null && portList.size() > 0) {
                String port = portList.get(0);
                label.setText("dostepny port: "+port);
                boolean connected = link.connect(port);
                System.out.println("Connected:" + connected);
                System.out.println(link.isConnected());

                //close this window
                Stage startWindow=(Stage) connectButton.getScene().getWindow();
                startWindow.close();

                //send is connected to main window

                MainModel.getInstance().currentLabel().setText("connected");
                MainModel.getInstance().setCheckMode(false);

                //set mainWindow
                MainModel.getInstance().currentStage().show();


            }
            else label.setText("port niedostepny");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML private void simulationModeClicked(){
        Stage startWindow=(Stage) connectButton.getScene().getWindow();
        startWindow.close();
        MainModel.getInstance().currentLabel().setText("simulation");
        MainModel.getInstance().setCheckMode(true);
        MainModel.getInstance().currentStage().show();
    }



}
