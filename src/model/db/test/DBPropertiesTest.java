package model.db.test;

import java.sql.SQLException;
import model.db.DBProperties;
import org.junit.Test;

/**
 * This <PPP_1> project in package <model.db.test> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 7:14 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class DBPropertiesTest
{
    @Test public void testPath()
    {
        DBProperties.getInstance().dbName = "sample";
        try
        {
            DBProperties.getInstance().connect();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            DBProperties.getInstance().disconnect();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}