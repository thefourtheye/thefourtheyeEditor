package thefourtheyeEditor.supportedLanguages;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;

public class VB extends Common
{
    public VB(Language lang, ProblemComponentModel component)
    {
        currentLanguage = lang;
        problemComponent = component;
    }

    @Override
    public String getSolutionBody()
    {
        String solutionBody = "Public Class " + getClassName() + "\n" + indentation + getMethodSignature() 
                + "\n" + indentation + indentation + "Dim answer As " + getReturnTypeDescriptor() + "\n" + 
                indentation + indentation + "Return answer\n" + indentation + "End Function\nEnd Class";
        return solutionBody;
    }

}
