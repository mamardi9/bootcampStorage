package com.bootcampstorageult.bootcampstorageult.repository;

import com.bootcampstorageult.bootcampstorageult.entity.Content;
import com.bootcampstorageult.bootcampstorageult.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
    Optional<Content> findByDocumentId(UUID documentId);
}
