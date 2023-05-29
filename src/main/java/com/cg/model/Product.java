package com.cg.model;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String title;
    private BigDecimal price;
    private Long quantity;
    private String summary;

    private String description;

    public Product() {

    }

    public Product(Long id, String title, BigDecimal price, Long quantity, String summary, String description) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.summary = summary;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
