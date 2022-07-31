import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;
    
    @FXML
    private Button bt_deldata;
    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DButils.logInUser(e, "login.fxml", "Welcome ", tf_username.getText(), tf_password.getText());
            }
        });
        btn_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DButils.changeScene(e, "signup.fxml", "Sign-up", null, null);
            }
        });
    }

    // will redirect to remove data page : - delete_user.  
    @FXML
    void del_data(ActionEvent event) {
        //System.out.println("data del");
        //tf_del_user.setVisible(true);
        //bt_del.setVisible(true);
        DButils.changeScene(event, "deleteUser.fxml", "Remove from DB", null, null);

    
        
    }


   
    

}
