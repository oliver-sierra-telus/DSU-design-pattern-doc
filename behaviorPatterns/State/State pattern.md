State is a behavioral design pattern that allows an object to change the behavior when its internal state changes. The pattern extracts state-related behaviors into separate state classes and forces the original object to delegate the work to an instance of these classes, instead of acting on its own.

State is a behavioral design pattern that lets an object alter its behavior when its internal state changes. It appears as if the object changed its class.

Problem: 

![text](./states.png)

The main idea is that, at any given moment, there’s a finite number of states which a program can be in. Within any unique state, the program behaves differently, and the program can be switched from one state to another instantaneously. However, depending on a current state, the program may or may not switch to certain other states. These switching rules, called transitions, are also finite and predetermined.

Solution:

The State pattern suggests that you create new classes for all possible states of an object and extract all state-specific behaviors into these classes.

Instead of implementing all behaviors on its own, the original object, called context, stores a reference to one of the state objects that represents its current state, and delegates all the state-related work to that object.

![text](./State.png)

Key concepts:

- Context: Defines an interface for clients to interact. It maintains references to concrete state objects which may be used to define the current state of objects. In other words, what is the object that changes state.

- State: Defines interface for declaring what each concrete state should do.

- ConcreteState: Provides the implementation for methods defined in State.


``` java
public abstract class State {
    protected Phone phone;
    public State(Phone phone) {
        this.phone = phone;
    }

    public abstract String onHome();
    public abstract String onOffOn();
}


public class OffState extends State {

    public OffState(Phone phone) {
        super(phone);
    }
    
    @Override
    public String onHome() {
        phone.setState(new LockedState(phone));
        return phone.turnOn();
    }
	
	@Override
    public String onOffOn() {
        phone.setState(new LockedState(phone));
        return phone.turnOn();
    }
}

public class ReadyState extends State {
    public ReadyState(Phone phone) {
        super(phone);
    }
    
    @Override
    public String onHome() {
        return phone.home();
    }
    
    @Override
    public String onOffOn() {
        phone.setState(new OffState(phone));
        return phone.lock();
    }
}

public class LockedState extends State {
    public LockedState(Phone phone) {
        super(phone);
    }
    
    @Override
    public String onHome() {
        phone.setState(new ReadyState(phone));
        return phone.unlock();
    }

    @Override
    public String onOffOn() {
        phone.setState(new OffState(phone));
        return phone.lock();
    }
}

Public class Phone {
    private State state;
  
    public Phone() {
        state = new OffState(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    //functionalities this phone can perform
    public String lock() {
        return "Locking phone and turning off the screen"
    }

    public String home() {
        return "Going to home-screen"
    } 
    
    public String unlock() {
        return "UNlocking the phone to home;
    }

    public turnOn() {
        return "Turning screen on, device still locked;
    }
}

public class Main {
    public static void main(String[] args) {
        Phone phone = new Phone();
        JButton home = new JButton("Home");
        home.addActionListener(e -> phone.state.onHome());
        JButton onOff = new JButton("On/Off");
        onOff.addActionListener(e -> phone.state.onOffOn())
    }
}

```

key aspects:

- The state pattern is about doing different things based on the state, hence the result may vary.

- allows an object to alter its behavior when its internal state changes. 

Uses:

- The State pattern is commonly used in Java to convert massive switch-base state machines into objects.

Pros:

- Applies the single responsiblity and the Open-closed Principles

- Each state is organized in a separate class, and we can easily introduce new states.