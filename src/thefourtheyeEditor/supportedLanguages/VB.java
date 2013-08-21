package thefourtheyeEditor.supportedLanguages;

import java.util.ArrayList;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;
import com.topcoder.shared.problem.TestCase;

public class VB extends Common
{
    public VB(Language lang, ProblemComponentModel component)
    {
       super(lang, component);
    }

    @Override
    public ArrayList<String> getTestSuite()
    {
        ArrayList<String> testSuite = new ArrayList<String>();
        TestCase[] testCase = problemComponent.getTestCases();
        for (int i = 0; i < testCase.length; i++)
        {
            for (int j = 0; j < testCase[i].getInput().length; j++)
            {
//                testSuite.add(getInputParameterDefinitionLine(j, testCase[i].getInput()[j]));
            }
        }
        return testSuite;
    }

   @Override
   public ArrayList<String> getSolutionTemplate()
   {
      // TODO Auto-generated method stub
      return null;
   }
}