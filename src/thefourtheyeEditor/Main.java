package thefourtheyeEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import thefourtheyeEditor.supportedLanguages.CPP;
import thefourtheyeEditor.supportedLanguages.CSharp;
import thefourtheyeEditor.supportedLanguages.JAVA;
import thefourtheyeEditor.supportedLanguages.Python;
import thefourtheyeEditor.supportedLanguages.VB;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;
import com.topcoder.shared.problem.Renderer;

public class Main
{
   private JPanel minimalPanel;
   private JTextArea minimalEditor;
   private String baseLocation = "/home/thefourtheye/TestBed/";
   private File solutionFileObject = null; 
   public Main()
   {
      minimalEditor = new JTextArea();
      minimalEditor.setForeground(Color.white);
      minimalEditor.setBackground(Color.black);
      minimalEditor.setEditable(false);

      minimalPanel = new JPanel(new BorderLayout());
      minimalPanel.add(new JScrollPane(minimalEditor), BorderLayout.CENTER);
   }

   public JPanel getEditorPanel()
   {
      return minimalPanel;
   }

   private void updateStatus(String statusMessage, boolean isError)
   {
      updateStatus ((isError?"[ERROR] ":"") + statusMessage);
   }

   private void updateStatus(String statusMessage)
   {
      if (statusMessage == "")
      {
         minimalEditor.setText("");
      }
      else
      {
         minimalEditor.setText(minimalEditor.getText() + statusMessage + "\n");
      }
   }

   public void setProblemComponent (ProblemComponentModel component, 
         Language language, Renderer renderer)throws Exception
   {
      String contestName = component.getProblem().getRound().getContestName();
      String problemName = component.getProblem().getName();
      File contestDirFileObject = new File(baseLocation, contestName);

      if (contestDirFileObject.exists() == false)
      {
         if (contestDirFileObject.mkdirs() == false)
         {
            updateStatus("Directory creation failed : " + 
                  contestDirFileObject.getAbsolutePath(), true);
            return;
         }
      }
      else if (contestDirFileObject.isDirectory() == false)
      {
         updateStatus("Directory creation failed : " + 
               contestDirFileObject.getAbsolutePath() + ". There is a " + 
               "file with the same name exists", true);
         return;
      }
      else
      {
         if (contestDirFileObject.canWrite() == false)
         {
            updateStatus("Directory exists : " + 
                  contestDirFileObject.getAbsolutePath() + ", but NOT WRITABLE");
         }
      }

      File problemFileObject = 
            new File (contestDirFileObject, problemName + ".html");
      BufferedWriter writeFile = 
            new BufferedWriter(new FileWriter(problemFileObject));
      writeFile.write(renderer.toHTML(language));
      writeFile.close();
      updateStatus("Wrote problem statement to : " + 
            problemFileObject.getAbsolutePath());

      LanguageInterface currentLanguage = getLanguage(language, component);
      
      solutionFileObject = new File (contestDirFileObject, 
            currentLanguage.getClassName() + currentLanguage.getFileExtension());
      BufferedWriter solutionWriter = 
            new BufferedWriter(new FileWriter(solutionFileObject));
      ArrayList<String> testSuit = currentLanguage.getSolutionTemplate();
      for (String str : testSuit)
      {
         solutionWriter.write(str + "\n");
      }
      solutionWriter.close();
      updateStatus("Wrote solution template to " + 
            solutionFileObject.getAbsolutePath());
   }

   private LanguageInterface getLanguage(Language language, 
         ProblemComponentModel component)
   {
      switch(language.getName())
      {
         case "C++"    : return new CPP    (language, component);
         case "Java"   : return new JAVA   (language, component);
         case "C#"     : return new CSharp (language, component);
         case "VB"     : return new VB     (language, component);
         case "Python" : return new Python (language, component);
         default       : return null;
      }
   }

   public String getSource() throws Exception
   {
      BufferedReader solutionReader = 
            new BufferedReader(new FileReader(solutionFileObject));
      String readLine = "", solution = "";
      boolean insideCutSection = false;
      while ((readLine = solutionReader.readLine()) != null)
      {
         if (readLine.contains("//BEGINCUT") || readLine.contains("#BEGINCUT")
               || readLine.contains("'BEGINCUT"))
         {
            insideCutSection = true;
         }
         if (insideCutSection == false)
         {
            solution += readLine + "\n";
         }
         if (readLine.contains("//ENDCUT") || readLine.contains("#ENDCUT")
               || readLine.contains("'ENDCUT"))
         {
            insideCutSection = false;
         }
      }
      solutionReader.close();
      return solution;
   }

   public void setSource(String source){}
}
