import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NaiveSearch
{
  static int instances = 0;

  static void countInstances(String pattern, String file)
  {
    int j = 0;
    for(int i = 0; i < file.length(); i++)
    {
      if (file.charAt(i) == pattern.charAt(j)) j++;
      else if(j > 0)
      {
        i -= (j-1);
        j = 0;
      }
      if(j == pattern.length())
      {
        i -= (j-1);
        j = 0;
        instances++;
      }
    }
  }



  public static void main(String[] args) throws IOException
  {
    String pattern = "Javier";
    String file = "hi i am Javier, and you are?";

    countInstances(pattern, file);

    System.out.println("There are " + instances + " instances of " + pattern + " in the file");



    //    int testIndex = 0;//will be used to know how far we were able to match from test
//    int instances = 0;
//    //String test = args[1];//the string that we are looking for
//    String test = "abc";//the string that we are looking for
//    BufferedReader in;
//
//      in = new BufferedReader(new FileReader(args[0]));
//
//      int i = 0;
//      int c;
//      while ((c = in.read()) != -1)//while we have not read a null char
//      {
//        if(c == (int)test.charAt(i))
//        {
//          i++;
//          testIndex++;
//          if(i == test.length())
//          {
//            instances++;
//          }
//        }
//        //we cannot look past what we have read, because we could potentially miss the match
//        else if (testIndex > 0)
//        {
//          i = 0;
//          for(int j = 1; j < testIndex; j++)
//          {
//            if(test.charAt(i) == test.charAt(j))
//            {
//              i++;
//            }
//            else i = 0;
//          }
//        }
//        //if there is no match and we didn't find any matches previously
////        else
////        {
////          testIndex = 0;
////          i = 0;
////        }
//
//      }
  }
}
