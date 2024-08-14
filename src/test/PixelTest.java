package src.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import src.model.Pixel;
import src.model.Pixel.Builder;

import static org.junit.Assert.assertEquals;

/**
 * Tests the constructor and all the methods for Pixel class
 */
public class PixelTest {

  Pixel p1;
  Pixel p2;
  Pixel p3;
  Pixel p4;
  Pixel p5;
  Pixel p6;
  Pixel p7;
  Pixel p8;
  List<Double> pixelList;

  /**
   * Sets the initial values for the data for the purposes for testing
   */
  public void initialConditions() {

    p1 = new Pixel(0, 0, 0);

    p2 = new Pixel(255, 255, 255);
    p3 = new Pixel(-100, -100, -100);
    Builder b = new Builder();
    b.withRed(500);
    b.withGreen(500);
    b.withBlue(255);
    p4 = b.build();
    p5 = new Pixel(256, 256, -1);
    p6 = new Pixel(Math.PI, Math.PI, Math.PI);
    p7 = new Pixel(7, 77, 777);
    p8 = new Pixel(8, 88, 888);
  }


  //constructor tests
  @Test
  public void pixelWorkingConstructor() {
    initialConditions();

    //accepts all zeroes
    assertEquals(p1.getRed(), 0, .000001);
    assertEquals(p1.getBlue(), 0, .000001);
    assertEquals(p1.getGreen(), 0, .000001);

    //accepts max value
    assertEquals(p2.getRed(), 255, .000001);
    assertEquals(p2.getBlue(), 255, .000001);
    assertEquals(p2.getGreen(), 255, .000001);

    //clamps on the lower bound
    assertEquals(p3.getRed(), 0, .000001);
    assertEquals(p3.getBlue(), 0, .000001);
    assertEquals(p3.getGreen(), 0, .000001);

    //clamps the upper bound
    assertEquals(p4.getRed(), 255, .000001);
    assertEquals(p4.getBlue(), 255, .000001);
    assertEquals(p4.getGreen(), 255, .000001);

    //clamps upper bound 1 index greater and 1 index less
    assertEquals(p5.getRed(), 255, .000001);
    assertEquals(p5.getBlue(), 0, .000001);
    assertEquals(p5.getGreen(), 255, .000001);

    //testing for a double
    assertEquals(p6.getRed(), Math.PI, .000001);
    assertEquals(p6.getBlue(), Math.PI, .000001);
    assertEquals(p6.getGreen(), Math.PI, .000001);

    assertEquals(p8.getGreen(), 888, .000001);
  }

  @Test
  public void pixelWorkingConstructorListInput() {
    List<Double> rgb = new ArrayList<Double>();
    rgb.add(256.0);
    rgb.add(0.0);
    rgb.add(-1.0);
    Pixel p = new Pixel(rgb);
    assertEquals(p.getRed(), 255, .000001);
    assertEquals(p.getGreen(), 0, .000001);
    assertEquals(p.getBlue(), 0, .000001);
  }

  @Test
  public void pixelAsListTest() {
    initialConditions();
    pixelList = p7.pixelAsList();

    assertEquals(pixelList.size(), 3, .000001);
    //red
    assertEquals(pixelList.get(0), p7.getRed(), .000001);
    //green
    assertEquals(pixelList.get(1), p7.getGreen(), .000001);

    //blue
    assertEquals(pixelList.get(2), p7.getBlue(), .000001);

    //testing for mutability and transitive
    List<Double> pixelListDuplicate = p7.pixelAsList();
    assertEquals(pixelList.get(0), pixelListDuplicate.get(0), .000001);
    assertEquals(pixelList.get(1), pixelListDuplicate.get(1), .000001);
    assertEquals(pixelList.get(2), pixelListDuplicate.get(2), .000001);
  }

  @Test
  public void toStringTest() {
    initialConditions();
    assertEquals(p2.toString(), "255\n"
        + "255\n"
        + "255\n");

  }

  @Test
  public void hashCodeTest() {
    initialConditions();
    p2.hashCode();
  }

  @Test
  public void equalTest() {
    initialConditions();
    assertEquals(p2.equals(p3), true);
  }
}
