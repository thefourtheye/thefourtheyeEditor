package thefourtheyeEditor.supportedLanguages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import thefourtheyeEditor.LanguageInterface;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;
import com.topcoder.shared.problem.DataType;
import com.topcoder.shared.problem.TestCase;

public abstract class Common implements LanguageInterface
{
    ProblemComponentModel problemComponent = null;
    Language              currentLanguage  = null;
    String                baseIndentation  = null; 
    String                indentation      = null;
    ArrayList<String>     testSuite        = new ArrayList<String>();
    ArrayList<String>     sourceTemplate   = new ArrayList<String>();
    Properties            properties       = new Properties();
    Common(Language language, ProblemComponentModel component)
    {
        indentation      = "    ";
        currentLanguage  = language;
        problemComponent = component;
        try
        {
           properties.load(new FileInputStream(new File(System.getProperty("user.home"), "contestapplet.conf")));
           properties.list(System.out);
           System.out.println(System.getProperty("user.home"));
           String fileName  = properties.getProperty("thefourtheyeEditor.Templates." + language.getName());
           System.out.println(fileName);
           System.out.println(language.getName());
           BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
           String currentLine = "";
           while ((currentLine = reader.readLine()) != null)
           {
              sourceTemplate.add(currentLine);
           }
           reader.close();
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
    }
    
    public String getMethodSignature()
    {
        return currentLanguage.getMethodSignature (getMethodName(), getReturnType(), getParamTypes(), getParamNames());
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
    
    public String getMethodName()
    {
        return problemComponent.getMethodName();
    }
    
    public TestCase[] getTestCases()
    {
        return problemComponent.getTestCases();
    }

    public DataType[] getParamTypes()
    {
        return problemComponent.getParamTypes();
    }
    
    public String[] getParamNames()
    {
        return problemComponent.getParamNames();
    }
}
