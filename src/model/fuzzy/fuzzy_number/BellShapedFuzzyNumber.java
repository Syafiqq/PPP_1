package model.fuzzy.fuzzy_number;

import java.io.Serializable;
import java.util.ListIterator;

/**
 * This <PPP_1> project in package <model> created by :
 * Name         : syafiq
 * Date / Time  : 31 May 2016, 3:52 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class BellShapedFuzzyNumber extends FuzzyNumber implements Serializable
{
    private final double EXP;
    private       double mean;
    /**
     * Mean
     */
    private       double standard_deviation;

    public BellShapedFuzzyNumber(final double mean, final double standard_deviation)
    {
        this(1.0, mean, standard_deviation);
    }

    public BellShapedFuzzyNumber(final double exp, final double mean, final double standard_deviation)
    {
        this.EXP = Math.exp(exp);
        this.setProperty(mean, standard_deviation);
    }

    public BellShapedFuzzyNumber(FuzzyNumberProperty property)
    {
        this.EXP = Math.exp(1);
        ListIterator<Double> iterator = property.properties.listIterator();
        this.setProperty(iterator.next(), iterator.next());
    }

    private void setProperty(double mean, double standard_deviation)
    {
        this.mean = mean;
        this.standard_deviation = standard_deviation;
    }

    @Override public double getFuzzy_X(final double value_y)
    {
        return Math.sqrt(-2 * Math.log(value_y) * Math.pow(this.standard_deviation, 2.0)) + this.mean;
    }

    @Override public double getFuzzy_Y(final double value_x)
    {
        return Math.pow(this.EXP, (-1 * Math.pow(value_x - this.mean, 2)) / (2 * Math.pow(this.standard_deviation, 2)));
    }

    @Override public String toString()
    {
        StringBuilder sb   = new StringBuilder();
        int           size = 6;
        for(int i = -1; ++i < size; )
        {
            for(int a = size - i; --a >= 0; )
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
            for(int a = size - i; --a >= 0; )
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
        sb.append(String.format("%-" + placement + ".2g ", -this.standard_deviation));
        sb.append(String.format("%-" + placement + ".2g ", this.mean));
        sb.append(String.format("%-" + placement + ".2g", this.standard_deviation));
        return sb.toString();
    }
}
