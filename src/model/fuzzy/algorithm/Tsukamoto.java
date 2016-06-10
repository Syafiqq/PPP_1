package model.fuzzy.algorithm;

import java.io.Serializable;
import java.util.LinkedList;
import model.fuzzy.fuzzy_inference.FuzzyInference;

/**
 * This <PPP_1> project in package <model.fuzzy.algorithm> created by :
 * Name         : syafiq
 * Date / Time  : 31 May 2016, 7:10 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public abstract class Tsukamoto implements FuzzyInference, Serializable
{
    protected LinkedList<Double> z, alpha;
    protected double result;

    public double getResult()
    {
        return this.result;
    }
}
