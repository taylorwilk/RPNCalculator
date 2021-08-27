package com.taylorwilk.operators;

import com.taylorwilk.*;
import com.taylorwilk.exceptions.*;

public class ArithmaticOperator extends BaseOperator {

    public ArithmaticOperator(Engine engine) throws Exception{
        super(engine);
    }
    
    public void execute(String operation) throws Exception{ 
        checkStackForArgs(2);               
        switch(operation) {
            case "+":
                add();
                break;
            case "-":
                subtract();
                break;
            case "*":
                multiply();
                break;
            case "/":
                divide();
                break;
            case "%":
                mod();
                break;
        }
    }

    public void add() throws Exception { 
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        String result = controller.convertToString(secondValueOnStack + topOfStack);
        controller.push(result);
    }

    public void subtract() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        String result = controller.convertToString(secondValueOnStack - topOfStack);
        controller.push(result);
    }

    public void multiply() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        String result = controller.convertToString(secondValueOnStack * topOfStack);
        controller.push(result);
    }

    public void divide() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());

        if (topOfStack == 0) {
            throw new DivideByZeroException("Cannot divide by zero");
        }
        String result = controller.convertToString(secondValueOnStack / topOfStack);
        controller.push(result);
    }

    public void mod() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        String result = controller.convertToString(secondValueOnStack % topOfStack);
        controller.push(result);
    }
}