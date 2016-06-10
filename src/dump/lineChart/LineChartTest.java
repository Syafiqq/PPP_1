package dump.lineChart;/**
 * This <PPP_1> project in package <dump.lineChart> created by :
 * Name         : syafiq
 * Date / Time  : 19 May 2016, 6:19 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LineChartTest extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(super.getClass().getResource("main.fxml"));
        loader.setController(new LineChartTestController());
        Stage server = new Stage();
        server.setScene(new Scene(loader.load(), 800, 600));
        server.show();
    }
}
