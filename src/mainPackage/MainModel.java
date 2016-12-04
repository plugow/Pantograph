package mainPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.zu.ardulink.Link;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainModel{
    private final static MainModel instance = new MainModel();

    public static MainModel getInstance() {
        return instance;
    }

    private Label label=new Label();
    public Label currentLabel() {
        return label;
    }

    private Link link=Link.getDefaultInstance();
    public Link currentLink() {
        return link;
    }

    private Stage stage=new Stage();
    public Stage currentStage() {
        return stage;
    }



    private ObservableList<String> labelList= FXCollections.observableArrayList("1","2","3");

    public ObservableList<String> getLabelList() {

        return labelList;
    }



}