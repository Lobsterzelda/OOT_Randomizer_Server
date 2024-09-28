package org.lobsterZelda.utils;

public class LobsterStringUtils {

    public static boolean isWholeStringAlphaNumeric(String inputString)
    {
        if (inputString == null)
            return true;
        int strLength = inputString.length();

        for (int i = 0; i < strLength; ++i)
        {
            if (!Character.isLetterOrDigit(inputString.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean isWholeStringASCIIPrintableChars(String inputString)
    {
        if (inputString == null)
            return true;
        int strLength = inputString.length();

        for (int i = 0; i < strLength; ++i)
        {
            char currentChar = inputString.charAt(i);

            if (currentChar < 0x20 || currentChar >= 0x7F)
                return false;
        }

        return true;
    }
}
