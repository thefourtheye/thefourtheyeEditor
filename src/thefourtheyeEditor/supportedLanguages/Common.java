package thefourtheyeEditor.supportedLanguages;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;
import com.topcoder.shared.problem.DataType;
import thefourtheyeEditor.LanguageInterface;

public abstract class Common implements LanguageInterface
{
    ProblemComponentModel problemComponent = null;
    Language              currentLanguage  = null;
    String                indentation      = null;

    Common()
    {
        indentation = "    ";
    }
    
    public String getMethodSignature()
    {
        return currentLanguage.getMethodSignature (problemComponent.getMethodName(), 
                problemComponent.getReturnType(), problemComponent.getParamTypes(), 
                problemComponent.getParamNames());
    }
    
    public String getClassName()
    {
        return problemComponent.getClassName();
    }
    
    public String getReturnTypeDescriptor()
    {
        return problemComponent.getReturnType().getDescriptor(currentLanguage);
    }
    
    public DataType getReturnType()
    {
        return problemComponent.getReturnType();
    }
}
