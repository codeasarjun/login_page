
// login controller 

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
//import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class login implements Initializable {

    @FXML
    private Button bt_logout;
    @FXML
    private Label lb_welcome;
    @FXML
    private Label lb_fav;
    @FXML
    private Button bt_updatedata;
    String username_="";
    @FXML
    void bt_udpatedata(ActionEvent event) {
        DButils.changeScene(event, "updateData.fxml", "Update your information", null, null);
        

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bt_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DButils.changeScene(e, "sample.fxml", "login", null, null);//can pass null if need to pass less than argumnet
            }

        });
    }

    // this will show the data after login
    public void setUserInfo(String username, String favC) {
        username_=username;
        lb_welcome.setText("Welcome " + username);
        lb_fav.setText("Hope you are doing well");
    }

}
