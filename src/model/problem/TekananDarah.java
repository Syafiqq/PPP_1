package model.problem;

import java.io.Serializable;
import model.fuzzy.fuzzy_number.FuzzyNumber;
import model.fuzzy.membership.MembershipFunction;

/**
 * This <PPP_1> project in package <model.problem.parameter> created by :
 * Name         : syafiq
 * Date / Time  : 31 May 2016, 6:55 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class TekananDarah extends MembershipFunction implements Serializable
{
    private double tekanan_darah;

    public TekananDarah(final double tekanan_darah)
    {
        super(4);
        this.setTekananDarah(tekanan_darah);
    }

    public TekananDarah()
    {
        this(1);
    }

    public double getTekananDarah()
    {
        return this.tekanan_darah;
    }

    public void setTekananDarah(double tekananDarah)
    {
        this.tekanan_darah = tekananDarah;
        //System.out.printf("%g\n", this.tekanan_darah);
    }

    public double getTekananDarahRendah()
    {
        return super.getMembership("rendah").getFuzzy_Y(this.tekanan_darah);
    }

    public void setTekananDarahRendah(final FuzzyNumber fuzzy_number)
    {
        super.registerMembershipFunction("rendah", fuzzy_number);
    }

    public double getTekananDarahNormal()
    {
        return super.getMembership("normal").getFuzzy_Y(this.tekanan_darah);
    }

    public void setTekananDarahNormal(final FuzzyNumber fuzzy_number)
    {
        super.registerMembershipFunction("normal", fuzzy_number);
    }

    public double getTekananDarahTinggi()
    {
        return super.getMembership("tinggi").getFuzzy_Y(this.tekanan_darah);
    }

    public void setTekananDarahTinggi(final FuzzyNumber fuzzy_number)
    {
        super.registerMembershipFunction("tinggi", fuzzy_number);
    }

    public double getTekananDarahSangatTinggi()
    {
        return super.getMembership("sangat_tinggi").getFuzzy_Y(this.tekanan_darah);
    }

    public void setTekananDarahSangatTinggi(final FuzzyNumber fuzzy_number)
    {
        super.registerMembershipFunction("sangat_tinggi", fuzzy_number);
    }
}
