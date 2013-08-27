package thefourtheyeEditor.supportedLanguages;

import java.util.ArrayList;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;
import com.topcoder.shared.problem.DataType;
import com.topcoder.shared.problem.TestCase;

public class Python extends Common
{
    public Python(Language lang, ProblemComponentModel component) throws Exception
    {
       super(lang, component);
    }
    
    private String getData(int dim, String data)
    {
        return dim == 0?data:"(" + data.substring(1, data.length() - 1) + ",)"; 
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
             invokeLine = getClassName() + "()." + getMethodName()+ "(";
             for (int j = 0; j < inputParams.length; j++)
             {
                invokeLine += "args" + String.valueOf(j)
                      + (j != inputParams.length - 1 ? "," : ")");
             }
          }
          for (int j = 0; j < inputParams.length; j++)
          {
             String inputParamDefn = "args" + String.valueOf(j) + " = ";
             inputParamDefn += getData(paramTypes[j].getDimension(), inputParams[j]);
             testSuite.add(inputParamDefn);
          }
          String outputParamDefn = "expected = " + 
                getData(getReturnType().getDimension(), testCases[i].getOutput());
          testSuite.add(outputParamDefn);
          testSuite.add("eq(" + i + ", expected, " + invokeLine +")");
          testSuite.add("");
       }
       return testSuite;
    }

    public String getFileExtension()
    {
       return ".py";
    }
}
