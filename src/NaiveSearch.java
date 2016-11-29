import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NaiveSearch
{
  static int instances = 0;
  static ArrayList<Integer> locations = new ArrayList<>();

  static void countInstances(String pattern, String file)
  {
    int m = pattern.length();
    for (int i=0; i<file.length()-m; i++)
    {
      for (int j=0; j<m; j++)
      {
        if (pattern.charAt(j) != file.charAt(i+j))
        {
          locations.add(i-m+1);
          break;
        }
        if (j==m-1 && (pattern.charAt(j) == file.charAt(i+j)))
        {
          i+=m-1;
          instances++;
        }
      }
    }
    System.out.println("Found " + instances +" of the pattern " + pattern);
    for(int i : locations)
    {
      System.out.println("Found at " + i);
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
  
  public static void main(String[] args) throws IOException
  {
    try
    {
      String pattern = args[1];
      BufferedReader txtFile = new BufferedReader(new FileReader(args[0]));
      int textLength = lengthOfText(args[0]);
      char[] t = new char[textLength];
      txtFile.read(t, 0, textLength);
      String text = new String(t);
      countInstances(pattern, text);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
  }
}
