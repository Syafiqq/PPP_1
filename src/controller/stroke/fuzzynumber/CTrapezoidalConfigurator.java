package controller.stroke.fuzzynumber;

import controller.stroke.CParameterCalibrator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.fuzzy.fuzzy_number.FuzzyNumberProperty;

/**
 * This <PPP_1> project in package <controller.stroke.fuzzynumber> created by :
 * Name         : syafiq
 * Date / Time  : 07 June 2016, 12:58 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class CTrapezoidalConfigurator extends FuzzyNumberConfigurator implements Initializable
{
    @FXML public TextField tf_leftPoint;
    @FXML public TextField tf_upperLeftPoint;
    @FXML public TextField tf_upperRightPoint;
    @FXML public TextField tf_rightPoint;

    public CTrapezoidalConfigurator()
    {
        this(null);
    }

    public CTrapezoidalConfigurator(CParameterCalibrator.ChartDrawer chartDrawer)
    {
        super(chartDrawer, chartDrawer.type = "Trapezoidal");
        super.property = chartDrawer.property = new FuzzyNumberProperty(0.0, 0.0, 0.0, 0.0);
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        this.tf_leftPoint.textProperty().addListener((observable, oldValue, newValue) -> {
            CTrapezoidalConfigurator.super.convertAndSet(oldValue, newValue, 0);
        });
        this.tf_upperLeftPoint.textProperty().addListener((observable, oldValue, newValue) -> {
            CTrapezoidalConfigurator.super.convertAndSet(oldValue, newValue, 1);
        });
        this.tf_upperRightPoint.textProperty().addListener((observable, oldValue, newValue) -> {
            CTrapezoidalConfigurator.super.convertAndSet(oldValue, newValue, 2);
        });
        this.tf_rightPoint.textProperty().addListener((observable, oldValue, newValue) -> {
            CTrapezoidalConfigurator.super.convertAndSet(oldValue, newValue, 3);
        });
    }

    @Override public void calculateRange()
    {
        super.boundary.lowerBound = (int) Math.floor(super.property.properties.get(0));
        super.boundary.upperBound = (int) Math.ceil(super.property.properties.get(super.property.properties.size() - 1));
    }
}
