package com.bootcampstorageult.bootcampstorageult.service;

import com.bootcampstorageult.bootcampstorageult.entity.Content;
import com.bootcampstorageult.bootcampstorageult.entity.Document;
import com.bootcampstorageult.bootcampstorageult.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;

    public Content save(MultipartFile file, Document document) throws IOException {
        Content content = new Content();
        content.setContent(file.getBytes());  // Guardamos el archivo como un arreglo de bytes
        content.setDocument(document);       // Asociamos el archivo con el documento
        content.setContentType(file.getContentType());
        return contentRepository.save(content);
    }

    // Metodo para obtener contenido por ID de documento
    public Optional<Content> findByDocumentId(UUID documentId) {
        return contentRepository.findByDocumentId(documentId);
    }
}
