package model.fuzzy.algorithm;

import java.io.Serializable;
import java.util.LinkedList;
import model.fuzzy.fuzzy_inference.FuzzyInference;

/**
 * This <PPP_1> project in package <model.fuzzy.algorithm> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 8:55 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public abstract class Sugeno implements FuzzyInference, Serializable
{
    protected LinkedList<Double> z, alpha;
    protected double result;

    public double getResult()
    {
        return this.result;
    }
}
