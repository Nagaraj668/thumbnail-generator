package com.srjons.thumbnail.controller;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

@Service
public class ImageProcessService {

    private static final String PATH = "E:\\Images";

    public String processAll() {
        File file = new File(PATH);
        Arrays.stream(file.listFiles()).forEach(this::processEach);
        return "All Completed";
    }

    private void processEach(File file) {
        BufferedImage bufferedImage = toBufferedImage(file);
        try {
            byte[] data = resizeImage(bufferedImage, 300, 300);
            OutputStream os = new FileOutputStream(file);
            os.write(data);
            System.out.println("Successfully"
                    + " byte inserted");
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public byte[] resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(originalImage)
                .size(targetWidth, targetHeight)
                .outputFormat("JPEG")
                .outputQuality(1)
                .toOutputStream(outputStream);
        byte[] data = outputStream.toByteArray();
        //ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        //return ImageIO.read(inputStream);
        return data;
    }

    public static BufferedImage toBufferedImage(File imgFile) {
        Image img = null;
        try {
            img = ImageIO.read(imgFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (img instanceof BufferedImage) {
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
}
