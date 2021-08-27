package com.taylorwilk.operators;

import com.taylorwilk.*;
import com.taylorwilk.exceptions.*;
import java.security.InvalidParameterException;

public class LogicalOperator extends BaseOperator{

    public LogicalOperator(Engine engine) throws Exception{
        super(engine);
    }
    
    @Override
    public void execute(String operation) throws Exception{                
        switch(operation) {
            case "&&":
                checkStackForArgs(2);
                logicalAnd();
                break;
            case "||":
                checkStackForArgs(2);
                logicalOr();
                break;
            case "!":
                checkStackForArgs(1);
                logicalNot();
                break;
            default:
                throw new InvalidParameterException();
        }
    }

    public void logicalAnd() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        String result = "0";

        if (secondValueOnStack > 0 && topOfStack > 0) {
            result = "1";
        }
        
        controller.push(result);
    }

    public void logicalOr() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        String result = "0";

        if (secondValueOnStack > 0 || topOfStack > 0) {
            result = "1";
        }
        
        controller.push(result);
    }

    public void logicalNot() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        String result = "0";

        if (topOfStack == 0) {
            result = "1";
        } else if (topOfStack == 1) {
            result = "0";
        } else {
            throw new NotALogicalExpressionException("Not the result of a logical expression");
        }
        
        controller.push(result);
    }
}