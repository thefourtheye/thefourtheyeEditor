package thefourtheyeEditor.supportedLanguages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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
      indentation = "    ";
      currentLanguage = language;
      problemComponent = component;
      try
      {
         properties.load(new FileInputStream(new File(System
               .getProperty("user.home"), "contestapplet.conf")));
         String fileName = properties
               .getProperty("thefourtheyeEditor.Templates."
                     + language.getName());
         BufferedReader reader = new BufferedReader(new FileReader(new File(
               fileName)));
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
      return currentLanguage.getMethodSignature(getMethodName(),
            getReturnType(), getParamTypes(), getParamNames());
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

   public ArrayList<String> getSolutionTemplate()
   {
      ArrayList<String> solutionTemplate = new ArrayList<String>();
      String currentLine = "";
      for (int i = 0; i < sourceTemplate.size(); i++)
      {
         currentLine = sourceTemplate.get(i);
         if (currentLine.contains("$CLASSNAME$"))
         {
            solutionTemplate.add(currentLine.replace("$CLASSNAME$",
                  getClassName()));
         }
         else if (currentLine.contains("$METHODSIGNATURE$"))
         {
            solutionTemplate.add(currentLine.replace("$METHODSIGNATURE$",
                  getMethodSignature()));
         }
         else if (currentLine.contains("$RC$"))
         {
            solutionTemplate.add(currentLine.replace("$RC$",
                  getReturnTypeDescriptor()));
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
