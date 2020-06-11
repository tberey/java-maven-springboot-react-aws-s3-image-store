package com.tomco.awsimageupload.datastore;

import com.tomco.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Fake data-store actions to store data in our in-memory database (List), via our API & Service layers. (Data Access Layer).
@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("30bbce58-4221-4d8e-a791-723399742493"), "Test User1", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("04c02063-04ad-43bc-a764-1905914d0127"), "Test User2", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
