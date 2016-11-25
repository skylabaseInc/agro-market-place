package com.skylabase.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Product {

    @Id
    String id;
    String name;
    String farm_id;
    List<String> category_ids;
    String descritpion;
    String image_path;
    long quantity;

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

    public String getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(String farm_id) {
        this.farm_id = farm_id;
    }

    public List<String> getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(List<String> category_ids) {
        this.category_ids = category_ids;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}