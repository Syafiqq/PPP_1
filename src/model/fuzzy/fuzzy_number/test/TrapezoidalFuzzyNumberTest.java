package model.fuzzy.fuzzy_number.test;

import model.fuzzy.fuzzy_number.FuzzyNumber;
import model.fuzzy.fuzzy_number.TrapezoidalFuzzyNumber;
import org.junit.Test;

/**
 * This <PPP_1> project in package <model.test> created by :
 * Name         : syafiq
 * Date / Time  : 31 May 2016, 3:40 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class TrapezoidalFuzzyNumberTest
{
    @Test public void printTrapezoidal()
    {
        TrapezoidalFuzzyNumber trapezoidal = new TrapezoidalFuzzyNumber(0.0, 2.0, 4.0, 6.0);
        System.out.printf("%s\n", trapezoidal);
    }

    @Test public void TestYgivenX()
    {
        FuzzyNumber trapezoidal = new TrapezoidalFuzzyNumber(0.0, 2.0, 4.0, 6.0);
        System.out.printf("%s\n", trapezoidal);
        System.out.printf("if x : %g\n\tso y : %g\n", -0.5, trapezoidal.getFuzzy_Y(-0.5));
        System.out.printf("if x : %g\n\tso y : %g\n", 1.0, trapezoidal.getFuzzy_Y(1.0));
        System.out.printf("if x : %g\n\tso y : %g\n", 2.5, trapezoidal.getFuzzy_Y(2.5));
        System.out.printf("if x : %g\n\tso y : %g\n", 5.0, trapezoidal.getFuzzy_Y(5.0));
        System.out.printf("if x : %g\n\tso y : %g\n", 7.0, trapezoidal.getFuzzy_Y(7.0));
    }

    @Test public void TextXgivenY()
    {
        FuzzyNumber trapezoidal = new TrapezoidalFuzzyNumber(0.0, 2.0, 4.0, 6.0);
        System.out.printf("%s\n", trapezoidal);
        System.out.printf("if y : %g\n\tso x : %g\n", 0.0, trapezoidal.getFuzzy_X(0.0));
        System.out.printf("if y : %g\n\tso x : %g\n", 0.25, trapezoidal.getFuzzy_X(0.25));
        System.out.printf("if y : %g\n\tso x : %g\n", 0.5, trapezoidal.getFuzzy_X(0.5));
        System.out.printf("if y : %g\n\tso x : %g\n", 0.75, trapezoidal.getFuzzy_X(0.75));
        System.out.printf("if y : %g\n\tso x : %g\n", 1.0, trapezoidal.getFuzzy_X(1.0));
    }
}