package model.fuzzy.membership;

import java.io.Serializable;
import java.util.LinkedHashMap;
import model.fuzzy.fuzzy_number.FuzzyNumber;

/**
 * This <PPP_1> project in package <model.fuzzy.membership> created by :
 * Name         : syafiq
 * Date / Time  : 31 May 2016, 6:00 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class MembershipFunction implements Serializable
{
    private LinkedHashMap<String, FuzzyNumber> membership;

    public MembershipFunction()
    {
        this.membership = new LinkedHashMap<>();
    }

    public MembershipFunction(int expected_size)
    {
        this.membership = new LinkedHashMap<>(expected_size);
    }

    protected void registerMembershipFunction(final String type, final FuzzyNumber fuzzy_number)
    {
        this.membership.put(type, fuzzy_number);
    }

    protected FuzzyNumber getMembership(final String type)
    {
        return this.membership.get(type);
    }
}
