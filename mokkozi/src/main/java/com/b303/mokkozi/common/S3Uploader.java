package com.b303.mokkozi.common;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;


    // Bucket 이름
    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    // S3에 파일 업로드하기.
    public String uploadToS3(MultipartFile file, String fileName) throws IOException {
       // 1. 프론트에서 받은 MultipartFile을 File형태로 변환한다.

        // getOriginalFilename : 사용자가 올린 최초 파일의 이름을 반환한다.
        // 이 때 file의 getOriginalFilename이 Null일 수 있다.

        File fileToUpload = new File(file.getOriginalFilename());
        try {
            file.transferTo(fileToUpload);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        // fileName은 S3 서버에 업로드할 파일의 이름이다.
        String fileNameToUpload = fileName + "/" + UUID.randomUUID() + fileToUpload.getName();
        // S3에 업로드한 후, 업로드한 주소를 반환한다.
        return putS3(fileToUpload, fileNameToUpload);
    }

    public String putS3(File file, String fileName) {
        // bucket : S3에 생성한 버킷 이름
        // fileName : S3 서버에 올릴 파일의 이름.
        // file : S3 서버에 올릴 파일.
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, file)
                // 파일의 권한을 설정한다. public으로 사용하여 누구나 읽고 쓸 수 있게 한다.
                .withCannedAcl(CannedAccessControlList.PublicReadWrite));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }
}
