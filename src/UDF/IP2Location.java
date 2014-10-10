package UDF;

import java.io.File;
import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.UDF;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import com.maxmind.geoip.timeZone;

import java.util.regex.*;

public class IP2Location  extends UDF {
    private static LookupService cl = null;
    private static String ipPattern = "\\d+\\.\\d+\\.\\d+\\.\\d+";
    private static String ipNumPattern = "\\d+";
    
    static LookupService getLS(String dbfile) throws IOException{
        /*
        String sep = System.getProperty("file.separator");
        String dir = "/home/landen/UntarFile/GeoIP";

        String dbfile = dir + sep + "GeoLiteCity.dat";
        String dbfile = "GeoLiteCity.dat";
    	*/
    	
        if(new File(dbfile).exists())
        {
            if(cl == null)
            {
                cl = new LookupService(dbfile,LookupService.GEOIP_MEMORY_CACHE);
            }    
        }
        
        return cl;

    }
    
    
    /**
     * @param str like "114.43.181.143"
     * */
    
    public String evaluate(String str,String ipDBInfo) {
        try
        {
            Location l1 = null;
            Matcher mIP = Pattern.compile(ipPattern).matcher(str);
            Matcher mIPNum = Pattern.compile(ipNumPattern).matcher(str);
            if(mIP.matches())
                l1 = getLS(ipDBInfo).getLocation(str);
            else if(mIPNum.matches())
                l1 = getLS(ipDBInfo).getLocation(Long.parseLong(str));    
            
            /*System.out.println("countryCode: " + l1.countryCode +
                    "\n countryName: " + l1.countryName +
                    "\n region: " + l1.region +
                    "\n regionName: " + regionName.regionNameByCode(l1.countryCode, l1.region) +
                    "\n city: " + l1.city +
                    "\n latitude: " + l1.latitude +
                    "\n longitude: " + l1.longitude +
                    "\n timezone: " + timeZone.timeZoneByCountryAndRegion(l1.countryCode, l1.region));*/
            
            return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",l1.countryCode,l1.countryName,l1.region,regionName.regionNameByCode(l1.countryCode, l1.region),l1.city,l1.latitude,l1.longitude,timeZone.timeZoneByCountryAndRegion(l1.countryCode, l1.region));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            if(cl != null)
                cl.close();
            return null;
        }
    }
    
    public static void main(String[] args)
    {
        String dbfile = "G:\\GeoLiteCity.dat\\GeoLiteCity.dat";
        IP2Location ipTocc = new IP2Location();
        String ipAdress = "124.16.31.151"; // "221.12.10.219";
        
        System.out.println(ipTocc.evaluate(ipAdress,dbfile));
    }

}
