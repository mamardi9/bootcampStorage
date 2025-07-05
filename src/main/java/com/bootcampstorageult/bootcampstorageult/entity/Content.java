package com.bootcampstorageult.bootcampstorageult.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Content {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Lob // Marca el campo como tipo binario de gran tamaño
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "document_id") // Relación con el Document
    private Document document;

    private String contentType;
}
