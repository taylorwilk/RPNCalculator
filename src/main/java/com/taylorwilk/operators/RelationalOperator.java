package com.taylorwilk.operators;

import com.taylorwilk.*;
import java.security.InvalidParameterException;

public class RelationalOperator extends BaseOperator{

    public RelationalOperator(Engine engine) throws Exception{
        super(engine);
    }
    
    @Override
    public void execute(String operation) throws Exception{    
        checkStackForArgs(2);            
        switch(operation) {
            case "==":
                checkIfEqual();
                break;
            case "!=":
                checkIfNotEqual();
                break;
            case ">":
                checkIfGreaterThan();
                break;
            case ">=":
                checkIfGreaterThanOrEqual();
                break;
            case "<":
                checkIfLessThan();
                break; 
            case "<=":
                checkIfLessThanOrEqual();
                break;
            default:
                throw new InvalidParameterException();
        }
    }

    public void checkIfEqual() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        
        String result = "0";

        if (secondValueOnStack == topOfStack) {
            result = "1";
        }
        
        controller.push(result);
    }

    public void checkIfNotEqual() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        
        String result = "0";

        if (secondValueOnStack != topOfStack) {
            result = "1";
        }
        
        controller.push(result);
    }

    public void checkIfGreaterThan() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        
        String result = "0";

        if (secondValueOnStack > topOfStack) {
            result = "1";
        }
        
        controller.push(result);
    }

    public void checkIfGreaterThanOrEqual() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        
        String result = "0";

        if (secondValueOnStack >= topOfStack) {
            result = "1";
        }
        
        controller.push(result);
    }

    public void checkIfLessThan() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        
        String result = "0";

        if (secondValueOnStack < topOfStack) {
            result = "1";
        }
        
        controller.push(result);
    }

    public void checkIfLessThanOrEqual() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        
        String result = "0";

        if (secondValueOnStack <= topOfStack) {
            result = "1";
        }
        
        controller.push(result);
    }   
}

