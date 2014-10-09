package UDF;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.hadoop.hive.ql.exec.UDF;

public class MD5Generator extends UDF
{
  public String evaluate(String str1)
  {
    return getMD5(str1);
  }

  public String evaluate(String str1, String str2, String str3) {
    return getMD5(str1 + str2 + str3);
  }

  public static String getMD5(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");

      byte[] messageDigest = md.digest(input.getBytes());
      BigInteger number = new BigInteger(1, messageDigest);
      String hashtext = number.toString(16);
      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }
      return hashtext; 
      
      } catch (NoSuchAlgorithmException e) {
    }
    throw new RuntimeException();
  }

  public static void main(String[] args)
  {
	  //test
    System.out.println(getMD5("pctnetpctnetpctnet"));
  }
}