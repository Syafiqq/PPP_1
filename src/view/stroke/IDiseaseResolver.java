package view.stroke;/**
 * This <PPP_1> project in package <view.stroke> created by :
 * Name         : syafiq
 * Date / Time  : 08 June 2016, 6:30 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import controller.stroke.CDiseaseResolver;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IDiseaseResolver extends Application
{
    public IDiseaseResolver()
    {
        //Todo : Keep that constructor so application can be tested
    }

    public IDiseaseResolver(CDiseaseResolver controller)
    {
        this.buildStage(new Stage(), controller);
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage)
    {
        this.buildStage(primaryStage, new CDiseaseResolver());
    }

    private void buildStage(Stage primaryStage, CDiseaseResolver controller)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(super.getClass().getResource("VDiseaseResolver.fxml"));
            loader.setController(controller);
            primaryStage.setTitle("Disease Resolver");
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
