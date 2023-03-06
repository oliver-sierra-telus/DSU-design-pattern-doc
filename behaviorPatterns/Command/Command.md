
The **Command** pattern is a behavioural design pattern, in which an abstraction exists between an object that invokes a command, and the object that performs it. *A request is wrapped under an object as command and passed to invoker object.* Invoker object looks for the appropriate object which can handle this command and passes the command to the corresponding object which executes the command.

A Concrete Class will delegate a request to a command object, instead of implementing the request directly.

Using a command design pattern allows you to separate concerns and to solve problems of the concerns independently of each other.

E.g., a button will call the **Invoker**, that will call a pre-registered **Command**, that the **Receiver** will perform.

# Terminology

-   **Receiver**: The object that will receive and execute the command.
-   **Invoker**: The object that sends the command to the receiver. E.g., A button.
-   **Command Object**: Itself, an object, that implements an execute, or action method, and contains     all required information to execute it.
-   **Client**: The application or component that is aware of the Receiver, Invoker and Commands.

### Check list

1.  Define a Command interface with a method signature like `execute()`.
2.  Create one or more derived classes that encapsulate some subset of the following: a "receiver" object, the method to invoke, the arguments to pass.
3.  Instantiate a Command object for each deferred execution request.
4.  Pass the Command object from the creator (aka sender) to the invoker (aka receiver).
5.  The invoker decides when to `execute()`.

Command objects can be thought of as "tokens" that are created by one client that knows what need to be done, and passed to another client that has the resources for doing it.


Problem:
Need to issue requests to objects without knowing anything about the operation being requested or the receiver of the request..

Solution:
A Concrete Class will delegate a request to a command object, instead of implementing the request directly. Using a command design pattern allows you to separate concerns and to solve problems of the concerns independently of each other.

![text](./pattern.png)


![text](./Capture.png)

Example:

```java
// RECEIVER / REQUEST
public class Cuenta {
    private int id;
    private double saldo;

    public Cuenta(int id, double monto) {
        this.id = id;
        this.saldo = monto;
    }
    
    public void depositar(double deposito) {
        this.saldo += deposito;
        System.out.println("[COMANDO DEPOSITAR] Cuenta: " + id + " saldo  " + saldo);
    }

    public void retirar(double retiro) {
        this.saldo -= retiro;
        System.out.println("[COMANDO RETIRAR] Cuenta: " + id + " saldo  " + saldo);
    }
}

// COMMAND
@FunctionalInterface
public interface IOperacion {
    void execute();
}

public class RetiroImpl implements IOperacion {
    Cuenta cuenta;
    double monto;
    
    public RetiroImpl(Cuenta cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }
    
    @Override
    public void execute() {
       this.cuenta.retirar(monto);
    }
}

public class DepositoImpl implements IOperacion {
    Cuenta cuenta;
    double monto;
    
    public DepositoImpl(Cuenta cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    @Override
    public void execute() {
        this.cuenta.depositar(monto);
    }
}

public class Invoker {
    public List<IOperacion> operaciones = new ArrayList<>();
    
    public void recibirOperacion(IOperacion operacion) {
        this.operaciones.add(operacion);
    }

    public void realizarOperaciones() {
        this.operaciones.forEach(x -> x.execute());
        operaciones.clear();
    }
}

public class Main {
    public static void main(String[] args) {
    
        Cuenta cuenta = new Cuenta(1, 200);
        
        IOperacion opDeposito = new DepositoImpl(cuenta, 100);
        IOperacion opRetiro = new RetiroImpl(cuenta, 50);

        Invoker ink = new Invoker();
        ink.recibirOperacion(opDeposito);
        ink.recibirOperacion(opRetiro);
        ink.realizarOperaciones();
    }
}
```

``` 
[COMANDO DEPOSITAR] Cuenta: 1 saldo  300.0
[COMANDO RETIRAR] Cuenta: 1 saldo  250.0
```
**Advantages:**

-   Makes our code extensible as we can add new commands without changing existing code.
-   Reduces coupling between the invoker and receiver of a command.

**Disadvantages:**

-   Increase in the number of classes for each individual command

Uses:

-   GUI Buttons, menus
-   Macro recording
-   Multi-level undo/redo
-   Networking — send whole command objects across a network, even as a batch
-   Parallel processing or thread pools
-   Transactional behaviour
-   Wizards