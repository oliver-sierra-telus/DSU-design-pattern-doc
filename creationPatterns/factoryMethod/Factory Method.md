**What is:** 

/*The Factory Method pattern is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of object that will be created.*/

Es un patron creacional que proporciona una interfaz para crear objetos en una superclase, pero permite que las subclases alteren el tipo de objetos que se crearan. Es decir, se utiliza cuando una clase no puede anticipar el tipo de objetos que debe crear, y deja que las subclases tomen esa decision.

The Factory Method pattern is designed to solve the problem of creating objects without specifying the exact class of object that will be created. It allows a client to create an object by relying on a factory object that encapsulates the object creation process. This helps to decouple the client code from the specific implementation of the object, making it more flexible and maintainable.

More specifically, the Factory Method pattern is useful when:

- A class cannot anticipate the class of objects it must create. 
- A class wants to delegate the responsibility of object creation to its subclasses.
- A class wants to provide a way for clients to extend the way objects are created without having to modify the existing code.
- A class wants to centralize the object creation logic in one place and avoid duplication of code.


PROBLEM

A company that sells electronic gadgets has recently expanded its product line to include smartwatches. The company wants to implement a system to manage the creation of different types of smartwatches. Each smartwatch has different features and functionality, and the company wants to be able to create new models of smartwatches easily in the future without modifying the existing code. Additionally, the company wants to keep the creation logic separate from the client code that creates and uses the smartwatches.

SOLUTION 

We can solve this problem using the Factory Method pattern. We will create an abstract `smartwatch` class that defines the common attributes and behaviours of all smartwatches. Then, we will create concrete classes that implement `smartwatch` for each type of smartwatch that the company sells. Finally, we will create a `smartwatchFactory` interface that defines a method for creating `smartwatch` objects, and concrete classes that implement this interface to create different types of smartwatches.


## Here's the Java code for our solution:

```java
// Smartwatch interface
public interface Smartwatch {
	void showFeatures();
}

// Concrete smartwatch classes
public class BasicSmartwatch implements Smartwatch {
		@Override
	public void showFeatures() {
		System.out.println("This is a basic smartwatch.");
	}
}

public class FitnessSmartwatch implements Smartwatch {
	@Override
	public void showFeatures() {
		System.out.println("This is a fitness smartwatch.");
	}
}

public class MusicSmartwatch implements Smartwatch {
	@Override
	public void showFeatures() {
		System.out.println("This is a music smartwatch.");
	}
}

// Smartwatch factory interface
public interface SmartwatchFactory {
	Smartwatch createSmartwatch();
}

// Concrete smartwatch factory classes
public class BasicSmartwatchFactory implements SmartwatchFactory {
	@Override
	public Smartwatch createSmartwatch() {
		return new BasicSmartwatch();
	}
}

public class FitnessSmartwatchFactory implements SmartwatchFactory {
	@Override
	public Smartwatch createSmartwatch() {
		return new FitnessSmartwatch();
	}
}

public class MusicSmartwatchFactory implements SmartwatchFactory {
	@Override
	public Smartwatch createSmartwatch() {
		return new MusicSmartwatch();
	}
}

// Client code that uses the smartwatch factories
public class Main {
	public static void main(String[] args) {
		SmartwatchFactory basicFactory = new BasicSmartwatchFactory();
		Smartwatch basicSmartwatch = basicFactory.createSmartwatch();
		basicSmartwatch.showFeatures(); //Output: This is a basic smartwatch.

		SmartwatchFactory fitnessFactory = new FitnessSmartwatchFactory();
		Smartwatch fitnessSmartwatch = fitnessFactory.createSmartwatch();
		fitnessSmartwatch.showFeatures(); //Output: This is a fitness smartwatch.

		SmartwatchFactory musicFactory = new MusicSmartwatchFactory();
		Smartwatch musicSmartwatch = musicFactory.createSmartwatch();
		musicSmartwatch.showFeatures(); //Output: This is a music smartwatch.
	}
}

```

CLASS DIAGRAM

PROS AND CONS
PROS:
- Proporciona una manera facil de crear objetos sin tener que preocuparse por su implementacion concreta.
- Simplifica el codigo y lo hace mas facil de mantener. MAINTAINABILITY
- Permite agregar nuevas subclases para crear nuevos tipos de objetos. EXTENSIBILITY
- It also helps to reduce coupling between classes, making in easier to modify and maintain them.

CONS:

KNOWN USES
**
-   getInstance() method of java.util.Calendar, NumberFormat, and ResourceBundle uses factory method design pattern.
    
-   All the wrapper classes like Integer, Boolean etc, in Java use this pattern to evaluate the values using the valueOf() method.
    
**
