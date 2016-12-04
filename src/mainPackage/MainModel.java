package mainPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.zu.ardulink.Link;

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


    public void setCheckMode(boolean checkMode) {
        this.checkMode = checkMode;
    }

    private boolean checkMode=false;

    private ObservableList<Integer> integerList = FXCollections.observableArrayList(1,2,3,0);

    public ObservableList<Integer> getIntegerList() {

        return integerList;
    }


    public boolean isCheckMode() {
        return checkMode;
    }
}