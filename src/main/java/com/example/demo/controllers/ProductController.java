//package com.example.demo.controllers;
//
//import com.example.demo.models.Product;
//import com.example.demo.models.ResponseObject;
//import com.example.demo.repositories.ProductRepositories;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path = "api/v1/products")
//public class ProductController {
//    @Autowired
//    private ProductRepositories repositories;
//
//    @RequestMapping("")
//        //this request is: http://localhost:8080/api/v1/products
//    List<Product> getAllProducts() {
//        return repositories.findAll();//where is data
//    }
//
//    @RequestMapping("/{id}")
//        //this request is: http://localhost:8080/api/v1/products/{id}
//        //let's return an object wih: data, message, status
//    ResponseEntity<ResponseObject> findByIdaaaa(@PathVariable Long id) {
//        Optional<Product> foundProduct = repositories.findById(id);
//        return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("success", "Query product successfully", foundProduct)) :
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject("failed", "Not found Id", foundProduct));
//    }
//
//    @PostMapping("/insert")
//    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
//        List<Product> foundProducts = repositories.findByProductName(newProduct.getProductName().trim());
//        if (!foundProducts.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                    new ResponseObject("failed", "Name of new product is exists", null)
//            );
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("success", "insert product successfully", repositories.save(newProduct))
//        );
//    }
//
//    @PutMapping("/{id}")
//    ResponseEntity<ResponseObject> putProduct(@RequestBody Product newProduct, @PathVariable Long id) {
//        Product updateProduct = repositories.findById(id).map(
//                product -> {
//                    product.setProductName(newProduct.getProductName());
//                    product.setPrice(newProduct.getPrice());
//                    product.setUrl(newProduct.getUrl());
//                    product.setYear(newProduct.getYear());
//                    return repositories.save(product);
//                }
//        ).orElseGet(() -> {
//            newProduct.setId(id);
//            return repositories.save(newProduct);
//        });
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("success", "update successfully", updateProduct)
//        );
//    }
//
//    @DeleteMapping("/{id}")
//    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id)
//    {
//        if (repositories.existsById(id))
//        {
//            repositories.deleteById(id);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("success", "Deleted",null)
//            );
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("failed", "ID not found",null)
//            );
//        }
//    }
//}
