package src.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import src.model.ColorFilterMatrix;
import src.model.ImageUtil;
import src.model.Photo;
import src.model.PhotoModel;
import src.model.Pixel;
import src.model.Pixel.Builder;
import src.model.TransformationFilterMatrix;

public class PhotoModelTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullConstructor() {
    PhotoModel broken = new PhotoModel(null);
  }

  @Test
  public void sepiaFromColor() {

    Builder b = new Builder();
    b.withRed(25);
    b.withBlue(25);
    b.withGreen(25);
    Pixel p2 = b.build();

    List<List<Pixel>> pixels = new ArrayList<List<Pixel>>();
    for (int i = 0; i < 3; i++) {
      pixels.add(new ArrayList<Pixel>());
      pixels.get(i).add(p2);
      pixels.get(i).add(p2);
      pixels.get(i).add(p2);
    }
    Photo photo = new Photo(pixels);
    PhotoModel model = new PhotoModel(photo);
    Photo sepiaPhoto = new Photo(model.colorTransform(ColorFilterMatrix.sepia));
    assertEquals(sepiaPhoto.toString(), "33\n"
        + "30\n"
        + "23\n"
        + "33\n"
        + "30\n"
        + "23\n"
        + "33\n"
        + "30\n"
        + "23\n"
        + "33\n"
        + "30\n"
        + "23\n"
        + "33\n"
        + "30\n"
        + "23\n"
        + "33\n"
        + "30\n"
        + "23\n"
        + "33\n"
        + "30\n"
        + "23\n"
        + "33\n"
        + "30\n"
        + "23\n"
        + "33\n"
        + "30\n"
        + "23\n");
  }

  @Test
  public void BlurTest() {

    Pixel p2 = new Pixel(25, 25, 25);

    List<List<Pixel>> pixels = new ArrayList<List<Pixel>>();
    for (int i = 0; i < 3; i++) {
      pixels.add(new ArrayList<Pixel>());
      pixels.get(i).add(p2);
      pixels.get(i).add(p2);
      pixels.get(i).add(p2);
    }
    Photo photo = new Photo(pixels);
    PhotoModel model = new PhotoModel(photo);
    Photo blurPhoto = new Photo(model.filterTransformation(TransformationFilterMatrix.blur));
    assertEquals(blurPhoto.toString(), "");
  }

  @Test
  public void SharpenTestTest() {

    Pixel p2 = new Pixel(25, 25, 25);

    List<List<Pixel>> pixels = new ArrayList<List<Pixel>>();
    for (int i = 0; i < 3; i++) {
      pixels.add(new ArrayList<Pixel>());
      pixels.get(i).add(p2);
      pixels.get(i).add(p2);
      pixels.get(i).add(p2);
    }
    Photo photo = new Photo(pixels);
    PhotoModel model = new PhotoModel(photo);
    Photo sharpenPhoto = new Photo(model.filterTransformation(TransformationFilterMatrix.sharpen));
    assertEquals(sharpenPhoto.toString(), "");
  }

  @Test
  public void koalaToGreyscale() throws IOException {
    ImageUtil util = new ImageUtil();
    Photo koala = util.readPPM("koala.ppm");
    PhotoModel model = new PhotoModel(koala);
    Photo greyKoala = new Photo(model.colorTransform(ColorFilterMatrix.grayscale));

    util.export("greyKoala.ppm", greyKoala);
  }

  @Test
  public void koalaToSepia() throws IOException {
    ImageUtil util = new ImageUtil();
    Photo koala = util.readPPM("koala.ppm");
    PhotoModel model = new PhotoModel(koala);
    Photo sepiaKoala = new Photo(model.colorTransform(ColorFilterMatrix.sepia));

    util.export("sepiaKoala.ppm", sepiaKoala);
  }

  @Test
  public void koalaIdentityTest() throws IOException {
    ImageUtil util = new ImageUtil();
    Photo koala = util.readPPM("koala.ppm");
    PhotoModel model = new PhotoModel(koala);
    Photo sameKoala = new Photo(model.colorTransform(ColorFilterMatrix.identity));

    util.export("sameKoala.ppm", sameKoala);
    assertEquals(koala.equals(sameKoala), true);
  }
}
