package thefourtheyeEditor.supportedLanguages;

import java.util.ArrayList;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;
import com.topcoder.shared.problem.DataType;
import com.topcoder.shared.problem.TestCase;

public class VB extends Common
{
    public VB(Language lang, ProblemComponentModel component) throws Exception
    {
       super(lang, component);
       indentation = "        ";
       baseIndentation = "    ";
    }

    @Override
    public ArrayList<String> getTestSuite()
    {
       DataType[] paramTypes = getParamTypes();
       TestCase[] testCases = getTestCases();
       String invokeLine = "";
       for (int i = 0; i < testCases.length; i++)
       {
          testSuite.add(baseIndentation + "Sub TestCase" + String.valueOf(i));
          String[] inputParams = testCases[i].getInput();
          if (invokeLine == "")
          {
             invokeLine = "new " + getClassName() + "()." + getMethodName()+ "(";
             for (int j = 0; j < inputParams.length; j++)
             {
                invokeLine += "args" + String.valueOf(j)
                      + (j != inputParams.length - 1 ? "," : ")");
             }
          }
          
          for (int j = 0; j < inputParams.length; j++)
          {
             String inputParamDefn = indentation + "Dim args" + String.valueOf(j) 
                   + " As " + paramTypes[j].getDescriptor(currentLanguage)
                   + " = " + inputParams[j];
             testSuite.add(inputParamDefn);
          }
          String outputParamDefn = indentation + "Dim expected As " + 
                getReturnTypeDescriptor() + " = " + testCases[i].getOutput();
          testSuite.add(outputParamDefn);

          testSuite.add(indentation + "Eq(" + String.valueOf(i) + ", expected, "
                + invokeLine + ")");
          testSuite.add(baseIndentation + "End Sub\n");
       }
       testSuite.add(baseIndentation + "Sub Main");
       for (int i = 0; i < testCases.length; i++)
       {
          testSuite.add(indentation + "TestCase" + String.valueOf(i) + "()");
       }
       testSuite.add(baseIndentation + "End Sub");
       return testSuite;
    }

    public String getFileExtension()
    {
       return ".vb";
    }
}