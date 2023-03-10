# Prototype

What exactly is a **Prototype Design Pattern**?

It is the way in which you can copy an exact object without making again your code, or by typing some information by user interface (UI).

### Problem

Imagine we have an object very large with many information inside it and we want to have a copy of it.

In many big applications you must to be able to access some information which is an another class at the moment without typing it again,  but we have a problem, the class now is depending from another different class to access data copy.

Then to avoid to creat a new instance and assign values, we can use this pattern to create the exact copy of it an object.

### Solution

The pattern declares a **common interface** for all objects that support cloning. Usually, such an interfafe contains just a single clone method.

If we have an existing object which contains many fields and several configurations, cloning them might serve a an alternative to subclassing.

When we need an object like the one we have configured. we just need to clone a prototype instead of create a new object.

### Code in Java

###### Shape: which has the clone() method.
```java
public abstract class Shape {
    public int x;
    public int y;
    public String color;

    public Shape(){}
    
    public Shape(Shape shape) {

        if(shape != null){
            this.x = shape.x;
            this.y = shape.y;
            this.color = shape.color;
        }
    }
    public abstract Shape clone();
}
```

###### Rectangle: which extends from Shape
```java
public class Rectangle extends Shape {
    public int width;
    public int height;

    public Rectangle(){}

    public Rectangle(Rectangle rectangle){
        super(rectangle);
        if(rectangle != null){
            this.width = rectangle.width;
            this.height = rectangle.height;
        }
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }

    @Override
    public String toString() {

        return "Rectangle [width=" + width + ", height=" + height + ", color="+ color +"]";
    }
}
```

###### Main Class.
```java
public static void main(String[] args){
        System.out.println("============PROTOTYPE PATTERN============");
        Rectangle rectangle = new Rectangle();
        rectangle.width = 10;
        rectangle.height = 20;
        rectangle.color = "blue";

        Rectangle rectangle2 = (Rectangle) rectangle.clone();

        System.out.println("> ORIGINAL OBJECT:");
        System.out.println(rectangle.toString());
        System.out.println("> COPY OBJECT:");
        System.out.println(rectangle2.toString());
    }
```

### Pros and Cons

| Pros |Cons |
|---------------------|---------------------|
|You can clone objects without coupling to their concrete classes|Cloning complex objects that have circular references might be very tricky.|
|You can produce complex objects more conveniently.|It also hides concrete product classes from the client|
|You can get rid of repeated initialization code in favor of cloning pre-built prototypes.|Each subclass of Prototype must implement the clone() operation which may be difficult, when the classes under consideration already exist|
|Specifying new objects by varying values||

### Known of Use

1. When a system should be independent of how its products are created, composed, and represented and when the classes to instantiate are specified at run-time.
2. When you want to generalize the object instantiation process since the object set up is complex in nature.
3. When multiple objects that share similar characteristics need to be created.

##### References

- Refactoring.Guru. (2023, January 1). _Prototype_. https://refactoring.guru/design-patterns/prototype
- GeeksforGeeks. (2022, December 25). _Prototype Design Pattern_. https://www.geeksforgeeks.org/prototype-design-pattern/