package com.moxuanran.learning.visitor;

import java.time.LocalDate;

/**
 * @author wutao
 * @date 2022/9/29 11:44
 */
public  class Product {
    private String name;
    private LocalDate productDate;
    private float price;

    public Product(String name, LocalDate productDate, float price) {
        this.name = name;
        this.productDate = productDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProductDate() {
        return productDate;
    }

    public void setProductDate(LocalDate productDate) {
        this.productDate = productDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
