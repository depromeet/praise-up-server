package com.praise.push.adapter.out.persistence;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.praise.push.application.port.out.RecordImagePort;
import com.praise.push.common.constant.Names;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Component
class ImagePersistenceAdapter implements RecordImagePort {
    @Value("${cloud.naver.credentials.bucket}")
    private String bucket;

    @Value("${cloud.naver.credentials.end-point}")
    private String endPoint;

    private final AmazonS3 objectStorage;

    private static final Long MAX_FILE_SIZE = 1000000L;

    @Override
    public String uploadImage(String domain, MultipartFile file) {
        String fileName = createFileName(file.getOriginalFilename());
        String filePath = domain + "/" + getUploadYYMM() + "/" + fileName;
        String uploadFileUrl = endPoint + "/" + bucket + "/" + filePath;

        validateFileSize(file.getSize());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()){
            // object storage 폴더 및 파일 업로드
            objectStorage.putObject(new PutObjectRequest(bucket, filePath, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            // TODO: custom exception 추가
        }

        return uploadFileUrl;
    }

    private String getUploadYYMM() {
        LocalDate now = LocalDate.now();
        String year = String.valueOf(now.getYear()).substring(2);
        String month = translateMonthValue(now.getMonthValue());

        return year + month;
    }

    private String translateMonthValue(int monthValue) {
        if (monthValue == 11) {
            return String.valueOf(monthValue);
        }

        if (monthValue == 12) {
            return String.valueOf(monthValue);
        }

        return "0" + monthValue;
    }


    private void validateFileSize(long size) {
        if (size > MAX_FILE_SIZE) {
            throw new RuntimeException("File size is over 1MB");
        }
    }

    private String createFileName(String fileName) {
        String fileExtension = getFileExtension(fileName);
        validateFileExtension(fileExtension);

        return UUID.randomUUID().toString().concat(".").concat(fileExtension);
    }

    private void validateFileExtension(String fileExtension) {
        if (fileExtension.equals(Names.JPG_EXTENSION.getName())) {
            return;
        }

        if (fileExtension.equals(Names.JPEG_EXTENSION.getName())) {
            return;
        }

        if (fileExtension.equals(Names.PNG_EXTENSION.getName())) {
            return;
        }

        if (fileExtension.equals(Names.WEBP_EXTENSION.getName())) {
            return;
        }

        if (fileExtension.equals(Names.HEIC_EXTENSION.getName())) {
            return;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid FileExtension Exception");
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
}
