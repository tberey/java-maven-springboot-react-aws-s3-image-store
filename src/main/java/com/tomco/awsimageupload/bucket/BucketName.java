package com.tomco.awsimageupload.bucket;

// Define the AWS S3 Bucket that is going to be used here, as an enum. (Service Layer).
public enum BucketName {

    PROFILE_IMAGE("tomco-image-upload-123");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
