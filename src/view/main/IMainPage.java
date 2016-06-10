package view.main;/**
 * This <PPP_1> project in package <view.main> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 12:52 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import controller.main.CMainPage;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IMainPage extends Application
{
    public IMainPage()
    {
        //Todo : Keep that constructor so application can be tested
    }

    public IMainPage(CMainPage controller)
    {
        this.buildStage(new Stage(), controller);
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage)
    {
        this.buildStage(primaryStage, new CMainPage());
    }

    private void buildStage(Stage primaryStage, CMainPage controller)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(super.getClass().getResource("VMainPage.fxml"));
            loader.setController(controller);
            primaryStage.setTitle("Sistem Diagnosis Penyakit Stroke");
            primaryStage.setScene(new Scene(loader.load(), 960, 720));
            primaryStage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
