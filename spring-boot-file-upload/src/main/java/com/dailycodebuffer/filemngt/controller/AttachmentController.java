package com.dailycodebuffer.filemngt.controller;

import com.dailycodebuffer.filemngt.ResponseData;
import com.dailycodebuffer.filemngt.entity.Attachment;
import com.dailycodebuffer.filemngt.service.AttachmentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AttachmentController {

    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }


    // New Method: Delete file by Id
    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileId) throws Exception {
        attachmentService.deleteAttachment(fileId);
        return ResponseEntity.ok("File deleted successfully");
    }



//    @PostMapping("/upload")
//    public ResponseData uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
//        Attachment attachment = attachmentService.saveAttachment(file);
//        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/download/")
//                .path(String.valueOf(attachment.getId()))
//                .toUriString();
//
//        return new ResponseData(
//                attachment.getId(), // Add this
//                attachment.getFileName(),
//                downloadURL,
//                file.getContentType(),
//                file.getSize(),
//                attachment.getUploadDate(),
//                attachment.getUploadTime(),
//                attachment.getUploadDurationMillis()
//        );
//
//    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        if (fileId == null || fileId.isEmpty()) {
            throw new Exception("File ID is required");
        }
        Attachment attachment = attachmentService.getAttachment(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

    @GetMapping("/search")
    public List<ResponseData> searchFiles(@RequestParam("query") String query) {
        return attachmentService.searchFiles(query)
                .stream()
                .map(attachment -> new ResponseData(
                        attachment.getId(), // Add this
                        attachment.getFileName(),
                        ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/download/")
                                .path(String.valueOf(attachment.getId()))
                                .toUriString(),
                        attachment.getFileType(),
                        attachment.getData().length,
                        attachment.getUploadDate(),
                        attachment.getUploadTime(),
                        attachment.getUploadDurationMillis()
                ))


                .collect(Collectors.toList());
    }

    // New Method: Show all files
    @GetMapping("/files")
    public List<ResponseData> showAllFiles() {
        return attachmentService.getAllFiles()
                .stream()
                .map(attachment -> new ResponseData(
                        attachment.getId(), // Add this
                        attachment.getFileName(),
                        ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/download/")
                                .path(String.valueOf(attachment.getId()))
                                .toUriString(),
                        attachment.getFileType(),
                        attachment.getData().length,
                        attachment.getUploadDate(),
                        attachment.getUploadTime(),
                        attachment.getUploadDurationMillis()
                ))


                .collect(Collectors.toList());
    }

    // New Method: Filter files by type
    @GetMapping("/filter")
    public List<ResponseData> filterByFileType(@RequestParam("type") String type) {
        return attachmentService.getAllFiles()
                .stream()
                .filter(attachment -> matchFileType(type, attachment.getFileType()))
                .map(attachment -> new ResponseData(
                        attachment.getId(), // Add this
                        attachment.getFileName(),
                        ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/download/")
                                .path(String.valueOf(attachment.getId()))
                                .toUriString(),
                        attachment.getFileType(),
                        attachment.getData().length,
                        attachment.getUploadDate(),
                        attachment.getUploadTime(),
                        attachment.getUploadDurationMillis()
                ))


                .collect(Collectors.toList());
    }

    private boolean matchFileType(String type, String fileType) {
        switch (type.toLowerCase()) {
            case "pdf":
                return fileType.equalsIgnoreCase("application/pdf");
            case "doc":
                return fileType.equalsIgnoreCase("application/msword")
                        || fileType.contains("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            case "image":
                return fileType.startsWith("image/");
            case "other":
                return !(fileType.equalsIgnoreCase("application/pdf") ||
                        fileType.equalsIgnoreCase("application/msword") ||
                        fileType.startsWith("image/"));
            default:
                return false;
        }
    }

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        long startTime = System.currentTimeMillis(); // Capture start time
        Attachment attachment = attachmentService.saveAttachment(file);
        long endTime = System.currentTimeMillis(); // Capture end time
        long uploadDuration = endTime - startTime; // Calculate the duration

        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(attachment.getId()))
                .toUriString();

        return new ResponseData(
                attachment.getId(),
                attachment.getFileName(),
                downloadURL,
                file.getContentType(),
                file.getSize(),
                attachment.getUploadDate(),
                attachment.getUploadTime(),
                uploadDuration // Return the upload duration
        );
    }

    @PutMapping("/update/{fileId}")
    public ResponseData updateFile(@PathVariable String fileId, @RequestParam("file") MultipartFile newFile) throws Exception {
        Attachment updatedAttachment = attachmentService.updateAttachment(fileId, newFile);
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(updatedAttachment.getId()))
                .toUriString();

        return new ResponseData(
                updatedAttachment.getId(),
                updatedAttachment.getFileName(),
                downloadURL,
                newFile.getContentType(),
                newFile.getSize(),
                updatedAttachment.getUploadDate(),
                updatedAttachment.getUploadTime(),
                updatedAttachment.getUploadDurationMillis()
        );
    }



}
