import sys

class $CLASSNAME$:
    $METHODSIGNATURE$
        res = 
        return res

#BEGINCUT
def printer(expected, returned):
    print "Returned :", '"' + returned + '"' if isinstance(returned, str) else returned
    print "Expected :", '"' + expected + '"' if isinstance(expected, str) else expected, "\n"

def checkEq(expected, returned):
    if isinstance(expected, float) and isinstance(returned, float):
        expected, returned = float(expected), float(returned)
        return ((returned - expected if returned - expected > 0 else -returned + expected) < 1E-9 or
            (returned > (1 - 1E-9 * (1 if expected > 0 else -1)) * expected and
            returned < (1 + 1E-9 * (1 if expected > 0 else -1)) * expected))
    else:
        return expected == returned


def eq(caseNo, expected, returned):
    if isinstance(expected, tuple) and isinstance(returned, tuple):
        if len(expected) != len(returned):
            print "Case", caseNo, "failed: Returned", len(returned), "elements; Expected", len(expected), "elements."
            printer(expected, returned)
            return
        else:
            for i in range(len(expected)):
                if (checkEq(expected[i], returned[i]) == False):
                    print "Case", caseNo, "failed: Expected and Returned tuples differ in position", i
                    printer(expected, returned)
                    return
        print "Case", caseNo, "passed."
    else:
        if checkEq(expected, returned):
            print "Case", caseNo, "passed."
        else:
            print "Case", caseNo, "failed."
            printer(expected, returned)

$TESTCASES$
#ENDCUT

# Powered by thefourtheyeEditor
