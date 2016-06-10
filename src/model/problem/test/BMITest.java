package model.problem.test;

import model.fuzzy.fuzzy_number.TrapezoidalFuzzyNumber;
import model.fuzzy.fuzzy_number.TriangularFuzzyNumber;
import model.problem.BMI;
import org.junit.Test;

/**
 * This <PPP_1> project in package <model.problem.test> created by :
 * Name         : syafiq
 * Date / Time  : 01 June 2016, 11:41 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class BMITest
{
    @Test public void testCalculateBMI()
    {
        TriangularFuzzyNumber  rendah       = new TriangularFuzzyNumber(0, 20, 30);
        TriangularFuzzyNumber  normal       = new TriangularFuzzyNumber(20, 35, 50);
        TriangularFuzzyNumber  tinggi       = new TriangularFuzzyNumber(40, 50, 60);
        TrapezoidalFuzzyNumber sangatTinggi = new TrapezoidalFuzzyNumber(50, 60, 70, 100);

        BMI bmi = new BMI(60, 1.7);
        bmi.setBMIRendah(rendah);
        bmi.setBMINormal(normal);
        bmi.setBMIOverweight(tinggi);
        bmi.setBMIObesity(sangatTinggi);

        System.out.println(bmi.getBmi());
    }
}