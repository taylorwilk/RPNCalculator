package com.taylorwilk.converters;

import java.security.InvalidParameterException;
import com.taylorwilk.*;

public class BinaryConverter implements ValueConverter {
    public Engine engine;

    public BinaryConverter(Engine engine) {
        this.engine = engine;
    }

    public String convertToString(int binaryValue) throws Exception {
        String binaryString = Integer.toBinaryString(binaryValue);
        int wordSize = engine.getwordSize();
        int length = binaryString.length();
        int zerosNeeded = wordSize-length;
        String binaryPrefix = "0b";

        if (zerosNeeded > 0) {
            for (int i = 0; i < zerosNeeded; i++) {
                binaryPrefix = binaryPrefix.concat("0");
            }
        } else {
            binaryString = binaryString.substring(-1*zerosNeeded);
        }
        return binaryPrefix.concat(binaryString); 
    }

    public int convertToInt(String binaryString) throws Exception{
        int binaryValue = -1;
        int bitSize = engine.getwordSize()-1;
        String signedBitFlag = engine.getflag(1);

        if (binaryString.matches("0b(0|1)+")) {
            binaryString = binaryString.substring(2);
            if (signedBitFlag == "S" && binaryString.matches("1(0|1){" + bitSize + "}")) {
                binaryValue = twosComplement(binaryString);
            } else {
                binaryValue = Integer.parseInt(binaryString, 2);
            }
        } else {
            throw new InvalidParameterException("Parameter must be a binary integer string");
        }
        return binaryValue;
    }

    public int twosComplement(String binaryString) {
        binaryString = binaryString.substring(1);
        int binaryValue = Integer.parseInt(binaryString, 2);
        binaryValue--;
        int secondValueOnStack = engine.getmaxIntegerValue();
        binaryValue = (binaryValue ^ secondValueOnStack)*-1;
        return binaryValue;
    }
}
