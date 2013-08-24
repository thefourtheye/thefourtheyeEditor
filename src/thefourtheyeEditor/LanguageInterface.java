package thefourtheyeEditor;

import java.util.ArrayList;

public interface LanguageInterface
{
   String getClassName();

   String getMethodSignature();

   String getReturnTypeDescriptor();

   ArrayList<String> getTestSuite();

   ArrayList<String> getSolutionTemplate();
   
   String getFileExtension();
}
