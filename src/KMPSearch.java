
public class KMPSearch
{
  public static int[] computeFailureTable(String pattern)
  {
    int m = pattern.length();
    int[] failureTable = new int[m+1];
    failureTable[1] = 0;

    int k = 0;
    for(int i = 2; i <= m; i++)
    {
      while(k > 0 && pattern.charAt(k+1) != pattern.charAt(i))
      {
        k = failureTable[k];
      }
      if(pattern.charAt(k+1) == pattern.charAt(i))
      {
        k = k+1;
      }
      failureTable[i] = k;
    }
    return failureTable;
  }

  public static void main(String[] args)
  {
    String p = "abcdabd";
    int[] failureTable = computeFailureTable(p);

    for(int i=0; i<failureTable.length; i++)
    {
      System.out.print(failureTable[i]);
      if(!(i == failureTable.length-1))
      {
        System.out.print(", ");
      }
    }
    System.out.println();
  }
}
