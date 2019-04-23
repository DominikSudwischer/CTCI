using CTCI.Chapter_5;
using System;

namespace CTCI
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            var result = Conversion.Convert(29, 15);
            Console.WriteLine(result);
            Console.ReadKey();
        }
    }
}
