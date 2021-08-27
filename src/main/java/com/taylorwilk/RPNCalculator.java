package com.taylorwilk;

import com.taylorwilk.operators.*;

public class RPNCalculator {

    private Engine engine;
    private StackController controller;

    public RPNCalculator(int wordSize) throws IndexOutOfBoundsException, Exception{       
        this.engine = new Engine(wordSize);
        this.controller = new StackController(engine);
    } 

    public void push(String inputValue) throws Exception{
        controller.push(inputValue);
    }
    
    public String peek(int index) throws Exception { 
        return controller.peek(index);
    }

    public String pop() throws Exception {  
        return controller.pop();
    }

    public void execute(String operation) throws Exception {
        Operator operator = engine.getOperator(operation);
        operator.execute(operation);
    }

    public void changeSign() throws Exception {
        controller.changeSign();
    }

    public void changeBase(String numberBaseFlag) throws Exception{
        controller.changeBase(numberBaseFlag);
    }

    public String flag(int flagIndex) {
        return engine.getflag(flagIndex);
    }

    public int size() throws Exception {
        return engine.getsize();
    }

    public void clear() throws Exception{
        controller.clear();
    }
}
