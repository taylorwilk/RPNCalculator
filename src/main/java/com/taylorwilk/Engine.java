package com.taylorwilk;

import java.util.*;
import com.taylorwilk.operators.*;
import java.security.InvalidParameterException;

public class Engine {
    private int wordSize;
    private int maxIntegerValue;
    private int minIntegerValue;
    private String flags[];
    private Stack<Integer> theStack;
    private HashMap<String, Operator> operatorMap;



    public Engine(int wordSize) throws Exception {
        if (wordSize % 4 != 0 || wordSize < 4 || wordSize > 64)
            throw new IndexOutOfBoundsException("Wordsize needs to be a multiple of 4 between 4 and 64");
        
        this.wordSize = wordSize;
        this.maxIntegerValue = (int)Math.pow(2,wordSize)-1;
        this.minIntegerValue = 0;
        this.theStack = new Stack<>();
        this.flags = new String[3];
        this.flags[0] = "o";    // Overflow flag set to signify no overflow
        this.flags[1] = "s";    // Signed number flag initialized to unsigned numbers
        this.flags[2] = "D";    // Number Base flag initialized to "D" for decimal numbers
        this.operatorMap = new HashMap<String, Operator>(); 
        initilizeOperatorMap(operatorMap); 
    }

    private void initilizeOperatorMap(HashMap<String, Operator> operatorMap) throws Exception{
        this.operatorMap.put("+", new ArithmaticOperator(this));
        this.operatorMap.put("-", new ArithmaticOperator(this));
        this.operatorMap.put("*", new ArithmaticOperator(this));
        this.operatorMap.put("/", new ArithmaticOperator(this));
        this.operatorMap.put("%", new ArithmaticOperator(this));
        this.operatorMap.put("&", new BitwiseOperator(this));
        this.operatorMap.put("|", new BitwiseOperator(this));
        this.operatorMap.put("^", new BitwiseOperator(this));
        this.operatorMap.put("~", new BitwiseOperator(this));
        this.operatorMap.put("<<", new BitwiseOperator(this));
        this.operatorMap.put(">>", new BitwiseOperator(this));
        this.operatorMap.put("==", new RelationalOperator(this));
        this.operatorMap.put("!=", new RelationalOperator(this));
        this.operatorMap.put(">", new RelationalOperator(this));
        this.operatorMap.put("<", new RelationalOperator(this));
        this.operatorMap.put(">=", new RelationalOperator(this));
        this.operatorMap.put("<=", new RelationalOperator(this));
        this.operatorMap.put("&&", new LogicalOperator(this));
        this.operatorMap.put("||", new LogicalOperator(this));
        this.operatorMap.put("!", new LogicalOperator(this));
        this.operatorMap.put("c", new BaseOperator(this));
        this.operatorMap.put("clear", new BaseOperator(this));
        this.operatorMap.put("s", new BaseOperator(this));
        this.operatorMap.put("size", new BaseOperator(this));
        this.operatorMap.put("d", new BaseOperator(this));
        this.operatorMap.put("duplicate", new BaseOperator(this));
        this.operatorMap.put("r", new BaseOperator(this));
        this.operatorMap.put("reverse", new BaseOperator(this));
    }

    public int getwordSize() {
        return this.wordSize;
    }

    public int getmaxIntegerValue() {
        return this.maxIntegerValue;
    }

    public void setmaxIntegerValue(int maxValue) {
        this.maxIntegerValue = maxValue;
    }

    public int getminIntegerValue() {
        return this.minIntegerValue;
    }

    public void setminIntegerValue(int minValue) {
        this.minIntegerValue = minValue;
    }

    public String getflag(int flagIndex) {
        return this.flags[flagIndex];
    }

    public void setflag(int flagIndex, String flag) {
        this.flags[flagIndex] = flag;
    }
    
    public Stack<Integer> gettheStack() {
        return this.theStack;
    }

    public Operator getOperator(String key) throws Exception {
        if (this.operatorMap.containsKey(key) != true) {
            throw new InvalidParameterException();
        }
        return this.operatorMap.get(key);
    }

    public int getsize() throws Exception {
        return gettheStack().size();
    }
}
