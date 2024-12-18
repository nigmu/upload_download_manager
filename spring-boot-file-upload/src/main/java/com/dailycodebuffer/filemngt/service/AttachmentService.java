package com.dailycodebuffer.filemngt.service;

import com.dailycodebuffer.filemngt.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;
//It is part of the org.springframework.web.multipart package and
// is commonly used in Spring MVC for receiving files from HTTP requests
//When you upload a file using a form or API endpoint with multipart/form-data content type,
//Spring binds the uploaded file to a MultipartFile object in the controller or service.
// getName() , isEmpty(), etc ...
import java.util.List;


public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;

    List<Attachment> searchFiles(String query);

    List<Attachment> getAllFiles();

    List<Attachment> filterFilesByType(String type);
}
