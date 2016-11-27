import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RabinKarpSearch
{
  /* Alphabets:
   * 'a'=1, 'b'=2, 'c'=3, 'd'=4, 'e'=5
   */
  public static int q = 1069;
  public static int base = 5; //for implementation of a,b,c,d,e characters only
  public static boolean DEBUG = false;
  
  public static int patternHash(String s, int base)
  {
    int hashValue = 0;
    int j = 0;
    for (int i = s.length() - 1; i >= 0; i--)
    {
      if (DEBUG)
      {
        System.out.println(s.charAt(j));
        System.out.println("s[j]%q=" + s.charAt(j) % q);
        System.out.println("b^i mod q=" + modExp(base, i, q));
        System.out.println("multiply them together=" + ((s.charAt(j) % q) * modExp(base, i, q)));
      }
      int alphabeVal = findAlphabetValue(s.charAt(j));
      hashValue += ((alphabeVal % q) * modExp(base, i, q)) % q;
      if (DEBUG)
      {
        System.out.println("hashValue = " + hashValue);
      }
      j++;
    }
    hashValue = hashValue % q;
    return hashValue;
  }
  
  private static int findAlphabetValue(char c)
  {
    if (c == 'a') return 1;
    if (c == 'b') return 2;
    if (c == 'c') return 3;
    if (c == 'd') return 4;
    if (c == 'e') return 5;
    return -99999; //should only have a,b,c,d, or e
  }
  
  //gives value of (base^exp)mod(modulus)
  public static int modExp(int base, int exp, int modulus)
  {
    String binary = Integer.toBinaryString(exp);
    int[] valArray = new int[binary.length()];
    int val = base % modulus;
    
    int e = 1;
    int i = 0;
    while (e <= exp)
    {
      valArray[i] = val;
      val = (val * val) % modulus;
      i++;
      e = 2 * e;
    }
    
    int valueToMod = 1;
    for (int j = 0; j < binary.length(); j++)
    {
      if (binary.charAt(j) == '1')
      {
        valueToMod = valueToMod * valArray[binary.length() - 1 - j];
      }
    }
    return valueToMod % modulus;
  }
  
  public static void rabinKarpSearch(String pattern, String text)
  {
    int matches = 0;
    int s = text.length();
    int m = pattern.length();
    int c1 = base % q;
    int c2 = modExp(base, m, q);
    int patternHash = patternHash(pattern, base);
    if (DEBUG)
    {
      System.out.println("patternHash=" + patternHash + ", c1=" + c1 + ", c2=" + c2);
    }
    boolean firstTime = true;
    int noCountTurns = 0;
    int chunkHash = 0;
    int j = 0;
    while (j < s - m + 1)
    {
      if (DEBUG)
      {
        System.out.println("noCountTurns=" + noCountTurns);
      }
      String chunk = text.substring(j, j + m); //current Chunk
      if (DEBUG)
      {
        System.out.println("text[" + j + "]=" + text.charAt(j));
      }
      if (DEBUG)
      {
        System.out.println("chunk=" + chunk);
      }
      if (firstTime)
      {
        chunkHash = patternHash(chunk, base);
        if (DEBUG)
        {
          System.out.println("chunkHash=" + chunkHash);
        }
        
        if (chunkHash == patternHash && noCountTurns == 0)
        {
          matches++;
          noCountTurns = m; //so no words inside words will be counted
        }
        j++;
        firstTime = false;
      }
      else if (!firstTime)
      {
        chunkHash = rollingHash(text.charAt(j + m - 1), text.charAt(j - 1), chunkHash, c1, c2);
        if (DEBUG)
        {
          System.out.println("chunkHash=" + chunkHash);
        }
        if (chunkHash == patternHash && noCountTurns == 0)
        {
          matches++;
          noCountTurns = m;
        }
        j++;
      }
      if (noCountTurns > 0)
      {
        noCountTurns--;
      }
    }
    System.out.println("Matches Found: " + matches);
  }
  
  public static int rollingHash(char newLast, char oldFirst, int oldHash,
                                int c1, int c2)
  {
    //from class notes:
    int j = findAlphabetValue(oldFirst);
    int jPlusM = findAlphabetValue(newLast);
    if (DEBUG)
    {
      System.out.println("in rollingHash():" + " newLast=" + jPlusM + ", oldFirst=" + j +
              ", oldHash=" + oldHash + ", negativeMod(j,q)=" + negativeMod(-j, q));
    }
    return ((oldHash * c1) + ((negativeMod(-j, q)) * c2) + (jPlusM % q)) % q;
  }
  
  private static int negativeMod(int a, int q)
  {
    while (a < 0)
    {
      a += q;
    }
    return a;
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
  
  //args[0] = file to read, args[1] = pattern to count the occurrences of
  //text file must be inside the src folder with the java source code.
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
      rabinKarpSearch(pattern, text);
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
}
