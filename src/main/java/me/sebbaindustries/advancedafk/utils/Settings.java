package me.sebbaindustries.advancedafk.utils;

import me.sebbaindustries.advancedafk.Core;
import org.jetbrains.annotations.NotNull;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author sebbaindustries
 * @version 1.0
 * @see XMLInputFactory as File factory
 * @see XMLStreamReader as stream reader for XML file
 */
public class Settings {

    /**
     * Prepares XML file and tries to find elementName (<b>>tag<</b>) and attributeName(<tag <b>attr="value"</b>)
     *
     * @param elementName   name of a element that contains data we need
     * @param attributeName name of a attribute that contains data we need
     * @return Value of a attribute, if not found <b>$ERROR_NOT_FOUND</b> and if file encounters an exception <b>$ERROR_STACK</b>
     */
    private String prepareXML(@NotNull final String elementName, @NotNull final String attributeName) {
        try {
            XMLInputFactory iFactory = XMLInputFactory.newInstance();
            XMLStreamReader sReader = iFactory.createXMLStreamReader(new FileReader(Core.gCore.fileManager.settings));
            while (sReader.hasNext()) {
                //Move to next event
                sReader.next();

                //Check if its 'START_ELEMENT'
                if (sReader.getEventType() == XMLStreamReader.START_ELEMENT) {
                    //message tag - opened
                    if (sReader.getLocalName().equalsIgnoreCase(elementName)) {

                        //Read attributes within message tag
                        if (sReader.getAttributeCount() > 0) {
                            final String s = sReader.getAttributeValue(null, attributeName);
                            sReader.close();
                            return s;
                        }
                    }
                }
            }
            sReader.close();
            // Tag or attribute not found
            return "$ERROR_NOT_FOUND";
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
            return "$ERROR_STACK";
        }
    }

    /**
     * Reloads all settings, called when class is created or command is run (/aafk reload)
     */
    public void reloadSettings() {
        this.trail = getTrail();
        this.points = getPoints();

        this.xMax = getX(value.MAX);
        this.xMin = getX(value.MIN);
        this.xAvg = getX(value.AVG);

        this.yMax = getY(value.MAX);
        this.yMin = getY(value.MIN);
        this.yAvg = getY(value.AVG);

        this.zMax = getZ(value.MAX);
        this.zMin = getZ(value.MIN);
        this.zAvg = getZ(value.AVG);

        this.yawMax = getYaw(value.MAX);
        this.yawMin = getYaw(value.MIN);
        this.yawAvg = getYaw(value.AVG);

        this.pitchMax = getPitch(value.MAX);
        this.pitchMin = getPitch(value.MIN);
        this.pitchAvg = getPitch(value.AVG);

        this.kickTime = getKickTime();
        this.maxOnlinePlayers = getMaxOnlinePlayers();
    }

    public int trail = 16;

    /**
     * Gets trail from XML file
     *
     * @return trail value
     */
    private int getTrail() {
        return Integer.parseInt(prepareXML("detection", "trail"));
    }

    public int points = 10;

    /**
     * Gets points from XML file
     *
     * @return points value
     */
    private int getPoints() {
        return Integer.parseInt(prepareXML("detection", "points"));
    }

    public int xMax = 1;
    public int xMin = 1;
    public int xAvg = 1;

    /**
     * Gets x value with specified enum from XML file
     *
     * @param value Enum with different states
     * @return x value with value of an enums type
     * @see value
     */
    private int getX(final @NotNull value value) {
        return Integer.parseInt(prepareXML("x", value.toString().toLowerCase()));
    }

    public int yMax = 1;
    public int yMin = 1;
    public int yAvg = 1;

    /**
     * Gets y value with specified enum from XML file
     *
     * @param value Enum with different states
     * @return y value with value of an enums type
     * @see value
     */
    private int getY(final @NotNull value value) {
        return Integer.parseInt(prepareXML("y", value.toString().toLowerCase()));
    }

    public int zMax = 1;
    public int zMin = 1;
    public int zAvg = 1;

    /**
     * Gets z value with specified enum from XML file
     *
     * @param value Enum with different states
     * @return z value with value of an enums type
     * @see value
     */
    private int getZ(final @NotNull value value) {
        return Integer.parseInt(prepareXML("z", value.toString().toLowerCase()));
    }

    public int yawMax = 1;
    public int yawMin = 1;
    public int yawAvg = 1;

    /**
     * Gets yaw value with specified enum from XML file
     *
     * @param value Enum with different states
     * @return yaw value with value of an enums type
     * @see value
     */
    private int getYaw(final @NotNull value value) {
        return Integer.parseInt(prepareXML("yaw", value.toString().toLowerCase()));
    }

    public int pitchMax = 1;
    public int pitchMin = 1;
    public int pitchAvg = 1;

    /**
     * Gets pitch value with specified enum from XML file
     *
     * @param value Enum with different states
     * @return pitch value with value of an enums type
     * @see value
     */
    private int getPitch(final @NotNull value value) {
        return Integer.parseInt(prepareXML("pitch", value.toString().toLowerCase()));
    }

    public int kickTime = 1;

    /**
     * Gets kick time from XML file
     *
     * @return points value
     */
    private int getKickTime() {
        return Integer.parseInt(prepareXML("kick", "time"));
    }

    public int maxOnlinePlayers = 1;

    /**
     * Gets max online players from XML file
     *
     * @return points value
     */
    private int getMaxOnlinePlayers() {
        return Integer.parseInt(prepareXML("players", "online"));
    }

    /**
     * Values for coordinates, made for easier use
     */
    private enum value {
        MAX("max"),
        MIN("min"),
        AVG("avg"),
        ;

        value(final String value) {
        }
    }

}
