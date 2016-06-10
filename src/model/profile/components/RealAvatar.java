package model.profile.components;

import java.sql.SQLException;
import javafx.scene.image.Image;
import model.db.DBProperties;
import model.pattern.proxy.ImageProxy;

/**
 * This <PPP_1> project in package <model.profile.components> created by :
 * Name         : syafiq
 * Date / Time  : 06 June 2016, 3:25 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class RealAvatar implements ImageProxy
{
    private int   userID;
    private Image image;

    public RealAvatar(int userID)
    {
        this.userID = userID;
        this.loadAvatarFromDatabase();
    }

    private void loadAvatarFromDatabase()
    {
        DBProperties properties = DBProperties.getInstance();
        try
        {
            properties.preparedStatement = properties.connection.prepareStatement("SELECT `avatar` FROM `user` WHERE `user`.`id` = ? LIMIT 1;\n");
            properties.preparedStatement.setInt(1, this.userID);
            properties.result = properties.preparedStatement.executeQuery();


            this.image = new Image(properties.result.getBinaryStream("avatar"));
        }
        catch(SQLException ignore)
        {

        }
    }

    @Override public Image showImage()
    {
        return this.image;
    }
}
