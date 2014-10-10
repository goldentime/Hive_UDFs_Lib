package UDF;

import org.apache.hadoop.hive.ql.exec.UDF;

public class IP2Long extends UDF
{
  public static long evaluate(String ip)
  {
    String[] ips = ip.split("[.]");     
    long num =  16777216L*Long.parseLong(ips[0]) + 65536L*Long.parseLong(ips[1]) + 256*Long.parseLong(ips[2]) + Long.parseLong(ips[3]);     
    return num;
  }
  
  /*
  public static void main(String[] args)
  {
	  //test
    System.out.println(evaluate("73.150.2.210"));
  }
  */
  
}