# Proxy

Proxy is a structural design pattern that lets you provide a substitue or placeholder for another object. A proxy controls access to the original object, allowing you to perform something either before of after the request gets through to the original object.

## Problem

Is necessary to control access and create certain functionalities and/or restrictions before of after access to a service.

## Solution

The creation of an intermediary object that represents or replaces the original object, that object extending the same interfaces as the original so that the difference is not detected when time to make a reuest to the original service.

* Class Diagram

![Proxy Class Diagram](/structuralPatterns/proxy/proxy.svg).

### Pros and Cons

| Pros | Cons |
|---------------------|---------------------|
|Hide tha fact that an object resides in another address space.|Introduces an additional level of indirection.|
|You can perform optimizations, such as creating objects on demand.||
|Allows various additional maintenance take to be performed when accessing a object.||

## Known uses

* Applies when you need a reference to a more flexible or sophisticated object than a pointer.
* When you need to add certain functionalities before or after calling a service but it belongs to a third-party library.
* When a service needs to be loaded, however, it is very heavy, so the proxy is becomes your 'representative' who takes your place until it is necessary to load the original service.

## Example

We have a *Product* class, which implements a POJO (Plain Old Java Object) and a *ProductDAO* interface in charge of defining the persistence methods to be implemented to take the business objects for Product to the database. The interface is implemented by the *ProductDAOImpl* class, which is in charge of the "mocked" logic of saving to the database. And on the other hand, the class that works as a Proxy called *ProductDAOProxy*, which of course also implements the interface so that both objects have the same methods.

The *ProductDAOProxy* class adds additional logic not related to the business of the DAO implementation directly.

[Source code](/structuralPatterns/proxy/src/)

```java
public class Product{
    private Long id;
    private String name;

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + "]";
    }

}
```

The *ProductDAO* interface, in charge of establishing the contract for the objects that are created from the main class ProductDaoImpl (which will be proxied) and for the objects that are generated from ProductDAOProxy (the one that will be the proxy, or that proxies).

```java
public interface ProductDAO{
    public Product findById(Long id);
    public Product save(Product product);
}
```
```java
public class ProductDAOImpl implements ProductDAO {

    @Override
    public Product findById(Long id) {
        System.out.println("Finding product with id " + id);
        return new Product(id, "Sample");
    }

    @Override
    public Product save(Product product) {
        System.out.println("Saving product...");
        return product;
    }
}
```

```java
public class ProductDAOProxy implements ProductDAO {
    private final ProductDAO productDAO;

    public ProductDAOProxy(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product findById(Long id) {
        System.out.println("Logic of proxy before find ...");
        Product p = this.productDAO.findById(id);
        System.out.println("Logic of proxy after find ...");
        return p;
    }

    @Override
    public Product save(Product product) {
        System.out.println("Logic of proxy before save ...");
        Product p = this.productDAO.save(product);
        System.out.println("Logic of proxy after save ...");
        return p;
    }
}
```

```java
public class App {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAOImpl();
        ProductDAOProxy productDAOProxy = new ProductDAOProxy(productDAO);
        Product myProduct = productDAOProxy.findById(10L);
        productDAOProxy.save(myProduct);    
    }
}
```
