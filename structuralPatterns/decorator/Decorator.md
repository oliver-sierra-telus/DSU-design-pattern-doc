# Decorator
A decorator pattern can be used to attach additional responsibilities to an object either statically or dynamically. A decorator provides an enhanced interface to the original object.

In the implementation of this pattern, we prefer composition over inheritance, in that way, we can reduce the overhead of subclassing again and again for each decorating element.

## Problem
We need to change the functionality of an object during runtime and other instances of the same class should not affect it, therefore its behavior will be changed.

## Solution
To expand the behavior of an object, we use inheritance or compositions, however, we do this at compile-time and apply to all instances of the class. We divide the functionality into classes with distinct areas of concern. To this, we should create a container object.

## Implementation

1. Create an interface
2. Create a concret class will be implements the interface.
3. Create an abstract decorator class that implements the same interface.
4. Create a concrete class who will be extends of the abstract decorator.
5. Now you may decorate interface objects with the concrete decorator class you generated earlier.

We create a single interface with a method.
```java
public interface Icecream {

  public String makeIcecream();

}
```

Then, we create a new class with the implementation of the interface.
```Java
public class SimpleIcecream implements Icecream {

    @Override
    public String makeIcecream() {
        return "Base Icecream";
    }
}
```

Next, the creation of an abstract class with the implementation.
```java
abstract class IcecreamDecorator implements Icecream {
	
	//Be aware that, this line of code is a key part of the implementation.
    protected Icecream specialIcecream;
	
	//Creation of its contructor
    public IcecreamDecorator(Icecream specialIcecream) {
        this.specialIcecream = specialIcecream;
    }

    public String makeIcecream() {
        return specialIcecream.makeIcecream();
    }
}
```

Now, the next two classes will extends of the abstract class and add the new behaviors or features without adding new classes.
```java
public class NuttyDecorator extends IcecreamDecorator {
	
	//Contructor
    public NuttyDecorator(Icecream specialIcecream) {
        super(specialIcecream);
    }

    public String makeIcecream() {
        return specialIcecream.makeIcecream() + addNuts();
    }

    private String addNuts() {
        return " + crunchy nuts";
    }
}
```

We use same implementation.
```java
public class HoneyDecorator extends IcecreamDecorator {
	
	// Contructor
    public HoneyDecorator(Icecream specialIcecream) {
        super(specialIcecream);
    }

    public String makeIcecream() {
        return specialIcecream.makeIcecream() + addHoney();
    }

    private String addHoney() {
        return " + sweet honey";
    }
}
```

```java
public class TestDecorator {

    public static void main(String args[]) {
        Icecream icecream = new HoneyDecorator(new NuttyDecorator(new BasicIcecream()));
        System.out.println(icecream.makeIcecream());
    }
}
```

Final result:
![text](./Result.png)

## Diagram

![text](./decorator.png)


## Pros and Cons
**Pros:**
- It can extend the behaviors of the object without additional creation classes.
- You can add new responsabilities to the object in runtime.

**Cons:**
- It is difficult to remove a specific wrapper from the stack.
- The initial set of the class can be difficult to read in the beginning.

Additional resources:
[Decorator Pattern - baeldung.com](https://www.baeldung.com/java-decorator-pattern)
[Decorator Pattern - refactoring.guru](https://refactoring.guru/design-patterns/decorator/java/example)
