package com.bootcampstorageult.bootcampstorageult.controller;

import com.bootcampstorageult.bootcampstorageult.entity.Content;
import com.bootcampstorageult.bootcampstorageult.entity.Document;
import com.bootcampstorageult.bootcampstorageult.service.ContentService;
import com.bootcampstorageult.bootcampstorageult.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.Base64;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private DocumentService documentService;

    // Ver el contenido de un documento específico
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadContent(@PathVariable("id") UUID documentId) {
        // Obtener el contenido asociado al documento
        Content content = contentService.findByDocumentId(documentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));
        // Configurar los encabezados para la descarga
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(content.getContentType()));
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("downloaded-file") // Puedes cambiarlo al nombre deseado
                .build());

        // Retornar el archivo como un arreglo de bytes
        return ResponseEntity.ok()
                .headers(headers)
                .body(content.getContent());
    }
}
