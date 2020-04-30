import me.sebbaindustries.advancedafk.detection.Storage;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.logging.Logger;

public class TestCore {

    public Logger log = Logger.getLogger("TestCore");

    private final int trailLength = 16;
    private final int[] trailX = {70, 4, 65, 100, 73, 66, 33, 27, 55, 33, 67, 27, 99, 44, 32, 12};
    private final int[] trailY = {1, 2, -100, 2, -100, 2, -100, 2, -100, 2, -100, 2, -100, 2, -100, 2};
    private final int[] trailZ = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2};
    private final int[] trailYaw = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private final int[] trailPitch = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    @Test
    public void testAvg() {
        int avgDistanceTrailX = 0;
        int avgDistanceTrailY = 0;
        int avgDistanceTrailZ = 0;
        int avgMoveTrailYaw = 0;
        int avgMoveTrailPitch = 0;

        // Get average distance
        for (int i = 0; i < trailLength; i++) {
            avgDistanceTrailX += trailX[i];
            avgDistanceTrailY += trailY[i];
            avgDistanceTrailZ += trailZ[i];
            avgMoveTrailYaw += trailYaw[i];
            avgMoveTrailPitch += trailPitch[i];
        }
        avgDistanceTrailX /= trailLength;
        avgDistanceTrailY /= trailLength;
        avgDistanceTrailZ /= trailLength;
        avgMoveTrailYaw /= trailLength;
        avgMoveTrailPitch /= trailLength;

        log.info("@Async{" +
                "x[" + avgDistanceTrailX + "] " +
                "y[" + avgDistanceTrailY + "] " +
                "z[" + avgDistanceTrailZ + "] " +
                "yaw[" + avgMoveTrailYaw + "] " +
                "pitch[" + avgMoveTrailPitch + "]"
                + "}");
        Assert.assertTrue(true);
    }

    /*@Test
    public void readArrayTest() {
        Storage s = new Storage(16);
        int[] array = s.getTrailX();
        for (int i = 0; i < 16; i++) {
            log.info(String.valueOf(array[i]));
        }
        int test = 4;
        Assert.assertTrue(test == array[10]);
    }

     */

    @Test
    public void testArray() {
        Storage s = new Storage(16);

        log.info(Arrays.toString(s.getTrailX()));
        log.info(Arrays.toString(s.getTrailY()));
        log.info(Arrays.toString(s.getTrailZ()));

        s.updateTrail(1, 70, 7, 3, 77);
        s.updateTrail(2, 80, 8, 54, 4565);
        s.updateTrail(3, 70, 9, 5, 0);
        s.updateTrail(4, -23, 10, 643, 1);

        log.info(Arrays.toString(s.getTrailX()));
        log.info(Arrays.toString(s.getTrailY()));
        log.info(Arrays.toString(s.getTrailZ()));
        int i = 4;
        Assert.assertTrue(i == 2 + 2);
    }

    @Test
    public void testPermission() {
        int i = 5;

        if (!(i == 5) && !(i == 7)) {
            log.info("return");
            return;
        }
        log.info("thru");
    }


    @Test
    public void formatMessage() {
        String message = "§7AFK Players\n §8» $p{§f}$s{§8, }";
        int startIndexPlayer = message.indexOf("$p");
        int startIndexSeparator = message.indexOf("$s");

        StringBuilder playerColor = new StringBuilder();
        StringBuilder separatorColor = new StringBuilder();

        for (int i = startIndexPlayer + 3; i < message.length(); i++) {
            if (message.charAt(i) == '}') break;
            playerColor.append(message.charAt(i));
        }

        for (int i = startIndexSeparator + 3; i < message.length(); i++) {
            if (message.charAt(i) == '}') break;
            separatorColor.append(message.charAt(i));
        }

        StringBuilder remove = new StringBuilder();
        for (int i = startIndexPlayer; i < message.length(); i++) {
            if (message.charAt(i) == '}') {
                remove.append(message.charAt(i));
                break;
            }
            remove.append(message.charAt(i));
        }
        for (int i = startIndexSeparator; i < message.length(); i++) {
            if (message.charAt(i) == '}') {
                remove.append(message.charAt(i));
                break;
            }
            remove.append(message.charAt(i));
        }

        log.info(remove.toString());

        log.info(message.replace(remove.toString(), "") + "something");

        log.info("[" + playerColor.toString() + "] [" + separatorColor.toString() + "]");


    }

}
