package thefourtheyeEditor.supportedLanguages;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;

public class Python extends Common
{
    public Python(Language lang, ProblemComponentModel component)
    {
        currentLanguage = lang;
        problemComponent = component;
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
    public String getSolutionBody()
    {
        String returnData = "0";
        int dimensions = getReturnType().getDimension();
        if (dimensions > 0)
        {
            returnData = "(" + getCompleteReturnType(dimensions - 1) + ")";
        }
        else if (getReturnTypeDescriptor() == "string")
        {
            returnData = "\"\"";
        }
        String solutionBody = "class " + getClassName() + ":\n" + indentation + getMethodSignature() + "\n" + indentation +
            indentation + "answer = " + returnData + "\n" + indentation + indentation + "return answer"; 
        return solutionBody;
    }
}
