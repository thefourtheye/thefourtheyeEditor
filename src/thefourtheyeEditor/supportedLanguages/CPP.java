package thefourtheyeEditor.supportedLanguages;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;

public class CPP extends Common
{
    public CPP(Language lang, ProblemComponentModel component)
    {
        currentLanguage = lang;
        problemComponent = component;
    }
    
    @Override
    public String getSolutionBody()
    {
        String solutionBody = "class " + getClassName() + "\n{\npublic:\n" + indentation + 
                getMethodSignature() + "\n" + indentation + "{\n" + indentation + indentation + 
                getReturnTypeDescriptor() + " answer;\n" + indentation + indentation + 
                "return answer;\n" + indentation + "}\n};";
        return solutionBody;
    }

}
