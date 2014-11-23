package Helpers;

public class Money
{
    public static int parse(String value)
    {
        if (value.matches("^(?:[1-9]\\d*|0)$"))
            return Integer.parseInt(value) * 100;
        
        if (value.matches("^(?:[1-9]\\d*|0)\\.[0-9]{1}$"))
            return Integer.parseInt(value.replace(".", "")) * 10;
        
        if (value.matches("^(?:[1-9]\\d*|0)\\.[0-9]{2}$"))
            return Integer.parseInt(value.replace(".", ""));
        
        else
            throw new NumberFormatException();
    }
    
    public static String toString(int value)
    {
        int units = value / 100;
        int cents = value % 100;
        
        if (value < 0)
            throw new IllegalArgumentException("Value must be positive");
        
        if (value < 10)
            return "0.0" + value;
        
        if (value < 100)
            return "0." + value;
        
        else
            return Integer.toString(units) + "."  + (cents < 10 ? "0" : "") + Integer.toString(cents);
    }
}
