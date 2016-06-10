package model.fuzzy.fuzzy_number;

/**
 * This <PPP_1> project in package <model.fuzzy.fuzzy_number> created by :
 * Name         : syafiq
 * Date / Time  : 07 June 2016, 10:53 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class FuzzyNumberFactory
{
    public static FuzzyNumber produce(String fuzzyNumberType, FuzzyNumberProperty property) throws ClassNotFoundException
    {
        switch(fuzzyNumberType)
        {
            case "Triangular":
            {
                return new TriangularFuzzyNumber(property);
            }
            case "Trapezoidal":
            {
                return new TrapezoidalFuzzyNumber(property);
            }
            case "Bell":
            {
                return new BellShapedFuzzyNumber(property);
            }
            default:
            {
                throw new ClassNotFoundException("No Class Matching " + fuzzyNumberType + " Type");
            }
        }
    }
}
