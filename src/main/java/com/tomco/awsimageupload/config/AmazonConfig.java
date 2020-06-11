package com.tomco.awsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Provide AWS Credentials and config to build an AWS S3 Client. (Service Layer).
@Configuration
public class AmazonConfig {

    @Bean // Instantiate AmazonS3 class as a Bean. Provide correct credentials to build our S3 client, and access bucket.
    public AmazonS3 S3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAJSU3OTGJCVSAECB","GXemB1BsoVY4U0If4cIl3+f9LL5oHu8G8JOnTqL");
        return AmazonS3ClientBuilder.standard()
                .withRegion("eu-west-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
