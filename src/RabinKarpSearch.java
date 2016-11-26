/**
 * Created by Arthur on 11/19/2016.
 */

//TODO: WORKING!
public class RabinKarpSearch
{
  /* Alphabets:
   * 'a'=1, 'b'=2, 'c'=3, 'd'=4, 'e'=5
   */
//  public static int q = 227; //prime number
//  public static int q = 127;
  public static int q = 1069;
  public static int base = 5; //for implementation of a,b,c,d,e characters only
//  public static int base = 3; //base b
  public static boolean DEBUG = true;

  public static int patternHash(String s, int base)
  {
    int hashValue=0;
    int j=0;
    for(int i=s.length()-1; i>=0; i--)
    {
      if(!DEBUG)
      {
        System.out.println(s.charAt(j));
        System.out.println("s[j]%q=" + s.charAt(j) % q);
        System.out.println("b^i mod q=" + modExp(base, i, q));
        System.out.println("multiply them together=" + ((s.charAt(j) % q) * modExp(base, i, q)));
      }
      int alphabeVal = findAlphabetValue(s.charAt(j));
//      hashValue += ((s.charAt(j)%q) * modExp(base, i, q))%q; //from my findings.
      hashValue += ((alphabeVal % q) * modExp(base, i, q))%q;
//      hashValue += (s.charAt(j)) * modExp(base, i, q);
      if(!DEBUG)
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

    int e=1;
    int i=0;
    while(e <= exp)
    {
      valArray[i] = val;
//      System.out.println("i="+i+", e="+e+", val=" + val);
      val = (val*val) % modulus;
      i++;
      e = 2*e;
    }

    int valueToMod = 1;
    for(int j=0; j<binary.length(); j++)
    {
      if(binary.charAt(j) == '1')
      {
        valueToMod = valueToMod*valArray[binary.length()-1-j];
      }
    }
//    System.out.println("valueToMod="+valueToMod);
//    for(int v : valArray) System.out.println(v);
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
    System.out.println("patternHash="+patternHash+", c1="+c1+", c2="+c2);

    boolean firstTime = true;
    int chunkHash = 0;
    int j=0;
    while(j < s-m+1)
    {
      String chunk = text.substring(j, j+m); //current Chunk
      if (DEBUG)
      {
        System.out.println("text["+j+"]=" + text.charAt(j));
      }
      if (DEBUG)
      {
        System.out.println("chunk="+chunk);
      }
      if (firstTime)
      {
        chunkHash = patternHash(chunk, base);
        if (DEBUG)
        {
          System.out.println("chunkHash="+chunkHash);
        }

        if(chunkHash == patternHash)
        {
          matches++;
          j += m; //matched!
        }
        else if(chunkHash != patternHash)
        {
          j++;  //not matched
        }
        firstTime = false;
      }
      else if (!firstTime)
      {
        chunkHash = rollingHash(text.charAt(j+m-1), text.charAt(j-1), chunkHash, c1, c2);
        if (DEBUG)
        {
          System.out.println("chunkHash="+chunkHash);
        }
        if(chunkHash == patternHash)
        {
          matches++;
          j+=m;
        }
        else if(chunkHash != patternHash)
        {
          j++;
        }
      }
    }

//    for(int j=0; j < s-m+1; j++)
//    {
//      String chunk = text.substring(j, j+m); //current Chunk
//      if (DEBUG)
//      {
//        System.out.println("text["+j+"]=" + text.charAt(j));
//      }
//      if (DEBUG)
//      {
//        System.out.println("chunk="+chunk);
//      }
//      if (firstTime)
//      {
//        chunkHash = patternHash(chunk, base);
//        if (DEBUG)
//        {
//          System.out.println("chunkHash="+chunkHash);
//        }
//        if(chunkHash == patternHash)
//        {
//          matches++;
//        }
//        firstTime = false;
//      }
//      else if (!firstTime)
//      {
//        chunkHash = rollingHash(text.charAt(j+m-1), text.charAt(j-1), chunkHash, c1, c2);
//        if (DEBUG)
//        {
//          System.out.println("chunkHash="+chunkHash);
//        }
//        if(chunkHash == patternHash)
//        {
//          matches++;
//        }
//      }
//    }
    System.out.println("Matches Found: " + matches);
  }

  public static int rollingHash(char newLast, char oldFirst, int oldHash,
                                int c1, int c2)
  {
    //from class notes:
    int j = findAlphabetValue(oldFirst);
    int jPlusM = findAlphabetValue(newLast);
    System.out.println("in rollingHash():"+ " newLast="+jPlusM+", oldFirst="+j+
            ", oldHash="+oldHash+", negativeMod(j,q)="+negativeMod(-j,q));
    return ((oldHash * c1) + ((negativeMod(-j, q)) * c2) + (jPlusM % q) ) % q;
//    return ((oldHash * c1) - ((oldFirst % q) * c2) + (newLast % q) ) % q;
  }

  private static int negativeMod(int a, int q)
  {
    while(a < 0)
    {
      a += q;
    }
    return a;
  }

  public static void main(String[] args)
  {
//    int n = modExp(3, 200, 227);
//    System.out.println("modulus exponential="+n);
//    int b = 3;
//    int hash = patternHash("abbd", 3);
//    System.out.println(hash);
    rabinKarpSearch("abdce", "abcdeabbbdceeaaabbbbdeddededaababdceaaaaaebed");
//    System.out.println((-15) % 2);
//    int rem = negativeMod(-12500, 1069);
//    System.out.println("rem="+rem);
  }
}
