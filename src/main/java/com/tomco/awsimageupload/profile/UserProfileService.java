package com.tomco.awsimageupload.profile;

import com.tomco.awsimageupload.bucket.BucketName;
import com.tomco.awsimageupload.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

// Business Logic can sit here. (Service Layer).
@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService; // Data-store access.
    private final FileStore fileStore;

    // Autowire/inject the data access service client, to access the data-store that has been configured/setup.
    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService; // Setup data-store access.
        this.fileStore = fileStore;
    }

    // Data Access method against data-store service client, to return a list of all items.
    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    // Method to check user by id exists in the DB/data-store, then get the user profile, by Java Steams.
    private UserProfile checkAndGetUserProfile(UUID userProfileId) {
        return userProfileDataAccessService.getUserProfiles()
                .stream()  // Abstraction phase. Stream<UserProfile>
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User Profile %s not found", userProfileId)));
    }

    // Upload file to AWS S3 Bucket.
    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {

        // Check id exists in data-store and get user profile, by Java Steams.
        UserProfile user = checkAndGetUserProfile(userProfileId); // Throw error, if no user found.

        // Error handling for empty file upload attempt, or not an image file.
        if (file.isEmpty()) throw new IllegalStateException("Cannot Upload Empty File: [" + file.getSize() + "bytes ]");
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType())
                .contains(file.getContentType())) throw new IllegalStateException("File Must Be An Image. You uploaded [" + file.getContentType() + "]");

        // Extract uploaded file metadata.
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        // Store the image in S3 and update database/userProfileImageLink, with S3 Image Link (Path+Filename).
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId()); // Path+FileName = S3 Image URL/Link.
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID()); // Path+FileName = S3 Image URL/Link.
        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream()); // Save image
            user.setUserProfileImageLink(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    // Download file from AWS S3 Bucket.
    public byte[] downloadUserProfileImage(UUID userProfileId) {

        // Check id exists and return corresponding user profile, then build target resource endpoint (excluding S3 Key, the last file uploaded).
        UserProfile user = checkAndGetUserProfile(userProfileId);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getUserProfileId());

        // Get S3 Key, the most recently uploaded image file to the bucket. Download specific image from the path.
        return user.getUserProfileImageLink()
                .map(key -> fileStore.dowload(path, key))
                .orElse (new byte[0]);
    }
}
