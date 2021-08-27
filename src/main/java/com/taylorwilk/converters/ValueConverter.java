package com.taylorwilk.converters;

public interface ValueConverter {
    public String convertToString(int input) throws Exception;
    public int convertToInt(String input) throws Exception;
}
