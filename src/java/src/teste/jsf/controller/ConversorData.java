/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.teste.jsf.controller;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author MEUS DOCUMENTOS
 */

@FacesConverter(value = "conversorData")
public class ConversorData implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        System.out.println("getAsObject");
        if(value == null || value.equals(""))
        return null;
            Calendar calendar=Calendar.getInstance();
            System.out.println("Data:"+value);
            SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
            try{
                Date data=sdf.parse(value);
                calendar.setTime(data);
            return calendar;    
            }catch(ParseException e){
                throw new RuntimeException(e);
            }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        System.out.println("getAsString");
        if(value ==  null)
        return null;
        
        Calendar calendar= (Calendar) value;
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String data=sdf.format(calendar.getTime());
        return data;
    }
    
}
