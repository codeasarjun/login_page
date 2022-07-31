import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

// Db connection and scene chnage will happen here 
public class DButils {

    // this will change scene 
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String favC) {
        Parent root = null;

        if (username != null && favC != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DButils.class.getResource(fxmlFile));
                root = loader.load();
                login Login = loader.getController();
                
                Login.setUserInfo(username, favC);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DButils.class.getResource(fxmlFile));


            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));


    }

    public static void signUpUser(ActionEvent event, String username, String password, String favC) {
        Connection con = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExsist = null;
        ResultSet resultSet = null;
        String url = "jdbc:postgresql://localhost/one";
        String user = "postgres";
        String pass = "Gur@2021";

        try {
            con = DriverManager.getConnection(url, user, pass);
            psCheckUserExsist = con.prepareStatement("SELECT *FROM users WHERE fname=?");
            psCheckUserExsist.setString(1, username);// 1 question mark then index is one
            resultSet = psCheckUserExsist.executeQuery();
            if (resultSet.isBeforeFirst())// use to check if result set is not empty
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you cannot use this username");
                alert.show();
            } else {
                // psInsert=con.prepareStatement("INSERT INTO users VALUES(fname,pass,favc) VALUES( ?,?,?)");
                String sql;
                sql = "INSERT INTO users" + "(fname,pass,favc)" + "VALUES(?,?,?)";
                psInsert = con.prepareStatement(sql);
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, favC);
                psInsert.executeUpdate();
                changeScene(event, "login.fxml", "Login", username, favC);
            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } // try close connection after every query 
            if (psCheckUserExsist != null) {
                try {
                    psCheckUserExsist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void logInUser(ActionEvent event, String fxmlFile, String title, String username, String password) {
        /// data connection 
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String url = "jdbc:postgresql://localhost/one";
        String user = "postgres";
        String pass = "Gur@2021";

        try {
            con = DriverManager.getConnection(url, user, pass);
            preparedStatement = con.prepareStatement("SELECT * FROM users WHERE fname=?");
           preparedStatement.setString(1, username);
           //preparedStatement.setString(2,password);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                String x="hi arjun";
                String retrivedpassword=resultSet.getString("pass");
                if(password.equals(retrivedpassword))
                {
                    changeScene(event, "login.fxml", "Welcome!", username, x);
                }
                else
                {
                    Alert alert= new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("User name password dont matchs");
                    alert.show();
                }
                //changeScene(event, "login.fxml", "Welcome!", username, x);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("user not found");
                alert.show();
                }
            

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // first close result set then preparedstatment then connection
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
