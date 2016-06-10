package dump.lineChart.test;

import dump.lineChart.BellShaped_Curve;
import org.junit.Test;

/**
 * This <PPP_1> project in package <dump.lineChart.test> created by :
 * Name         : syafiq
 * Date / Time  : 19 May 2016, 9:04 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class BellShaped_CurveTest
{
    @Test public void Test_001()
    {
        BellShaped_Curve curve = new BellShaped_Curve(20.0, 5.0);
        for(int i = -1, is = 40; ++i < is; )
        {
            System.out.println(curve.getY(i));
        }
    }
}