# Composite

Composite is a structural design pattern that lets you compose objects into tree structures and then work with these structures as if they were individual objects.

## Problem

This pattern can be used only when the base model of an application can be represented as a tree. For example, there are Order made up of Products and Boxes, the Boxes can contain a variety of Products. The order system is created for these classes. How would the price of an Order be calculated?

## Solution

A common interface is used that declares a method to calculate the total price. It is not required know if one of the Boxes is complex (composed of more boxes or more than one product) since each can be treated equally via the common interface. When the method is called, objects pass through the request down the tree.

[Class Diagram:](/structuralPatterns/composite/composite.svg)
![Composite Class Diagram](/structuralPatterns/composite/composite.svg).

## Pros and Cons
| Pros | Cons |
|---------------------|---------------------|
|More complex tree structures can be worked with in a more convenient by means of polymorphism and recursion.|It may be difficult to prove.|
|*Open/Closed Principle* can be implemented new element types to an application without breaking existing code.||

## Known uses

* Allows composing objects in tree structures and working with these structures as if they were individual objects.
* To implement a tree structure object.
* It allows to treat simple elements and uniformly complex from client code.
