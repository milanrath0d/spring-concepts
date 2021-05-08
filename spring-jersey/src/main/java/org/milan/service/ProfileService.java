package org.milan.service;

import org.milan.database.Database;
import org.milan.model.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Milan Rathod
 */
@Service
public class ProfileService {

    private final Map<String, Profile> profiles = Database.getProfiles();

    public ProfileService() {
        profiles.put("milan", new Profile(1L, "milan", "milan", "rathod"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile deleteProfile(String profileName) {
        return profiles.remove(profileName);
    }

}
