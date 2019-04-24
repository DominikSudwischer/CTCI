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
            var bytesPerRow = width / 2;

            // ... and how many rows it has
            var numRows = screen.Length / bytesPerRow;

            // y is the row (where 0 is the top row, 1 is the second row, ...)
            // So what we have to to is to compute the columns that contain x1 and x2, respectively.
            var x1Col = x1 / 8;
            var x2Col = x2 / 8;

            // ToDo
        }
    }
}
