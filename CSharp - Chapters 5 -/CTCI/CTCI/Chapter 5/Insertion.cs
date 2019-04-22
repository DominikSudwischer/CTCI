using System;
using System.Collections.Generic;
using System.Text;

namespace CTCI.Chapter_5
{
    /// <summary>
    /// Given two 32 bit integers N and M and two indices i and j, insert M into N such that M starts at bit j of N and ends at
    /// bit i j of N.
    /// Example: N = 100000000, M = 10011, i = 2, j = 6
    /// Output: 101001100
    /// </summary>
    public static class Insertion
    {
        public static uint Insert(uint N, uint M, int i, int j)
        {
            uint x = (~0u) << i;
            Console.WriteLine(x);
            // for example, if i = 2, then x = 1...100
            
            uint y = (~0u) >> 32 - j - 1;
            // for example, if j = 6, then y = 0...0[32-j-1 times]1...1[j+1 times]
            x = ~(x & y); // x is now 0 if and only if a bit of M should be inserted into that bit.

            // We shift M in order to account for the fact that M should end at i-th bit of N (counted from the right).
            M = M << i;

            // Using x as a mask, we clear N and fill the cleared bits with the bits of M
            return (N & x) | M;
        }
    }
}
