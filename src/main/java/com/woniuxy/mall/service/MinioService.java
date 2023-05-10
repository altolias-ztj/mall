package com.woniuxy.mall.service;

import com.woniuxy.mall.config.MinioConfig;
import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class MinioService {
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioConfig minioConfig;

    public String uploadToMinIO(MultipartFile file) {
        PutObjectArgs objectArgs = null;
        String originalFileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "");
        String suffix = originalFileName.substring((originalFileName.lastIndexOf(".")));
        try {
            objectArgs = PutObjectArgs.builder().bucket(minioConfig.getBucketName()).object(file.getOriginalFilename())
                    .stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build();
            //文件名称相同会覆盖
            minioClient.putObject(objectArgs);
            String url = minioConfig.getEndpoint() + "/" + file.getOriginalFilename() +
                    minioConfig.getBucketName() + "/" + newFileName + suffix;
            GetPresignedObjectUrlArgs build = new GetPresignedObjectUrlArgs().builder().bucket("goods")
                    .object(newFileName + suffix).method(Method.GET).build();
            return url;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void test() {
        boolean found = false;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("goods").build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(found);
    }
}
