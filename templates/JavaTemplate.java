import java.util.*;
public class $CLASSNAME$
{
    $METHODSIGNATURE$
    {
        $RC$ res;
        return res;
    }

//BEGINCUT
    public static void main(String[] args)
    {
        $TESTCASES$
    }

    private static <T> boolean eq ( int n, T returned, T expected, boolean printOnScreen )
    {
        boolean result = (returned == expected);
        if (printOnScreen)
        {
            if (result)
            {
                System.err.println("Case " + n + " passed.");
            }
            else
            {
                System.err.println("Case " + n + " failed.");
                print( "Returned", returned );
                System.err.println();
                print( "Expected", expected );
                System.err.println("\n");
            }
        }
        return result;
    }

    private static <T> boolean eq ( int n, T[] returned, T[] expected, boolean printOnScreen )
    {
        boolean result = (returned.length == expected.length);
        if ( printOnScreen )
        {
            if ( returned.length != expected.length )
            {
                System.err.println("Case " + n + " failed: Returned " + returned.length + " elements; Expected " + expected.length + " elements.");
                print( "Returned", returned );
                print( "Expected", expected );
                System.err.println();
                return false;
            }
            for ( int i = 0; i < returned.length; i++)
            {
                if ( eq (i, returned[i], expected[i], false) == false )
                {
                    System.err.println("Case " + n + " failed. Expected and Returned array differ in position " + i);
                    print( "Returned", returned );
                    print( "Expected", expected );
                    System.err.println();
                    return false;
                }
            }
            System.err.println("Case " + n + " passed.");
        }
        return true;
    }

    private static <T> void print( String str, T a )
    {
        if ( str != "" )
        {
            str += " : ";
        }
        System.err.print(str + a);
    }

    private static <Long> void print( String str, long a )
    {
        if ( str != "" )
        {
            str += " : ";
        }
        System.err.print(str + a + "L");
    }

    private static <String> void print( String str, String s )
    {
        if ( str != "" )
        {
            str += " : ";
        }
        System.err.print(str + "\"" + s + "\"");
    }

    private static <T> void print( String str, T[] rs )
    {
        if ( rs == null) return;
        System.err.print(str + " : {");
        for ( int i = 0; i < rs.length; i++ )
        {
            if ( i != 0 ) System.err.print(", ");
            print ("", rs[i]);
        }
        System.err.println('}');
    }

//ENDCUT
}
