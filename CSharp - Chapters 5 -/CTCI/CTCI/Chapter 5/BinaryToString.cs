using System;
using System.Collections.Generic;
using System.Text;

namespace CTCI.Chapter_5
{
    /// <summary>
    /// Given a double x between 0 and 1, print the binary representation of x.
    /// e.g. 0.25 -> "0.01".
    /// If 32 characters are not enough for an exact representation, return "ERROR".
    /// </summary>
    public static class BinaryToString
    {
        public static string Convert(double x)
        {
            if(x == 0) { return "0"; }
            // We start by declaring an array of characters. Since the number is between 0 or 1, we can start with "0.".
            var digits = new char[32];
            digits[0] = '0';
            digits[1] = '.';
            double currentPower = 0.5;

            // We use an algorithm similar to converting decimal numbers to binary, but using negative powers of 2 instead
            // of positive powers here. If a number between 0 and 1 is at least 0.5, its bit for the power 2^(-1) is 1.
            // If we subtract that and the result is at least 0.25, its bit for the power 2^(-2) is 1 and so on.
            for(int i = 2; (i < 32) && x > 0.0; i++)
            {
                if(x >= currentPower)
                {
                    digits[i] = '1';
                    x = x - currentPower;
                }
                else
                {
                    digits[i] = '0';
                }

                currentPower /= 2;
            }

            // Since sums of negative powers of 2 (like 1/2, 1/4, 1/8, ...) can be represented exactly with double precision,
            // we can check for x being exactly 0. 
            return x == 0.0 ? string.Join("", digits) : "ERROR";
        }
    }
}
