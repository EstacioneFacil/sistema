package model.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    
    public static Date getData(Date data) throws Exception {
        return getData(getDataString(data), "dd/MM/yyyy");
    }
    
    public static Date getDataHora(String data) throws Exception {
        return getData(data, "dd/MM/yyyy HH:mm:ss");
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
            m.setMask(getMaskPlaca());
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static String formatarPlaca(Object value) {
        MaskFormatter m;
        try {
            m = new MaskFormatter(getMaskPlaca());
            m.setValueContainsLiteralCharacters(false); 
            return m.valueToString(value);
        } catch (Exception e) {
            System.err.println(e);
        }
        return value.toString();
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
    
    public static String getMaskPlaca() {
        return "UUU-####";
    }
    
    public static Integer getCalculaTempoHoras(Date dataInicial, Date dataFinal) {
        Integer horas = 0;

        Integer dias = diferencaDias(dataFinal, dataInicial);
        if (dias != null && dias != 0) {
                horas = dias * 24;
        }
        int hours = diferencaHoras(dataInicial, dataFinal);
        horas = horas + hours;
        return horas;
    }
    
    @SuppressWarnings("deprecation")
    public static int diferencaHoras(Date horaMenor, Date horaMaior) {
        int nroHoras = 0;
        if (horaMenor.getHours() < horaMaior.getHours()) {
                nroHoras = (horaMaior.getHours() - horaMenor.getHours());
        } else if (horaMaior.getHours() < horaMenor.getHours()) {
                nroHoras = (horaMenor.getHours() - horaMaior.getHours());
        }
        return nroHoras;
    }

    public static int diferencaMinutos(Date maior, Date menor) {
        try {
            menor = getDataHora(getDataString(menor)+" "+getHoraString(menor)+":00");
            maior = getDataHora(getDataString(maior)+" "+getHoraString(maior)+":00");
            Calendar calMenor = Calendar.getInstance();
            calMenor.setTime(menor);
            Calendar calMaior = Calendar.getInstance();
            calMaior.setTime(maior);
            long diferenca = calMaior.getTimeInMillis()-calMenor.getTimeInMillis();
            return (int)diferenca/(60*1000);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return 0;
    }

    public static int diferencaSegundos(Date maior, Date menor) {
        Calendar calMenor = Calendar.getInstance();
        calMenor.setTime(menor);
        Calendar calMaior = Calendar.getInstance();
        calMaior.setTime(maior);
        long diferenca = calMaior.getTimeInMillis()-calMenor.getTimeInMillis();
        return (int)diferenca/(1000);
    }

    public static int diferencaDias(Date maior, Date menor) {
        if (maior == null || menor == null) {
                return 0;
        }
        Date maiorCorrigida = null;
        Date menorCorrigida = null;
        try {
                maiorCorrigida = getData(maior);
                menorCorrigida = getData(menor);
        } catch (Exception e) {
                maiorCorrigida = maior;
                menorCorrigida = menor;
                e.printStackTrace();
        }
        Calendar calMenor = Calendar.getInstance();
        calMenor.setTime(menorCorrigida);
        Calendar calMaior = Calendar.getInstance();
        calMaior.setTime(maiorCorrigida);
        long ini = calMenor.getTimeInMillis();
        long fim = calMaior.getTimeInMillis();
        long nroHoras = (fim - ini) / 1000 / 3600;
        int nroDias = (int) nroHoras / 24;
        return nroDias;
    }
        
    public static String getValorFormatado(Double preco) {
        if (preco != null) {
            DecimalFormat twoPlaces = new DecimalFormat("0.00");
            String valor = twoPlaces.format(preco);
            return valor;
        } 
        return "";
    }
}
