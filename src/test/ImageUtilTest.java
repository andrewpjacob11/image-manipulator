package src.test;

import java.io.IOException;
import org.junit.Test;
import src.model.ImageUtil;
import src.model.Photo;

import static org.junit.Assert.assertEquals;

/**
 * Tests all public facing methods for ImageUtil class.
 */
public class ImageUtilTest {

  ImageUtil util;

  @Test
  public void readPPMWorking() {
    util = new ImageUtil();
    Photo koala = util.readPPM("koala.ppm");
    assertEquals(koala instanceof Photo, true);

    //testing that it's non-empty
    assertEquals(koala.getPixels().size() != 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void readPPMInvalidFile() {
    util = new ImageUtil();
    Photo dne = util.readPPM("doesNotExist.ppm");
  }

  @Test
  public void importAndExportEquality() throws IOException {
    util = new ImageUtil();
    Photo simplepic = util.readPPM("simplepic.ppm");
    assertEquals("255\n"
        + "254\n"
        + "253\n"
        + "252\n"
        + "251\n"
        + "250\n"
        + "1\n"
        + "2\n"
        + "3\n"
        + "4\n"
        + "5\n"
        + "6\n", simplepic.toString());

    assertEquals(simplepic.getWidth(), 2);
    assertEquals(simplepic.getLength(), 2);
    util.export("simplepic_copy.ppm", simplepic);

    Photo simpleCopy = util.readPPM("simplepic_copy.ppm");

    assertEquals(simpleCopy.equals(simplepic), true);
  }

}
