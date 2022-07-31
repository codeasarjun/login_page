//signup controller

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class signup implements Initializable {

    @FXML
    private TextField bt_password;

    @FXML
    private Button bt_signup;

    @FXML
    private TextField bt_username;

    @FXML
    private Button btn_login;

    @FXML
    private RadioButton rb_google;

    @FXML
    private RadioButton rv_yt;


    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup = new ToggleGroup(); // used to handle randio button
        rb_google.setToggleGroup(toggleGroup);
        rv_yt.setToggleGroup(toggleGroup);
        rb_google.setSelected(true);// it will come with selction 
        bt_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                if (!bt_username.getText().trim().isEmpty() && !bt_password.getText().trim().isEmpty()) {
                    DButils.signUpUser(e, bt_username.getText(), bt_password.getText(), toggleName);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill all the details");
                    alert.show();
                }
            }
        });
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DButils.changeScene(e, "sample.fxml", "Login", null, null);
            }
        });
    }

}
