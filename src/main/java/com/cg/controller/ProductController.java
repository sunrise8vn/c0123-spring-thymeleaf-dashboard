package com.cg.controller;

import com.cg.model.Product;
import com.cg.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String showListPage(Model model) {
        ProductService productService = new ProductService();

        List<Product> products = productService.getALl();

        model.addAttribute("products", products);

        return "/product/list";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {

        Product product = new Product();
        model.addAttribute("product", product);

        return "/product/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product){

        product.setId(ProductService.productId++);
        product.setQuantity(0L);
        ProductService.products.add(product);

        return "/product/create";
    }
}
