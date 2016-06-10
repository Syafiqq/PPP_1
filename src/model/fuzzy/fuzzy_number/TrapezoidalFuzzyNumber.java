package model.fuzzy.fuzzy_number;

import java.io.Serializable;
import java.util.ListIterator;

/**
 * This <PPP_1> project in package <model> created by :
 * Name         : syafiq
 * Date / Time  : 30 May 2016, 10:16 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class TrapezoidalFuzzyNumber extends FuzzyNumber implements Serializable
{
    private double leftPoint;
    private double upperLeftPoint;
    private double upperRightPoint;
    private double rightPoint;

    public TrapezoidalFuzzyNumber(final double leftPoint, final double upperLeftPoint, final double upperRightPoint, final double rightPoint)
    {
        this.setProperty(leftPoint, upperLeftPoint, upperRightPoint, rightPoint);
    }

    public TrapezoidalFuzzyNumber(FuzzyNumberProperty property)
    {
        ListIterator<Double> iterator = property.properties.listIterator();
        this.setProperty(iterator.next(), iterator.next(), iterator.next(), iterator.next());
    }

    private void setProperty(double leftPoint, double upperLeftPoint, double upperRightPoint, double rightPoint)
    {
        this.leftPoint = leftPoint;
        this.upperLeftPoint = upperLeftPoint;
        this.upperRightPoint = upperRightPoint;
        this.rightPoint = rightPoint;
    }

    @Override public double getFuzzy_X(final double value_y)
    {
        return (value_y * this.upperLeftPoint) - (value_y * this.leftPoint) + this.leftPoint;
    }

    @Override public double getFuzzy_Y(final double value_x)
    {
        double result;
        if((value_x >= this.upperLeftPoint) && (value_x <= this.upperRightPoint))
        {
            result = 1.0;
        }
        else if((value_x > this.leftPoint) && (value_x < this.upperLeftPoint))
        {
            result = ((value_x - this.leftPoint) / (this.upperLeftPoint - this.leftPoint));
        }
        else if((value_x > this.upperRightPoint) && (value_x < this.rightPoint))
        {
            result = ((this.rightPoint - value_x) / (this.rightPoint - this.upperRightPoint));
        }
        else
        {
            result = 0.0;
        }
        return result;
    }

    @Override public String toString()
    {
        StringBuilder sb   = new StringBuilder();
        int           size = 6;
        for(int i = -1, is = size; ++i < is; )
        {
            for(int a = is - i; --a >= 0; )
            {
                sb.append(' ');
            }
            sb.append('/');
            for(int a = i; --a >= 0; )
            {
                sb.append(' ');
            }
            for(int a = is; --a >= 0; )
            {
                sb.append(i == 0 ? '-' : ' ');
            }
            for(int a = i; --a >= 0; )
            {
                sb.append(' ');
            }
            sb.append('\\');
            for(int a = is - i; --a >= 0; )
            {
                sb.append(' ');
            }
            sb.append('\n');
        }

        for(int i = -1, is = size * 3 + 2; ++i < is; )
        {
            sb.append('-');
        }
        sb.append('\n');
        int placement = (int) Math.ceil((size * 3) / 4);
        sb.append(String.format("%-" + placement + ".2g ", this.leftPoint));
        sb.append(String.format("%-" + placement + ".2g ", this.upperLeftPoint));
        sb.append(String.format("%-" + placement + ".2g ", this.upperRightPoint));
        sb.append(String.format("%-" + placement + ".2g", this.rightPoint));
        return sb.toString();
    }
}
