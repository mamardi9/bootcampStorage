package com.bootcampstorageult.bootcampstorageult.repository;

import com.bootcampstorageult.bootcampstorageult.entity.Document;
import com.bootcampstorageult.bootcampstorageult.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID>, JpaSpecificationExecutor<Document> {
    List<Document> findByUsuario(Usuario usuario); // Obtener documentos de un usuario
}
