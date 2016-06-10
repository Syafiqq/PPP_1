package model.problem.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import model.db.DBProperties;
import model.fuzzy.fuzzy_number.TriangularFuzzyNumber;
import model.problem.BMI;
import model.problem.Stroke;
import model.problem.TekananDarah;
import org.junit.Test;

/**
 * This <PPP_1> project in package <model.problem.test> created by :
 * Name         : syafiq
 * Date / Time  : 01 June 2016, 12:21 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class StrokeTest
{
    @Test public void testStroke()
    {
        TriangularFuzzyNumber tDrendah       = new TriangularFuzzyNumber(0, 20, 30);
        TriangularFuzzyNumber tDnormal       = new TriangularFuzzyNumber(20, 35, 50);
        TriangularFuzzyNumber tDtinggi       = new TriangularFuzzyNumber(40, 50, 60);
        TriangularFuzzyNumber tDsangatTinggi = new TriangularFuzzyNumber(50, 70, 100);

        TekananDarah tekananDarah = new TekananDarah(25);
        tekananDarah.setTekananDarahRendah(tDrendah);
        tekananDarah.setTekananDarahNormal(tDnormal);
        tekananDarah.setTekananDarahTinggi(tDtinggi);
        tekananDarah.setTekananDarahSangatTinggi(tDsangatTinggi);

        TriangularFuzzyNumber rendah       = new TriangularFuzzyNumber(0, 60, 80);
        TriangularFuzzyNumber normal       = new TriangularFuzzyNumber(20, 35, 50);
        TriangularFuzzyNumber tinggi       = new TriangularFuzzyNumber(40, 50, 60);
        TriangularFuzzyNumber sangatTinggi = new TriangularFuzzyNumber(50, 70, Integer.MAX_VALUE - 1);

        BMI bmi = new BMI(60, 1.7);
        bmi.setBMIRendah(rendah);
        bmi.setBMINormal(normal);
        bmi.setBMIOverweight(tinggi);
        bmi.setBMIObesity(sangatTinggi);

        Stroke stroke = new Stroke(bmi, tekananDarah);

        stroke.execute();
        System.out.println(stroke.getResult());

    }


    @Test public void testStrokeWithWriteSerialization()
    {
        TriangularFuzzyNumber tDrendah       = new TriangularFuzzyNumber(0, 20, 30);
        TriangularFuzzyNumber tDnormal       = new TriangularFuzzyNumber(20, 35, 50);
        TriangularFuzzyNumber tDtinggi       = new TriangularFuzzyNumber(40, 50, 60);
        TriangularFuzzyNumber tDsangatTinggi = new TriangularFuzzyNumber(50, 70, 100);

        TekananDarah tekananDarah = new TekananDarah(25);
        tekananDarah.setTekananDarahRendah(tDrendah);
        tekananDarah.setTekananDarahNormal(tDnormal);
        tekananDarah.setTekananDarahTinggi(tDtinggi);
        tekananDarah.setTekananDarahSangatTinggi(tDsangatTinggi);

        TriangularFuzzyNumber rendah       = new TriangularFuzzyNumber(0, 60, 80);
        TriangularFuzzyNumber normal       = new TriangularFuzzyNumber(20, 35, 50);
        TriangularFuzzyNumber tinggi       = new TriangularFuzzyNumber(40, 50, 60);
        TriangularFuzzyNumber sangatTinggi = new TriangularFuzzyNumber(50, 70, Integer.MAX_VALUE - 1);

        BMI bmi = new BMI(60, 1.7);
        bmi.setBMIRendah(rendah);
        bmi.setBMINormal(normal);
        bmi.setBMIOverweight(tinggi);
        bmi.setBMIObesity(sangatTinggi);

        Stroke stroke = new Stroke(bmi, tekananDarah);
        stroke.execute();
        System.out.println(stroke.getResult());


        String path = System.getProperty("user.dir") + "/src/assets/tmp/stroke_001.usr";
        try
        {
            FileOutputStream   fileOut = new FileOutputStream(path);
            ObjectOutputStream out     = new ObjectOutputStream(fileOut);
            out.writeObject(stroke);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/employee.ser");
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    @Test public void testStrokeWithReadSerialization()
    {
        String path   = System.getProperty("user.dir") + "/src/assets/tmp/stroke_001.usr";
        Stroke stroke = null;
        try
        {
            FileInputStream   fileIn = new FileInputStream(path);
            ObjectInputStream in     = new ObjectInputStream(fileIn);
            stroke = (Stroke) in.readObject();
            in.close();
            fileIn.close();
            stroke.execute();
            System.out.println(stroke.getResult());
        }
        catch(IOException i)
        {
            i.printStackTrace();
            return;
        }
        catch(ClassNotFoundException c)
        {
            System.out.println("Stroke class not found");
            c.printStackTrace();
            return;
        }
    }

    @Test public void testStrokeWithReadSerializationFromDatabase()
    {
        DBProperties properties = DBProperties.getInstance();
        properties.dbName = "sample";
        try
        {
            properties.connect();
            properties.preparedStatement = properties.connection.prepareStatement("SELECT `strokeDisease` FROM `user` WHERE `user`.`id` = 1\n");
            properties.result = properties.preparedStatement.executeQuery();
            String path   = System.getProperty("user.dir") + "/src/assets/tmp/stroke_001.usr";
            Stroke stroke = null;
            if(properties.result.next())
            {
                try
                {
                    //FileInputStream   fileIn = new FileInputStream(path);
                    ObjectInputStream in = new ObjectInputStream(properties.result.getBinaryStream("strokeDisease"));
                    stroke = (Stroke) in.readObject();
                    in.close();
                    //fileIn.close();
                    stroke.execute();
                    System.out.println(stroke.getResult());
                }
                catch(IOException i)
                {
                    i.printStackTrace();
                    return;
                }
                catch(ClassNotFoundException c)
                {
                    System.out.println("Stroke class not found");
                    c.printStackTrace();
                    return;
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}