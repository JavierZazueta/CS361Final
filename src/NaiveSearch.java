import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NaiveSearch
{
  public static void main(String[] args)
  {
    int testIndex = 0;//will be used to know how far we were able to match from test
    int instances = 0;
    String test = args[1];//the string that we are looking for
    BufferedReader in;

    try
    {
      in = new BufferedReader(new FileReader(args[0]));

      int i = 0;
      int c;
      while ((c = in.read()) > -1)//while we have not read a null char
      {
        if(c == (int)test.charAt(i))
        {
          i++;
          testIndex++;
          if(i == test.length())
          {
            instances++;
          }
        }
        //we cannot look past what we have read, because we could potentially miss the match
        else if (testIndex != 0)
        {
          i = 0;
          for(int j = 0; j < testIndex; j++)
          {
            if(test.charAt(i) == test.charAt(j))
            {
              i++;
            }
            else i = 0;
          }
        }
        //if there is no match and we didn't find any matches previously
        else
        {
          testIndex = 0;
          i = 0;
        }
      }
      System.out.println("There are " + instances + " of " + test + " in the file");

    }
    catch (IOException e)
    {
      String error = "IOException: "+e.getMessage();
      JOptionPane.showMessageDialog(null, error, "Warning", JOptionPane.WARNING_MESSAGE);
    }

  }
}
