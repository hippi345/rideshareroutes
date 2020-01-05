package com.company;

public class inputValidation
{
    public static boolean checkForHelp(String[] input)
    {
        if (input[0].equals("-h")
                || input[0].equals("help")
                || input[0].equals("--help"))
        {
            Usage.usage(input);
            return true;
        }
        else
        {
            return false;
        }
    }
}
