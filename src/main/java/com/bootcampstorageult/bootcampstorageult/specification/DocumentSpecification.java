package com.bootcampstorageult.bootcampstorageult.specification;

import com.bootcampstorageult.bootcampstorageult.entity.Document;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DocumentSpecification {

    public static Specification<Document> search(String name, String description, Long size, String metadataKey, String metadataValue) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por nombre
            if (!name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            // Filtro por descripción
            if (!description.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%"));
            }

            // Filtro por tamaño
            if (size != null) {
                predicates.add(criteriaBuilder.equal(root.get("size"), size));
            }

            // Filtro por metadataKey y metadataValue
            if (!metadataKey.isEmpty() && !metadataValue.isEmpty()) {
                var metadataJoin = root.join("metadata"); // Relación con metadatos
                predicates.add(criteriaBuilder.and(
                        criteriaBuilder.like(criteriaBuilder.lower(metadataJoin.get("name")), "%" + metadataKey.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(metadataJoin.get("valor")), "%" + metadataValue.toLowerCase() + "%")
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
