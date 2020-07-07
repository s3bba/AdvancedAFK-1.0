package me.sebbaindustries.advancedafk.utils;

import me.sebbaindustries.advancedafk.Core;
import org.apache.commons.lang.BooleanUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sebbaindustries
 * @version 1.0
 */
public final class Message {

    /**
     * map containing all messages
     */
    private final Map<Integer, String> messages = new HashMap<>();

    /**
     * gets message from map
     *
     * @param message Message enum
     * @return Translated message from memory
     * @see M message enum
     */
    public final String getMessage(final M message) {
        return messages.get(message.value);
    }

    /**
     * Reloads messages and loads them in HashMao
     */
    public final void reloadMessages() {
        messages.clear();
        for (final M ID : M.values()) {
            messages.put(ID.value, getMessage(ID.value));
        }
    }

    /**
     * Gets prefix for messages
     *
     * @return formatted prefix
     */
    private String getPrefix() {
        try {
            final XMLInputFactory iFactory = XMLInputFactory.newInstance();
            final XMLStreamReader sReader = iFactory.createXMLStreamReader(new FileReader(Core.gCore.fileManager.messages));
            while (sReader.hasNext()) {
                sReader.next();
                if (sReader.getEventType() == XMLStreamReader.START_ELEMENT) {
                    if (sReader.getLocalName().equalsIgnoreCase("prefix")) {
                        return readPrefix(sReader);
                    }
                }
            }
            // Tag or attribute not found
            return "$ERROR_NOT_FOUND";
        } catch (final XMLStreamException | FileNotFoundException ex) {
            ex.printStackTrace();
            return "$ERROR_STACK";
        }
    }

    /**
     * Reads all messages inside <msg></msg> element and looks for right language
     *
     * @param sReader Instance
     * @return String with right message or null if it's not found
     * @throws XMLStreamException Handled in public method getPrefix()
     */
    private String readPrefix(final XMLStreamReader sReader) throws XMLStreamException {
        while (sReader.hasNext()) {
            //Move to next event
            sReader.next();

            //Check if its 'START_ELEMENT'
            if (sReader.getEventType() == XMLStreamReader.START_ELEMENT) {
                //msg tag - opened
                if (sReader.getLocalName().equalsIgnoreCase("msg")) {
                    // return sReader.getElementText();
                    if (sReader.getAttributeCount() > 0) {
                        // checks if element has lang attribute
                        final String id = sReader.getAttributeValue(null, "lang");

                        // if attribute is same as lang tag it returns a message
                        if (id.equalsIgnoreCase(Core.gCore.lang.LANG)) return Color.chat(sReader.getElementText());
                    }
                }
            }
        }
        return "$ERROR_NOT_FOUND";
    }


    /**
     * Gets right message by ID, in a <message id="N"></message> tag
     *
     * @param messageID Integer
     * @return formatted message
     */
    private String getMessage(final int messageID) {
        try {
            final XMLInputFactory iFactory = XMLInputFactory.newInstance();
            final XMLStreamReader sReader = iFactory.createXMLStreamReader(new FileReader(Core.gCore.fileManager.messages));
            while (sReader.hasNext()) {
                //Move to next event
                sReader.next();

                //Check if its 'START_ELEMENT'
                if (sReader.getEventType() == XMLStreamReader.START_ELEMENT) {
                    //message tag - opened
                    if (sReader.getLocalName().equalsIgnoreCase("message")) {

                        //Read attributes within message tag
                        if (sReader.getAttributeCount() > 0) {
                            final String id = sReader.getAttributeValue(null, "id");

                            // check if id is same to message name
                            if (messageID == Integer.parseInt(id)) {
                                return readMessage(sReader);
                            }
                        }
                    }
                }
            }
            return "$ERROR_NOT_FOUND";
        } catch (final XMLStreamException | FileNotFoundException ex) {
            ex.printStackTrace();
            return "$ERROR_STACK";
        }
    }

    /**
     * Reads all messages inside <msg></msg> element and looks for right language
     *
     * @param sReader Instance
     * @return String with right message or null if it's not found
     * @throws XMLStreamException Handled in getMessage()
     */
    private String readMessage(final XMLStreamReader sReader) throws XMLStreamException {
        while (sReader.hasNext()) {
            //Move to next event
            sReader.next();

            //Check if its 'START_ELEMENT'
            if (sReader.getEventType() == XMLStreamReader.START_ELEMENT) {
                //msg tag - opened
                if (sReader.getLocalName().equalsIgnoreCase("msg")) {
                    // return sReader.getElementText();
                    if (sReader.getAttributeCount() > 0) {
                        // checks if element has lang attribute
                        final String lang = sReader.getAttributeValue(null, "lang");
                        // FOR ANYONE READING THIS: USE BooleanUtils by Apache, life savers tbh -Sebba
                        final boolean prefix = BooleanUtils.toBoolean(sReader.getAttributeValue(null, "prefix"));

                        // if attribute is same as lang tag it returns a message
                        if (lang.equalsIgnoreCase(Core.gCore.lang.LANG)) {
                            if (prefix) {
                                return Color.chat(getPrefix() + sReader.getElementText());
                            }
                            return Color.chat(sReader.getElementText());
                        }
                    }
                }
            }
        }
        return "$ERROR_NOT_FOUND";
    }

    /**
     * @param tag like ID, but in string form -> #N
     * @return list of help messages
     * @deprecated may get used in the feature
     */
    @Deprecated
    private List<String> getHelpList(String tag) {
        final List<String> helpList = new ArrayList<>();
        tag = "help line " + tag;
        try {
            final XMLInputFactory iFactory = XMLInputFactory.newInstance();
            final XMLStreamReader sReader = iFactory.createXMLStreamReader(new FileReader(Core.gCore.fileManager.messages));
            while (sReader.hasNext()) {
                //Move to next event
                sReader.next();

                //Check if its 'START_ELEMENT'
                if (sReader.getEventType() == XMLStreamReader.START_ELEMENT) {
                    //message tag - opened
                    if (sReader.getLocalName().equalsIgnoreCase("message")) {

                        //Read attributes within message tag
                        if (sReader.getAttributeCount() > 0) {
                            final String id = sReader.getAttributeValue(null, "id");

                            // check if id is same to message name
                            if (tag.equalsIgnoreCase(id)) {
                                helpList.add(readMessage(sReader));
                            }
                        }
                    }
                }
            }
            return helpList;
        } catch (final XMLStreamException | FileNotFoundException ex) {
            ex.printStackTrace();
            return Collections.singletonList("$ERROR_STACK");
        }
    }

    /**
     * Enums for messages, they just point to id
     */
    public enum M {

        noPermission(0),
        noConsoleSupport(1),
        kickReason(2),
        cleanupHelp(3),
        cleanupStart(4),
        cleanupKickedPlayer(5),
        cleanupKick(6),
        cleanupEnd(7),
        listHelp(8),
        listLS_Flag(9),
        listLSALL_flag(10),
        listNoPlayers(11),
        reloadStart(12),
        reloadEnd(13),
        reloadFailed(14),
        targetNotFound(15),
        targetNotSpecified(16),
        missingArguments(17),
        lookupHelp(18),
        lookup(19),
        debugHelp(20);

        private final int value;

        M(final int value) {
            this.value = value;
        }
    }

}
