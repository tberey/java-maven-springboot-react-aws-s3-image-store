package com.tomco.awsimageupload.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

// Requests controller/routing, against the endpoint "<hostname>:<port>/api/v1/user-profile". (API/Controller Level).
@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("*") // Allow everything.
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired // Autowire/inject in our UserProfileService, to access data layers.
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping // GET Request, to our api endpoint mapping. Data Access, to return all profiles stored.
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }

    // POST Request, to store the post/sent file in the request to our AWS S3 bucket.
    @PostMapping(
            path = "{userProfileId}/image/upload", // POST to 'http://localhost:8080/api/v1/user-profile/${userProfileId}/image/upload'.
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId,
                                       @RequestParam("file")MultipartFile file) {
        userProfileService.uploadUserProfileImage(userProfileId, file);
    }

    // GET Request, to download the most recently uploaded image, from the user profile.
    @GetMapping("{userProfileId}/image/download") // GET to 'http://localhost:8080/api/v1/user-profile/${userProfileId}/image/upload'.
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId) {
        return userProfileService.downloadUserProfileImage(userProfileId);
    }
}
