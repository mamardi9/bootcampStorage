package com.bootcampstorageult.bootcampstorageult.repository;

import com.bootcampstorageult.bootcampstorageult.entity.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MetadataRepository extends JpaRepository<Metadata, UUID> {
}
