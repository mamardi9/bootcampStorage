package com.bootcampstorageult.bootcampstorageult.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
public class Metadata {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @CreationTimestamp
    private Timestamp create_time;

    @UpdateTimestamp
    private Timestamp update_time;

    private String name;
    private String valor;

    @ManyToOne
    private Document document;
}
