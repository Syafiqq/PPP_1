package model.helper;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This <PPP_1> project in package <model.helper> created by :
 * Name         : syafiq
 * Date / Time  : 07 June 2016, 6:30 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class ImageViewCenterImage
{
    public static void adjustImagePosition(final ImageView imageView)
    {
        final Image img = imageView.getImage();
        if(img != null)
        {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY)
            {
                reducCoeff = ratioY;
            }
            else
            {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }
}
