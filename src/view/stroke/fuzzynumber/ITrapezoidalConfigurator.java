package view.stroke.fuzzynumber;/**
 * This <PPP_1> project in package <view.stroke.fuzzynumber> created by :
 * Name         : syafiq
 * Date / Time  : 07 June 2016, 12:55 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import controller.stroke.fuzzynumber.CTrapezoidalConfigurator;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ITrapezoidalConfigurator extends Application
{
    public ITrapezoidalConfigurator()
    {
        //Todo : Keep that constructor so application can be tested
    }

    public ITrapezoidalConfigurator(CTrapezoidalConfigurator controller)
    {
        this.buildStage(new Stage(), controller);
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage)
    {
        this.buildStage(primaryStage, new CTrapezoidalConfigurator());
    }

    private void buildStage(Stage primaryStage, CTrapezoidalConfigurator controller)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(super.getClass().getResource("VTrapezoidalConfigurator.fxml"));
            loader.setController(controller);
            primaryStage.setTitle("Parameter Calibrator");
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
