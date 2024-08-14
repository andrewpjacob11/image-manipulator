package src.test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import src.model.Photo;
import src.model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests all public facing methods for the Photo class
 */
public class PhotoTest {

  Photo photo;


  @Test
  public void photoLengthWidthConstructorTest() {
    photo = new Photo(1, 2);
    assertEquals(photo instanceof Photo, true);

    assertEquals(photo.getLength(), 1);
    assertEquals(photo.getWidth(), 2);

    assertEquals(photo.getPixels().size(), 1);
    assertEquals(photo.getPixels().get(0).size(), 2);

    //ensuring the pixels were instantiated correctly
    for (int i = 0; i < 1; i++) {
      for (int j = 0; j < 2; j++) {
        Pixel p = photo.getPixels().get(i).get(i);
        assertEquals(p.getRed(), 255, .001);
        assertEquals(p.getBlue(), 255, .001);
        assertEquals(p.getGreen(), 255, .001);
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidLengthPhotoConstructorTest() {
    photo = new Photo(-1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidWidthPhotoConstructorTest() {
    photo = new Photo(1, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void zeroPhotoConstructorTest() {
    photo = new Photo(0, 0);
  }

  @Test
  public void singlePixelPhotoConstructorTest() {
    photo = new Photo(1, 1);
    assertEquals(photo.getPixels().size(), 1);
    assertEquals(photo.getPixels().get(0).size(), 1);
  }

  @Test
  public void photoEqualsTest() {
    photo = new Photo(1, 1);
    assertEquals(photo.equals(new Photo(1, 1)), true);
  }

  @Test
  public void photoToStringTest() {
    photo = new Photo(1, 1);
    assertEquals(photo.toString(), "255\n"
        + "255\n"
        + "255\n");
  }

  @Test
  public void checkerBoardConstructor() {
    photo = new Photo(4, 4, Color.BLUE, Color.RED);
    assertEquals(photo instanceof Photo, true);
    throw new IllegalArgumentException("Need to be implemented");

  }

  @Test(expected = IllegalArgumentException.class)
  public void checkerBoardConstructorInvalidArgs() {
    photo = new Photo(4, 4, Color.BLUE, Color.RED);

  }


  @Test
  public void photoListConstructor() {
    Pixel p1 = new Pixel(0.0, 0.0, 0.0);
    Pixel p2 = new Pixel(255.0, 255.0, 255.0);
    Pixel p6 = new Pixel(Math.PI, Math.PI, Math.PI);
    List<List<Pixel>> pixels = new ArrayList<List<Pixel>>();
    for (int i = 0; i < 3; i++) {
      pixels.add(new ArrayList<Pixel>());
      pixels.get(i).add(p1);
      pixels.get(i).add(p2);
      pixels.get(i).add(p6);
    }
    Photo photo = new Photo(pixels);
    assertEquals(photo.toString(), "0\n"
        + "0\n"
        + "0\n"
        + "255\n"
        + "255\n"
        + "255\n"
        + "3\n"
        + "3\n"
        + "3\n"
        + "0\n"
        + "0\n"
        + "0\n"
        + "255\n"
        + "255\n"
        + "255\n"
        + "3\n"
        + "3\n"
        + "3\n"
        + "0\n"
        + "0\n"
        + "0\n"
        + "255\n"
        + "255\n"
        + "255\n"
        + "3\n"
        + "3\n"
        + "3\n");
  }


  @Test(expected = IllegalArgumentException.class)
  public void photoListConstructorNullTest() {
    Photo photo = new Photo(null);

  }

  @Test
  public void photoGetPixelsTest() {
    Pixel p1 = new Pixel(0, 0, 0);
    Pixel p2 = new Pixel(255, 255, 255);
    Pixel p6 = new Pixel(Math.PI, Math.PI, Math.PI);
    List<List<Pixel>> pixels = new ArrayList<List<Pixel>>();
    for (int i = 0; i < 3; i++) {
      pixels.add(new ArrayList<Pixel>());
      pixels.get(i).add(p1);
      pixels.get(i).add(p2);
      pixels.get(i).add(p6);
    }
    Photo photo = new Photo(pixels);
    //checking to ensure they aren't statically linked
    assertEquals(pixels.equals(photo.getPixels()), true);

    //checking for equality
    List<List<Pixel>> photoPixels = photo.getPixels();
    for (int i = 0; i < 3; i++) {
      assertEquals(pixels.get(i).get(0).equals(photoPixels.get(i).get(0)), true);
      assertEquals(pixels.get(i).get(1).equals(photoPixels.get(i).get(1)), true);
      assertEquals(pixels.get(i).get(2).equals(photoPixels.get(i).get(2)), true);
    }
  }


  @Test
  public void photoGetLengthAndWidthTest() {
    Pixel p1 = new Pixel(0, 0, 0);
    Pixel p2 = new Pixel(255, 255, 255);
    Pixel p6 = new Pixel(Math.PI, Math.PI, Math.PI);
    List<List<Pixel>> pixels = new ArrayList<List<Pixel>>();
    for (int i = 0; i < 3; i++) {
      pixels.add(new ArrayList<Pixel>());
      pixels.get(i).add(p1);
      pixels.get(i).add(p2);
      pixels.get(i).add(p6);
    }
    Photo photo = new Photo(pixels);
    assertEquals(photo.getLength(), 3);
    assertEquals(photo.getWidth(), 3);
  }

  @Test
  public void hashCodeTest() {
    Photo photo = new Photo(1, 1);
    photo.hashCode();
  }

  @Test
  public void equals() {
    Photo photo = new Photo(1, 1);
    Photo dup = new Photo(1, 1);
    assertEquals(photo.equals(dup), true);
  }


}




