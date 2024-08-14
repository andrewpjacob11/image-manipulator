package Controller;

import java.io.StringReader;
import java.util.List;
import src.model.Photo;

public class PhotoController {

  private final Readable in;
  private final Appendable out;
  private List<Photo> model;


  public PhotoController(List<Photo> model, Readable rd, Appendable ap) {
    in = rd;
    out = ap;
    this.model = model;

  }

  public void doPhotoshop() {

    while (this.parseInput()) {

    }
  }

  /**
   * If possible, gets the next user input. If the user inputs just "q" surrounded by whitespace,
   * quits the program.
   *
   * @return
   */
  private boolean parseInput() {

  }


}
