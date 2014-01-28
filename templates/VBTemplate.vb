Imports System.Collections.Generic
Imports Microsoft.VisualBasic

Public Class $CLASSNAME$
    $METHODSIGNATURE$
        Dim Res As $RC$
        Return Res
    End Function
End Class

'BEGINCUT
Module MainModule
	Sub Print (ByVal data As Object)
        If TypeOf data Is Array Then
            Dim arrayData As Array = CType(data, Array), Index As Integer
            Console.Write("{")
            For Index = 0 To arrayData.GetUpperBound(0)
                If Index <> 0 Then
                    Console.Write(", ")
                End If
                Print (arrayData.GetValue(Index))
            Next
            Console.WriteLine("}")
    	Else If TypeOf data Is String Then
            Console.Write("'" + data + "'")
        Else If TypeOf data Is Long Then
            Console.Write(CStr(data) + "L")
        Else If TypeOf data Is Double Then
            Console.Write(String.Format("{0:F10}", data))
        Else
            Console.Write(CStr(data))
        End If
	End Sub

    Function Eq (ByVal expected As Object, ByVal returned As Object) As Boolean
        If TypeOf expected Is Double And TypeOf returned Is Double Then
            ' Source : http://msdn.microsoft.com/en-us/library/ae382yt8(VS.80).aspx
            Dim have, need as Double
            have = Convert.ToDouble(returned)
            need = Convert.ToDouble(expected)
            Dim closeEnough As Double = 0.0000000001
            Dim absoluteDifference As Double = Math.Abs(returned - expected)
            Return (absoluteDifference < closeEnough)
        Else
            Return expected.Equals(returned)
        End If
    End Function

    Sub Eq (ByVal caseNo As Integer, ByVal expected As Object, ByVal returned As Object)
        If TypeOf expected Is Array And TypeOf returned Is Array Then
            Dim expectedArray As Array = CType(expected, Array), Index As Integer
            Dim returnedArray As Array = CType(returned, Array)
            If (expectedArray.GetUpperBound(0) <> returnedArray.GetUpperBound(0)) Then
                Console.WriteLine("Case " + CStr(caseNo) + " failed: Returned " + CStr(returnedArray.Length) _
                    + " elements; Expected " + CStr(expectedArray.Length) + " elements.")
                Console.Write("Returned : ")
                Print(returnedArray)
                Console.Write("Expected : ")
                Print(expectedArray)
                Console.Write(VbCrLf)
                Return
            Else
                For Index = 0 To expectedArray.GetUpperBound(0)
                    If Eq(expectedArray.GetValue(Index), returnedArray.GetValue(Index)) = False Then
                        Console.WriteLine("Case " + CStr(caseNo) + _
                            " failed: Expected and Returned Arrays differ in position " + CStr(Index))
                        Console.Write("Returned : ")
                        Print(returnedArray)
                        Console.Write("Expected : ")
                        print(expectedArray)
                        Console.Write(VbCrLf)
                        Return
                    End If
                Next
            End If
        Else
            If Eq(expected, returned) = False Then
                Console.WriteLine("Case " + CStr(caseNo) + " failed.")
                Console.Write("Returned : ")
                Print(returned)
                Console.WriteLine()
                Console.Write("Expected : ")
                Print(expected)
                Console.WriteLine(VbCrLf)
                Return
            End If
        End If
        Console.WriteLine("Case " + CStr(caseNo) + " passed.")
    End Sub

    $TESTCASES$

End Module
'ENDCUT

' Powered by thefourtheyeEditor
