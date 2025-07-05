package com.bootcampstorageult.bootcampstorageult.service;

import com.bootcampstorageult.bootcampstorageult.entity.Document;
import com.bootcampstorageult.bootcampstorageult.entity.Usuario;
import com.bootcampstorageult.bootcampstorageult.repository.ContentRepository;
import com.bootcampstorageult.bootcampstorageult.repository.DocumentRepository;
import com.bootcampstorageult.bootcampstorageult.repository.MetadataRepository;
import com.bootcampstorageult.bootcampstorageult.repository.UserRepository;
import com.bootcampstorageult.bootcampstorageult.specification.DocumentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MetadataRepository metadataRepository;

    @Autowired
    private ContentRepository contentRepository;

    // Crear un documento y asociarlo a un usuario
    public Document createDocument(String name, String description, long size, String username) {
        Usuario user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Document document = new Document();
        document.setName(name);
        document.setDescription(description);
        document.setSize(size);
        document.setUsuario(user);

        return documentRepository.save(document);
    }

    // Obtener todos los documentos de un usuario
    public List<Document> getAllDocuments(String username) {
        Usuario user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return documentRepository.findByUsuario(user);
    }

    public Optional<Document> findById(UUID id) {
        return documentRepository.findById(id);  // Usa el metodo findById proporcionado por JpaRepository
    }

    public List<Document> searchDocuments(String name, String description, Long size, String metadataKey, String metadataValue) {
        return documentRepository.findAll(DocumentSpecification.search(name, description, size, metadataKey, metadataValue));
    }

    @Transactional
    public void deleteDocument(UUID documentId) {
        // Buscar el documento por ID
        Document document = documentRepository.findById(documentId).orElseThrow(() -> new RuntimeException("Document not found"));

        // Eliminar los metadatos asociados
        metadataRepository.deleteAll(document.getMetadata());

        // Eliminar el contenido asociado
        contentRepository.deleteAll(document.getContents());

        // Eliminar el documento
        documentRepository.delete(document);
    }

    public Document findDocumentById(UUID id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid document Id:" + id));
    }

    public Document updateDocument(UUID id, String name, String description) {
        Document document = findDocumentById(id); // Buscar el documento
        document.setName(name);
        document.setDescription(description);
        return documentRepository.save(document); // Guardar el documento actualizado
    }
}
