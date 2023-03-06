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