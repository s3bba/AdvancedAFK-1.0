package me.sebbaindustries.advancedafk.detection;

import me.sebbaindustries.advancedafk.Core;
import org.bukkit.entity.Player;

/**
 * @author sebbaindustries
 * @version 1.0
 */
class Analysis {

    private int trailLength;
    private int[] trailX;
    private int[] trailY;
    private int[] trailZ;
    private int[] trailYaw;
    private int[] trailPitch;
    private Player p;
    private int avgDistanceTrailX;
    private int avgDistanceTrailY;
    private int avgDistanceTrailZ;
    private int avgMoveTrailYaw;
    private int avgMoveTrailPitch;
    private int maxX;
    private int minX;
    private int maxY;
    private int minY;
    private int maxZ;
    private int minZ;
    private int maxYaw;
    private int minYaw;
    private int maxPitch;
    private int minPitch;

    void startAnalysis(final Player p) {
        this.p = p;
        final Storage storage = PlayerData.playerStorageHashMap.get(p);
        this.trailLength = storage.trailLength;
        this.trailX = storage.getTrailX();
        this.trailY = storage.getTrailY();
        this.trailZ = storage.getTrailZ();
        this.trailYaw = storage.getTrailYaw();
        this.trailPitch = storage.getTrailPitch();

            /*
            Calculate current trail
             */
        trailCalculation(storage);
    }

    private void trailCalculation(final Storage storage) {
        // initialize them with 0
        avgDistanceTrailX = 0;
        avgDistanceTrailY = 0;
        avgDistanceTrailZ = 0;
        avgMoveTrailYaw = 0;
        avgMoveTrailPitch = 0;
        maxX = trailX[0];
        minX = trailX[0];
        maxY = trailY[0];
        minY = trailY[0];
        maxZ = trailZ[0];
        minZ = trailZ[0];
        maxYaw = trailYaw[0];
        minYaw = trailYaw[0];
        maxPitch = trailPitch[0];
        minPitch = trailPitch[0];

        // Get average distance, max value, min value
        for (int i = 0; i < trailLength; i++) {
            // Max
            if (trailX[i] > maxX) maxX = trailX[i];
            if (trailY[i] > maxY) maxY = trailY[i];
            if (trailZ[i] > maxZ) maxZ = trailZ[i];
            if (trailYaw[i] > maxYaw) maxYaw = trailYaw[i];
            if (trailPitch[i] > maxPitch) maxPitch = trailPitch[i];

            // Min
            if (trailX[i] < minX) minX = trailX[i];
            if (trailY[i] < minY) minY = trailY[i];
            if (trailZ[i] < minZ) minZ = trailZ[i];
            if (trailYaw[i] < minYaw) minYaw = trailYaw[i];
            if (trailPitch[i] < minPitch) minPitch = trailPitch[i];

            // Average
            avgDistanceTrailX += trailX[i];
            avgDistanceTrailY += trailY[i];
            avgDistanceTrailZ += trailZ[i];
            avgMoveTrailYaw += trailYaw[i];
            avgMoveTrailPitch += trailPitch[i];
        }
        // Average
        avgDistanceTrailX /= trailLength;
        avgDistanceTrailY /= trailLength;
        avgDistanceTrailZ /= trailLength;
        avgMoveTrailYaw /= trailLength;
        avgMoveTrailPitch /= trailLength;

        // Analise data
        trailAnalysis(storage);

        // Replace old data with new data
        storage.avgDistanceTrailX = this.avgDistanceTrailX;
        storage.avgDistanceTrailY = this.avgDistanceTrailY;
        storage.avgDistanceTrailZ = this.avgDistanceTrailZ;
        storage.avgMoveTrailYaw = this.avgMoveTrailYaw;
        storage.avgMoveTrailPitch = this.avgMoveTrailPitch;

        storage.maxX = this.maxX;
        storage.maxY = this.maxY;
        storage.maxZ = this.maxZ;
        storage.maxYaw = this.maxYaw;
        storage.maxPitch = this.maxPitch;

        storage.minX = this.minX;
        storage.minY = this.minY;
        storage.minZ = this.minZ;
        storage.minYaw = this.minYaw;
        storage.minPitch = this.minPitch;
    }

