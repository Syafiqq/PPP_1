package controller.stroke.fuzzynumber;

import controller.stroke.CParameterCalibrator;
import javafx.event.ActionEvent;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import model.fuzzy.fuzzy_number.FuzzyNumberProperty;
import model.helper.Range;

/**
 * This <PPP_1> project in package <controller.stroke.fuzzynumber> created by :
 * Name         : syafiq
 * Date / Time  : 07 June 2016, 7:50 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public abstract class FuzzyNumberConfigurator
{
    protected FuzzyNumberProperty              property;
    protected Range                            boundary;
    protected StringConverter<Double>          stringToDouble;
    protected CParameterCalibrator.ChartDrawer drawer;
    private   String                           type;

    public FuzzyNumberConfigurator(CParameterCalibrator.ChartDrawer chartDrawer, String type)
    {
        this.drawer = chartDrawer;
        this.boundary = new Range(0, 0);
        this.stringToDouble = new DoubleStringConverter();
        this.type = type;
    }


    public void convertAndSet(String oldValue, String newValue, int index)
    {
        double val;
        try
        {
            val = this.stringToDouble.fromString(newValue);
        }
        catch(NumberFormatException ignored)
        {
            val = this.stringToDouble.fromString(oldValue);
        }
        this.property.properties.set(index, val);
        this.calculateRange();
    }

    public abstract void calculateRange();

    public void calibrateChart(ActionEvent actionEvent)
    {
        this.drawer.setSeriesFunction(this.type, this.property);
        this.drawer.resetSeries();
        this.drawer.draw(this.boundary);
    }
}
