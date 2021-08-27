package com.taylorwilk;

import java.security.InvalidParameterException;
import java.util.EmptyStackException;
import com.taylorwilk.exceptions.StackUnderflowException;
import java.util.HashMap;
import com.taylorwilk.converters.*;

public class StackController {
    private Engine engine;
    public ValueConverter converter;
    private HashMap<String, ValueConverter> numberBaseMap;

    public StackController(Engine engine) throws Exception{
        this.engine = engine;
        this.numberBaseMap = new HashMap<String, ValueConverter>();
        initilizeNumberBaseMap(numberBaseMap);
        this.converter = getConverter();
    }

    private void initilizeNumberBaseMap(HashMap<String, ValueConverter> numberBaseMap) {
        this.numberBaseMap.put("D", new DecimalConverter(engine));
        this.numberBaseMap.put("B", new BinaryConverter(engine));
        this.numberBaseMap.put("O", new OctalConverter(engine));
        this.numberBaseMap.put("H", new HexConverter(engine));
    }

    private ValueConverter getConverter() throws Exception {
        String numberBaseFlag = engine.getflag(2);
        if (this.numberBaseMap.containsKey(numberBaseFlag) != true) {
            throw new InvalidParameterException();
        }
        return this.numberBaseMap.get(numberBaseFlag);
    }

    public void push(String inputValue) throws Exception{
        converter = getConverter(); 
        int intToPush = convertToInt(inputValue);      
        int maxValue = engine.getmaxIntegerValue();
        int minValue = engine.getminIntegerValue();
            
        if (intToPush > maxValue) {
            intToPush = intToPush%(maxValue+1) + minValue;
            this.engine.setflag(0, "O");
        } else if (intToPush < minValue) {
            throw new StackUnderflowException("Underflow exception: Cannot push a value less than " + minValue);
        }
        engine.gettheStack().push(intToPush); 
    }
    
    public String peek(int index) throws Exception {
        int size = engine.gettheStack().size();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 0) {
            throw new EmptyStackException();
        }
        converter = getConverter();
        // java's implementation of Stack has the index start at bottom of stack 
        int topOfStack = engine.gettheStack().get(size - index - 1);
        return converter.convertToString(topOfStack);
    }
    
    public String pop() throws Exception {       
        if (engine.gettheStack().size() == 0) {
            throw new EmptyStackException();
        }
        converter = getConverter();
        int topOfStack = engine.gettheStack().pop();
        return converter.convertToString(topOfStack);
    }

    public int convertToInt(String inputValue) throws Exception {
        return converter.convertToInt(inputValue);
    }

    public String convertToString(int inputValue) throws Exception {
        return converter.convertToString(inputValue);
    }

    public void clear() throws Exception {
        engine.setflag(0, "o");
        int size = engine.getsize();

        while (size > 0) {
            pop();
            size--;
        }
    }

    public void changeSign() throws Exception {
        clear();

        if (engine.getflag(1) == "s") {
            engine.setflag(1, "S");
            changeMinAndMaxValues("S");
        } else {
            engine.setflag(1, "s");
            changeMinAndMaxValues("s");
        }
    }

    public void changeMinAndMaxValues(String bitFlag) {
        int wordSize = engine.getwordSize();
        int maxValue, minValue;
        
        if (bitFlag == "S") {
            maxValue = (int)Math.pow(2,wordSize-1)-1;
            minValue = -(int)Math.pow(2,wordSize-1);
        } else {
            maxValue = (int)Math.pow(2,wordSize)-1;
            minValue = 0;
        }

        engine.setmaxIntegerValue(maxValue);
        engine.setminIntegerValue(minValue);
    }

    public void changeBase(String numberBaseFlag) throws Exception{
        engine.setflag(2, numberBaseFlag);
        converter = getConverter();

    }
    
    public Engine getEngine() {
        return this.engine;
    }

    public void checkStackForArgs(int numberOfArgs) throws Exception {
        if (getEngine().gettheStack().size() < numberOfArgs) {
            throw new StackUnderflowException("Too few arguments on the stack");
        }
    }
}
