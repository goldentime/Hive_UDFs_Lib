package UDF;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

/*
 * 计算所给日期的自然周的最后一天
 * 
*/
public class LastDayOfWeek extends UDF {
	
	public String evaluate(String dateStr)
	  {
	    String lastDay = "-1";
	    try {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      Date date = sdf.parse(dateStr.trim());
	      Calendar c = Calendar.getInstance();
	      c.setTime(date);
	      c.setFirstDayOfWeek(2);
	      c.set(7, 8);
	      Date lastDate = c.getTime();
	      lastDay = sdf.format(lastDate);
	    } catch (ParseException e) {
	      return "-1";
	    }
	    return lastDay;
	    
	  }
	/*
	public static void main (String args[]) {
		LastDayOfWeek ldw = new LastDayOfWeek();
		System.out.println(ldw.evaluate("2013-10-10"));
		
	}
	*/
	
}
