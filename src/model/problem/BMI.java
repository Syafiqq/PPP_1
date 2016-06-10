package model.problem;

import java.io.Serializable;
import model.fuzzy.fuzzy_number.FuzzyNumber;
import model.fuzzy.membership.MembershipFunction;
import org.jetbrains.annotations.Contract;

/**
 * This <PPP_1> project in package <model.fuzzy.membership> created by :
 * Name         : syafiq
 * Date / Time  : 31 May 2016, 6:04 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class BMI extends MembershipFunction implements Serializable
{
    private double bmi;
    private double berat_badan;
    private double tinggi_badan;

    public BMI(final double berat_badan, final double tinggi_badan)
    {
        super(4);
        this.setBmiProperties(berat_badan, tinggi_badan);
    }

    public BMI()
    {
        this(1, 1);
    }

    public void setBmiProperties(final double berat_badan, final double tinggi_badan)
    {
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
        this.bmi = this.calculateBMIValue();
        //System.out.printf("%g %g %g\n", this.berat_badan, this.tinggi_badan, this.bmi);
    }

    public double getBmi()
    {
        return this.bmi;
    }

    public double getBeratBadan()
    {
        return this.berat_badan;
    }

    public double getTinggiBadan()
    {
        return this.tinggi_badan;
    }

    @Contract(pure = true) private double calculateBMIValue()
    {
        return (this.berat_badan / Math.pow(this.tinggi_badan, 2));
    }

    public double getBMIRendah()
    {
        return super.getMembership("rendah").getFuzzy_Y(this.bmi);
    }

    public void setBMIRendah(final FuzzyNumber fuzzy_number)
    {
        super.registerMembershipFunction("rendah", fuzzy_number);
    }

    public double getBMINormal()
    {
        return super.getMembership("normal").getFuzzy_Y(this.bmi);
    }

    public void setBMINormal(final FuzzyNumber fuzzy_number)
    {
        super.registerMembershipFunction("normal", fuzzy_number);
    }

    public double getBMIOverweight()
    {
        return super.getMembership("tinggi").getFuzzy_Y(this.bmi);
    }

    public void setBMIOverweight(final FuzzyNumber fuzzy_number)
    {
        super.registerMembershipFunction("tinggi", fuzzy_number);
    }

    public double getBMIObesity()
    {
        return super.getMembership("sangat_tinggi").getFuzzy_Y(this.bmi);
    }

    public void setBMIObesity(final FuzzyNumber fuzzy_number)
    {
        super.registerMembershipFunction("sangat_tinggi", fuzzy_number);
    }
}
