package model.profile.components;

/**
 * This <PPP_1> project in package <model.profile.components> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 9:07 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public enum Gender
{
    L("Male"),
    P("Female");

    private final String description;

    Gender(String description)
    {
        this.description = description;
    }

    public String realName()
    {
        return this.description;
    }
}
