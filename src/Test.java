import java.io.BufferedReader;
import java.io.FileReader;

//For spikes and stuff
public class Test
{
  public static void main(String[] args)
  {
//    int length = KMPSearch.lengthOfText(args[0]);
//    BufferedReader bR = new BufferedReader(new FileReader(args[0]));
//    System.out.println(KMPSearch.stringSearchKMP(bR, "Through and through", length));
    try
    {
      BufferedReader bR = new BufferedReader(new FileReader("src/MobyDick.txt"));
      int s = RabinKarpSearch.lengthOfText("src/MobyDick.txt");
      int j = 0;
      int m = 5; //pattern length of 5
      char[] text = new char[s];
      char[] chunk = new char[m];
      bR.read(text, 0, s);
      System.out.println(new String(text));
//      System.out.println(chunk.length);
//      bR.read(chunk, j, m);
//      System.out.println(chunk);
//      while (j < s-m+1)
//      {
//        bR.read(chunk, 0, m);
//        System.out.println(chunk);
//        j++;
//      }
      bR.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
//    int c = 97;
//    System.out.println((char)c);
}