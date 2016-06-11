package model.problem;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;
import model.fuzzy.algorithm.Tsukamoto;
import model.pattern.command.Command;
import org.jetbrains.annotations.Contract;

/**
 * This <PPP_1> project in package <model.problem> created by :
 * Name         : syafiq
 * Date / Time  : 01 June 2016, 11:52 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class Stroke extends Tsukamoto implements Command, Serializable
{
    public BMI          bmi;
    public TekananDarah tekanan_darah;

    public Stroke(final double berat_badan, final double tinggi_badan, final double tekanan_darah)
    {
        this.bmi = new BMI(berat_badan, tinggi_badan);
        this.tekanan_darah = new TekananDarah(tekanan_darah);
    }

    public Stroke()
    {
    }

    public Stroke(BMI bmi, TekananDarah tekananDarah)
    {
        this.bmi = bmi;
        this.tekanan_darah = tekananDarah;
    }

    @Override public void fuzzyfikasi()
    {
        super.z = new LinkedList<>();
        super.alpha = new LinkedList<>();

        super.alpha.addLast(Math.min(this.bmi.getBMIRendah(), this.tekanan_darah.getTekananDarahRendah()));
        this.z.addLast(this.getRendah(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMIRendah(), this.tekanan_darah.getTekananDarahNormal()));
        this.z.addLast(this.getRendah(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMIRendah(), this.tekanan_darah.getTekananDarahTinggi()));
        this.z.addLast(this.getSedang(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMIRendah(), this.tekanan_darah.getTekananDarahSangatTinggi()));
        this.z.addLast(this.getTinggi(super.alpha.getLast()));

        super.alpha.addLast(Math.min(this.bmi.getBMINormal(), this.tekanan_darah.getTekananDarahRendah()));
        this.z.addLast(this.getRendah(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMINormal(), this.tekanan_darah.getTekananDarahNormal()));
        this.z.addLast(this.getSedang(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMINormal(), this.tekanan_darah.getTekananDarahTinggi()));
        this.z.addLast(this.getTinggi(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMINormal(), this.tekanan_darah.getTekananDarahSangatTinggi()));
        this.z.addLast(this.getTinggi(super.alpha.getLast()));

        super.alpha.addLast(Math.min(this.bmi.getBMIOverweight(), this.tekanan_darah.getTekananDarahRendah()));
        this.z.addLast(this.getSedang(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMIOverweight(), this.tekanan_darah.getTekananDarahNormal()));
        this.z.addLast(this.getTinggi(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMIOverweight(), this.tekanan_darah.getTekananDarahTinggi()));
        this.z.addLast(this.getTinggi(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMIOverweight(), this.tekanan_darah.getTekananDarahSangatTinggi()));
        this.z.addLast(this.getTinggi(super.alpha.getLast()));

        super.alpha.addLast(Math.min(this.bmi.getBMIObesity(), this.tekanan_darah.getTekananDarahRendah()));
        this.z.addLast(this.getTinggi(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMIObesity(), this.tekanan_darah.getTekananDarahNormal()));
        this.z.addLast(this.getTinggi(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMIObesity(), this.tekanan_darah.getTekananDarahTinggi()));
        this.z.addLast(this.getTinggi(super.alpha.getLast()));
        super.alpha.addLast(Math.min(this.bmi.getBMIObesity(), this.tekanan_darah.getTekananDarahSangatTinggi()));
        this.z.addLast(this.getTinggi(super.alpha.getLast()));
/*        System.out.println(this.z);
        System.out.println(this.alpha);
        System.out.printf("%g %g %g %g\n", this.bmi.getBMIRendah(), this.bmi.getBMINormal(), this.bmi.getBMIOverweight(), this.bmi.getBMIObesity());
        System.out.printf("%g %g %g %g\n", this.tekanan_darah.getTekananDarahRendah(), this.tekanan_darah.getTekananDarahNormal(), this.tekanan_darah.getTekananDarahTinggi(), this.tekanan_darah.getTekananDarahSangatTinggi());
        for(double i = 1; i < 25; ++i)
        {
            this.bmi.setBmiProperties(i,1);
            System.out.println("   " + this.bmi.getBMIRendah());
        }*/
    }

    @Override public void defuzzyfikasi()
    {
        double pembilang = 0.0, penyebut = 0.0;

        for(ListIterator<Double> alpha_it = super.alpha.listIterator(), z_it = super.z.listIterator(); alpha_it.hasNext() && z_it.hasNext(); )
        {
            double alpha_value = alpha_it.next();
            double z_value     = z_it.next();
            pembilang += (alpha_value * z_value);
            penyebut += alpha_value;
        }
        //System.out.printf("%g, %g\n", pembilang, penyebut);
        super.result = pembilang / penyebut;
    }

    @Contract(pure = true) private double getRendah(double alpha_value)
    {
        return 40 - (10 * alpha_value);
    }

    @Contract(pure = true) private double getSedang(double alpha_value)
    {
        return 70 - (40 * alpha_value);
    }

    @Contract(pure = true) private double getTinggi(double alpha_value)
    {
        return 60 + (10 * alpha_value);
    }

    @Override public void execute()
    {
        this.fuzzyfikasi();
        this.defuzzyfikasi();
    }

    public String getCategoryValue()
    {
        if(super.result < 0.5)
        {
            return "Rendah";
        }
        else if(super.result < 0.7)
        {
            return "Sedang";
        }
        else
        {
            return "Tinggi";
        }
    }
}
