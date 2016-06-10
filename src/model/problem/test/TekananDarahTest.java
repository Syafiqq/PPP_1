package model.problem.test;

import model.fuzzy.fuzzy_number.TriangularFuzzyNumber;
import model.problem.TekananDarah;
import org.junit.Test;

/**
 * This <PPP_1> project in package <model.problem.test> created by :
 * Name         : syafiq
 * Date / Time  : 01 June 2016, 12:19 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class TekananDarahTest
{
    @Test public void testTekananDarah()
    {
        TriangularFuzzyNumber rendah       = new TriangularFuzzyNumber(0, 20, 30);
        TriangularFuzzyNumber normal       = new TriangularFuzzyNumber(20, 35, 50);
        TriangularFuzzyNumber tinggi       = new TriangularFuzzyNumber(40, 50, 60);
        TriangularFuzzyNumber sangatTinggi = new TriangularFuzzyNumber(50, 70, 100);

        TekananDarah tekananDarah = new TekananDarah(100);
        tekananDarah.setTekananDarahRendah(rendah);
        tekananDarah.setTekananDarahNormal(normal);
        tekananDarah.setTekananDarahTinggi(tinggi);
        tekananDarah.setTekananDarahSangatTinggi(sangatTinggi);
    }
}