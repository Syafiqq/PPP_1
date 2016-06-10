package model.profile;

import java.time.LocalDate;
import javafx.scene.image.Image;
import model.profile.components.Gender;
import model.profile.components.ProxifiedAvatar;

/**
 * This <PPP_1> project in package <model.profile> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 9:05 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class User
{
    public  int             userID;
    public  ProxifiedAvatar profile_picture;
    private String          full_name;
    private String          nick_name;
    private LocalDate       birth_date;
    private Gender          gender;

    public User(int userID, String full_name, String nick_name, LocalDate birth_date, Gender gender, ProxifiedAvatar profile_picture)
    {
        this.userID = userID;
        this.full_name = full_name;
        this.nick_name = nick_name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.profile_picture = profile_picture;
    }

    @Override public String toString()
    {
        return "User{" +
                "userID=" + userID +
                ", full_name='" + full_name + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", birth_date=" + birth_date.toString() +
                ", gender=" + gender.realName() +
                ", profile_picture=" + (profile_picture != null) +
                '}';
    }

    public String getNickname()
    {
        return this.nick_name;
    }

    public Image getThumbnail()
    {
        return this.profile_picture.loadProxy();
    }
}
