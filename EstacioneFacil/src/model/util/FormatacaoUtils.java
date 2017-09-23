package model.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Douglas
 */
public class FormatacaoUtils {
    
    static DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
    
    public static String getDataHoraString(Date data) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(data);
    }
    
    public static String getDataString(Date data) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(data);
    }
    
    public static String getHoraString(Date data) {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(data);
    }
    
    public static String getDataSQL(Date data) throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(data);
    }
    
    public static Date getData(String data) throws Exception {
        return getData(data, "dd/MM/yyyy");
    }
        
    public static Date getData(String data, String formato) throws Exception {
        DateFormat formatter = new SimpleDateFormat(formato);
        return (data == null) ? null : formatter.parse(data);
    }
    
    public static void reformatarData(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##/##/####");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void reformatarHora(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##:##");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void reformatarPlaca(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("UUU-####");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static String removerFormatacao(String dado) {
        String retorno = "";
        for (int i = 0; i < dado.length(); i++) {
            if (dado.charAt(i) != '.' && dado.charAt(i) != '/' && dado.charAt(i) != '-' && dado.charAt(i) != '(' && dado.charAt(i) != ')') {
                retorno = retorno + dado.charAt(i);
            }
        }
        return (retorno);
    }
    
    public static void formatarCampoValor(JFormattedTextField campo) {
        try {
            DecimalFormat dFormat = new DecimalFormat("#,###,###.00") ;
            NumberFormatter formatter = new NumberFormatter(dFormat) ;
            formatter.setFormat(dFormat) ;
            formatter.setAllowsInvalid(false) ; 
            campo.setFormatterFactory(new DefaultFormatterFactory (formatter)) ;
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static String removerFormatacaoValor(String dado) {
        return dado.replace(",", ".");
    }
    
    public static String formatarStringValor(Double dado) {
        return df.format(dado);
    }
}
