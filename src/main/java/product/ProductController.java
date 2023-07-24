package product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class ProductController {
    //Vytvořte Rest Controller pro manipulaci s produkty. Controller by měl obsahovat
    //závislost na ProductRepository pomocí dependency injection následující operace:
    //Získání seznamu všech produktů (GET request na "/products").
    //Vytvoření nového produktu (POST request na "/products") s tělem obsahujícím id, název, cenu a popis.
    //Získání konkrétního produktu podle ID (GET request na "/products/{id}").
    //Aktualizace existujícího produktu podle ID (PUT request na "/products/{id}") s
    //tělem obsahujícím nový název, novou cenu a nový popis.
    //Smazání produktu podle ID (DELETE request na "/products/{id}").

    /*4. Otestuje aplikaci pomocí příkazů:
    curl -d '{"id": 1, "name": "Mobilni telefon", "description": "skvely novy telefon", "price":
            14999.90}' -H "Content-Type: application/json" http://localhost:8080/products
        curl -d '{"id": 2, "name": "Zubni kartacek", "description": "skvely novy zubni kartacek",
        "price": 1499.90}' -H "Content-Type: application/json" http://localhost:8080/products
        curl http://localhost:8080/products
        Exercise blok 4 2
        curl http://localhost:8080/products/1
        curl http://localhost:8080/products/2
        curl -d '{"id": 7, "name": "Zehlicka", "description": "tefal", "price": 1600.90}' -H "Content-
        Type: application/json" http://localhost:8080/products
    */

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {//dependency injection
        this.productService = productService;
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts()
    {//získání seznamu všech produktů
        System.out.println("RestController - getProducts");
        return ResponseEntity.ok(productService.getProductList());
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product)
    {//Vytvoření nového produktu (POST request na "/products") s tělem obsahující id, název, cenu a popis.
        System.out.println("RestController - createProduct");
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created");
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id)
    {//Získání konkrétního produktu podle ID (GET request na "/products/{id}")
        System.out.println("RestController - getProduct");
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PutMapping("/products")
    public ResponseEntity<String> updateProduct(@RequestBody Product product)
    {//Aktualizace existujícího produktu podle ID (PUT request na "/products/{id}") s
        //tělem obsahujícím nový název, novou cenu a nový popis.
        System.out.println("RestController - updateProduct");
        productService.updateProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body("Product updated");
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id)
    {//Smazání produktu podle ID (DELETE request na "/products/{id}")
        System.out.println("RestController - deleteProduct");
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
    }

}
