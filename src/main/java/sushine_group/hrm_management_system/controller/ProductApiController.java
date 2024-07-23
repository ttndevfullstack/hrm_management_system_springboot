package sushine_group.hrm_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sushine_group.hrm_management_system.model.Product;
import sushine_group.hrm_management_system.service.ProductService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductApiController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found on :: "+ id));
        return ResponseEntity.ok().body(product);
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
//                                                 @RequestBody Product productDetails) {
////        Product product = productService.getProductById(id)
////                .orElseThrow(() -> new RuntimeException("Product not found on :: " + id));
//        Product product = productRepository.findById(product.getId())
//                .orElseThrow(() -> new IllegalStateException("Product with ID " +
//                        product.getId() + " does not exist."));
//        product.setName(productDetails.getName());
//        product.setPrice(productDetails.getPrice());
//        product.setDescription(productDetails.getDescription());
//        product.setCategory(productDetails.getCategory());
//        final Product updatedProduct = productService.addProduct(product);
//        return ResponseEntity.ok(updatedProduct);
//    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found on :: "+ id));
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
}