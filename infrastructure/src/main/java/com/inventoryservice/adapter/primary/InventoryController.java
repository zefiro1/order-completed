package com.inventoryservice.adapter.primary;

import com.inventoryservice.domain.model.Product;
import com.inventoryservice.ports.primary.ManageInventoryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/inventories")
public class InventoryController {
    private final ManageInventoryUseCase manageInventoryUseCase;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        manageInventoryUseCase.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<HttpStatus> updateProductStock(@PathVariable("productId") String productId, @RequestParam int stock) {
        manageInventoryUseCase.updateProductStock(productId, stock);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> getLowStockProducts() {
        List<Product> lowStockProducts = manageInventoryUseCase.getLowStockProducts();
        return new ResponseEntity<>(lowStockProducts, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> allProducts() {
        List<Product> allProducts = manageInventoryUseCase.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") String productId) {
        Product productById = manageInventoryUseCase.getProductById(productId);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("productId") String productId) {
        manageInventoryUseCase.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
