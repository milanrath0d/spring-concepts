package org.milan.database;

import org.milan.model.Message;
import org.milan.model.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory database for message & profile
 *
 * @author Milan Rathod
 */
public class Database {

    private static Map<Long, Message> messages = new HashMap<>();

    private static Map<String, Profile> profiles = new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }
}
