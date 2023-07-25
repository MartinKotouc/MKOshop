package product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductJpaRepository productJpaRepository;

    @Autowired
    public ProductService(ProductJpaRepository productJpaRepository)
    {//dependency injection
        System.out.println("ProductService constructor");
        this.productJpaRepository = productJpaRepository;
    }

    //public ProductService(){};

    public void createProduct(Product p)
    {//vytvoří nový Product a přidá ho do seznamu
        System.out.println("createProduct - id = " + p.getId() + ", nazev = " + p.getName() + ", cena = " + p.getPrice() +
                ", popis = " + p.getDescription());
        productJpaRepository.save(p);
    }

    public Product getProductById(long id)
    {//najde product podle vstupniho ID a vrati ho
        return productJpaRepository.findById(id).get();
    }

    public void updateProduct(Product p)
    {//upraví vlastnosti productu
        System.out.println("updateProduct - id = " + p.getId() + ", nazev = " + p.getName() + ", cena = " + p.getPrice() +
                ", popis = " + p.getDescription());

        productJpaRepository.save(p);

    }

    public List<Product> getProductList() {
        System.out.println(("Seznam obsahuje " + productJpaRepository.count() + " produktu"));
        return productJpaRepository.findAll();
    }

    public void addProductInList(Product product)
    {//prida product do seznamu
        productJpaRepository.save(product);
    }
    public void deleteProduct(long id)
    {//smaze product ze seznamu
        System.out.println("Mažu produkt ID: " + id );
        productJpaRepository.deleteById(id);
    }

}
