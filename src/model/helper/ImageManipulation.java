package model.helper;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import org.jetbrains.annotations.NotNull;

/**
 * This <PPP_1> project in package <model.helper> created by :
 * Name         : syafiq
 * Date / Time  : 03 June 2016, 3:28 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class ImageManipulation
{
    /**
     * scale image
     *
     * @param imageSource image to scale
     * @return scaled image
     */
    public static BufferedImage scale(@NotNull final BufferedImage imageSource, int scaledSize)
    {
        double          scaleFactor = scaledSize * 1f / (imageSource.getWidth() > imageSource.getHeight() ? imageSource.getWidth() : imageSource.getHeight());
        int             dHeight     = (int) Math.floor(imageSource.getHeight() * scaleFactor);
        int             dWidth      = (int) Math.floor(imageSource.getWidth() * scaleFactor);
        BufferedImage   scaleResult = new BufferedImage(dWidth, dHeight, imageSource.getType());
        Graphics2D      graphic     = scaleResult.createGraphics();
        AffineTransform transform   = AffineTransform.getScaleInstance(scaleFactor, scaleFactor);
        graphic.drawRenderedImage(imageSource, transform);
        return scaleResult;
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(@NotNull final Image img)
    {
        if(img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static Image bufferedImageToImage(@NotNull final BufferedImage imageSource)
    {
        return imageSource;
    }
}
