package controller.auth;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import model.helper.Session;
import model.problem.Stroke;
import model.profile.User;
import model.profile.components.Gender;
import model.profile.components.ProxifiedAvatar;

/**
 * This <PPP_1> project in package <controller.auth> created by :
 * Name         : syafiq
 * Date / Time  : 06 June 2016, 12:41 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class CLogin implements Initializable
{
    final private Session    session;
    @FXML public  BorderPane root;
    @FXML public  TextField  tf_fullName;
    @FXML public  TextField  tf_nickName;
    @FXML public  Button     b_doLogin;

    public CLogin(Session session)
    {
        this.session = session;
    }

    public CLogin()
    {
        this.session = new Session();
        System.err.println("For Testing Only");
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        this.tf_fullName.textProperty().addListener((observable, oldValue, newValue) -> {
            CLogin.this.doLoginFilter(newValue, CLogin.this.tf_nickName.getText());
        });

        this.tf_nickName.textProperty().addListener((observable, oldValue, newValue) -> {
            CLogin.this.doLoginFilter(CLogin.this.tf_fullName.getText(), newValue);
        });

        //this.forTestOnly();
    }

    private void forTestOnly()
    {
        DBProperties.getInstance().dbName = "sample";
        try
        {
            DBProperties.getInstance().connect();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void doLoginFilter(final String fullName, final String nickName)
    {
        this.b_doLogin.setDisable(fullName.trim().isEmpty() || nickName.trim().isEmpty());
    }

    public void doLogin(ActionEvent actionEvent)
    {
        DBProperties properties = DBProperties.getInstance();
        try
        {
            properties.preparedStatement = properties.connection.prepareStatement("SELECT `id`, `fullName`, `nickName`, `birthDate`, `gender`, `thumbnail`, `strokeDisease` FROM `user` WHERE `user`.`fullName` = ? AND `user`.`nickName` = ? LIMIT 1;\n");
            properties.preparedStatement.setString(1, this.tf_fullName.getText());
            properties.preparedStatement.setString(2, this.tf_nickName.getText());
            properties.result = properties.preparedStatement.executeQuery();

            if(properties.result.next())
            {
                this.session.user = new User(properties.result.getInt("id"), properties.result.getString("fullName"), properties.result.getString("nickName"), LocalDate.parse(properties.result.getString("birthDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")), Gender.valueOf(properties.result.getString("gender")), new ProxifiedAvatar(properties.result.getInt("id"), properties.result.getBinaryStream("thumbnail")));
                properties.result.getObject("strokeDisease");
                if(!properties.result.wasNull())
                {
                    try
                    {
                        InputStream       fileIn = properties.result.getBinaryStream("strokeDisease");
                        ObjectInputStream in     = new ObjectInputStream(fileIn);
                        this.session.disease = (Stroke) in.readObject();
                        in.close();
                        fileIn.close();
                    }
                    catch(IOException i)
                    {
                        i.printStackTrace();
                        return;
                    }
                    catch(ClassNotFoundException c)
                    {
                        System.out.println("Stroke class not found");
                        c.printStackTrace();
                        return;
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Login");
                alert.setHeaderText("Look, an Success");
                alert.setContentText("Success Login User");
                alert.showAndWait();
                ((Stage) this.root.getScene().getWindow()).close();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Login");
                alert.setHeaderText("Look, an Warning");
                alert.setContentText("User not Registered");
                alert.showAndWait();
            }

        }
        catch(SQLException ignored)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Register");
            alert.setHeaderText("Look, an Error");
            alert.setContentText("Error Login User");
            alert.showAndWait();
            ignored.printStackTrace();
        }
    }

    public void cancelLogin(ActionEvent actionEvent)
    {
        ((Stage) this.root.getScene().getWindow()).close();
    }
}
