package src.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

/**
 * This class contains utility methods to read and write PPM to and from files. Only supports files
 * with one period in the name.
 */
public class IO {

  /**
   * Converts the given file to a Photo if the file is valid.
   *
   * @param filename
   * @return
   * @throws IOException
   */
  public static Photo readFile(String filename) throws IOException {
    if (filename == null) {
      throw new IllegalArgumentException("Invalid file");
    }

    File file = new File(filename);
    BufferedImage image;
    try {
      image = ImageIO.read(file);
    } catch (IOException e) {
      throw new IllegalArgumentException("Unsupported fileType");
    }
    return imageToPhoto(image);
  }

  /**
   * Saves the given Photo as the desired fileName. The fileName must end wiht the supported
   * extension of .png or .jpeg.
   */
  public static void exportPhotoAsFile(Photo photo, String fileName) {
    File file = new File(fileName);
    ImageOutputStream imageOutput;
    try {
      imageOutput = new FileImageOutputStream(file);
    } catch(IOException e){
      throw new IllegalArgumentException("File unable to be created");
    }

    int indexOfPeriod = fileName.indexOf('.');
    String extension = fileName.substring(indexOfPeriod);
    ImageIO.write(bufferedImage, extension, new File(fileName));
  }

  /**
   * Converts the given BufferedImage to a Photo as defined in the Photo class.
   *
   * @param image
   * @return
   */
  private static Photo imageToPhoto(BufferedImage image) {
    int imageLength = image.getHeight();
    int imageWidth = image.getWidth();
    List<List<Pixel>> imageInfo = new ArrayList<List<Pixel>>();
    for (int i = 0; i < imageLength; i++) {
      imageInfo.add(new ArrayList<Pixel>());
      for (int j = 0; j < imageWidth; j++) {
        Color c = new Color(image.getRGB(i, j));
        Pixel p = new Pixel(c.getRed(), c.getGreen(), c.getBlue());
        imageInfo.get(i).add(p);
      }
    }
    return new Photo(imageInfo);
  }


  /**
   * Read an image file in the PPM format and creates a photo from that image. The maxvalue of that
   * photo is always 255.
   *
   * @param filename the path of the file.
   * @throws IllegalArgumentException if the file DNE
   */
  private static Photo readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {

      throw new IllegalArgumentException("File not found");

    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();

    int height = sc.nextInt();
    sc.nextInt();
    List<List<Pixel>> photoPixels = new ArrayList<List<Pixel>>();
    Pixel pixel;

    for (int i = 0; i < height; i++) {
      photoPixels.add(new ArrayList<Pixel>());
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixel = new Pixel(r, g, b);
        photoPixels.get(i).add(pixel);
      }
    }

    return new Photo(photoPixels);
  }

  /**
   * Exports the given photo to PPM format with the filename titled the given fileName.
   *
   * @param filename
   * @param photo
   * @throws IllegalArgumentException if the filename or the photo are invalid
   * @throws IOException              if the file cannot be written to
   */
  public static void export(String filename, Photo photo) throws IOException {
    if (filename.length() == 0) {
      throw new IllegalArgumentException("Required nonempty filename");
    }
    if (photo == null) {
      throw new IllegalArgumentException("Required nonempty photo");
    }

    File file = new File(filename);

    file.createNewFile();
    FileWriter writer = new FileWriter(file);
    StringBuilder builder = new StringBuilder();
    builder.append("P3\n" + photo.getWidth() + " " + photo.getLength() + "\n" + 255 + "\n");

    List<List<Pixel>> photoPixels = photo.getPixels();
    double red;
    double green;
    double blue;
    Pixel pixel;
    for (int i = 0; i < photo.getLength(); i++) {
      for (int j = 0; j < photo.getWidth(); j++) {
        pixel = photoPixels.get(i).get(j);

        builder.append(pixel.toString());
      }
    }
    writer.write(builder.toString());
    writer.flush();
    writer.close();
  }


}


