package com.taylorwilk.converters;

import java.security.InvalidParameterException;
import com.taylorwilk.*;


public class OctalConverter implements ValueConverter {
    public Engine engine;
    
    public OctalConverter(Engine engine) {
        this.engine = engine;
    }

    public String convertToString(int octalValue) throws Exception {
        String octalString = Integer.toOctalString(octalValue);
        int wordSize = engine.getwordSize()/4;
        int length = octalString.length();
        int zerosNeeded = wordSize-length;
        String prefix = "0";
        if (zerosNeeded < 0) {
            octalString = octalString.substring(-1*zerosNeeded);
            octalString = "3".concat(octalString);
        }
        octalString = prefix.concat(octalString);
        return octalString;
    }

    public int convertToInt(String octalString) throws Exception{
        int octalValue = -1;
        int maxValue = engine.getmaxIntegerValue();
        int minValue = engine.getminIntegerValue();
        String signedBitFlag = engine.getflag(1);
    
        if (octalString.matches("0[0-7]*")) {
            octalString = octalString.substring(1);
            octalValue = Integer.parseInt(octalString, 8);
            if (signedBitFlag == "S" && octalValue > maxValue) {
                octalValue = octalValue%(maxValue+1) + minValue;
            }
        } else {
            throw new InvalidParameterException("Parameter must be an octal integer string");
        }
        return octalValue;     
    }

    public int twosComplement(int octalValue) {
        octalValue--;
        int secondValueOnStack = engine.getmaxIntegerValue();
        octalValue = (octalValue ^ secondValueOnStack)*-1;
        return octalValue;
    }
}