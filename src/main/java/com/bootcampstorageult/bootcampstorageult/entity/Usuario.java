package com.bootcampstorageult.bootcampstorageult.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String username;
    private String password;
    private String email;
    @CreationTimestamp
    private Timestamp create_time;
    // Relación uno a muchos: un usuario puede tener varios documentos
    // mappedBy = "usuario" le dice a JPA que en la clase Document existe un atributo llamado usuario, que es el que mapea la relación con la entidad Usuario
    @OneToMany(mappedBy = "usuario")
    private List<Document> documents;
    public Usuario() {
        this.documents = new ArrayList<>();
    }

}
