package com.taylorwilk.operators;

import com.taylorwilk.*;
import java.security.InvalidParameterException;

public class BitwiseOperator extends BaseOperator {

    public BitwiseOperator(Engine engine) throws Exception{
        super(engine);
    }
    
    @Override
    public void execute(String operation) throws Exception{                
        switch(operation) {
            case "&":
                checkStackForArgs(2);
                bitwiseAND();
                break;
            case "|":
                checkStackForArgs(2);
                bitwiseOR();
                break;
            case "^":
                checkStackForArgs(2);
                bitwiseXOR();
                break;
            case "~":
                checkStackForArgs(1);
                bitwiseComplement();
                break;
            case ">>":
                checkStackForArgs(2);
                bitwiseShiftRight();
                break;
            case "<<":
                checkStackForArgs(2);
                bitwiseShiftLeft();
                break;
            default:
                throw new InvalidParameterException();
        }
    }

    public void bitwiseAND() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        
        String result = controller.convertToString(secondValueOnStack & topOfStack);
        controller.push(result);
    }

    public void bitwiseOR() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        
        String result = controller.convertToString(secondValueOnStack | topOfStack);
        controller.push(result);
    }

    public void bitwiseXOR() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        
        String result = controller.convertToString(secondValueOnStack ^ topOfStack);
        controller.push(result);
    }
    
    public void bitwiseComplement() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = getEngine().getmaxIntegerValue();
        
        String result = controller.convertToString(secondValueOnStack ^ topOfStack);
        controller.push(result);
    }

    public void bitwiseShiftRight() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        
        String result = controller.convertToString(secondValueOnStack >> topOfStack);
        controller.push(result);
    }

    public void bitwiseShiftLeft() throws Exception {
        int topOfStack = controller.convertToInt(controller.pop());
        int secondValueOnStack = controller.convertToInt(controller.pop());
        
        String result = controller.convertToString(secondValueOnStack << topOfStack);
        controller.push(result);
    }
}