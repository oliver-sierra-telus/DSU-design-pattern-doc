# Strategy Pattern

## Problem
This is a behavioral design pattern also known as the *Policy Pattern*, it allow us to change the behavior of an algorithm at runtime. Essentially, the main purporse is devide the behaviors according to the needs fo the algorithms. So defines a family of functionality, encapsulate each one and make them interchangeable.

Tipically we would start with an interface which is used to apply an algorithm and then implement it multiple times for each posible scenario so we can modify the behavior in runtime.

## Solution
- Define a family of algorithms.
- Encapsulate each algorithm.
- Make algorithms interchangeable within that family.

## Implementation
- Identify an algorithm that's prone to frequent changes. It may also be a massive conditional that selects and executes a variant of the same algorithm at runtime.
- Declare the strategy interface common to all variants of the algorithm.
- One by one, extract all algorithms into their own classes. They should implement the stragety interface.
- In the context class, add a field for storing a reference to a strategy object. Provide the setter to replace values of that field.
- Clients of the context must associate it with a suitable strategy that matches the way they expect the context to perform its primary job.

## Diagram

![text](./Strategy.png)

## Coding
Steps to implement it.

First we create the basis interface that we will implement in the classes.
``` Java
public interface Strategy {
   public int doOperation(int num1, int num2);
}
```

We are going to code a calculator, so per each operation we will implement the method doOperation according to a calculator.
``` Java
public class OperationAdd implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return num1 + num2;
   }
}
```

We override the method doOperation with a subtraction operation.
``` Java
public class OperationSubstract implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return num1 - num2;
   }
}
```

Then, we implement the same interface and override the doOperation Methods to multiply two numbers.
``` Java
public class OperationMultiply implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return num1 * num2;
   }
}
```

We create a context class with a composition of Strategy.
``` Java
public class Context {
   private Strategy strategy;

   public Context(Strategy strategy){
      this.strategy = strategy;
   }

   public int executeStrategy(int num1, int num2){
      return strategy.doOperation(num1, num2);
   }
}
```

``` Java
public class StrategyPatternDemo {
   public static void main(String[] args) {
      Context context = new Context(new OperationAdd());		
      System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

      context = new Context(new OperationSubstract());		
      System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

      context = new Context(new OperationMultiply());		
      System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
   }
}
```

## Pros and Cons
**Pros:**
- By encapsulating the algorithm separately, new algorithms complying with the same interface can be easily introduced.
- The application can switch strategies at run-time.
- Strategy enables the clients to choose the required algorithm, without using a “switch” statement or a series of “if-else” statements.
**Cons:**
- The application must be aware of all the strategies to select the right one for the right situation.
- Context and the Strategy classes normally communicate through the interface specified by the abstract Strategy base class. Strategy base class must expose interface for all the required behaviours.

Additional resources:
[Strategy Pattern - refactoring.guru](https://refactoring.guru/design-patterns/strategy)
[Strategy Pattern - geeksforgeeks.org](https://www.geeksforgeeks.org/strategy-pattern-set-1/)