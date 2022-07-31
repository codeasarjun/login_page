import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class update_data {

    @FXML
    private Button bt_logout;

    @FXML
    private Label lb_fav;

    @FXML
    public  Label lb_welcome;
    
    
    

    @FXML
    void logout_session(ActionEvent event) {
DButils.changeScene(event, "sample.fxml", "Login", null, null);

    }

   public void udpatedata()
    {
        lb_welcome.setText("Helooooooo moto");
    }
    public  void  setInfo(String s,login obj)
    {
        
    
        String x;
        x=obj.username_;
        System.out.println(x);
        
       // lb_welcome.setText(x);

    }




}
