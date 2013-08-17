package thefourtheyeEditor.supportedLanguages;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;

public class CSharp extends Common
{
    public CSharp(Language lang, ProblemComponentModel component)
    {
        currentLanguage = lang;
        problemComponent = component;
    }
    
    @Override
    public String getSolutionBody()
    {
        String solutionBody = "public class " + getClassName() + "\n{\n" + indentation + "public " + 
                getMethodSignature() + "\n" + indentation + "{\n" + indentation + indentation + 
                getReturnTypeDescriptor() + " answer;\n" + indentation + indentation + 
                "return answer;\n" + indentation + "}\n}";
        return solutionBody;
    }

}
