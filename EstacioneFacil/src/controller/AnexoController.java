package controller;

import config.ConfiguracaoSistema;
import dao.AnexoDao;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.UUID;
import javax.imageio.ImageIO;
import model.Anexo;
import model.util.FileUtils;

/**
 *
 * @author Douglas
 */
public class AnexoController {
    
    private AnexoDao anexoDao;
    
    public AnexoController() {
        anexoDao = new AnexoDao();
    }
        
    public Anexo salvarAnexo(BufferedImage arquivo, String observacao) throws Exception {
        Anexo anexo = montarAnexo(arquivo, observacao);
        anexoDao.gravar(anexo);
        return anexo;
    }
    
    public void excluirAnexo(Anexo anexo) throws Exception {
//        FileUtils.apagarArquivo(anexo.getCaminhoCompleto());
//        anexoDao.excluir(anexo);
    }
    
    private Anexo montarAnexo(BufferedImage arquivo, String observacao) throws Exception {
        UUID uuid = UUID.randomUUID();  
        Anexo anexo = new Anexo();

        anexo.setData(new Date());
        if (observacao != null && !observacao.isEmpty()) {
            anexo.setObservacao(observacao);
        } else {
            anexo.setObservacao(null);
        }
        anexo.setNome(uuid.toString());
        gravarAnexo(arquivo, anexo);
        
        System.out.println("Anexo: "+anexo.toString());
        return anexo;
    }

    private void gravarAnexo(BufferedImage arquivo, Anexo anexo) throws Exception {  
        String pathCompleto = ConfiguracaoSistema.CAMINHO_ANEXOS;

        // Cria pastas automaticamente
        FileUtils.criarPastas(pathCompleto);
        String caminho = pathCompleto + File.separator + anexo.getNome() + ".jpg";

        File outputfile = new File(caminho);
        ImageIO.write(arquivo, "jpg", outputfile);       
    }
}
