import java.util.Random;

public class RabinKarp
{

  private long[] q = {1234567};//array of prime numbers
  private Random random = new Random();
  private final int rand = random.nextInt(q.length);//randomly picked prime number
  private final int E = 256;//size of alphabet(ascii characters)
  private static int pLength;//length of pattern, needs to be initialized when the patter is given

  public RabinKarp()
  {

  }

  //hashes a string
  private double hashString(String p)
  {
    double h = 0;
    for(int i = 0; i < pLength; i++)
    {
      h += (int)p.charAt(i) * Math.pow(E, pLength-(i+1));
    }
    return h%q[rand-1];
  }

  //gets the next hash. subtracts the first value then adds the last value
  private double nextHash(double old, char first, char last)
  {
    double newHash = (old-first*Math.pow(E, pLength-1))*E + last;
    return newHash % q[rand];
  }

  private boolean findInstance(String pattern, String file)
  {
    String current = file.substring(0,pLength);
    double pHashVal = hashString(pattern);
    double currentHashVal = hashString(current);
    if(pHashVal == currentHashVal)
    {
      return true;
    }
    else
    {
      for (int i = 0; i < file.length()-pLength-1; i++)
      {
        if (currentHashVal == nextHash(currentHashVal, current.charAt(0), current.charAt(pLength-1))) return true;
        current = file.substring(i, i+pLength);
      }
    }
    return false;
  }

  public static void main(String[] args)
  {
    pLength = args[1].length();

    if(findInstance(args[1], args[0]))
    {
      System.out.println("The pattern " + args[1] + ", was found.");
    }
    else
    {
      System.out.println("The pattern " + args[1] + ", does not exist in the file.");
    }
  }
}
