package com.bootcampstorageult.bootcampstorageult.service;

import com.bootcampstorageult.bootcampstorageult.entity.Document;
import com.bootcampstorageult.bootcampstorageult.entity.Metadata;
import com.bootcampstorageult.bootcampstorageult.repository.DocumentRepository;
import com.bootcampstorageult.bootcampstorageult.repository.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MetadataService {

    @Autowired
    private MetadataRepository metadataRepository;

    @Autowired
    private DocumentRepository documentRepository;

    // Obtener todos los metadatos de un documento
    public List<Metadata> getMetadataByDocument(UUID documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return document.getMetadata();
    }

    // Crear un nuevo metadato
    public Metadata createMetadata(UUID documentId, String name, String value) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        Metadata metadata = new Metadata();
        metadata.setName(name);
        metadata.setValor(value);
        metadata.setDocument(document);

        return metadataRepository.save(metadata);
    }

    // Eliminar un metadato
    public void deleteMetadata(UUID metadataId) {
        metadataRepository.deleteById(metadataId);
    }
}

