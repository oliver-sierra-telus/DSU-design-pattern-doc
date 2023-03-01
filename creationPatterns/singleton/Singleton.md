# Singleton

It is a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance, in other words, let you have just a single instance.

## Problem
The  Singleton Pattern solves to problem at the same time.
1. Ensure that a class has just a single instance.
2. Provide a global access point to that instance.

## Solution
It has two steps to implement the pattern.
- The constructor will be private, due to prevent other objects from using the `new` operator.
- Create a static creation method how will be acts as a constructor. Under the hood, this methods calls the private constructor to create an object and saves it in a static filed.

## Coding (Java)

First part of the code.
```
public class SingleObject {

   private static SingleObject instance = null;

   private SingleObject() {
      //Make the constructor private to avoid any instantia.
   }

   //Evaluate if the instance has been created.
   public static SingleObject getInstance(){
      if(instance == null) {
         instance = new SingleObject();
      }
      return instance;
   }

   public void showMessage(){
      System.out.println("Hello World!");   
   }
}
```

Second part of the code.
```
public class UsingPatternDemo {
   
   public static void main(String[] args) {
      SingleObject myObject = SingleObject.getInstance();
   }
   
   myObject.showMessage();
}
```

## Pros and Cons
**Pros:**
- You can be sure that a class has only a single instance.
- You gain a global access point to that instance.
- The singleton object is initialized only when it's requested for the firs time.
**Cons:**
- Cloning commplex objects that have circular references might be very tricky.