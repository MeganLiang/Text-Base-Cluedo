import controller.Game;
import model.Player;
import org.junit.Test;

import java.awt.*;

/**
 * Created by liangmeij on 3/08/16.
 */
public class CluedoTestsMoving {

    @Test
    public void Test1() {
        Game g = new Game();
        Player megan = new Player("Megan");
        megan.setPositionPoint(new Point(1, 1));
        g.getMoving().movePlayer(megan, g);
    }
}
