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
 * Date / Time  : 07 June 2016, 1:16 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class CBellConfigurator extends FuzzyNumberConfigurator implements Initializable
{
    @FXML public TextField tf_mean;
    @FXML public TextField tf_standardDeviation;

    public CBellConfigurator()
    {
        this(null);
    }

    public CBellConfigurator(CParameterCalibrator.ChartDrawer chartDrawer)
    {
        super(chartDrawer, chartDrawer.type = "Bell");
        this.property = chartDrawer.property = new FuzzyNumberProperty(0.0, 1.0);
    }


    @Override public void initialize(URL location, ResourceBundle resources)
    {
        this.tf_mean.textProperty().addListener((observable, oldValue, newValue) -> {
            CBellConfigurator.super.convertAndSet(oldValue, newValue, 0);
        });
        this.tf_standardDeviation.textProperty().addListener((observable, oldValue, newValue) -> {
            CBellConfigurator.super.convertAndSet(oldValue, newValue, 1);
        });
    }

    @Override public void calculateRange()
    {
        int multiplier = 3 * (int) Math.ceil(super.property.properties.get(super.property.properties.size() - 1));
        super.boundary.lowerBound = (int) Math.floor(super.property.properties.get(0)) - multiplier;
        super.boundary.upperBound = (int) Math.floor(super.property.properties.get(0)) + multiplier;
    }
}
