package com.bootcampstorageult.bootcampstorageult.controller;

import com.bootcampstorageult.bootcampstorageult.entity.Metadata;
import com.bootcampstorageult.bootcampstorageult.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/documents/{documentId}/metadata")
public class MetadataController {
    @Autowired
    private MetadataService metadataService;

    @GetMapping
    public String viewMetadata(@PathVariable("documentId") UUID documentId, Model model) {
        List<Metadata> metadata = metadataService.getMetadataByDocument(documentId);
        model.addAttribute("documentId", documentId);  // Pasar el id del documento
        model.addAttribute("metadata", metadata);
        return "view-metadata";  // Vista para mostrar los metadatos
    }

    // Crear un nuevo metadato para un documento
    @PostMapping
    public String createMetadata(@PathVariable("documentId") UUID documentId,
                                 @RequestParam("name") String name,
                                 @RequestParam("value") String value) {
        metadataService.createMetadata(documentId, name, value);
        return "redirect:/documents/" + documentId + "/metadata";  // Redirigir a la vista de metadatos
    }

    // Eliminar un metadato
    @PostMapping("/delete/{metadataId}")
    public String deleteMetadata(@PathVariable("documentId") UUID documentId,
                                 @PathVariable("metadataId") UUID metadataId) {
        metadataService.deleteMetadata(metadataId);
        return "redirect:/documents/" + documentId + "/metadata";  // Redirigir a la vista de metadatos
    }
}
