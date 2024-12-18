package com.dailycodebuffer.filemngt.service;

import com.dailycodebuffer.filemngt.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


public interface AttachmentService {

    void deleteAttachment(String fileId) throws Exception;

    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;

    List<Attachment> searchFiles(String query);

    List<Attachment> getAllFiles();

    List<Attachment> filterFilesByType(String type);
}
