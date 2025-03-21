package com.godoc.service.common;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class S3Utils {

    @Value("${image.upload.bucket.name}")
    private static String fileUploadBucket;

    public static String uploadMultipartFileToS3(String path, MultipartFile file) throws Exception {

        if (file == null) {
            log.error("Empty file is being uploaded");
            throw new IllegalArgumentException("Empty file is being uploaded.");
        }

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();

        File imageFile = convertMultiPartToFile(file);

        s3Client.putObject(fileUploadBucket, path, imageFile);
        log.info(imageFile.delete() ? "Deleted file " : "Failed to delete file " + "after uploading to s3");

        return "s3://" + fileUploadBucket +"/" + path;
    }

    private static File convertMultiPartToFile(MultipartFile file) throws IOException {

        String fileName = StringUtils.isEmpty(file.getOriginalFilename()) ?
                String.valueOf(System.currentTimeMillis()) :
                file.getOriginalFilename();

        File convFile = new File(fileName);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
