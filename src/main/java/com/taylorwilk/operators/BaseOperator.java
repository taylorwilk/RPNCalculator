package com.taylorwilk.operators;

import com.taylorwilk.*;
import java.security.InvalidParameterException;

public class BaseOperator implements Operator {
    
    private Engine engine;
    public StackController controller;

    public BaseOperator(Engine engine) throws Exception{
        this.engine = engine;
        this.controller = new StackController(engine);
    }


    public void execute(String operation) throws Exception{                
        switch(operation) {   
            case "c":
            case "clear":
                clear();
                break;
            case "s":
            case "size":
                pushSize();
                break;
            case "d":
            case "duplicate":
                checkStackForArgs(1);
                duplicateTopOfStack();
                break;
            case "r":
            case "reverse":
                checkStackForArgs(2);
                reverseTwoValues();
                break;
            default:
                throw new InvalidParameterException();
        }
    }

    public void clear() throws Exception {
        controller.clear();
    }

    public void pushSize() throws Exception {
        String size = String.valueOf(engine.gettheStack().size());
        controller.push(size);
    }

    public void duplicateTopOfStack() throws Exception {
        String topOfStack = controller.peek(0);
        controller.push(topOfStack);
    }

    public void reverseTwoValues() throws Exception {
        String topOfStack = controller.pop();
        String secondValueOnStack = controller.pop();
        controller.push(topOfStack);
        controller.push(secondValueOnStack);
    }
 
    public Engine getEngine() {
        return this.engine;
    }

    public void checkStackForArgs(int numberOfArgs) throws Exception {
        controller.checkStackForArgs(numberOfArgs);
    }
}
