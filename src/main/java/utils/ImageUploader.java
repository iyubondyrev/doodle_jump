package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Image uploader.
 */
public class ImageUploader {

    private static final String BASE_PATH = "src/main/java/resourses/";

    /**
     * Upload image.
     *
     * @param fileName name of file in directory src/main/java/resourses/.
     */
    public static BufferedImage upload(String fileName) {
        BufferedImage image;

        try {
            image = readImage(fileName);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return image;
    }

    /**
     * Read image for uplaoder.
     * 
     * @param fileName image name in image directory.
     * @return read image.
     * @throws IOException Exception.
     */
    private static BufferedImage readImage(String fileName) throws IOException {
        return ImageIO.read(new File(BASE_PATH + fileName));
    }
}
