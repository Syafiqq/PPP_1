package view.setting.databaseConfigurator;/**
 * This <PPP_1> project in package <view.setting.databaseConfigurator> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 3:10 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import controller.setting.CDatabaseConf;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IDatabaseConf extends Application
{

    public IDatabaseConf()
    {
        //Todo : Keep that constructor so application can be tested
    }

    public IDatabaseConf(CDatabaseConf controller)
    {
        this.buildStage(new Stage(), controller);
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage)
    {
        this.buildStage(primaryStage, new CDatabaseConf());
    }

    private void buildStage(Stage primaryStage, CDatabaseConf controller)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(super.getClass().getResource("VDatabaseConf.fxml"));
            loader.setController(controller);
            primaryStage.setTitle("Setting Database");
            primaryStage.setScene(new Scene(loader.load(), 300, 200));
            primaryStage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
