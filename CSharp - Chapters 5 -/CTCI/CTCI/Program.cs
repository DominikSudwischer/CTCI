using CTCI.Chapter_5;
using System;

namespace CTCI
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            var screen = new byte[4];
            DrawLine.Draw(screen, 32, 16, 25, 0);
            foreach (var b in screen) {
                Console.WriteLine(b);
            }
            Console.ReadKey();
        }
    }
}
