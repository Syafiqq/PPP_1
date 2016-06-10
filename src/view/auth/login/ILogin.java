package view.auth.login;/**
 * This <PPP_1> project in package <view.auth.login> created by :
 * Name         : syafiq
 * Date / Time  : 06 June 2016, 12:41 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import controller.auth.CLogin;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ILogin extends Application
{
    public ILogin()
    {
        //Todo : Keep that constructor so application can be tested
    }

    public ILogin(CLogin controller)
    {
        this.buildStage(new Stage(), controller);
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage)
    {
        buildStage(primaryStage, new CLogin());
    }

    private void buildStage(Stage primaryStage, CLogin controller)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(super.getClass().getResource("VLogin.fxml"));
            loader.setController(controller);
            primaryStage.setTitle("Login Screen");
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
            primaryStage.setResizable(false);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
