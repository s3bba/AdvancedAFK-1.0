package me.sebbaindustries.advancedafk.detection;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public class Storage {

    public int afkTime = 0;

    /*
    Old values, they update after trail length is run out
     */
    public int avgDistanceTrailX = 0;
    public int avgDistanceTrailY = 0;
    public int avgDistanceTrailZ = 0;
    public int avgMoveTrailYaw = 0;
    public int avgMoveTrailPitch = 0;
    public int maxX = 0;
    public int minX = 0;
    public int maxY = 0;
    public int minY = 0;
    public int maxZ = 0;
    public int minZ = 0;
    public int maxYaw = 0;
    public int minYaw = 0;
    public int maxPitch = 0;
    public int minPitch = 0;


    /**
     * Trail length - length of coordinates player leaves, trailLength == seconds
     */
    public int trailLength;

    /**
     * Trail is just how long aafk stores players last location, trailLength == seconds
     *
     * @param trailLength Length of the trail that player will leave behind
     */
    public Storage(int trailLength) {
        this.trailLength = trailLength;
        trailX = new int[trailLength];
        trailY = new int[trailLength];
        trailZ = new int[trailLength];
        trailYaw = new int[trailLength];
        trailPitch = new int[trailLength];
    }

    private final int[] trailX;
    private final int[] trailY;
    private final int[] trailZ;
    private final int[] trailYaw;
    private final int[] trailPitch;

    /**
     * Gets array of locations for X coordinate
     *
     * @return trail coordinates for X coordinate
     */
    public final int[] getTrailX() {
        return trailX;
    }

    /**
     * Gets array of locations for Y coordinate
     *
     * @return trail coordinates for Y coordinate
     */
    public final int[] getTrailY() {
        return trailY;
    }

    /**
     * Gets array of locations for Z coordinate
     *
     * @return trail coordinates for Z coordinate
     */
    public final int[] getTrailZ() {
        return trailZ;
    }

    /**
     * Gets array of locations for Yaw coordinate
     *
     * @return trail coordinates for Yaw coordinate
     */
    public int[] getTrailYaw() {
        return trailYaw;
    }

    /**
     * Gets array of locations for Pitch coordinate
     *
     * @return trail coordinates for Pitch coordinate
     */
    public int[] getTrailPitch() {
        return trailPitch;
    }

    /**
     * <b>Updates coordinates and shifts old ones +1 place,</b>
     * last coordinate falls into the void never to be seen again.
     * Length of the trail is initialized when class is created
     *
     * @param newX     New coordinate on X axis
     * @param newY     New coordinate on Y axis
     * @param newZ     New coordinate on Z axis
     * @param newYaw   New Yaw coordinate
     * @param newPitch New Pitch coordinate
     */
    public final void updateTrail(final int newX, final int newY, final int newZ, final int newYaw, final int newPitch) {
        // Shift
        for (int i = trailLength - 1; i > 0; i--) {
            trailX[i] = trailX[i - 1];
            trailY[i] = trailY[i - 1];
            trailZ[i] = trailZ[i - 1];
            trailYaw[i] = trailYaw[i - 1];
            trailPitch[i] = trailPitch[i - 1];
        }
        // Add new
        trailX[0] = newX;
        trailY[0] = newY;
        trailZ[0] = newZ;
        trailYaw[0] = newYaw;
        trailPitch[0] = newPitch;
    }

}
