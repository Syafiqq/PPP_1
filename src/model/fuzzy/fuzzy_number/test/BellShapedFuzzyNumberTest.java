package model.fuzzy.fuzzy_number.test;

import model.fuzzy.fuzzy_number.BellShapedFuzzyNumber;
import org.junit.Test;

/**
 * This <PPP_1> project in package <model.fuzzynumber.test> created by :
 * Name         : syafiq
 * Date / Time  : 31 May 2016, 5:49 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class BellShapedFuzzyNumberTest
{
    @Test public void printBellShaped()
    {
        BellShapedFuzzyNumber bell_shaped = new BellShapedFuzzyNumber(0.0, 1.0);
        System.out.printf("%s\n", bell_shaped);
    }

    @Test public void TestYgivenX()
    {
        BellShapedFuzzyNumber bell_shaped = new BellShapedFuzzyNumber(0.0, 1.0);
        System.out.printf("%s\n", bell_shaped);
        System.out.printf("if x : %g\n\tso y : %g\n", -0.5, bell_shaped.getFuzzy_Y(-0.5));
        System.out.printf("if x : %g\n\tso y : %g\n", 1.0, bell_shaped.getFuzzy_Y(1.0));
        System.out.printf("if x : %g\n\tso y : %g\n", 2.5, bell_shaped.getFuzzy_Y(2.5));
        System.out.printf("if x : %g\n\tso y : %g\n", 5.0, bell_shaped.getFuzzy_Y(5.0));
        System.out.printf("if x : %g\n\tso y : %g\n", 7.0, bell_shaped.getFuzzy_Y(7.0));
    }

    @Test public void TextXgivenY()
    {
        BellShapedFuzzyNumber bell_shaped = new BellShapedFuzzyNumber(0.0, 1.0);
        System.out.printf("%s\n", bell_shaped);
        System.out.printf("if y : %g\n\tso x : %g\n", 0.0, bell_shaped.getFuzzy_X(0.0));
        System.out.printf("if y : %g\n\tso x : %g\n", 0.25, bell_shaped.getFuzzy_X(0.25));
        System.out.printf("if y : %g\n\tso x : %g\n", 0.5, bell_shaped.getFuzzy_X(0.5));
        System.out.printf("if y : %g\n\tso x : %g\n", 0.75, bell_shaped.getFuzzy_X(0.75));
        System.out.printf("if y : %g\n\tso x : %g\n", 1.0, bell_shaped.getFuzzy_X(1.0));
    }
}