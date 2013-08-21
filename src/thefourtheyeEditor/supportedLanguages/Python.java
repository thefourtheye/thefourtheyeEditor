package thefourtheyeEditor.supportedLanguages;

import java.util.ArrayList;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;

public class Python extends Common
{
    public Python(Language lang, ProblemComponentModel component)
    {
       super(lang, component);
    }
    
    private String getCompleteReturnType(int dimensions)
    {
        if (dimensions == 0)
        {
            return "";
        }
        else
        {
            return "(" + getCompleteReturnType(dimensions - 1) + "),";
        }
    }
    
    @Override
    public ArrayList<String> getTestSuite()
    {
        return testSuite;
    }

   @Override
   public ArrayList<String> getSolutionTemplate()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
