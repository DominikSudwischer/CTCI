using System;
using System.Collections.Generic;
using System.Text;

namespace CTCI.Chapter_5
{
    /// <summary>
    /// Given a positive integer, print the next smallest and next largest number that have the same number of 1s
    /// in their binary representation.
    /// </summary>
    public static class NextNumber
    {
        public static void PrintNextNumbers(int x)
        {
            // Idea: To increase the number as little as possible, one should replace the rightmost non-trailing 0
            // by a 1 and then flip the rightmost 1 to a 0. Afterwards, shift all 1s right of the initially flipped 0
            // to the right as much as possible.
            // ToDo: Actually solve this.
        }
    }
}
