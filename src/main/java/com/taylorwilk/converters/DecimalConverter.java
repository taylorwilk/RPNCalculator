package com.taylorwilk.converters;

import java.security.InvalidParameterException;
import com.taylorwilk.*;


public class DecimalConverter implements ValueConverter {
    public Engine engine;
    
    public DecimalConverter(Engine engine) {
        this.engine = engine;
    }

    public String convertToString(int input) throws Exception {
        String stringValue = Integer.toString(input);
        return stringValue;
    }

    public int convertToInt(String input) throws Exception{

        int intValue = -1;
    
        if (input.matches("\\-?[1-9]\\d*||0")) {
                intValue = Integer.parseInt(input);
        } else {
            throw new InvalidParameterException("Parameter must be a integer string");
        }
        return intValue;     
    }
}
