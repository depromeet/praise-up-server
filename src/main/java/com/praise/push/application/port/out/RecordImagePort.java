package com.praise.push.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface RecordImagePort {
    String uploadImage(String domain, MultipartFile file);
}
