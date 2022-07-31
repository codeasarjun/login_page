import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.sql.SQLException;
public class delete_User {
    

    @FXML
    private TextField admin_password;

    @FXML
    private TextField admin_username;

    @FXML
    private Button bt_back;

    @FXML
    private Button delete_user;

    @FXML
    private Button remove_user;

    @FXML
    private TextField user_id_for_del;

    @FXML
    private Label user_status_inDB;
    @FXML
    private Button verify_access;
    

    
    @FXML
    void back_to_login(ActionEvent event) {
        DButils.changeScene(event, "sample.fxml", "login", null, null);
    }
    String username;
    @FXML
    void check_userinDB(ActionEvent event) {
        username=user_id_for_del.getText();
        user_status_inDB.setVisible(true);
        String sql="SELECT * FROM users WHERE fname=?";
        dbConnection(sql,username);
    }

    @FXML
    void remove_user_frm_DB(ActionEvent event) {
       // String sql="DELETE FROM users WHERE fname=?";
        //System.out.println(username);
        Connection con = null;
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet = null;
        String url = "jdbc:postgresql://localhost/one";
        String user = "postgres";
        String pass = "Gur@2021";
        String sql;
    try {
        con=DriverManager.getConnection(url, user, pass);
        sql="DELETE FROM users WHERE fname=?";
        preparedStatement=con.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.execute();         //it will close it after first 
        
        clear_data(username+" is deleted from DB");
        preparedStatement.close();
        con.close();

    } catch (SQLException a) {
        a.printStackTrace();
    }



    }

    @FXML
    void verify_access_inDB(ActionEvent event) {

        String sql="SELECT * FROM users WHERE fname=?";
        String a_username=admin_username.getText();
        String a_password=admin_password.getText();
        
        dbConnection(sql, a_username, a_password);
}

@FXML
void useridIsNull(KeyEvent event) {
if(user_id_for_del.getText().trim().isEmpty())
{
    clear_data(" ");
}
}

    // for DB connection 
    void dbConnection(String query,String obj)
    {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String url = "jdbc:postgresql://localhost/one";
        String user = "postgres";
        String pass = "Gur@2021";
        try {
            con = DriverManager.getConnection(url, user, pass);
            preparedStatement = con.prepareStatement(query);
           preparedStatement.setString(1, obj);
           resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                
                user_status_inDB.setText("User found in DB");
                admin_username.setVisible(true);
                admin_password.setVisible(true);
                verify_access.setVisible(true);
                
            }
            else
            {
                user_status_inDB.setText("user not found in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    void dbConnection(String query,String a_user,String a_pass)
    {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String url = "jdbc:postgresql://localhost/one";
        String user = "postgres";
        String pass = "Gur@2021";
        
        try {
            con = DriverManager.getConnection(url, user, pass);
            preparedStatement = con.prepareStatement(query);
           preparedStatement.setString(1, a_user);
           resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                
                String retrivedpassword=resultSet.getString("pass");
               // System.out.println(retrivedpassword);
                if(a_pass.equals(retrivedpassword))
                {
                    remove_user.setVisible(true);
                }
                else {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("incorrect password");
                    alert.show();
                }
                
            }
            else
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("user not found");
                    alert.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    void clear_data(String data)
    {
        
            admin_username.setVisible(false);
            admin_password.setVisible(false);
            verify_access.setVisible(false);
            remove_user.setVisible(false);
            user_status_inDB.setText(data);
        
    }


}
