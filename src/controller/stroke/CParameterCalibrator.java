package controller.stroke;

import controller.main.CMainPage;
import controller.stroke.fuzzynumber.CBellConfigurator;
import controller.stroke.fuzzynumber.CTrapezoidalConfigurator;
import controller.stroke.fuzzynumber.CTriangularConfigurator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.db.DBProperties;
import model.fuzzy.fuzzy_number.FuzzyNumber;
import model.fuzzy.fuzzy_number.FuzzyNumberFactory;
import model.fuzzy.fuzzy_number.FuzzyNumberProperty;
import model.helper.Range;
import model.helper.Session;
import model.problem.BMI;
import model.problem.Stroke;
import model.problem.TekananDarah;
import view.stroke.IParameterCalibrator;

/**
 * This <PPP_1> project in package <controller.stroke> created by :
 * Name         : syafiq
 * Date / Time  : 07 June 2016, 6:36 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class CParameterCalibrator implements Initializable
{
    private final Session                    session;
    @FXML public  ChoiceBox<String>          cb_lowBmi;
    @FXML public  ChoiceBox<String>          cb_normalBmi;
    @FXML public  ChoiceBox<String>          cb_highBmi;
    @FXML public  ChoiceBox<String>          cb_veryHighBmi;
    @FXML public  ChoiceBox<String>          cb_lowBloodPressure;
    @FXML public  ChoiceBox<String>          cb_normalBloodPressure;
    @FXML public  ChoiceBox<String>          cb_highBloodPressure;
    @FXML public  ChoiceBox<String>          cb_veryHighBloodPressure;
    @FXML public  Pane                       p_lowBmi;
    @FXML public  Pane                       p_normalBmi;
    @FXML public  Pane                       p_highBmi;
    @FXML public  Pane                       p_veryHighBmi;
    @FXML public  Pane                       p_lowBloodPressure;
    @FXML public  Pane                       p_normalBloodPressure;
    @FXML public  Pane                       p_highBloodPressure;
    @FXML public  Pane                       p_veryHighBloodPressure;
    public        LineChart<Integer, Double> lc_bmiChart;
    public        LineChart<Integer, Double> lc_bloodPressureChart;
    private       HashMap<String, String>    choiceBoxProperties;
    private       ChartDrawer[]              setOfSeriesBmi;
    private       ChartDrawer[]              setOfSeriesBloodPressure;
    private       Object                     parent;


    public CParameterCalibrator()
    {
        this(null, null);
    }

    public CParameterCalibrator(Object parent, Session session)
    {
        this.parent = parent;
        this.session = session;
        this.choiceBoxProperties = new HashMap<>(3);
        this.setOfSeriesBmi = new ChartDrawer[4];
        this.setOfSeriesBloodPressure = new ChartDrawer[4];
        for(int i = -1, is = this.setOfSeriesBmi.length; ++i < is; )
        {
            this.setOfSeriesBmi[i] = new ChartDrawer("Series" + (i + 1));
            this.setOfSeriesBloodPressure[i] = new ChartDrawer("Series" + (i + 1));
        }
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        this.choiceBoxProperties.put("", "");
        this.choiceBoxProperties.put("Triangular Fuzzy Number", "Triangular");
        this.choiceBoxProperties.put("Trapezoidal Fuzzy Number", "Trapezoidal");
        this.choiceBoxProperties.put("Bell-Shaped Fuzzy Number", "Bell");
        this.initializeChoiceBox(this.cb_lowBmi, this.choiceBoxProperties.keySet());
        this.initializeChoiceBox(this.cb_normalBmi, this.choiceBoxProperties.keySet());
        this.initializeChoiceBox(this.cb_highBmi, this.choiceBoxProperties.keySet());
        this.initializeChoiceBox(this.cb_veryHighBmi, this.choiceBoxProperties.keySet());
        this.initializeChoiceBox(this.cb_lowBloodPressure, this.choiceBoxProperties.keySet());
        this.initializeChoiceBox(this.cb_normalBloodPressure, this.choiceBoxProperties.keySet());
        this.initializeChoiceBox(this.cb_highBloodPressure, this.choiceBoxProperties.keySet());
        this.initializeChoiceBox(this.cb_veryHighBloodPressure, this.choiceBoxProperties.keySet());

        this.cb_lowBmi.valueProperty().addListener((observable, oldValue, newValue) -> {
            CParameterCalibrator.this.changeParameterInterface(newValue, CParameterCalibrator.this.setOfSeriesBmi[0], CParameterCalibrator.this.p_lowBmi);
        });
        this.cb_normalBmi.valueProperty().addListener((observable, oldValue, newValue) -> {
            CParameterCalibrator.this.changeParameterInterface(newValue, CParameterCalibrator.this.setOfSeriesBmi[1], CParameterCalibrator.this.p_normalBmi);
        });
        this.cb_highBmi.valueProperty().addListener((observable, oldValue, newValue) -> {
            CParameterCalibrator.this.changeParameterInterface(newValue, CParameterCalibrator.this.setOfSeriesBmi[2], CParameterCalibrator.this.p_highBmi);
        });
        this.cb_veryHighBmi.valueProperty().addListener((observable, oldValue, newValue) -> {
            CParameterCalibrator.this.changeParameterInterface(newValue, CParameterCalibrator.this.setOfSeriesBmi[3], CParameterCalibrator.this.p_veryHighBmi);
        });

        this.cb_lowBloodPressure.valueProperty().addListener((observable, oldValue, newValue) -> {
            CParameterCalibrator.this.changeParameterInterface(newValue, CParameterCalibrator.this.setOfSeriesBloodPressure[0], CParameterCalibrator.this.p_lowBloodPressure);
        });
        this.cb_normalBloodPressure.valueProperty().addListener((observable, oldValue, newValue) -> {
            CParameterCalibrator.this.changeParameterInterface(newValue, CParameterCalibrator.this.setOfSeriesBloodPressure[1], CParameterCalibrator.this.p_normalBloodPressure);
        });
        this.cb_highBloodPressure.valueProperty().addListener((observable, oldValue, newValue) -> {
            CParameterCalibrator.this.changeParameterInterface(newValue, CParameterCalibrator.this.setOfSeriesBloodPressure[2], CParameterCalibrator.this.p_highBloodPressure);
        });
        this.cb_veryHighBloodPressure.valueProperty().addListener((observable, oldValue, newValue) -> {
            CParameterCalibrator.this.changeParameterInterface(newValue, CParameterCalibrator.this.setOfSeriesBloodPressure[3], CParameterCalibrator.this.p_veryHighBloodPressure);
        });

        ObservableList<LineChart.Series<Integer, Double>> linechartDataBmi           = FXCollections.observableArrayList();
        ObservableList<LineChart.Series<Integer, Double>> linechartDataBloodPressure = FXCollections.observableArrayList();
        for(int i = -1, is = this.setOfSeriesBmi.length; ++i < is; )
        {
            linechartDataBmi.add(this.setOfSeriesBmi[i].series);
            linechartDataBloodPressure.add(this.setOfSeriesBloodPressure[i].series);
        }

        this.lc_bmiChart.setData(linechartDataBmi);
        this.lc_bmiChart.setAnimated(false);
        this.lc_bmiChart.setTitle("BMI (Body Mass Index)");
        this.lc_bmiChart.setHorizontalGridLinesVisible(true);
        this.lc_bmiChart.setCreateSymbols(false);

        this.lc_bloodPressureChart.setData(linechartDataBloodPressure);
        this.lc_bloodPressureChart.setAnimated(false);
        this.lc_bloodPressureChart.setTitle("Blood Pressure");
        this.lc_bloodPressureChart.setHorizontalGridLinesVisible(true);
        this.lc_bloodPressureChart.setCreateSymbols(false);
    }

    private void changeParameterInterface(String functionType, ChartDrawer chartDrawer, Pane container)
    {
        if(functionType.trim().isEmpty())
        {
            return;
        }
        try
        {
            FXMLLoader fuzzyNumberInterface = new FXMLLoader(Paths.get(IParameterCalibrator.class.getResource("").getFile() + "fuzzynumber" + "/V" + this.choiceBoxProperties.get(functionType) + "Configurator.fxml").toUri().toURL());
            switch(this.choiceBoxProperties.get(functionType))
            {
                case "Triangular":
                {
                    fuzzyNumberInterface.setController(new CTriangularConfigurator(chartDrawer));
                }
                break;
                case "Trapezoidal":
                {
                    fuzzyNumberInterface.setController(new CTrapezoidalConfigurator(chartDrawer));
                }
                break;
                case "Bell":
                {
                    fuzzyNumberInterface.setController(new CBellConfigurator(chartDrawer));
                }
                break;
                default:
                {
                    return;
                }
            }
            try
            {
                container.getChildren().removeAll(container.getChildren());
                container.getChildren().addAll((GridPane) fuzzyNumberInterface.load());
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

    private void initializeChoiceBox(final ChoiceBox<String> choiceBox, Collection<String> choices)
    {
        choiceBox.getItems().removeAll(choiceBox.getItems());
        choiceBox.getItems().addAll(choices);
        choiceBox.getSelectionModel().select(0);
    }

    public void saveFunction(ActionEvent actionEvent)
    {
        BMI          bmi          = new BMI();
        TekananDarah tekananDarah = new TekananDarah();
        Stroke       stroke       = new Stroke(bmi, tekananDarah);
        try
        {
            bmi.setBMIRendah(FuzzyNumberFactory.produce(this.setOfSeriesBmi[0].type, this.setOfSeriesBmi[0].property));
            bmi.setBMINormal(FuzzyNumberFactory.produce(this.setOfSeriesBmi[1].type, this.setOfSeriesBmi[1].property));
            bmi.setBMIOverweight(FuzzyNumberFactory.produce(this.setOfSeriesBmi[2].type, this.setOfSeriesBmi[2].property));
            bmi.setBMIObesity(FuzzyNumberFactory.produce(this.setOfSeriesBmi[3].type, this.setOfSeriesBmi[3].property));
            tekananDarah.setTekananDarahRendah(FuzzyNumberFactory.produce(this.setOfSeriesBloodPressure[0].type, this.setOfSeriesBloodPressure[0].property));
            tekananDarah.setTekananDarahNormal(FuzzyNumberFactory.produce(this.setOfSeriesBloodPressure[1].type, this.setOfSeriesBloodPressure[1].property));
            tekananDarah.setTekananDarahTinggi(FuzzyNumberFactory.produce(this.setOfSeriesBloodPressure[2].type, this.setOfSeriesBloodPressure[2].property));
            tekananDarah.setTekananDarahSangatTinggi(FuzzyNumberFactory.produce(this.setOfSeriesBloodPressure[3].type, this.setOfSeriesBloodPressure[3].property));
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        DBProperties properties = DBProperties.getInstance();
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(baos))
        {
            out.writeObject(stroke);
            out.close();
            baos.close();
            properties.preparedStatement = properties.connection.prepareStatement("UPDATE `user` SET `strokeDisease` = ? WHERE `user`.`id` = ?");
            properties.preparedStatement.setBytes(1, baos.toByteArray());
            properties.preparedStatement.setInt(2, this.session.user.userID);
            properties.preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success Update Function");
            alert.setHeaderText("Look, an Success");
            alert.setContentText("Success Update Function to User");
            alert.showAndWait();

            this.session.disease = stroke;
            ((CMainPage) this.parent).root.setCenter(null);
            ((CMainPage) this.parent).generateCalculatorResolver();
        }
        catch(SQLException ignored)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Update Function");
            alert.setHeaderText("Look, an Error");
            alert.setContentText("Error Update Function to User");
            alert.showAndWait();
        }
        catch(IOException ignored)
        {
            ignored.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Update data Function");
            alert.setHeaderText("Look, an Error");
            alert.setContentText("Error Update data Function to User");
            alert.showAndWait();
        }
    }

    public class ChartDrawer
    {
        public FuzzyNumberProperty property;
        public String              type;
        LineChart.Series<Integer, Double> series;
        FuzzyNumber                       seriesFunction;

        public ChartDrawer()
        {
            this.series = new XYChart.Series<>();
        }

        public ChartDrawer(String name)
        {
            this();
            this.series.setName(name);
        }

        public void setSeriesFunction(String functionType, FuzzyNumberProperty property)
        {
            try
            {
                this.seriesFunction = FuzzyNumberFactory.produce(functionType, property);
            }
            catch(ClassNotFoundException ignored)
            {
            }
        }

        public void resetSeries()
        {
            this.series.getData().removeAll(this.series.getData());
        }

        public void draw(Range boundary)
        {
            final ObservableList<LineChart.Data<Integer, Double>> data = this.series.getData();
            for(int i = boundary.lowerBound - 1, is = boundary.upperBound; ++i <= is; )
            {
                data.add(new LineChart.Data<>(i, this.seriesFunction.getFuzzy_Y(i)));
            }
        }
    }
}
