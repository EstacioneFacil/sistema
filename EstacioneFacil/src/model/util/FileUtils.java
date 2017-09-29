package model.util;

import java.io.File;

/**
 *
 * @author Douglas
 */
public class FileUtils {
     
    public static void criarPastas(String path) throws Exception {
        String separator = File.separator.equals("\\") ? "\\\\" : "/";
        String teste[] = path.split(separator);
        String auxPath = teste[0] + separator;

        for (int i = 1; i < teste.length; i++) {
            auxPath += separator + teste[i];

            File fileOut = new File(auxPath);
            fileOut.setReadable(true, false);
            fileOut.setExecutable(true, false);
            fileOut.setWritable(true, false);
            if (!fileOut.isDirectory())
                    fileOut.mkdir();

            fileOut = new File(auxPath);
            if (!fileOut.isDirectory()) {
                fileOut.mkdir();
            }
        }
    }
    	
    public static void apagarArquivo(String caminho) {
        if (caminho != null && !caminho.equals("")) {
            File fileOut = new File(caminho);
            if (fileOut.isFile()) {
                fileOut.delete();
            }
	}
    }   
}
