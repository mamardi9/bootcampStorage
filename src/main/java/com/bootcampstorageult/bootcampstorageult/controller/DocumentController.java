package com.bootcampstorageult.bootcampstorageult.controller;

import com.bootcampstorageult.bootcampstorageult.entity.Content;
import com.bootcampstorageult.bootcampstorageult.entity.Document;
import com.bootcampstorageult.bootcampstorageult.entity.Usuario;
import com.bootcampstorageult.bootcampstorageult.service.ContentService;
import com.bootcampstorageult.bootcampstorageult.service.DocumentService;
import com.bootcampstorageult.bootcampstorageult.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private ContentService contentService;

    @GetMapping("/create")
    public String createDocumentForm() {
        return "create-document";  // Redirige a la vista para crear un documento
    }

    // Endpoint para procesar la creación de un documento y subir un archivo
    @PostMapping("/create")
    public String createDocument(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("file") MultipartFile file) throws IOException {

        // Obtener el usuario autenticado
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        long size = file.getSize();

        // Crear un nuevo documento
        Document document = documentService.createDocument(name, description, size, username);

        contentService.save(file, document);        // Guardamos el contenido en la base de datos

        return "redirect:/welcome"; // Redirigir a la página de bienvenida o el listado de documentos
    }
    // Página para ver todos los documentos de un usuario
    @GetMapping("/list")
    public String listDocuments(Model model) {
        // Obtener el usuario autenticado
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        // Obtener los documentos del usuario
        List<Document> documents = documentService.getAllDocuments(username);
        model.addAttribute("documents", documents);

        return "list-documents"; // Redirige a la vista de listado de documentos
    }

    @GetMapping("/search-form")
    public String showSearchForm() {
        return "search-form"; // Vista que contiene el formulario de búsqueda
    }

    @GetMapping("/search")
    public String searchDocuments(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "description", required = false) String description,
                                  @RequestParam(value = "size", required = false) Long size,
                                  @RequestParam(value = "metadataKey", required = false) String metadataKey,
                                  @RequestParam(value = "metadataValue", required = false) String metadataValue,
                                  Model model) {
        List<Document> documents = documentService.searchDocuments(name, description, size, metadataKey, metadataValue);
        model.addAttribute("search", documents);

        return "search-results";  // Vista con los resultados de búsqueda
    }

    @GetMapping("/delete/{id}")
    public String deleteDocument(@PathVariable("id") UUID id) {
        documentService.deleteDocument(id);
        return "redirect:/documents/list";  // Redirigir a la lista de documentos después de la eliminación
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") UUID id, Model model) {
        Document document = documentService.findDocumentById(id); // Usar el servicio
        model.addAttribute("document", document);
        return "editDocument"; // Nombre del archivo HTML para el formulario
    }

    @PostMapping("/update")
    public String updateDocument(@RequestParam UUID id, @RequestParam String name, @RequestParam String description) {
        documentService.updateDocument(id, name, description);
        return "redirect:/documents/list"; // Redirigir después de la actualización
    }
}
