using System;
using System.Collections.Generic;
using System.Text;

namespace CTCI.Chapter_5
{
    /// <summary>
    /// Given two integers A and B, write a function that outputs the number of bits to flip to
    /// transform A to B.
    /// </summary>
    public static class Conversion
    {
        public static int Convert(int A, int B)
        {
            // We need to count the number of different bits.
            // This is exactly the number of 1s in A XOR B.
            var xord = A ^ B;

            // Now we just need to count the number of 1s in xord.
            var counter = 0;
            while(xord != 0)
            {
                // Always check whether the rightmost bit is 1 and shift all bits to the right
                // until xord has no 1s left.
                counter += (xord & 1);
                xord >>= 1;
            }
            return counter;
        }
    }
}
