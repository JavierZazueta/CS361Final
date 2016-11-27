import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Arthur on 11/26/2016.
 */
public class TestFileBuilder
{
  //command line args:
  //[0] = total number of a's
  //[1] = total number of b's
  //[2] = total number of c's
  //[3] = total number of d's
  //[4] = total number of e's
  //[5] = word you want in there (from a to e only)
  //[6] = occurrences you want for that word (from a to e only)
  public static void main(String[] args)
  {
    Random rand = new Random((int)Math.random()*100);
    try
    {
      //creates the file name for you if you do not have it yet.
      BufferedWriter bW = new BufferedWriter(new FileWriter("src/bigTestFile.txt"));
      int aCount = Integer.parseInt(args[0]);
      int bCount = Integer.parseInt(args[1]);
      int cCount = Integer.parseInt(args[2]);
      int dCount = Integer.parseInt(args[3]);
      int eCount = Integer.parseInt(args[4]);
      String pattern = args[5];
      int patternCount = Integer.parseInt(args[6]);
      int patternWritten = 0;
      int aWritten = 0;
      int bWritten = 0;
      int cWritten = 0;
      int dWritten = 0;
      int eWritten = 0;
      int occurenceNeeded = aCount + bCount + cCount + dCount + eCount + patternCount;
      int occurenceWritten = 0;
      while (occurenceWritten < occurenceNeeded)
      {
        int charType = rand.nextInt(10)+1; //1 to 10 only, 1 to 5 for fillers, 6 to 10 for words
        if (charType == 1 && aWritten < aCount)
        {
          bW.write("a");
          aWritten++;
          occurenceWritten++;
        }
        else if (charType == 2 && bWritten < bCount)
        {
          bW.write("b");
          bWritten++;
          occurenceWritten++;
        }
        else if (charType == 3 && cWritten < cCount)
        {
          bW.write("c");
          cWritten++;
          occurenceWritten++;
        }
        else if (charType == 4 && dWritten < dCount)
        {
          bW.write("d");
          dWritten++;
          occurenceWritten++;
        }
        else if (charType == 5 && eWritten < eCount)
        {
          bW.write("e");
          eWritten++;
          occurenceWritten++;
        }
        else if (charType > 6 && patternWritten < patternCount)
        {
          bW.write(pattern);
          patternWritten++;
          occurenceWritten++;
        }
        
      }
      bW.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
