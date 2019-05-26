using System;
using System.Collections.Generic;
using System.Text;

namespace CTCI.Chapter_5
{
    /// <summary>
    /// Given an integer, you are allowed to flip exactly one bit from 0 to 1. Output the length of the longest contiguous sequence
    /// of 1's that can be achieved by flipping one bit of x.
    /// </summary>
    public static class FlipBitToWin
    {
        public static int LongestChain(int x)
        {
            // We use a queue to keep track of the 1's and 0's in the binary representation of x.
            var max = 0;
            var q = new Queue<bool>();
            var hasZero = false;
            // For 32 bit integers, we iterate over 32 bits, obviously.
            for(int i = 0; i < 32; i++)
            {
                var isOne = (x & 1) == 1; // This one always gives us rightmost bit. In the next line, we do a bit shift.
                x = x >> 1; // That means, we iterate from right to left through the binary representation of x.
                if(isOne)
                {
                    q.Enqueue(true);
                }
                else
                {
                    if(!hasZero) // When we hit the first zero, everything is fine, since one zero can be flipped.
                    {
                        hasZero = true;
                        q.Enqueue(false);
                    }
                    else // Otherwise, we will have to remove the everything up to and including the previous 0 to consider
                    // flipping the more recent 0 instead of the older 0.
                    // In this step, we have to count the length of the previous chain.
                    {
                        max = Math.Max(max, q.Count);
                        var tmp = true;
                        do
                        {
                            tmp = q.Dequeue();
                        } while (tmp != false); // We stop when the first 0 is removed
                        q.Enqueue(false);
                    }
                }
            }
            max = Math.Max(max, q.Count); // If the last bit is different from 0, the queue is not popped at the end,
            // so we need to consider its current size.
            return max;
        }
    }
}
