package controller.main;

import controller.auth.CLogin;
import controller.auth.CRegister;
import controller.helper.CPhotoViewer;
import controller.setting.CDatabaseConf;
import controller.stroke.CDiseaseResolver;
import controller.stroke.CParameterCalibrator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.db.DBProperties;
import model.helper.Session;
import model.pattern.adapter.DatabaseStateObserverAdapter;
import view.auth.login.ILogin;
import view.auth.register.IRegister;
import view.helper.IPhotoViewer;
import view.setting.databaseConfigurator.IDatabaseConf;
import view.stroke.IDiseaseResolver;
import view.stroke.IParameterCalibrator;

/**
 * This <PPP_1> project in package <controller.main> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 12:55 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class CMainPage implements Initializable
{
    @FXML public BorderPane root;
    @FXML public Button     b_registrate;
    @FXML public Button     b_login;
    @FXML public ImageView  iv_t_avatar;
    @FXML public Label      l_t_nickName;
    public       Session    session;


    @Override public void initialize(URL location, ResourceBundle resources)
    {
        this.session = new Session();
        DBProperties.getInstance().registerObserver("databaseState", new DatabaseStateObserverAdapter(state -> {
            CMainPage.this.b_registrate.setDisable(!state);
            CMainPage.this.b_login.setDisable(!state);
        }, DBProperties.getInstance()));
    }

    public void change_db_setting(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader loader      = new FXMLLoader(IDatabaseConf.class.getResource("VDatabaseConf.fxml"));
            Stage      dialogStage = new Stage();
            Stage      rootStage   = (Stage) this.root.getScene().getWindow();
            loader.setController(new CDatabaseConf());
            Scene scene = new Scene(loader.load());
            dialogStage.setTitle("Database Connection");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(root.getScene().getWindow());
            dialogStage.setScene(scene);
            dialogStage.show();
            dialogStage.setX(rootStage.getX() + (rootStage.getWidth() / 2) - (dialogStage.getWidth() / 2));
            dialogStage.setY(rootStage.getY() + (rootStage.getHeight() / 2) - (dialogStage.getHeight() / 2));
            dialogStage.close();
            dialogStage.showAndWait();
        }
        catch(IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
        }
    }

    public void registerUser(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader loader      = new FXMLLoader(IRegister.class.getResource("VRegister.fxml"));
            Stage      dialogStage = new Stage();
            Stage      rootStage   = (Stage) this.root.getScene().getWindow();
            loader.setController(new CRegister());
            Scene scene = new Scene(loader.load());
            dialogStage.setTitle("User Registration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(root.getScene().getWindow());
            dialogStage.setScene(scene);
            dialogStage.show();
            dialogStage.setX(rootStage.getX() + (rootStage.getWidth() / 2) - (dialogStage.getWidth() / 2));
            dialogStage.setY(rootStage.getY() + (rootStage.getHeight() / 2) - (dialogStage.getHeight() / 2));
            dialogStage.close();
            dialogStage.showAndWait();
        }
        catch(IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Ooops, there was an error when openin Registration window");

            alert.showAndWait();
        }
    }

    public void loginUser(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader loader      = new FXMLLoader(ILogin.class.getResource("VLogin.fxml"));
            Stage      dialogStage = new Stage();
            Stage      rootStage   = (Stage) this.root.getScene().getWindow();
            loader.setController(new CLogin(this.session));
            Scene scene = new Scene(loader.load());
            dialogStage.setTitle("User Login");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(root.getScene().getWindow());
            dialogStage.setScene(scene);
            dialogStage.show();
            dialogStage.setX(rootStage.getX() + (rootStage.getWidth() / 2) - (dialogStage.getWidth() / 2));
            dialogStage.setY(rootStage.getY() + (rootStage.getHeight() / 2) - (dialogStage.getHeight() / 2));
            dialogStage.close();
            dialogStage.showAndWait();

            if(this.session.user != null)
            {
                this.l_t_nickName.setText(this.session.user.getNickname());
                this.iv_t_avatar.setImage(this.session.user.getThumbnail());
                model.helper.ImageViewCenterImage.adjustImagePosition(this.iv_t_avatar);

                if(this.session.disease == null)
                {
                    FXMLLoader problemParameterLoader = new FXMLLoader(IParameterCalibrator.class.getResource("VParameterCalibrator.fxml"));
                    problemParameterLoader.setController(new CParameterCalibrator(this, this.session));
                    this.root.setCenter(problemParameterLoader.load());
                }
                else
                {
                    this.generateCalculatorResolver();
                }
            }
        }
        catch(IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Ooops, there was an error when openin Login window");

            alert.showAndWait();
        }
    }

    public void generateCalculatorResolver()
    {
        if(this.session.disease == null)
        {
            return;
        }
        else
        {
            FXMLLoader diseaseResolver = new FXMLLoader(IDiseaseResolver.class.getResource("VDiseaseResolver.fxml"));
            diseaseResolver.setController(new CDiseaseResolver(this.session));
            try
            {
                this.root.setCenter(diseaseResolver.load());
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void showImage(Event event)
    {
        if(this.session.user != null)
        {
            try
            {
                FXMLLoader loader      = new FXMLLoader(IPhotoViewer.class.getResource("VPhotoViewer.fxml"));
                Stage      dialogStage = new Stage();
                Stage      rootStage   = (Stage) this.root.getScene().getWindow();
                loader.setController(new CPhotoViewer(this.session.user.profile_picture.showImage()));
                Scene scene = null;
                scene = new Scene(loader.load());
                dialogStage.setTitle("Image Viewer");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(root.getScene().getWindow());
                dialogStage.setScene(scene);
                dialogStage.show();
                dialogStage.setX(rootStage.getX() + (rootStage.getWidth() / 2) - (dialogStage.getWidth() / 2));
                dialogStage.setY(rootStage.getY() + (rootStage.getHeight() / 2) - (dialogStage.getHeight() / 2));
                dialogStage.close();
                dialogStage.showAndWait();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
