package dump.dump;

/**
 * This <PPP_1> project in package <dump.dump> created by :
 * Name         : syafiq
 * Date / Time  : 06 June 2016, 3:57 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class TestRootPath
{
    public static void main(String[] args)
    {
        System.out.println(TestRootPath.class.getProtectionDomain().getCodeSource().getLocation().getFile());
    }
}
