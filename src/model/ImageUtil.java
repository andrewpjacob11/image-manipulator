package src.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * This class contains utility methods to read and write PPM to and from files.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and creates a photo from that image. The maxvalue of that
   * photo is always 255.
   *
   * @param filename the path of the file.
   * @throws IllegalArgumentException if the file DNE
   */
  public static Photo readPPM(String filename) {
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
   * @throws IOException if the file cannot be written to
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

