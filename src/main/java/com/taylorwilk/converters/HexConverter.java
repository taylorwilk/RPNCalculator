package com.taylorwilk.converters;

import java.security.InvalidParameterException;
import com.taylorwilk.*;

public class HexConverter implements ValueConverter {
    public Engine engine;

    public HexConverter(Engine engine) {
        this.engine = engine;
    }

    public String convertToString(int hexValue) throws Exception {
        String hexString = Integer.toHexString(hexValue);
        int wordSize = engine.getwordSize();
        int zerosNeeded = (wordSize/4)-8;
        String hexPrefix = "0x";

        if (hexString.length() == 8 && hexString.matches("f|([a-f]|\\d)*")) {
            hexString = hexString.substring(-1*zerosNeeded);
        }
        return hexPrefix.concat(hexString); 
    }

    public int convertToInt(String hexString) throws Exception{
        int hexValue = -1;
        int maxValue = engine.getmaxIntegerValue();
        int minValue = engine.getminIntegerValue();
        String signedBitFlag = engine.getflag(1);
    
        if (hexString.matches("0x([a-f]|\\d)+")) {
            hexString = hexString.substring(2);
            hexValue = Integer.parseInt(hexString, 16);
            if (signedBitFlag == "S" && hexValue > maxValue) {
                hexValue = hexValue%(maxValue+1) + minValue;
            }
        } else {
            throw new InvalidParameterException("Parameter must be an octal integer string");
        }
        return hexValue;     
    }
}
