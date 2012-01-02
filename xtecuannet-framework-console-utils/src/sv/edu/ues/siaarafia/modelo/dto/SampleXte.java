/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.siaarafia.modelo.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xtecuan
 */
public class SampleXte {
    public static void main(String[] args) {
        
        List<NotasExcelDto> dtos = new ArrayList<NotasExcelDto>(0);
        
        dtos.add(new NotasExcelDto());
        
        System.out.println(dtos.get(0).getClass());
        
        
    }
}
