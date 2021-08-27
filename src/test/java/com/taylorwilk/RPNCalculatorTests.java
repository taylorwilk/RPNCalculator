package com.taylorwilk;

import static org.junit.jupiter.api.Assertions.*;
import java.security.InvalidParameterException;
import org.junit.jupiter.api.Test;
import com.taylorwilk.exceptions.*;

public class RPNCalculatorTests {

    @Test
    public void testRPNCalculatorConstructorWithInvalidWordsize() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            RPNCalculator calc = new RPNCalculator(11);
        });

        String expectedMessage = "Wordsize needs to be a multiple of 4 between 4 and 64";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testRPNCalculatorConstructorWithInvalidWordsize2() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            RPNCalculator calc = new RPNCalculator(68);
        });

        String expectedMessage = "Wordsize needs to be a multiple of 4 between 4 and 64";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testRPNCalculatorConstructorFlagSet() throws Exception{
        RPNCalculator calc = new RPNCalculator(12);
        assertEquals("o", calc.flag(0));
        assertEquals("s", calc.flag(1));
    }

    // @Test(expected = IndexOutOfBoundsException.class)
    // public void testRPNCalculatorConstructorException() throws Exception{
    //     RPNCalculator calc = new RPNCalculator(14);
    // }

    // @Test(expected = IndexOutOfBoundsException.class)
    // public void testPeekIndexException() throws Exception {
    //     RPNCalculator calc = new RPNCalculator(14);
    //     calc.peek(10);
    // }

    // @Test (expected = EmptyStackException.class)
    // public void testPeekEmptyStack() throws Exception {
    //     RPNCalculator calc = new RPNCalculator(8);
    //     calc.peek(0);
    // }

    @Test
    public void testPushAndPeek() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("10");
        assertEquals("10", calc.peek(0));
    }

    @Test
    public void testPushOverflow() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("260");
        assertEquals("4", calc.peek(0));
        
    }

    @Test
    public void testPushInvalidString() throws InvalidParameterException {
        
        Exception exception = assertThrows(InvalidParameterException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("hello world");
        });

        String expectedMessage = "Parameter must be a integer string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        
    }

    @Test
    public void testPopReturnsTopOfTheStack() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("10");
        assertEquals("10", calc.pop());
    }

    @Test
    public void testPopReturnsTopOfTheStack2() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("10");
        calc.push("15");
        assertEquals("15", calc.pop());
    }

    @Test
    public void testPopRemovesNumberAtTopOfStack() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("10");
        assertEquals("10", calc.pop());
    }

    // @Test (expected = EmptyStackException.class)
    // public void testPopEmptyStack() throws Exception{
        
    //     RPNCalculator calc = new RPNCalculator(8);
    //     calc.pop();
    // }

    // @Test (expected = EmptyStackException.class)
    // public void testPushingAndPoppingThreeValuesCreatesEmptyStack() throws Exception {
    //     RPNCalculator calc = new RPNCalculator(8);
    //     calc.push("3");
    //     calc.push("22");
    //     calc.push("11");
    //     calc.pop();
    //     calc.pop();
    //     calc.pop();
    //     calc.peek(0);
    // }

    @Test
    public void testExecuteAddReturnsCorrectValue() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("3");
        calc.push("22");
        calc.execute("+");
        assertEquals("25", calc.pop());
    }

    @Test
    public void testExecuteAddPutsResultOnTopOfStack() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("3");
        calc.push("22");
        calc.execute("+");
        assertEquals("25", calc.peek(0));
    }

    @Test
    public void testExecuteAddWithTooFewArguments() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("3");
            calc.execute("+");
        });
        
        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test 
    public void testExecuteAddWithOverflow() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("200");
        calc.push("56");
        calc.execute("+"); 
        assertEquals("0", calc.peek(0));      
    }

    @Test 
    public void testExecuteAddWithOverflowSetsFlag() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("200");
        calc.push("56");
        calc.execute("+");
        assertEquals("O", calc.flag(0));          
    }

    @Test 
    public void testExecuteAddCloseToOverflow() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("200");
        calc.push("55");
        calc.execute("+"); 
        assertEquals("255", calc.peek(0));      
    }

    @Test
    public void testExecuteSubtractReturnsCorrectValue() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("22");
        calc.push("3");
        calc.execute("-");
        assertEquals("19", calc.pop());
    }

    @Test
    public void testExecuteSubtractPutsResultOnTopOfStack() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("22");
        calc.push("10");
        calc.execute("-");
        assertEquals("12", calc.peek(0));
    }

    @Test
    public void testExecuteSubtractWithResult() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("22");
        calc.push("10");
        calc.execute("-");
        calc.push("12");
        calc.execute("-");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testExecuteSubtractWithTooFewArguments() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("3");
            calc.execute("-");
        });
        
        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExecuteSubtractWithTooFewArguments2() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.execute("-");
        });
        
        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExecuteSubtractWithUnderflow() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("10");
            calc.push("56");
            calc.execute("-"); 
        });
          
        String expectedMessage = "Underflow exception: Cannot push a value less than 0";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }  
    
    @Test
    public void testExecuteMultiply() throws Exception {
        RPNCalculator calc = new RPNCalculator(16);
        calc.push("22");
        calc.push("10");
        calc.execute("*");
        assertEquals("220", calc.peek(0));
    }

    @Test
    public void testExecuteMultiplyOverflow() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("22");
        calc.push("100");
        calc.execute("*");
        assertEquals("152", calc.peek(0));
    }
    
    @Test
    public void testExecuteMultiplyWithTooFewArguments() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("3");
            calc.execute("*");
        });
        
        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExecuteAddAndMultiply() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("2");
        calc.push("3");
        calc.push("4");
        calc.execute("+");
        calc.execute("*");
        assertEquals("14", calc.pop());
    }

    @Test
    public void testExecuteDivide() throws Exception {
        RPNCalculator calc = new RPNCalculator(16);
        calc.push("22");
        calc.push("10");
        calc.execute("/");
        assertEquals("2", calc.peek(0));
    }

    @Test
    public void testExecuteDivide2() throws Exception {
        RPNCalculator calc = new RPNCalculator(16);
        calc.push("20");
        calc.push("30");
        calc.execute("/");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testExecuteDivideByZero() throws Exception {
        Exception exception = assertThrows(DivideByZeroException.class, () -> {
            RPNCalculator calc = new RPNCalculator(16);
            calc.push("22");
            calc.push("0");
            calc.execute("/");
        });

        String expectedMessage = "Cannot divide by zero";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExecuteDivideByZero2() throws Exception {
        Exception exception = assertThrows(DivideByZeroException.class, () -> {
            RPNCalculator calc = new RPNCalculator(16);
            calc.push("5");
            calc.push("22");
            calc.push("11");
            calc.execute("/");
            calc.push("2");
            calc.execute("-");
            calc.execute("/");
        });

        String expectedMessage = "Cannot divide by zero";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExecuteModulus() throws Exception {
        RPNCalculator calc = new RPNCalculator(16);
        calc.push("40");
        calc.push("10");
        calc.execute("%");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testExecuteModulus2() throws Exception {
        RPNCalculator calc = new RPNCalculator(16);
        calc.push("40");
        calc.push("7");
        calc.execute("%");
        assertEquals("5", calc.peek(0));
    }

    @Test
    public void testExecuteModWithTooFewArguments() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("3");
            calc.execute("%");
        });
        
        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // @Test (expected = InvalidParameterException.class)
    // public void testExecuteWithNoArguments() throws Exception {
    //     RPNCalculator calc = new RPNCalculator(16);
    //     calc.execute("asfasdf");
    // }

    @Test
    public void testTooManyOperators() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("2");
            calc.push("4");
            calc.execute("+");
            calc.execute("*");
            calc.execute("%");
        });

        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    // @Test (expected = EmptyStackException.class)
    // public void testExecuteClear() throws Exception {
    //     RPNCalculator calc = new RPNCalculator(8);
    //         calc.push("2");
    //         calc.push("4");
    //         calc.push("2");
    //         calc.push("4");
    //         calc.execute("c");
    //         calc.peek(0);
    // }
    // @Test (expected = EmptyStackException.class)
    // public void testExecuteClear2() throws Exception {
    //     RPNCalculator calc = new RPNCalculator(8);
    //         calc.push("2");
    //         calc.push("4");
    //         calc.push("2");
    //         calc.push("4");
    //         calc.execute("clear");
    //         calc.peek(0);
    // }

    @Test
    public void testExecuteSize() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("2");
        calc.push("4");
        calc.push("10");
        calc.execute("s");
        assertEquals("3", calc.peek(0));
        calc.execute("size");
        assertEquals("4", calc.peek(0));
    }

    @Test
    public void testExecuteSizeCausesOverflow() throws Exception {
        
        RPNCalculator calc = new RPNCalculator(4);
        int i = 16;
        while (i > 0) {
            calc.push("2");
            i--;
        }
        calc.execute("s");
        assertEquals("0", calc.peek(0));
    }

    // @Test (expected = EmptyStackException.class)
    // public void testClear() throws Exception {
    //     RPNCalculator calc = new RPNCalculator(8);
    //         calc.push("2");
    //         calc.push("4");
    //         calc.push("2");
    //         calc.push("4");
    //         calc.clear();
    //         calc.peek(0);
    // }

    @Test
    public void testSize() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("2");
        calc.push("4");
        assertEquals(2, calc.size());
    }

    @Test 
    public void testFlags() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        assertEquals("o", calc.flag(0));
    }

    @Test
    public void testBitwiseAND() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("14");
        calc.push("2");
        calc.execute("&");
        assertEquals("2", calc.pop());
    }
    
    @Test
    public void testBitwiseAND2() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("2");
            calc.execute("&");
        });

        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testBitwiseOR() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("14");
        calc.push("1");
        calc.execute("|");
        assertEquals("15", calc.pop());
    }

    @Test
    public void testBitwiseOR2() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("200");
            calc.execute("|");
        });

        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testBitwiseXOR() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("6");
        calc.push("5");
        calc.execute("^");
        assertEquals("3", calc.pop());
    }

    @Test
    public void testBitwiseXOR2() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("200");
            calc.execute("^");
        });

        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testBitwiseComplementWithWordsize4() throws Exception{
        RPNCalculator calc = new RPNCalculator(4);
        calc.push("20");
        calc.push("5");
        calc.execute("~");
        assertEquals("10", calc.pop());
    }

    @Test
    public void testBitwiseComplementWithWordsize16() throws Exception{
        RPNCalculator calc = new RPNCalculator(16);
        calc.push("20");
        calc.execute("~");
        assertEquals("65515", calc.pop());
    }

    @Test
    public void testBitwiseComplementUnderflowException() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(4);
            calc.push("200");
            calc.clear();
            calc.execute("~");
        });

        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testBitwiseShiftRight() throws Exception{
        RPNCalculator calc = new RPNCalculator(16);
        calc.push("21");
        calc.push("2");
        calc.execute(">>");
        assertEquals("5", calc.pop());
    }

    @Test
    public void testBitwiseShiftRight2() throws Exception{
        RPNCalculator calc = new RPNCalculator(16);
        calc.push("21");
        calc.push("0");
        calc.execute(">>");
        assertEquals("21", calc.pop());
    }

    @Test
    public void testBitwiseShiftRight3() throws Exception{
        RPNCalculator calc = new RPNCalculator(16);
        calc.push("21");
        calc.push("5");
        calc.execute(">>");
        assertEquals("0", calc.pop());
    }

    @Test
    public void testBitwiseShiftLeft() throws Exception{
        RPNCalculator calc = new RPNCalculator(4);
        calc.push("3");
        calc.push("2");
        calc.execute("<<");
        assertEquals("12", calc.pop());
    }

    @Test
    public void testBitwiseShiftLeft2() throws Exception{
        RPNCalculator calc = new RPNCalculator(4);
        calc.push("3");
        calc.push("3");
        calc.execute("<<");
        assertEquals("8", calc.pop());
    }

    @Test
    public void testBitwiseShiftLeft3() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("3");
        calc.push("3");
        calc.execute("<<");
        assertEquals("24", calc.pop());
    }

    @Test
    public void testBitwiseShiftLeftSetOverflow() throws Exception{
        RPNCalculator calc = new RPNCalculator(4);
        calc.push("3");
        calc.push("3");
        calc.execute("<<");
        assertEquals("O", calc.flag(0));
    }

    @Test
    public void testIsEqualTo() throws Exception{
        RPNCalculator calc = new RPNCalculator(4);
        calc.push("3");
        calc.push("3");
        calc.execute("==");
        assertEquals("1", calc.peek(0));
    }

    @Test
    public void testIsEqualTo2() throws Exception{
        RPNCalculator calc = new RPNCalculator(4);
        calc.push("3");
        calc.push("2");
        calc.execute("==");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testIsNotEqualTo() throws Exception{
        RPNCalculator calc = new RPNCalculator(4);
        calc.push("3");
        calc.push("3");
        calc.execute("!=");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testIsNotEqualTo2() throws Exception{
        RPNCalculator calc = new RPNCalculator(4);
        calc.push("3");
        calc.push("2");
        calc.execute("!=");
        assertEquals("1", calc.peek(0));
    }

    @Test
    public void testIsGreaterThan() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("8");
        calc.push("3");
        calc.execute(">");
        assertEquals("1", calc.peek(0));
    }

    @Test
    public void testIsNotGreaterThan() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("24");
        calc.push("50");
        calc.execute(">");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testIsGreaterThanOrEqual() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("8");
        calc.push("3");
        calc.execute(">=");
        assertEquals("1", calc.peek(0));
    }

    @Test
    public void testIsNotGreaterThanOrEqaul() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("24");
        calc.push("50");
        calc.execute(">");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testIsLessThan() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("8");
        calc.push("3");
        calc.execute("<");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testIsNotLessThan() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("24");
        calc.push("50");
        calc.execute("<");
        assertEquals("1", calc.peek(0));
    }

    @Test
    public void testIsLessThanOrEqual() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("8");
        calc.push("3");
        calc.execute("<=");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testIsNotLessThanOrEqaul() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("24");
        calc.push("50");
        calc.execute("<=");
        assertEquals("1", calc.peek(0));
    }

    @Test
    public void testLogicalAnd() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("8");
        calc.push("4");
        calc.execute("&&");
        assertEquals("1", calc.peek(0));
    }

    @Test
    public void testLogicalAnd2() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("4");
        calc.push("0");
        calc.execute("&&");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testLogicalNot() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("8");
        calc.push("4");
        calc.execute("&&");
        calc.execute("!");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testLogicalNot2() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("0");
        calc.push("8");
        calc.push("4");
        calc.execute("&&");
        calc.execute("||");
        calc.execute("!");
        assertEquals("0", calc.peek(0));
    }

    @Test
    public void testLogicalNotException() throws Exception {
        Exception exception = assertThrows(NotALogicalExpressionException.class, () -> {
            RPNCalculator calc = new RPNCalculator(4);
            calc.push("200");
            calc.execute("!");
        });

        String expectedMessage = "Not the result of a logical expression";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDuplicate() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("4");
        calc.push("8");
        calc.execute("duplicate");
        assertEquals("8", calc.peek(0));
        assertEquals("8", calc.peek(1));
    }

    @Test
    public void testDuplicate2() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("4");
        calc.push("8");
        calc.execute("d");
        assertEquals("8", calc.peek(0));
        assertEquals("8", calc.peek(1));
    }

    @Test
    public void testCannotDuplicate() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("4");
            calc.push("8");
            calc.execute("c");
            calc.execute("duplicate");
        });

        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testReverse() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("4");
        calc.push("8");
        calc.execute("reverse");
        assertEquals("4", calc.peek(0));
        assertEquals("8", calc.peek(1));
    }

    @Test
    public void testReverse2() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("4");
        calc.push("8");
        calc.execute("r");
        assertEquals("4", calc.peek(0));
        assertEquals("8", calc.peek(1));
    }

    @Test
    public void testCannotReverse() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.push("4");
            calc.execute("r");
        });

        String expectedMessage = "Too few arguments on the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testchangeSignChangesSignedBitFlag() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        assertEquals("S", calc.flag(1));
    }

    @Test
    public void testchangeSignChangesMaxValue() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        calc.push("129");
        assertEquals("-127", calc.peek(0));
    }

    @Test
    public void testchangeSignChangesOverflowFlag() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        calc.push("129");
        assertEquals("O", calc.flag(0));
    }


    @Test
    public void testchangeSignChangesMinValue() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        calc.push("-10");
        assertEquals("-10", calc.peek(0));
    }

    @Test
    public void testchangeSignWithUnderflow() throws Exception {
        Exception exception = assertThrows(StackUnderflowException.class, () -> {
            RPNCalculator calc = new RPNCalculator(8);
            calc.changeSign();
            calc.push("-129");
        });
          
        String expectedMessage = "Underflow exception: Cannot push a value less than -128";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }  

    @Test
    public void testAddingNegativeNumbers() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        calc.push("-10");
        calc.push("-100");
        calc.execute("+");
        assertEquals("-110", calc.peek(0));
    }

    @Test
    public void testMultiplyingNegativeNumbers() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        calc.push("-10");
        calc.push("8");
        calc.execute("*");
        assertEquals("-80", calc.peek(0));
    }

    @Test
    public void testMultiplyingNegativeNumbersWithOverflow() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        calc.push("-10");
        calc.push("-20");
        calc.execute("*");
        assertEquals("-56", calc.peek(0));
    }

    @Test
    public void testBinaryNumberPush() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeBase("B");
        calc.push("0b0001");
        calc.push("0b0011");
        calc.execute("+");
        assertEquals("0b00000100", calc.peek(0));

    }

    @Test
    public void testnumberBaseChange() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("1");
        calc.changeBase("B");
        calc.push("0b0011");
        calc.execute("+");
        assertEquals("0b00000100", calc.peek(0));
    }

    @Test
    public void testnumberBaseChange2() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.push("1");
        calc.changeBase("B");
        calc.push("0b0011");
        calc.execute("+");
        calc.changeBase("D");
        assertEquals("4", calc.peek(0));
    }

    @Test
    public void testMultiplyBinary() throws Exception {
        RPNCalculator calc = new RPNCalculator(16);
        calc.changeBase("B");
        calc.push("0b10110");
        calc.push("0b1010");
        calc.execute("*");
        assertEquals("0b0000000011011100", calc.peek(0));
    }

    @Test
    public void testMultiplyBinaryOverflow() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeBase("B");
        calc.push("0b10110");
        calc.push("0b1100100");
        calc.execute("*");
        assertEquals("0b10011000", calc.peek(0));
    }

    @Test
    public void testBitwiseANDInBinary() throws Exception{
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeBase("B");
        calc.push("0b1110");
        calc.push("0b10");
        calc.execute("&");
        assertEquals("0b00000010", calc.pop());
    }

    @Test
    public void testAddingNegativeNumbersBinary() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        calc.changeBase("B");
        calc.push("0b11110110");
        calc.push("0b10011100");
        calc.execute("+");
        assertEquals("0b10010010", calc.peek(0));
    }

    @Test
    public void testMultiplyingNegativeNumbersBinary() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        calc.changeBase("B");
        calc.push("0b11110110");
        calc.push("0b1000");
        calc.execute("*");
        assertEquals("0b10110000", calc.peek(0));
    }

    @Test
    public void testMultiplyingNegativeNumbersWithOverflowBinary() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        calc.changeBase("B");
        calc.push("0b11110110");
        calc.push("0b11101100");
        calc.execute("*");
        assertEquals("0b11001000", calc.peek(0));
    }

    @Test
    public void testOctalNumberPush() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeBase("O");
        calc.push("016");
        calc.push("04");
        calc.execute("+");
        assertEquals("022", calc.peek(0));

    }

    @Test
    public void testAddingNegativeNumbersOctal() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        calc.changeBase("O");
        calc.push("0374");
        calc.push("0372");
        calc.execute("+");
        assertEquals("0366", calc.peek(0));
    }

    @Test
    public void testHexNumberPush() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeBase("H");
        calc.push("0x19");
        calc.push("0x5");
        calc.execute("+");
        assertEquals("0x1e", calc.peek(0));

    }

    @Test
    public void testAddingNegativeNumbersHex() throws Exception {
        RPNCalculator calc = new RPNCalculator(8);
        calc.changeSign();
        calc.changeBase("H");
        calc.push("0xfc");
        calc.push("0xfc");
        calc.execute("+");
        assertEquals("0xf8", calc.peek(0));
    }
    
    @Test
    public void testAddingNegativeNumbersHex2() throws Exception {
        RPNCalculator calc = new RPNCalculator(16);
        calc.changeSign();
        calc.changeBase("H");
        calc.push("0xfffc");
        calc.push("0xfffc");
        calc.execute("+");
        assertEquals("0xfff8", calc.peek(0));
    }

    @Test
    public void testAddingWhileChangingBases() throws Exception {
        RPNCalculator calc = new RPNCalculator(16);
        calc.push("10");
        calc.changeBase("H");
        calc.push("0xc");
        calc.changeBase("O");
        calc.execute("+");
        calc.push("03");
        calc.execute("+");
        calc.changeBase("B");
        assertEquals("0b0000000000011001", calc.peek(0));
    }
}