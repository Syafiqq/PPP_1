package view.stroke.fuzzynumber;/**
 * This <PPP_1> project in package <view.stroke.fuzzynumber> created by :
 * Name         : syafiq
 * Date / Time  : 07 June 2016, 11:33 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import controller.stroke.fuzzynumber.CTriangularConfigurator;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ITriangularConfigurator extends Application
{
    public ITriangularConfigurator()
    {
        //Todo : Keep that constructor so application can be tested
    }

    public ITriangularConfigurator(CTriangularConfigurator controller)
    {
        this.buildStage(new Stage(), controller);
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage)
    {
        this.buildStage(primaryStage, new CTriangularConfigurator());
    }

    private void buildStage(Stage primaryStage, CTriangularConfigurator controller)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(super.getClass().getResource("VTriangularConfigurator.fxml"));
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