    private void trailAnalysis(final Storage storage) {
        // get max values
        int xMax = Math.abs(storage.maxX - this.maxX);
        int yMax = Math.abs(storage.maxY - this.maxY);
        int zMax = Math.abs(storage.maxZ - this.maxZ);
        int yawMax = Math.abs(storage.maxYaw - this.maxYaw);
        int pitchMax = Math.abs(storage.maxPitch - this.maxPitch);

        // get min values
        int xMin = Math.abs(storage.minX - this.minX);
        int yMin = Math.abs(storage.minY - this.minY);
        int zMin = Math.abs(storage.minZ - this.minZ);
        int yawMin = Math.abs(storage.minYaw - this.minYaw);
        int pitchMin = Math.abs(storage.minPitch - this.minPitch);

        // get avg values
        int xAvg = Math.abs(storage.avgDistanceTrailX - this.avgDistanceTrailX);
        int yAvg = Math.abs(storage.avgDistanceTrailY - this.avgDistanceTrailY);
        int zAvg = Math.abs(storage.avgDistanceTrailZ - this.avgDistanceTrailZ);
        int yawAvg = Math.abs(storage.avgMoveTrailYaw - this.avgMoveTrailYaw);
        int pitchAvg = Math.abs(storage.avgMoveTrailPitch - this.avgMoveTrailPitch);


        // checks
        int checksFailed = 0;

        if (xAvg <= Core.gCore.settings.xAvg) checksFailed++;
        if (yAvg <= Core.gCore.settings.yAvg) checksFailed++;
        if (zAvg <= Core.gCore.settings.zAvg) checksFailed++;
        if (yawAvg <= Core.gCore.settings.yawAvg) checksFailed++;
        if (pitchAvg <= Core.gCore.settings.pitchAvg) checksFailed++;

        if (xMin <= Core.gCore.settings.xMin) checksFailed++;
        if (yMin <= Core.gCore.settings.yMin) checksFailed++;
        if (zMin <= Core.gCore.settings.zMin) checksFailed++;
        if (yawMin <= Core.gCore.settings.yawMin) checksFailed++;
        if (pitchMin <= Core.gCore.settings.pitchMin) checksFailed++;

        if (xMax <= Core.gCore.settings.xMax) checksFailed++;
        if (yMax <= Core.gCore.settings.yMax) checksFailed++;
        if (zMax <= Core.gCore.settings.zMax) checksFailed++;
        if (yawMax <= Core.gCore.settings.yawMax) checksFailed++;
        if (pitchMax <= Core.gCore.settings.pitchMax) checksFailed++;

        // print to console if debug is on
        if (Core.gCore.debug.useDebug) {
            System.out.println(" ");

            System.out.println("@AsyncSettings(max, min, avg){" +
                    "x[" + Core.gCore.settings.xMax + ", " + Core.gCore.settings.xMin + ", " + Core.gCore.settings.xAvg + "] " +
                    "y[" + Core.gCore.settings.yMax + ", " + Core.gCore.settings.yMin + ", " + Core.gCore.settings.yAvg + "] " +
                    "z[" + Core.gCore.settings.zMax + ", " + Core.gCore.settings.zMin + ", " + Core.gCore.settings.zAvg + "] " +
                    "yaw[" + Core.gCore.settings.yawMax + ", " + Core.gCore.settings.yawMin + ", " + Core.gCore.settings.yawAvg + "] " +
                    "pitch[" + Core.gCore.settings.pitchMax + ", " + Core.gCore.settings.pitchMin + ", " + Core.gCore.settings.pitchAvg + "]"
                    + "}");

            System.out.println("@AsyncData(" + p.getName() + ") -> (max, min, avg){" +
                    "x[" + xMax + ", " + xMin + ", " + xAvg + "] " +
                    "y[" + yMax + ", " + yMin + ", " + yAvg + "] " +
                    "z[" + zMax + ", " + zMin + ", " + zAvg + "] " +
                    "yaw[" + yawMax + ", " + yawMin + ", " + yawAvg + "] " +
                    "pitch[" + pitchMax + ", " + pitchMin + ", " + pitchAvg + "]"
                    + "}");
        }

        if (checksFailed >= Core.gCore.settings.points) {
            storage.afkTime += 15;
            addToAfkList(storage);
            // print to console if debug is on
            if (Core.gCore.debug.useDebug) System.out.println("@AsyncTime(afkTime){" + storage.afkTime + "}");
            return;
        }
        // if player passes all checks time sets to 0
        storage.afkTime = 0;
        removeFromAfkList();
    }

    private void addToAfkList(final Storage storage) {
        if (!(storage.afkTime >= Core.gCore.settings.kickTime)) return;
        if (!Core.gCore.playerData.kickList.contains(p)) Core.gCore.playerData.kickList.add(p);
    }

    private void removeFromAfkList() {
        Core.gCore.playerData.kickList.remove(p);
    }

}
