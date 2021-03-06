import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class KMPSearch
{
  
  private static boolean DEBUG = false;
  
  //args[0] = text file
  //args[1] = pattern
  public static void main(String[] args)
  {
    try
    {
      String pattern = args[1];
      BufferedReader txtFile = new BufferedReader(new FileReader(args[0]));
      int textLength = lengthOfText(args[0]);
      char[] t = new char[textLength];
      txtFile.read(t, 0, textLength);
      String text = new String(t);
      stringSearchKMP(text, pattern);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static int[] computeFailureTable(String pattern)
  {
    int m = pattern.length();
    int[] failureTable = new int[m];
    
    failureTable[0] = 0;
    
    //all possible substrings
    for (int i = 1; i < m; i++)
    {
      String currentScanned = pattern.substring(0, i + 1);
      if (DEBUG)
      {
        System.out.println("currentScanned = " + currentScanned);
      }
      failureTable[i] = longestOverlap(currentScanned, pattern);
    }
    return failureTable;
  }
  
  private static int longestOverlap(String currentScanned, String pattern)
  {
    int l = currentScanned.length();
    int currentLongest = 0;
    //longest prefix must not be equal to the current scanned, hence l-1
    for (int i = 1; i < l; i++)
    {
      String suffix = currentScanned.substring(l - i, l);
      String prefix = pattern.substring(0, i);
      if (prefix.equals(suffix))
      {
        currentLongest = prefix.length();
      }
    }
    return currentLongest;
  }
  
  public static String stringSearchKMP(String text, String pattern)
  {
    int occurences = 0;
    int[] failureTable = computeFailureTable(pattern);
    if (DEBUG)
    {
      printFailureTable(failureTable);
    }
    int n = text.length();
    int m = pattern.length();
    
    int matched = 0;
    int k = 0;
    
    //do while ensures that early exit happens
    do
    {
      int j = 0;
      while (j < m)
      {
        char pChar = pattern.charAt(j);
        char tChar = text.charAt(k);
        if (DEBUG)
        {
          System.out.print("pattern[" + j + "]=" + pChar + " , text[" + k + "]=" + tChar);
        }
        if (pChar == tChar)
        {
          matched++;
          j++;
          k++;
        }
        else if (pChar != tChar) //mismatched
        {
          if (matched > 0)
          {
            j = failureTable[matched - 1];
            matched = failureTable[matched - 1];
          }
          else if (matched == 0)
          {
            j = 0;
            k++;
            if (k >= n - m)
            {
              break;
            }
          }
        }
        if (DEBUG)
        {
          System.out.println(", matched=" + matched);
        }
        if (matched == m)
        {
          if (DEBUG)
          {
            System.out.println("found pattern on index " + (k - m + 1) + " of the text.");
          }
          occurences++;
          matched = 0;
        }
        if (DEBUG)
        {
          //for testing:
          try
          {
            Thread.sleep(250);
          }
          catch(Exception e){}
        }
      }
    }
    while (k < n);
    
    System.out.println("Found " + occurences + " occurence(s) of " + pattern + " in the text file.");
    return "Found " + occurences + " occurence(s) of \"" + pattern + "\"" + " in the text file";
  }
  
//  public static String stringSearchKMP(BufferedReader text,
//                                       String pattern,
//                                       int textLength)
//  {
//    int occurences = 0;
//    int[] failureTable = computeFailureTable(pattern);
//    if
////    printFailureTable(failureTable);
//    int n = textLength;
//    int m = pattern.length();
//
//    int matched = 0;
//    int k = 0;
//
//    try
//    {
//      //do while ensures that early exit happens
//      int tChar = text.read();
//      do
//      {
//        int j = 0;
//        while (j < m)
//        {
//          char pChar = pattern.charAt(j);
////          System.out.print("pattern[" + j + "]=" + pChar + " , text[" + k + "]=" + tChar);
//          if (pChar == tChar)
//          {
//            matched++;
//            j++;
//            k++;
//            tChar = text.read(); //get a new character
//          }
//          else if (pChar != tChar) //mismatched
//          {
//            if (matched > 0)
//            {
//              j = failureTable[matched - 1];
//              matched = failureTable[matched - 1];
//            }
//            else if (matched == 0)
//            {
//              j = 0;
//              k++;
//              tChar = text.read();
//              if (k >= n - m)
//              {
//                break;
//              }
//            }
//          }
////          System.out.println(", matched=" + matched);
//          if (matched == m)
//          {
////            System.out.println("found pattern on index " + (k - m + 1) + " of the text.");
//            occurences++;
//            matched = 0;
//          }
////        //for testing:
////        try {
////          Thread.sleep(250);
////        } catch(Exception e){}
//        }
//      }
//      while (k < n);
//    } catch (IOException e)
//    {
//      e.printStackTrace();
//    }
//
//    System.out.println("Found " + occurences + " occurence(s) of " + pattern + " in the text file");
//    return "Found " + occurences + " occurence(s) of \"" + pattern + "\"" + " in " + "\"the file read.\"";
//  }
  
  private static void printFailureTable(int[] failureTable)
  {
    for (int i = 0; i < failureTable.length; i++)
    {
      System.out.println(failureTable[i]);
    }
  }
  
  public static int lengthOfText(String fileName) throws IOException
  {
    BufferedReader bR = new BufferedReader(new FileReader(fileName));
    int numChars = 0;
    while (bR.read() > -1)
    {
      numChars++;
    }
    bR.close();
    return numChars;
  }
}
