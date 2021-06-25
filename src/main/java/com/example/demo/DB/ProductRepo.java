package com.example.demo.DB;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.beans.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
