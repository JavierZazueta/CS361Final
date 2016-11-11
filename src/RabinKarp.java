import java.util.Random;

public class RabinKarp
{

  private long[] q = {1234567};
  private Random random = new Random();
  private int rand = random.nextInt(q.length);

  private long hashChar(char x, int lenght)
  {
    long h = (int)x * q[rand-1];
    return h;
  }

  private long hashString(String p)
  {
    long h = 0;
    for(int i = 0; i < p.length(); i++)
    {
      h += (int)p.charAt(i) * Math.pow(256, p.length()-(i+1));
    }
    return h;
  }
}
