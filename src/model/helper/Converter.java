package model.helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jetbrains.annotations.NotNull;

/**
 * This <PPP_1> project in package <model.helper> created by :
 * Name         : syafiq
 * Date / Time  : 03 June 2016, 4:00 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class Converter
{
    public static byte[] fileToBytes(@NotNull final File file) throws IOException, NullPointerException
    {
        ByteArrayOutputStream outputStreamm = new ByteArrayOutputStream();
        FileInputStream       inputStrem    = new FileInputStream(file);
        byte[]                buffer        = new byte[1024];
        for(int len; (len = inputStrem.read(buffer)) != -1; )
        {
            outputStreamm.write(buffer, 0, len);
        }
        return outputStreamm.toByteArray();
    }

    public static byte[] bufferedImageToBytes(@NotNull final BufferedImage imageSource, @NotNull String type) throws IOException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(imageSource, type, outputStream);
        return outputStream.toByteArray();
    }
}
