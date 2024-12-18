package com.dailycodebuffer.filemngt.repository;

import com.dailycodebuffer.filemngt.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}

//Why Extend an Interface Instead of Writing Code?
//When you extend JpaRepository, Spring Data JPA auto-generates the implementation of these methods at runtime.
// You don't need to manually implement these methods, as Spring handles it dynamically.

