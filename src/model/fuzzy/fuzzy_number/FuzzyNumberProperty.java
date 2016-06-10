package model.fuzzy.fuzzy_number;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This <PPP_1> project in package <model.fuzzy.fuzzy_number> created by :
 * Name         : syafiq
 * Date / Time  : 07 June 2016, 10:58 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class FuzzyNumberProperty
{
    public ArrayList<Double> properties;

    public FuzzyNumberProperty(Double... properties)
    {
        this.properties = new ArrayList<>(properties.length);
        this.properties.addAll(Arrays.asList(properties));
    }

    @Override public String toString()
    {
        return "FuzzyNumberProperty{" +
                "properties=" + properties +
                '}';
    }
}
