package model.fuzzy.fuzzy_number.test;

import model.fuzzy.fuzzy_number.TriangularFuzzyNumber;
import org.junit.Test;

/**
 * This <PPP_1> project in package <model.test> created by :
 * Name         : syafiq
 * Date / Time  : 30 May 2016, 9:36 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class TriangularFuzzyNumberTest
{
    @Test public void testGetYGivenX()
    {
        TriangularFuzzyNumber triangularFuzzyNumber = new TriangularFuzzyNumber(0, 2, 4);
        System.out.printf("%s\nif x : %g\n\tso y : %g\n", triangularFuzzyNumber.toString(), 1.5, triangularFuzzyNumber.getFuzzy_Y(1.5));
        System.out.printf("if x : %g\n\tso y : %g\n", -0.5, triangularFuzzyNumber.getFuzzy_Y(-0.5));
        System.out.printf("if x : %g\n\tso y : %g\n", 5.0, triangularFuzzyNumber.getFuzzy_Y(5.0));
    }

    @Test public void TestGetXGivenY()
    {
        TriangularFuzzyNumber triangularFuzzyNumber = new TriangularFuzzyNumber(0, 2, 4);
        System.out.printf("%s\n", triangularFuzzyNumber);
        System.out.printf("if y : %g\n\tso x : %g\n", 1.0, triangularFuzzyNumber.getFuzzy_X(1.0));
        System.out.printf("if y : %g\n\tso x : %g\n", 0.5, triangularFuzzyNumber.getFuzzy_X(0.5));
        System.out.printf("if y : %g\n\tso x : %g\n", 0.25, triangularFuzzyNumber.getFuzzy_X(0.25));
    }
}