package model.fuzzy.fuzzy_number;

import java.io.Serializable;
import java.util.ListIterator;

/**
 * This <PPP_1> project in package <model> created by :
 * Name         : syafiq
 * Date / Time  : 30 May 2016, 9:00 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class TriangularFuzzyNumber extends FuzzyNumber implements Serializable
{
    private double leftPoint;
    private double middlePoint;
    private double rightPoint;

    public TriangularFuzzyNumber(final double leftPoint, final double middlePoint, final double rightPoint)
    {
        this.setProperty(leftPoint, middlePoint, rightPoint);
    }

    public TriangularFuzzyNumber(FuzzyNumberProperty properties)
    {
        ListIterator<Double> iterator = properties.properties.listIterator();
        this.setProperty(iterator.next(), iterator.next(), iterator.next());
    }

    private void setProperty(double leftPoint, double middlePoint, double rightPoint)
    {
        this.leftPoint = leftPoint;
        this.middlePoint = middlePoint;
        this.rightPoint = rightPoint;
    }

    @Override public double getFuzzy_X(final double value_y)
    {
        return (value_y * this.middlePoint) - (value_y * this.leftPoint) + this.leftPoint;
    }

    @Override public double getFuzzy_Y(final double value_x)
    {
        double result;
        if(value_x == this.middlePoint)
        {
            result = 1.0;
        }
        else if((value_x > this.leftPoint) && (value_x < this.middlePoint))
        {
            result = ((value_x - this.leftPoint) / (this.middlePoint - this.leftPoint));
        }
        else if((value_x > this.middlePoint) && (value_x < this.rightPoint))
        {
            result = ((this.rightPoint - value_x) / (this.rightPoint - this.middlePoint));
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

        for(int i = -1, is = size * 2 + 2; ++i < is; )
        {
            sb.append('-');
        }
        sb.append('\n');
        int placement = (int) Math.ceil((size * 2) / 3);
        sb.append(String.format("%-" + placement + ".2g ", this.leftPoint));
        sb.append(String.format("%-" + placement + ".2g ", this.middlePoint));
        sb.append(String.format("%-" + placement + ".2g", this.rightPoint));
        return sb.toString();
    }
}
