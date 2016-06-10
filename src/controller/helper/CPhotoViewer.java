package controller.helper;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This <PPP_1> project in package <controller.helper> created by :
 * Name         : syafiq
 * Date / Time  : 08 June 2016, 9:58 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class CPhotoViewer implements Initializable
{
    final private Image     image;
    public        ImageView iv_image;

    public CPhotoViewer()
    {
        this(null);
    }

    public CPhotoViewer(Image image)
    {
        this.image = image;
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        if(this.image != null)
        {
            this.iv_image.setImage(this.image);
        }
    }
}
