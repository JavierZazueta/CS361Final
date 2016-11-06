
public class KMPSearch
{
  public static int[] computeFailureTable(String pattern)
  {
    int m = pattern.length();
    int[] failureTable = new int[m];

    failureTable[0] = 0;

    //all possible substrings
    for(int i=1; i < m; i++)
    {
      String currentScanned = pattern.substring(0, i+1);
      System.out.println("currentScanned = " + currentScanned);
      failureTable[i] = longestOverlap(currentScanned, pattern);
    }
    return failureTable;
  }

  private static int longestOverlap(String currentScanned, String pattern)
  {
    int l = currentScanned.length();
    int currentLongest = 0;
    //longest prefix must not be equal to the current scanned, hence l-1
    for(int i=1; i < l; i++)
    {
      String suffix = currentScanned.substring(l-i,l);
      String prefix = pattern.substring(0, i);
      if(prefix.equals(suffix))
      {
        currentLongest = prefix.length();
      }
    }
    return currentLongest;
  }

  public static void stringSearchKMP(String text, String pattern)
  {
    int[] failureTable = computeFailureTable(pattern);
//    printFailureTable(failureTable);
    int s = text.length();
    int m = pattern.length();
    //IMPLEMENT A FOR LOOP FOR KMP
  }

  private static void printFailureTable(int[] failureTable)
  {
    for(int i=0 ; i<failureTable.length; i++)
    {
      System.out.println(failureTable[i]);
    }
  }

  public static void main(String[] args)
  {
//    int[] fTable = computeFailureTable("aabaababd");
    stringSearchKMP("abcdab abcdabcdabde", "abcdabd");
  }

}
