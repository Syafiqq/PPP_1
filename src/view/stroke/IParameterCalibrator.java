package view.stroke;/**
 * This <PPP_1> project in package <view.stroke> created by :
 * Name         : syafiq
 * Date / Time  : 07 June 2016, 6:34 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import controller.stroke.CParameterCalibrator;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IParameterCalibrator extends Application
{

    public IParameterCalibrator()
    {
        //Todo : Keep that constructor so application can be tested
    }

    public IParameterCalibrator(CParameterCalibrator controller)
    {
        this.buildStage(new Stage(), controller);
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage)
    {
        this.buildStage(primaryStage, new CParameterCalibrator());
    }

    private void buildStage(Stage primaryStage, CParameterCalibrator controller)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(super.getClass().getResource("VParameterCalibrator.fxml"));
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
