package com.cg.service.product;

import com.cg.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public static List<Product> products = new ArrayList<Product>();
    public static Long productId = 1L;

    static {
        products.add(new Product(productId++, "Iphone XX", BigDecimal.valueOf(4000L), 102L, "...", "desc..."));
        products.add(new Product(productId++, "Samsung Galaxy S60", BigDecimal.valueOf(6000L), 22L, "...", "desc..."));
        products.add(new Product(productId++, "Mì xào Leica 10", BigDecimal.valueOf(1000L), 52L, "...", "desc..."));
        products.add(new Product(productId++, "Vsmart V23", BigDecimal.valueOf(500L), 1000L, "...", "desc..."));
    }

    public List<Product> getALl() {
        return products;
    }

}
