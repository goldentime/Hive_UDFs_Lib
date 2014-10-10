package UDF;

import org.apache.hadoop.hive.ql.exec.UDF;

public class Long2IP extends UDF {
	
	public static String evaluate(String longip){
		StringBuffer ipInfo = new StringBuffer();
		if ((longip != null) && (!longip.equals(""))){
			long longIp = Long.parseLong(longip);
			long mask[] = {0x000000FF,0x0000FF00,0x00FF0000,0xFF000000};     
	        long num = 0;    
	        for(int i=0;i<4;i++){     
	            num = (longIp & mask[i])>>(i*8);     
	            if(i>0) ipInfo.insert(0,".");     
	            ipInfo.insert(0,Long.toString(num,10));     
	        }
		}
        return ipInfo.toString();     
		
	}
	
	/*
	 * 
	 * public static String evaluate(String longip){
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
	 * 
	 */
	
	/*
	public static void main(String[] args)
	{
		//test
	   System.out.println(evaluate("1234567890"));
	}
	*/

}
