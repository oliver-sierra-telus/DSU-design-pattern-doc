# Observer

Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any event that happen to the object they're observing.

## Problem

You need to define a one to many dependency between objects so that once an object changes its state. all of it dependents are notified and updated automatically.

## Solution

A 'subject' contains a list of 'observers' to notify of any change in its state, so it should provide methods using which observers can register and unregister themselves. It contains a method to notify observers.

* Class Diagram

![Observer Class Diagram](/behaviorPatterns/observer/observer.svg)

## Pros and Cons

| Pros | Cons |
|---------------------|---------------------|
|You can introduce new subscriber classes without having to change the publisher's code.|Subscribers are notified in random order.|
|You can establish relations between objects at runtime.||

## Known uses

* It is heavily used in GUI toolkits and event listeners. In java the button(subject) and onClickListener(observer) are modeled with observer pattern.
* Social media, RSSS feeds, email subscription in which you have the option to follow or subscribe and you receive the latest notification.
* All users of an app on the Play Store get notified if there is an update.