import sys

class $CLASSNAME$:
    $METHODSIGNATURE$
        res = 
        return res

#BEGINCUT
def printer(expected, returned):
    print "Returned :", '"' + returned + '"' if isinstance(returned, str) else returned
    print "Expected :", '"' + expected + '"' if isinstance(expected, str) else expected, "\n"

def eq(caseNo, expected, returned):
    if isinstance(expected, tuple) and isinstance(returned, tuple):
        if len(expected) != len(returned):
            print "Case", caseNo, "failed: Returned", len(returned), "elements; Expected", len(expected), "elements."
            printer(expected, returned)
            return
        else:
            for i in range(len(expected)):
                if (expected[i] != returned[i]):
                    print "Case", caseNo, "failed: Expected and Returned tuples differ in position", i
                    printer(expected, returned)
                    return
        print "Case", caseNo, "passed."
    else:
        if expected == returned:
            print "Case", caseNo, "passed."
        else:
            print "Case", caseNo, "failed."
            printer(expected, returned)

$TESTCASES$
#ENDCUT
