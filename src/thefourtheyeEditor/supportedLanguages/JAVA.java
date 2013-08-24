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
            invokeLine = "new " + getClassName()+"()."+getMethodName()+ "(";
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
               + getReturnTypeDescriptor() + " testCaseResult = "
               + testCases[i].getOutput() + ";";
         testSuite.add(outputParamDefn);
         String scalarIden = getReturnType().getDimension() == 0?", true":"";
         testSuite.add(baseIndentation + indentation + "TestableDataModel expected" +
               " = new TestableDataModel(testCaseResult" + scalarIden + ");");

         testSuite.add(baseIndentation + indentation + "expected.eq("
               + String.valueOf(i) + ", new TestableDataModel(" + invokeLine + 
               scalarIden + ")" + scalarIden + ");\n" + baseIndentation + "}");
      }
      return testSuite;
   }
   public String getFileExtension()
   {
      return ".java";
   }
}
