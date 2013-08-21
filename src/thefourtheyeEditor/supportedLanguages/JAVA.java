package thefourtheyeEditor.supportedLanguages;

import java.util.ArrayList;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;
import com.topcoder.shared.problem.DataType;
import com.topcoder.shared.problem.TestCase;

public class JAVA extends Common
{ 
    public JAVA(Language lang, ProblemComponentModel component)
    {
       super(lang, component);
       baseIndentation = indentation + indentation;
    }
    
    @Override
    public ArrayList<String> getTestSuite()
    {
       DataType[] paramTypes = getParamTypes();
       TestCase[] testCases = getTestCases();
       String invokeLine = "";
       for (int i = 0; i < testCases.length; i++)
       {
          String[] inputParams = testCases[i].getInput();
          if (invokeLine == "")
          {
             invokeLine = "new " + getClassName() + "()." + getMethodName() + "(";
             for (int j = 0; j < inputParams.length; j++)
             {
                invokeLine += "args" + String.valueOf(j)
                      + (j != inputParams.length - 1 ? "," : ")");
             }
          }
          testSuite.add(baseIndentation + "{");
          for (int j = 0; j < inputParams.length; j++)
          {
             String inputParamDefn = baseIndentation + indentation
                   + paramTypes[j].getDescriptor(currentLanguage) + " args"
                   + String.valueOf(j) + " = " + inputParams[j] + ";";
             testSuite.add(inputParamDefn);
          }

          String outputParamDefn = baseIndentation + indentation
                + getReturnTypeDescriptor() + " expected = " + 
                testCases[i].getOutput() + ";";
          testSuite.add(outputParamDefn);

          testSuite.add(baseIndentation + indentation + "eq("
                + String.valueOf(i) + ", " + invokeLine + ", expected, true);\n"
                + baseIndentation + "}");
       }
       return testSuite;
    }

   @Override
   public ArrayList<String> getSolutionTemplate()
   {
      ArrayList<String> solutionTemplate = new ArrayList<String>();
      String currentLine = "";
      for (int i = 0; i < sourceTemplate.size(); i++)
      {
         currentLine = sourceTemplate.get(i);
         if (currentLine.contains("$CLASSNAME$"))
         {
            solutionTemplate.add(currentLine.replace("$CLASSNAME$", getClassName()));
         }
         else if (currentLine.contains("$METHODSIGNATURE$"))
         {
            solutionTemplate.add(currentLine.replace("$METHODSIGNATURE$", getMethodSignature()));
         }
         else if (currentLine.contains("$RC$"))
         {
            solutionTemplate.add(currentLine.replace("$RC$", getReturnTypeDescriptor()));
         }
         else if (currentLine.contains("$TESTCASES$"))
         {
            solutionTemplate.addAll(getTestSuite());
         }
         else
         {
            solutionTemplate.add(currentLine);
         }
      }
      return solutionTemplate;
   }
}
