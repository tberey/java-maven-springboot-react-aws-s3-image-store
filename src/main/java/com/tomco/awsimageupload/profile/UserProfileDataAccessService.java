package com.tomco.awsimageupload.profile;

import com.tomco.awsimageupload.datastore.FakeUserProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

// Class to supply the data-store/db infrastructure, via Dependency Injection, for our data access service/client. Implement available actions/methods for our autowired db. (Data Access Layer).
@Repository
public class UserProfileDataAccessService {

    private final FakeUserProfileDataStore fakeUserProfileDataStore;

    @Autowired // Dependency Injection, instantiate an instance of FakeUserProfileDataStore Repository class (our db/data-store). Allows us to change DB/Data-store here.
    public UserProfileDataAccessService(FakeUserProfileDataStore fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
    }

    List<UserProfile> getUserProfiles() {
        return fakeUserProfileDataStore.getUserProfiles();
    }
}
