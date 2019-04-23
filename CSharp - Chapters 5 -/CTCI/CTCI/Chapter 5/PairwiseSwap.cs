using System;
using System.Collections.Generic;
using System.Text;

namespace CTCI.Chapter_5
{
    // Given an integer A, swap the 0th and 1st bit, the 2nd and 3rd bit, and so on.
    public static class PairwiseSwap
    {
        public static int Swap(int x)
        {
            // We need to move all the even bits to the left and all the odd bits to the right
            // So let us first create masks to obtain the even and the odd bits.
            var odd = 0;
            for(int i = 0; i < 32; i++)
            {
                odd += (i % 2);
                odd <<= 1;
            }
            // Now, odd should have 1s in all odd bits, i.e. odd = 1010...1010.
            // A mask can be obtained by flipping all bits of odd.
            var even = ~odd;

            Console.WriteLine(((x & even) << 1) + " " +  ((x & odd) >> 1));
            // We can now use the masks, shift the bits and put x back together.
            return ((x & even) << 1) + ((x & odd) >> 1);
        }
    }
}
