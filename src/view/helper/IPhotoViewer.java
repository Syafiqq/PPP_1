package view.helper;/**
 * This <PPP_1> project in package <view.helper> created by :
 * Name         : syafiq
 * Date / Time  : 08 June 2016, 9:56 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import controller.helper.CPhotoViewer;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IPhotoViewer extends Application
{
    public IPhotoViewer()
    {
        //Todo : Keep that constructor so application can be tested
    }

    public IPhotoViewer(CPhotoViewer controller)
    {
        this.buildStage(new Stage(), controller);
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage)
    {
        this.buildStage(primaryStage, new CPhotoViewer(null));
    }

    private void buildStage(Stage primaryStage, CPhotoViewer controller)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(super.getClass().getResource("VPhotoViewer.fxml"));
            loader.setController(controller);
            primaryStage.setTitle("Photo Viewer");
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
