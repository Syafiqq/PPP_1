package model.profile.components;

import java.io.InputStream;
import javafx.scene.image.Image;
import model.pattern.proxy.ImageProxy;

/**
 * This <PPP_1> project in package <model.profile.components> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 9:05 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class ProxifiedAvatar implements ImageProxy
{
    private ImageProxy realImage;
    private Image      proxy;
    private int        userID;

    public ProxifiedAvatar(int userID, InputStream thumbnail)
    {
        this.userID = userID;
        this.proxy = new Image(thumbnail);
    }

    @Override public Image showImage()
    {
        if(this.realImage == null)
        {
            this.realImage = new RealAvatar(this.userID);
        }
        return this.realImage.showImage();
    }

    public Image loadProxy()
    {
        return this.proxy;
    }
}
