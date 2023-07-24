package product;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class ProductRepository {
    //Implementujte repository pro správu produktů. (Prozatím stačí vytvořit balíček
    //repository a uvnitř třídu ProductRepository, která bude držet seznam produktů a
    //poskytovat metody pro vytvoření, editaci, získání všech produktů, získání produktu
    //dle ID a smazání produktu dle nejakeho ID)

    private final ArrayList<Product> productList;

    public ProductRepository() {
        this.productList = new ArrayList<>();
    }

    public boolean createProduct(Product p)
    {//vytvoří nový Product a přidá ho do seznamu
        System.out.println("createProduct - id = " + p.getId() + ", nazev = " + p.getName() + ", cena = " + p.getPrice() +
                ", popis = " + p.getDescription());
        return addProductInList(p);
    }

    public void updateProduct(Product p)
    {//upraví vlastnosti productu
        System.out.println("updateProduct - id = " + p.getId() + ", nazev = " + p.getName() + ", cena = " + p.getPrice() +
                ", popis = " + p.getDescription());
        Product found = getProductById(p.getId());
        found.setName(p.getName());
        found.setDescription(p.getDescription());
        found.setPrice(p.getPrice());

    }

    public ArrayList<Product> getProductList() {
        System.out.println(("Seznam obsahuje " + productList.size() + " produktu"));
        return productList;
    }

    public Product getProductById(long id)
    {//najde product podle vstupniho ID a vrati ho
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst().orElseThrow(
                        () -> new RuntimeException(
                                String.format("Product with id %d was not foutn.", id)
                        )
                );
    }


    public boolean addProductInList(Product product)
    {//prida product do seznamu
        return productList.add(product);
    }
    public Product deleteProduct(long id)
    {//smaze product ze seznamu
        Product p = new Product();
        for (int i = 0; i < productList.size(); i++)
        {//najdi product ke smazani
            if (productList.get(i).getId() == id)
            {
                return productList.remove(i);
            }
        }
        return p;
    }
}

