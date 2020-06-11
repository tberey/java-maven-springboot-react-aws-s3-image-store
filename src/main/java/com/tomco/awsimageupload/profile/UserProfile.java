package com.tomco.awsimageupload.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

// UserProfile Model class. This is the data object model we will use to pass data to store in our chosen db. (Data Access Layer).
public class UserProfile {

    private final UUID userProfileId;
    private final String username;
    private String userProfileImageLink; // S3 Key, corresponding to user's stored image(s). Always latest image uploaded by a user profile. Not final, so we can set method on it.

    public UserProfile(UUID userProfileId, String username, String userProfileImageLink) {
        this.userProfileId = userProfileId;
        this.username = username;
        this.userProfileImageLink = userProfileImageLink;
    }

    public UUID getUserProfileId() {
        return userProfileId;
    }

    public String getUsername() {
        return username;
    }

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(userProfileImageLink);
    }

    public void setUserProfileImageLink(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileId, that.userProfileId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(userProfileImageLink, that.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, username, userProfileImageLink);
    }
}
