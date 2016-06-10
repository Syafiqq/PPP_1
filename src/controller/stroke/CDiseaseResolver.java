package controller.stroke;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import model.db.DBProperties;
import model.helper.Session;

/**
 * This <PPP_1> project in package <controller.stroke> created by :
 * Name         : syafiq
 * Date / Time  : 08 June 2016, 6:32 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class CDiseaseResolver implements Initializable
{
    private final Session                          session;
    @FXML public  TextField                        tf_weight;
    @FXML public  TextField                        tf_height;
    @FXML public  TextField                        tf_bloodPressure;
    @FXML public  Label                            l_result;
    @FXML public  LineChart<String, Double>        lc_weight;
    @FXML public  LineChart<String, Double>        lc_height;
    @FXML public  LineChart<String, Double>        lc_bloodPressure;
    @FXML public  LineChart<String, String>        lc_result;
    private       LineChart.Series<String, Double> weightSeries;
    private       LineChart.Series<String, Double> heightSeries;
    private       LineChart.Series<String, Double> bloodPressureSeries;
    private       LineChart.Series<String, String> resultSeries;

    public CDiseaseResolver()
    {
        this(null);
    }

    public CDiseaseResolver(Session session)
    {
        this.session = session;
        this.weightSeries = new LineChart.Series<>();
        this.heightSeries = new LineChart.Series<>();
        this.bloodPressureSeries = new LineChart.Series<>();
        this.resultSeries = new LineChart.Series<>();
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<LineChart.Series<String, Double>> dataWeight        = FXCollections.observableArrayList();
        ObservableList<LineChart.Series<String, Double>> dataHeight        = FXCollections.observableArrayList();
        ObservableList<LineChart.Series<String, Double>> dataBloodPressure = FXCollections.observableArrayList();
        ObservableList<LineChart.Series<String, String>> dataResult        = FXCollections.observableArrayList();
        dataWeight.add(this.weightSeries);
        dataHeight.add(this.heightSeries);
        dataBloodPressure.add(this.bloodPressureSeries);
        dataResult.add(this.resultSeries);
        this.setUpChartValue(this.lc_weight, dataWeight, "Weight");
        this.setUpChartValue(this.lc_height, dataHeight, "Height");
        this.setUpChartValue(this.lc_bloodPressure, dataBloodPressure, "Blood Pressure");
        this.setUpChartCategory(this.lc_result, dataResult, "Result");
        this.reloadData();
    }

    private void reloadData()
    {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:sss");
        DBProperties     properties = DBProperties.getInstance();
        try
        {
            properties.preparedStatement = properties.connection.prepareStatement("SELECT `height`, `weight`, `bloodPressure`, `result`, strftime('%Y-%m-%d %H:%M:%f', `date`, 'localtime') AS 'date' FROM `rekamMedis` WHERE `rekamMedis`.`user` = ?");
            properties.preparedStatement.setInt(1, this.session.user.userID);
            properties.result = properties.preparedStatement.executeQuery();

            ObservableList<LineChart.Data<String, Double>> weightSet        = this.weightSeries.getData();
            ObservableList<LineChart.Data<String, Double>> heightSet        = this.heightSeries.getData();
            ObservableList<LineChart.Data<String, Double>> bloodPressureSet = this.bloodPressureSeries.getData();
            ObservableList<LineChart.Data<String, String>> resultSet        = this.resultSeries.getData();

            while(properties.result.next())
            {
                weightSet.add(new LineChart.Data<>(dateFormat.format(properties.result.getTimestamp("date")), properties.result.getDouble("weight")));
                heightSet.add(new LineChart.Data<>(dateFormat.format(properties.result.getTimestamp("date")), properties.result.getDouble("height")));
                bloodPressureSet.add(new LineChart.Data<>(dateFormat.format(properties.result.getTimestamp("date")), properties.result.getDouble("bloodPressure")));
                resultSet.add(new LineChart.Data<>(dateFormat.format(properties.result.getTimestamp("date")), properties.result.getString("result")));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates") private void setUpChartCategory(LineChart<String, String> chart, ObservableList<LineChart.Series<String, String>> data, String name)
    {
        chart.setData(data);
        chart.setAnimated(false);
        chart.setTitle(name);
        chart.setLegendVisible(false);
        chart.setHorizontalGridLinesVisible(true);
        chart.setCreateSymbols(false);
    }

    @SuppressWarnings("Duplicates") private void setUpChartValue(LineChart<String, Double> chart, ObservableList<LineChart.Series<String, Double>> data, String name)
    {
        chart.setData(data);
        chart.setAnimated(false);
        chart.setTitle(name);
        chart.setLegendVisible(false);
        chart.setHorizontalGridLinesVisible(true);
        chart.setCreateSymbols(false);
    }

    public void doCalculate(ActionEvent actionEvent)
    {
        StringConverter<Double> stringToDouble = new DoubleStringConverter();
        this.session.disease.bmi.setBmiProperties(stringToDouble.fromString(this.tf_weight.getText()), stringToDouble.fromString(this.tf_height.getText()));
        this.session.disease.tekanan_darah.setTekananDarah(stringToDouble.fromString(this.tf_bloodPressure.getText()));
        this.session.disease.execute();
        this.l_result.setText(this.session.disease.getCategoryValue());
        DBProperties properties = DBProperties.getInstance();
        try
        {
            properties.preparedStatement = properties.connection.prepareStatement("INSERT INTO `rekamMedis` VALUES (NULL, ?, ?, ?, ?, CURRENT_TIMESTAMP , ?)");
            properties.preparedStatement.setDouble(1, this.session.user.userID);
            properties.preparedStatement.setDouble(2, this.session.disease.bmi.getTinggiBadan());
            properties.preparedStatement.setDouble(3, this.session.disease.bmi.getBeratBadan());
            properties.preparedStatement.setDouble(4, this.session.disease.tekanan_darah.getTekananDarah());
            properties.preparedStatement.setString(5, this.session.disease.getCategoryValue());
            properties.preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            this.reloadData();
        }
    }
}
