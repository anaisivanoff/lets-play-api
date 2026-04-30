package com.letsplay.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Import des annotations de validation (Jakarta pour Spring Boot 4)
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @NotBlank(message = "Le nom du produit est obligatoire")
    private String name;

    @NotBlank(message = "La description du produit est obligatoire")
    private String description;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être strictement positif")
    private Double price;

    @NotBlank(message = "L'identifiant utilisateur (userId) est obligatoire")
    private String userId;

    public Product() {
    }

    public Product(String id, String name, String description, Double price, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
