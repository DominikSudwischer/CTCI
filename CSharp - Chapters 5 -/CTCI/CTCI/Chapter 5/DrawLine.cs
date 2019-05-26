using System;
using System.Collections.Generic;
using System.Text;

namespace CTCI.Chapter_5
{
    /// <summary>
    /// A monochrome screen is stored as a single array of bytes, with each byte denoting 8 consecutive pixels.
    /// The screen has some width which is a multiple of 8 (that means, if the byte array has length 50 and the width
    /// is 16, then each row has 2 bytes. The height is 25 in this case, since 50 * 8 / 16 = 25).
    /// Implement a function to draw a horizontal line from (x1, y) to (x2, y).
    /// </summary>
    public static class DrawLine
    {
        public static void Draw(byte[] screen, int width, int x1, int x2, int y)
        {
            // We start by computing how many bytes per row the screen has ...
            var bytesPerRow = width / 8;

            // ... and how many rows it has
            var numRows = screen.Length / bytesPerRow;

            // y is the row (where 0 is the top row, 1 is the second row, ...)
            // So what we have to to is to compute the columns that contain x1 and x2, respectively
            // where x1 and x2 start at 0 as well.
            var x1Col = x1 / 8;
            var x2Col = x2 / 8;

            // The y-th row contains the array elements from bytesPerRow * y to bytesPerRow * (y + 1) - 1, inclusively.
            var idxMin = y * bytesPerRow;

            // All columns strictly between x1Col and x2Col can be set to 1, i.e. the corresponding bytes
            // can be set to 255 each.
            for(int i = idxMin + x1Col + 1; i < idxMin + x2Col; i++)
            {
                screen[i] = 255;
            }

            // In the columns that contain x1 and x2, we have to be more careful. We only set those bits
            // to 1 that are >= x1 and <= x2.
            // So the number of bits that need not be shifted in the byte containing x1 is x1 % 8.
            var x1Shift = x1 % 8;

            // For x2, we need to set all bits up to and including x2 to 1, so that includes the (x2 % 8 + 1) bit (from the left)
            // within the byte.
            var x2Shift = 7 - x2 % 8;

            // We can do the shifts by setting the corresponding byte to 0b11111111 ( = 255 ) and shift the number of
            // skippable bits.
            screen[x1Col] = (byte)(0b11111111 >> x1Shift);
            screen[x2Col] = (byte)(0b11111111 << x2Shift);
        }
    }
}
