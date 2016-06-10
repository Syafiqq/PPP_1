package dump.lineChart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * This <PPP_1> project in package <dump.lineChart> created by :
 * Name         : syafiq
 * Date / Time  : 19 May 2016, 7:13 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class LineChartTestController implements Initializable
{
    @FXML private LineChart<Integer, Integer> graph, graph1;
    @FXML private LineChart<Integer, Double> graph2;

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<LineChart.Series<Integer, Integer>> lineChartData = FXCollections.observableArrayList();

        generateLineChartData(lineChartData, new int[][] {{0, 25}, {20, 0}}, new int[][] {{5, 0}, {25, 25}, {35, 25}, {55, 0}}, new int[][] {{40, 0}, {60, 25}, {70, 25}, {75, 20}});
        graph.setData(lineChartData);
        lineChartData = FXCollections.observableArrayList();
        generateLineChartData(lineChartData, new int[][] {{0, 25}, {20, 0}}, new int[][] {{5, 0}, {25, 25}, {45, 0}}, new int[][] {{30, 0}, {50, 25}, {70, 0}}, new int[][] {{55, 0}, {75, 25}});
        graph1.setData(lineChartData);
        ObservableList<XYChart.Series<Integer, Double>> lineChartData1 = FXCollections.observableArrayList();
        for(int i = 0, is = 4; ++i < is; )
        {
            double[][]       bellShaped = new double[40][2];
            BellShaped_Curve curve      = new BellShaped_Curve(i * 20.0, 5.0);
            for(int j = -1; ++j < 40; )
            {
                int x = j + 1 + ((i - 1) * 20);
                bellShaped[j][0] = x;
                bellShaped[j][1] = curve.getY(x);
            }
            generateLineChartData(lineChartData1, bellShaped);
        }
        graph2.setData(lineChartData1);

        graph.setCreateSymbols(false);
        graph.getYAxis().setTickLabelsVisible(false);
        graph.getYAxis().setOpacity(0);
        graph.getXAxis().setTickLabelsVisible(false);
        graph.getXAxis().setOpacity(0);
        graph1.setCreateSymbols(false);
        graph1.getYAxis().setTickLabelsVisible(false);
        graph1.getYAxis().setOpacity(0);
        graph1.getXAxis().setTickLabelsVisible(false);
        graph1.getXAxis().setOpacity(0);
        graph2.setCreateSymbols(false);
        graph2.getYAxis().setTickLabelsVisible(false);
        graph2.getYAxis().setOpacity(0);
        graph2.getXAxis().setTickLabelsVisible(false);
        graph2.getXAxis().setOpacity(0);
    }

    private void generateLineChartData(ObservableList<XYChart.Series<Integer, Integer>> lineChartData, int[][]... ints)
    {
        for(int[][] dataset_collection : ints)
        {
            LineChart.Series<Integer, Integer>             series = new LineChart.Series<>();
            ObservableList<XYChart.Data<Integer, Integer>> data   = series.getData();
            for(int[] dataset : dataset_collection)
            {
                data.add(new XYChart.Data<>(dataset[0], dataset[1]));
            }
            lineChartData.add(series);
        }
    }

    private void generateLineChartData(ObservableList<XYChart.Series<Integer, Double>> lineChartData, double[][]... ints)
    {
        for(double[][] dataset_collection : ints)
        {
            LineChart.Series<Integer, Double>             series = new LineChart.Series<>();
            ObservableList<XYChart.Data<Integer, Double>> data   = series.getData();
            for(double[] dataset : dataset_collection)
            {
                data.add(new XYChart.Data<>((int) dataset[0], dataset[1]));
            }
            lineChartData.add(series);
        }
    }

}
