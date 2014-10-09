package UDF;

import org.apache.hadoop.hive.ql.exec.UDF;

public class IP2Long extends UDF
{
  public static String evaluate(String longip)
  {
    StringBuffer sb = new StringBuffer("");
    if ((longip != null) && (!longip.equals(""))) {
      long longIp = Long.valueOf(longip).longValue();

      sb.append(String.valueOf(longIp >>> 24));
      sb.append(".");

      sb.append(String.valueOf((longIp & 0xFFFFFF) >>> 16));
      sb.append(".");

      sb.append(String.valueOf((longIp & 0xFFFF) >>> 8));
      sb.append(".");

      sb.append(String.valueOf(longIp & 0xFF));
    }
    return sb.toString();
  }
  
  /*
  public static void main(String[] args)
  {
	  //test
    System.out.println(evaluate("1234567890"));
  }
  */
  
}