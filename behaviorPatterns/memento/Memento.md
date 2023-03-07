# Memento Pattern

It is a behavioral design and is used to restore state of an object to a previous state. Like a rollback of the object, you may want to save checkpoints in your application and restore back to those checkpoints later.

## Problem
When the situation where some actions are undoable and requiring to rollback to a previous state. However, if the state of the Originator is heavy, using the Memento Design Pattern can lead to an expensive creation process and increased use of memory. Even, the encapsulation can be a problem because has restrict access to their state.

## Solution
The object for which the state is to be saved creates a “memento” -which is the object that is going to maintain state- and uses it in future to undo. There is also an object that keeps track of all of them.

If we want to restore the previous state, we need to have some container to do it.

- Originator is the object who need to be saved in some point.
- Memento is the object who will keep the information of the Originator, it is a POJO.
- Caretaker keep tracks of the multiple memento like savepoints.

However, we can't ignore the encaptulation principle, instead of an object try to make a copy from outside, the editor class itself can make the snapshot since it has full access to its own state. This copy will be store in the Memento, but this one is not accessible to any other object except the one that produce it.

## Diagraman

![text](./Memento.png)

## Coding

TextEditor example:
```Java
public class TextEditor { 

	private TextWindow textWindow;

    public TextEditor(TextWindow textWindow) {
        this.textWindow = textWindow;
    }
	
}
```

The class will hold the currently entered text and let you adding new text.
```Java
public class TextWindow {

    private StringBuilder currentText;

    public TextWindow() {
        this.currentText = new StringBuilder();
    }

    public void addText(String text) {
        currentText.append(text);
    }
}
```

We will create an object holding the current text of the window, to this class we will use a String instead of StringBuilder to prevent any update.
```Java
public class TextWindowState {

    private String text;

    public TextWindowState(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
```

We will provide to the TextWindow class the methods save and restore. Making the TextWindow class our Originatior.
```Java
public class TextWindow {
	
	private StringBuilder currentText;

    public TextWindow() {
        this.currentText = new StringBuilder();
    }

    public void addText(String text) {
        currentText.append(text);
    }
    
	public TextWindowState save() {
	    return new TextWindowState(currentText.toString());
	}

	public void restore(TextWindowState textWindowState) {
	    currentText = new StringBuilder(textWindowState.getText());
	}
}	
```

Updating our TextEditor class, As the Caretaker, it will hold the state of the Originator and ask to restore it when needed:
```Java

public class TextEditor { 

	private TextWindow textWindow;
	// Variable kind of POJO
	private TextWindowState savedTextWindow;
	
    public TextEditor(TextWindow textWindow) {
        this.textWindow = textWindow;
    }

	public void hitSave() {
	    savedTextWindow = textWindow.save();
	}

	public void hitUndo() {
	    textWindow.restore(savedTextWindow);
	}
}
```

## Pros and Cons
**Pros:**
- You can produce snapshots of the object’s state without violating its encapsulation.
- You can simplify the originator’s code by letting the caretaker maintain the history of its state.

**Cons:**
- The app might consume lots of RAM if clients create mementos too often.
- Caretakers should track the originator’s lifecycle to be able to destroy obsolete mementos.

[Memento Pattern - refactoring.guru](https://refactoring.guru/design-patterns/memento)
[Memento Pattern - baeldung.com](https://www.baeldung.com/java-memento-design-pattern#:~:text=The%20Memento%20Design%20Pattern%20offers,saved%20is%20called%20an%20Originator.)