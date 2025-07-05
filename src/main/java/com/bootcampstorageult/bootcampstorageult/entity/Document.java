package com.bootcampstorageult.bootcampstorageult.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @CreationTimestamp
    private Timestamp create_time;
    @UpdateTimestamp
    private Timestamp update_time;
    private String name;
    private String description;
    private long size;
    // Relación muchos a uno: cada documento pertenece a un único usuario
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Content> contents;  // Puede haber múltiples contenidos por documento
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Metadata> metadata;
    public Document() {
        this.contents = new ArrayList<>();
        this.metadata = new ArrayList<>();
    }
}
