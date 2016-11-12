import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//For spikes and stuff
public class Test
{
  public static void main(String[] args) throws IOException
  {
    int length = KMPSearch.lengthOfText(args[0]);
    BufferedReader bR = new BufferedReader(new FileReader(args[0]));
    System.out.println(KMPSearch.stringSearchKMP(bR, "Through and through", length));
  }
}
