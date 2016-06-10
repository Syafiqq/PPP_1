package view.auth.register;/**
 * This <PPP_1> project in package <view.auth.register> created by :
 * Name         : syafiq
 * Date / Time  : 03 June 2016, 5:34 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import controller.auth.CRegister;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IRegister extends Application
{
    public IRegister()
    {
        //Todo : Keep that constructor so application can be tested
    }

    public IRegister(CRegister controller)
    {
        this.buildStage(new Stage(), controller);
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage)
    {
        buildStage(primaryStage, new CRegister());
    }

    private void buildStage(Stage primaryStage, CRegister controller)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(super.getClass().getResource("VRegister.fxml"));
            loader.setController(controller);
            primaryStage.setTitle("Register Screen");
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
