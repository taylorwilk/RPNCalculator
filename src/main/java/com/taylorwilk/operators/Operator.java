package com.taylorwilk.operators;

public interface Operator {  
    public void execute(String operation) throws Exception;
    public void checkStackForArgs(int numberOfArgs) throws Exception;
}