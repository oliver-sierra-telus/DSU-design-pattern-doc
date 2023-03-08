

Abstract factory is a creational pattern that lets you to produce families of related objects without specifying their concrete classes.


# Problem

Imagine that youre creating a furniture shop simulator. your code consist of classes that represent : 

1. A family of realted products like : Chair, Sofa, Coffetable
2. Severial variants of this family, for example these products are available in these variants: **Modern, Victorian, ArtDeco**

![](https://refactoring.guru/images/patterns/diagrams/abstract-factory/problem-en.png?id=e38c307511e684828be898de02d6c268)

you need a way to create individual furniture objects so they match others objects of the same family, customers will get mad if they receive a non-matching furniture

![](https://refactoring.guru/images/patterns/content/abstract-factory/abstract-factory-comic-1-en.png?id=f4012920c5034122eedbb0c9fec0cdb3)

Also, you don’t want to change existing code when adding new products or families of products to the program. Furniture vendors update their catalogs very often, and you wouldn’t want to change the core code each time it happens.

# Solution

The first thing the abstract Factory suggest is to explicitly declare interfaces for each distinct product of the product family (*-Chair,Sofa or coffe table-*). Then you can make all variants of products using those interfaces, for example all **Sofa** variants can implement the **Sofa interface**. all **Chair** varianst can implement the **Chair interface** and so on.

![](https://refactoring.guru/images/patterns/diagrams/abstract-factory/solution1.png?id=71f2018d8bb443b9cce90d57110675e3)

The next move is to declare the _Abstract Factory_—an interface with a list of creation methods for all products that are part of the product family (for example, **createChair, createSofa and createCoffeeTable**). These methods must return **abstract*** product types represented by the interfaces we extracted previously: **Chair`, `Sofa`, `CoffeeTable** and so on.


![](https://refactoring.guru/images/patterns/diagrams/abstract-factory/solution2.png?id=53975d6e4714c6f942633a879f7ac571)

#### Each factory corresponds to a specific product variant

Now, how about the product variants? For each variant of a product family, we create a separate factory class based on the **AbstractFactory** interface. A factory is a class that returns products of a particular kind. For example, the **ModernFurnitureFactory** can only create **ModernChair`, `ModernSofa` and `ModernCoffeeTable** objects.



![](https://refactoring.guru/images/patterns/diagrams/abstract-factory/structure-indexed.png?id=6ae1c99cbd90cf58753c633624fb1a04)
1.  **Abstract Products** declare interfaces for a set of distinct but related products which make up a product family.
    
2.  **Concrete Products** are various implementations of abstract products, grouped by variants. Each abstract product (chair/sofa) must be implemented in all given variants (Victorian/Modern).
    
3.  The **Abstract Factory** interface declares a set of methods for creating each of the abstract products.
    
4.  **Concrete Factories** implement creation methods of the abstract factory. Each concrete factory corresponds to a specific variant of products and creates only those product variants.
    
5.  Although concrete factories instantiate concrete products, signatures of their creation methods must return corresponding _abstract_ products. This way the client code that uses a factory doesn’t get coupled to the specific variant of the product it gets from a factory. The **Client** can work with any concrete factory/product variant, as long as it communicates with their objects via abstract interfaces.


![](https://refactoring.guru/images/patterns/diagrams/abstract-factory/example.png?id=5928a61d18bf00b047463471c599100a)