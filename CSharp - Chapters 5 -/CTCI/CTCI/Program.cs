using CTCI.Chapter_5;
using System;

namespace CTCI
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            var result = PairwiseSwap.Swap(0b101110);
            Console.WriteLine(result);
            Console.ReadKey();
        }
    }
}
