package model.fuzzy.fuzzy_number;

import java.io.Serializable;

/**
 * This <PPP_1> project in package <model> created by :
 * Name         : syafiq
 * Date / Time  : 30 May 2016, 8:59 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public abstract class FuzzyNumber implements Serializable
{
    public abstract double getFuzzy_X(final double value_y);

    public abstract double getFuzzy_Y(final double value_x);
}
