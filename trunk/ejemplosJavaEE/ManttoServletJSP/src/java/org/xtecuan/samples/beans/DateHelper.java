/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.samples.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public class DateHelper {

    private static Logger logger = Logger.getLogger(DateHelper.class);
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    private final SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
    private final SimpleDateFormat sdfMonthSpanish = new SimpleDateFormat("MMMMM", new Locale("es", "SV"));
    private static SimpleDateFormat sdfDate = new SimpleDateFormat("dd_MM_yyyy");
    private static final int DEC = 12;
    private static List<String> months;
    private String dateStr;
    

    public String getCurrentYear() {

        return sdf.format(new Date());

    }

    public List<String> getMonths() {

        if (months == null) {

            months = new ArrayList<String>(0);


            for (int i = 1; i <= DEC; i++) {
                try {
                    Date d = sdfMonth.parse(String.valueOf(i));

                    months.add(sdfMonthSpanish.format(d).toUpperCase());
                } catch (Exception ex) {
                    logger.error("Error al convertir entero a Mes");
                }
            }
        }

        return months;

    }

    
    public Date getDateFromStr(){
        
        Date date = null;
        
        try {
            
            date = sdfDate.parse(dateStr);
            
        } catch (Exception e) {
            logger.error("Error al parsear fecha desde JSP", e);
        }
        
        return date;
        
    }
    
    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
    
    
}
