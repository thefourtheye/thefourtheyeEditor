import java.util.*;
public class $CLASSNAME$
{
    public $METHODSIGNATURE$
    {
        $RC$ res;
        return res;
    }

//BEGINCUT
    public static void main(String[] args)
    {
        class TestableDataModel
        {
            public  Object   scalarData = null;
            public  ArrayList<Object> arrayData  = new ArrayList<Object>();
            private String   dataType   = "";
            private boolean  isArray    = true;

            TestableDataModel(Object obj, boolean isScalar)
            {
                scalarData = (Object)obj;
                isArray = false;
            }
            TestableDataModel(String[] obj)
            {
                for (Object data : obj) arrayData.add(data);
            }
            TestableDataModel(int[] obj)
            {
                for (Object data : obj) arrayData.add(data);
            }
            TestableDataModel(double[] obj)
            {
                for (Object data : obj) arrayData.add(data);
            }
            TestableDataModel(long[] obj)
            {
                for (Object data : obj) arrayData.add(data);
            }
            TestableDataModel(boolean[] obj)
            {
                for (Object data : obj) arrayData.add(data);
            }

            public boolean eq(int caseNo, TestableDataModel obj, boolean printOnScreen)
            {
                return eq(caseNo, scalarData, obj.scalarData, printOnScreen);
            }

            public void eq(int caseNo, TestableDataModel obj)
            {
                boolean result = (obj.arrayData.size() == arrayData.size());
                if (!result)
                {
                    System.out.println("Case " + caseNo + " failed: Returned " + obj.arrayData.size() +
                        " elements; Expected " + arrayData.size() + " elements.");
                    System.out.print("Returned : "); obj.print();
                    System.out.print("Expected : "); print();
                    System.out.println();
                    return;
                }
                for (int i = 0; i < arrayData.size(); i++)
                {
                    if (!eq(caseNo, arrayData.get(i), obj.arrayData.get(i), false))
                    {
                        System.out.println("Case " + caseNo + 
                            " failed. Expected and Returned array differ in position " + i + ".");
                        System.out.print("Returned : "); obj.print();
                        System.out.print("Expected : "); print();
                        System.out.println();
                        return;
                    }
                }
                System.out.println("Case " + caseNo + " passed.");
            }

            private boolean eq(int caseNo, Object obj1, Object obj2, boolean printOnScreen)
            {
                boolean result = true;
                if (obj1 instanceof Double && obj2 instanceof Double)
                {
                    double have = ((Double)obj2).doubleValue(), need = ((Double)obj1).doubleValue();
                    result = ((have - need > 0 ? have - need : -have + need) < 1E-9 ||
                        (have > (1 - 1E-9 * (need > 0 ? 1 : -1)) * need &&
                        have < (1 + 1E-9 * (need > 0 ? 1 : -1)) * need));
                }
                else
                {
                    if (obj1 == null || obj2 == null) result = false;
                    else  result = obj1.equals(obj2);
                }
                if (printOnScreen)
                {
                    if (result)
                    {
                        System.out.println("Case " + caseNo + " passed.");
                    }
                    else
                    {
                        System.out.println("Case " + caseNo + " failed.");
                        System.out.print("Returned : "); printScalar(obj2);
                        System.out.println();
                        System.out.print("Expected : "); printScalar(obj1);
                        System.out.println("\n");
                    }
                }
                return result;
            }

            private void printScalar(Object dataToBePrinted)
            {
                if (dataToBePrinted instanceof Long)
                {
                    System.out.print(dataToBePrinted + "L");
                }
                else if (dataToBePrinted instanceof Double)
                {
                    System.out.format("%.10f", dataToBePrinted);
                }
                else if (dataToBePrinted instanceof String)
                {
                    System.out.print("\"" + dataToBePrinted + "\"");
                }
                else
                {
                    System.out.print(dataToBePrinted);
                }
            }

            public void print()
            {
                if (isArray)
                {
                    System.out.print("{");
                    for (int i = 0; i < arrayData.size(); i++)
                    {
                        if (i != 0) System.out.print(", ");
                        printScalar(arrayData.get(i));
                    }
                    System.out.println("}");
                }
                else
                {
                    printScalar(scalarData);
                }
            }
        }
        $TESTCASES$
    }
//ENDCUT
}

// Powered by thefourtheyeEditor
