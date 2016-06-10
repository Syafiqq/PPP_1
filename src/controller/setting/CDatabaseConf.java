package controller.setting;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.db.DBProperties;
import model.pattern.adapter.DatabaseStateObserverAdapter;

/**
 * This <PPP_1> project in package <controller.setting> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 3:20 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class CDatabaseConf implements Initializable
{
    @FXML public BorderPane root;
    @FXML public TextField  tf_databaseName;
    @FXML public Button     b_connect;

    private void evaluateConnectButtonRole()
    {
        if(DBProperties.getInstance().isActive)
        {
            this.b_connect.setDisable(false);
            this.b_connect.setText("Disconnect");
        }
        else
        {
            this.b_connect.setText("Connect");
        }
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        this.evaluateConnectButtonRole();
        DBProperties.getInstance().registerObserver("databaseState", new DatabaseStateObserverAdapter(state -> CDatabaseConf.this.b_connect.setText(state ? "Disconnect" : "Connect"), DBProperties.getInstance()));

        this.tf_databaseName.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!DBProperties.getInstance().isActive)
            {
                CDatabaseConf.this.b_connect.setDisable(newValue.trim().isEmpty());
            }
        });
    }

    public void connectToDatabase(ActionEvent actionEvent)
    {
        if(DBProperties.getInstance().isActive)
        {
            try
            {
                DBProperties.getInstance().disconnect();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Disconnect");
                alert.setHeaderText("Look, an Success");
                alert.setContentText("Success Disconnecting Existing Database");
                alert.showAndWait();
                ((Stage) this.root.getScene().getWindow()).close();
            }
            catch(SQLException ignored)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Disconnect");
                alert.setHeaderText("Look, an Error");
                alert.setContentText("Error Disconnecting Existing Database");
                alert.showAndWait();
            }
        }
        else
        {
            String dbName = tf_databaseName.getText();
            DBProperties.getInstance().dbName = dbName;
            try
            {
                DBProperties.getInstance().connect();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Connect");
                alert.setHeaderText("Look, an Success");
                alert.setContentText("Success Connecting to " + dbName);
                alert.showAndWait();
                ((Stage) this.root.getScene().getWindow()).close();
            }
            catch(SQLException ignored)
            {
                ignored.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Connect");
                alert.setHeaderText("Look, an Error");
                alert.setContentText("Error Connecting to " + dbName);
                alert.showAndWait();
            }
        }
    }

    public void cancelDBConnection(ActionEvent actionEvent)
    {
        ((Stage) this.root.getScene().getWindow()).close();
    }
}
