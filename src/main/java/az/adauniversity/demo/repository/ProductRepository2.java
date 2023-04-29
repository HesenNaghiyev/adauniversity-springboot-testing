package az.adauniversity.demo.repository;

import az.adauniversity.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository2 extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}