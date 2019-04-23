using System;
using System.Collections.Generic;
using System.Text;

namespace CTCI.Chapter_5
{
    /// <summary>
    /// Explain what the following code does: (n & (n - 1)) == 0
    /// </summary>
    class Debugger
    {
        /* We do not have to write code here.
         * If n & n - 1 equals 0, this means that n and n - 1 do not have a single 1 in common.
         * This means any existing 1s in the binary representation of n - 1 would have to be
         * flipped by a carry over. Thus, n - 1 can only have a contiguous sequence of 1s beginning
         * at the rightmost bit.
         * Thus, n - 1 is of the form 0*1* (in regular expression notation).
         * This is exactly the form any integer of the form 2^k - 1 has.
         * We conclude that the code (n & (n - 1)) == 0 returns true if and only if n is a power of 2.
         */
    }
}
