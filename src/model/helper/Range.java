package model.helper;

/**
 * This <PPP_1> project in package <model.helper> created by :
 * Name         : syafiq
 * Date / Time  : 07 June 2016, 2:39 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class Range
{
    public int upperBound;
    public int lowerBound;

    public Range(int upperBound, int lowerBound)
    {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    public void updateIfSufficient(Range boundary)
    {
        if(boundary.lowerBound < this.lowerBound)
        {
            this.lowerBound = boundary.lowerBound;
        }
        if(boundary.upperBound > this.upperBound)
        {
            this.upperBound = boundary.upperBound;
        }
    }

    @Override public String toString()
    {
        return String.format("[%3d, %3d]", this.lowerBound, this.upperBound);
    }
}
