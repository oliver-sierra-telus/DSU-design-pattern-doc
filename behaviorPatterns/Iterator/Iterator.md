
**Iterator** is a behavioral design pattern that lets you traverse elements of a collection without exposing its underlying representation (list, stack, tree, etc.).



## Problem

Collections are one of the most used data types in programming. Nonetheless, a collection is just a container for a group of objects.

![](https://refactoring.guru/images/patterns/diagrams/iterator/problem1.png?id=52ef2fe2d4920e3fed696c221fe757f2)

Most collections store their elements in simple lists. However, some of them are based on stacks, trees, graphs and other complex data structures.

If you have a collection based on a list. You just loop over all of the elements. But how do you sequentially traverse elements of a complex data structure, such as a tree? For example, one day you might be just fine with depth-first traversal of a tree. Yet the next day you might require breadth-first traversal. And the next week, you might need something else, like random access to the tree elements.

Adding more and more traversal algorithms to the collection gradually blurs its primary responsibility, which is efficient data storage. Additionally, some algorithms might be tailored for a specific application, so including them into a generic collection class would be weird.


## SOLUTION 

The main idea of the Iterator pattern is to extract the traversal behavior of a collection into a separate object called an _iterator_.

![](https://refactoring.guru/images/patterns/diagrams/iterator/solution1.png?id=2f5fbcce6099d8ea09b2fbb83e3e7059)


In addition to implementing the algorithm itself, an iterator object encapsulates all of the traversal details, such as the current position and how many elements are left till the end. Because of this, several iterators can go through the same collection at the same time, independently of each other.

All iterators must implement the same interface. This makes the client code compatible with any collection type or any traversal algorithm as long as there’s a proper iterator. If you need a special way to traverse a collection, you just create a new iterator class, without having to change the collection or the client.

## CODE

Iterator.java
```java
public interface Iterator {
   public boolean hasNext();
   public Object next();
}
```

Container.java
```java
public interface Container {
   public Iterator getIterator();
}
```

NameRepository.java
```java
public class NameRepository implements Container {
   public String names[] = {"Robert" , "John" ,"Julie" , "Lora"};

   @Override
   public Iterator getIterator() {
      return new NameIterator();
   }

   private class NameIterator implements Iterator {

      int index;

      @Override
      public boolean hasNext() {
      
         if(index < names.length){
            return true;
         }
         return false;
      }

      @Override
      public Object next() {
      
         if(this.hasNext()){
            return names[index++];
         }
         return null;
      }		
   }
}
```

IteratorPatternDemo.java
```java
public class IteratorPatternDemo {
	
   public static void main(String[] args) {
      NameRepository namesRepository = new NameRepository();

      for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
         String name = (String)iter.next();
         System.out.println("Name : " + name);
      } 	
   }
}

```

## OUTPUT

```java
Name : Robert
Name : John
Name : Julie
Name : Lora
```


## PROS

-    _Single Responsibility Principle_. You can clean up the client code and the collections by extracting bulky traversal algorithms into separate classes.
-    _Open/Closed Principle_. You can implement new types of collections and iterators and pass them to existing code without breaking anything.
-    You can iterate over the same collection in parallel because each iterator object contains its own iteration state.
-    For the same reason, you can delay an iteration and continue it when needed.

## CONS

-    Applying the pattern can be an overkill if your app only works with simple collections.
-    Using an iterator may be less efficient than going through elements of some specialized collections directly.