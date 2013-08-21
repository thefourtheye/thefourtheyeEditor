package thefourtheyeEditor.supportedLanguages;

import java.util.ArrayList;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;
import com.topcoder.shared.problem.DataType;
import com.topcoder.shared.problem.TestCase;

/**
 * @author thefourtheye
 *
 */
public class CPP extends Common
{
   public CPP(Language lang, ProblemComponentModel component)
   {
      super(lang, component);
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
            invokeLine = getClassName() + "()." + getMethodName() + "(";
            for (int j = 0; j < inputParams.length; j++)
            {
               invokeLine += "args" + String.valueOf(j)
                     + (j != inputParams.length - 1 ? "," : ")");
            }
         }
         testSuite.add(indentation + "{");
         for (int j = 0; j < inputParams.length; j++)
         {
            String inputParamDefn = indentation + indentation
                  + paramTypes[j].getDescriptor(currentLanguage) + " args"
                  + String.valueOf(j);

            if (paramTypes[j].getDimension() == 0)
            {
               inputParamDefn += " = " + inputParams[j];
            }
            else if (paramTypes[j].getDimension() == 1)
            {
               String tempArg = "tempArray" + String.valueOf(j);

               testSuite.add(indentation + indentation
                     + paramTypes[j].getBaseName() + " " + tempArg + "[] = "
                     + inputParams[j] + ";");

               inputParamDefn += "(" + tempArg + ", " + tempArg + " + ARRSIZE("
                     + tempArg + "))";
            }
            testSuite.add(inputParamDefn + ";");
         }

         String outputParamDefn = indentation + indentation
               + getReturnTypeDescriptor() + " expected";
         if (getReturnType().getDimension() == 0)
         {
            outputParamDefn += " = " + testCases[i].getOutput();
         }
         else if (getReturnType().getDimension() == 1)
         {
            String tempArg = "tempResultArray";

            testSuite.add(indentation + indentation
                  + getReturnType().getBaseName().toLowerCase() + " " + tempArg
                  + "[] = " + testCases[i].getOutput() + ";");

            outputParamDefn += "(" + tempArg + ", " + tempArg + " + ARRSIZE("
                  + tempArg + "))";
         }
         testSuite.add(outputParamDefn + ";");

         testSuite.add(indentation + indentation + "eq("
               + String.valueOf(i) + ", " + invokeLine + ", expected);\n"
               + indentation + "}");
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

