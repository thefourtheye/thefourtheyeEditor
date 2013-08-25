using System;
using System.IO;
using System.Collections;

public class $CLASSNAME$
{
    $METHODSIGNATURE$
    {
        $RC$ res;
        return res;
    }
//BEGINCUT
    public static void print(Object data)
    {
        if (data.GetType().IsArray)
        {
            Console.Write("{");
            IEnumerable enumerable = data as IEnumerable;
            bool isFirst = true;
            foreach (Object arrayData in enumerable)
            {
                if (!isFirst)
                    Console.Write(", ");
                else
                    isFirst = !isFirst;
                print(arrayData);
            }
            Console.WriteLine("}");
        }
        else if (data.GetType() == typeof(System.String))
        {
            Console.Write("'" + data + "'");
        }
        else if(data.GetType() == typeof(double))
        {
            Console.Write(String.Format("{0:F10}", data));
        }
        else if(data.GetType() == typeof(long))
        {
            Console.Write(data + "L");
        }
        else
        {
            Console.Write(data);
        }
    }

    public static void eq(int caseNo, Object expected, Object returned)
    {
        if (expected.GetType().IsArray && returned.GetType().IsArray)
        {
            Array expectedArray = (Array)expected;
            Array returnedArray = (Array)returned;
            if (expectedArray.Length != returnedArray.Length)
            {
                Console.WriteLine("Case " + caseNo + " failed: Returned " + returnedArray.Length
                    + " elements; Expected " + expectedArray.Length + " elements.");
                Console.Write("Returned : "); print(returnedArray);
                Console.Write("Expected : "); print(expectedArray);
                Console.WriteLine();
                return;
            }
            else
            {
                for(int i = 0; i < expectedArray.Length; i++)
                {
                    if (expectedArray.GetValue(i).Equals(returnedArray.GetValue(i)) == false)
                    {
                        Console.WriteLine("Case " + caseNo + 
                            " failed: Expected and Returned Arrays differ in position " + i);
                        Console.Write("Returned : "); print(returnedArray);
                        Console.Write("Expected : "); print(expectedArray);
                        Console.WriteLine();
                        return;
                    }
                }
            }
        }
        else
        {
            if (expected.Equals(returned) == false)
            {
                Console.WriteLine("Case " + caseNo + " failed.");
                Console.Write("Returned : "); print(returned); Console.WriteLine();
                Console.Write("Expected : "); print(expected); Console.WriteLine();
                Console.WriteLine();
                return;
            }
        }
        Console.WriteLine("Case " + caseNo + " passed.");
    }

    public static void Main()
    {
        $TESTCASES$
    }
//ENDCUT
}
