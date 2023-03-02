
The bridge pattern is a structural pattern that provides a means to decouple the abstraction from its implementation. It helps to establish a separation between an abstraction which is a superclass or an interface, and its underlying implementation, which is a concrete class or library.

The bridge pattern consists of two main parts: the abstraction and the implementation. The abstraction is the interface that clients use to interact with the underlying implementation. The implementation is the concrete class that actually performs the task.

By utilizing the Bridge pattern, both components can evolve independently. Any modification to the implementation can be performed without impacting the abstraction, and any change to the abstraction can be made without affecting the implementation.

In summary, Bridge is a structural design pattern that allows you to divide a large class or a group of closely related classes into two separate hierarchies (abstraction and implementation) that can be developed independently of each other.

![text](./bridge.png)

Problem:

Let's say you have a geometric class Form/Shape with a couple of subclasses: Circle and Square. You want to extend this class hierarchy to incorporate colors, so you plan to create the color subclasses Red and Blue. However, since you already have two subclasses, you would have to create four class combinations, such as BlueCircle and RedSquare.

![text](./shapeDiagram.png)

Adding new types of shape and color to the hierarchy will make it grow exponentially. For example, to add a triangle shape you would need to introduce two subclasses, one for each color. And then, to add a new color you would have to create three subclasses, one for each type of shape. The further we go, the worse it gets.

This problem arises because we are trying to extend shape classes in two independent dimensions: by shape and by color. It is a very common problem in class inheritance.

Solution:
The Bridge pattern attempts to solve this problem by moving from inheritance to object composition. This means that one of the dimensions is extracted to a separate class hierarchy, so that the original classes reference an object from the new hierarchy, rather than having all of their state and functionality within a single class.

![text](./Solution.png)

With this solution, we can extract the code related to color and place it within its own class, with two subclasses: Red and Blue. The Forma class then obtains a reference field that points to one of the color objects. Now the shape can delegate any color-related work to the linked color object. This reference will act as a bridge between the Forma and Color classes. Going forward, adding new colors will not require changing the shape hierarchy and vice versa.

```java

// Implementación de la interface Color
interface Color {
    void aplicarColor();
}

// Clase concreta que implementa la interface Color
class Rojo implements Color {
    @Override
    public void aplicarColor() {
        System.out.println("Aplicando color rojo.");
    }
}

// Otra clase concreta que implementa la interface Color
class Azul implements Color {
    @Override
    public void aplicarColor() {
        System.out.println("Aplicando color azul.");
    }
}

// Clase abstracta Forma que tiene una referencia a la interface Color
abstract class Forma {
    protected Color color;
    
    public Forma(Color color) {
        this.color = color;
    }
    
    abstract public void dibujar();
}

// Clase concreta que extiende la clase Forma
class Circulo extends Forma {
    public Circulo(Color color) {
        super(color);
    }
    
    @Override
    public void dibujar() {
        System.out.print("Dibujando un círculo. ");
        color.aplicarColor();
    }
}

// Otra clase concreta que extiende la clase Forma
class Cuadrado extends Forma 
    public Cuadrado(Color color) {
        super(color);
    }
    
    @Override
    public void dibujar() {
        System.out.print("Dibujando un cuadrado. ");
        color.aplicarColor();
    }
}

// Ejemplo de uso
public class Main {
    public static void main(String[] args) {
        // Creamos objetos de las clases concretas
        Forma circuloRojo = new Circulo(new Rojo());
        Forma cuadradoAzul = new Cuadrado(new Azul());
        // Ejecutamos los métodos de los objetos
        circuloRojo.dibujar();
        cuadradoAzul.dibujar();
    }
}
```

This code will produce the next Output:

```
Dibujando un círculo. Aplicando color rojo.
Dibujando un cuadrado. Aplicando color azul.
```


In this example, the Forma class acts as an abstraction that has a reference to the Color interface, which is in turn implemented by the Rojo and Azul classes. The concrete classes Circulo and Cuadrado extend the Forma class and use the reference to the Color interface to apply a specific color when drawing the shape. In this way, the Bridge pattern allows for independence between the abstraction and implementation, allowing changes to be made to either part without affecting the other.

Pros:
-   The client code works with high-level abstractions. It is not exposed to platform details.
-   Open/Closed Principle. You can introduce new abstractions and implementations independently of each other.
Cons:
-    The code may become more complex if you apply the pattern to a highly cohesive class.
