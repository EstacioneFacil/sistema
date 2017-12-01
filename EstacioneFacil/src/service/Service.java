package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import model.Movimentacao;
import model.vo.InfoVeiculoVO;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class Service {
    
    private final Logger logger = Logger.getLogger(getClass().getName());
    
    public InfoVeiculoVO buscarInfoVeiculoWS(String placa) {
        InfoVeiculoVO info = new InfoVeiculoVO();
        try {
            RestTemplate rt = new RestTemplate();
            placa = placa.replace("-", "");
            String uri = "https://www.checkmeucarro.com.br/minha-conta/pesquisaGratis/pesquisar/?placa="+placa;
            HttpEntity entity = new HttpEntity(new HttpHeaders());
            ResponseEntity<String> ret = rt.exchange(uri, HttpMethod.GET, entity, String.class);
                
            if (ret.getBody() != null) {
                logger.info("respEnt:" + ret.getStatusCode());
                info = getMontarInformacoes(ret.getBody());
            }
        } catch (HttpClientErrorException e) {
            logger.error("http error:" + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
        }
        return info;
    }
    
    private InfoVeiculoVO getMontarInformacoes(String json) throws JSONException {
        InfoVeiculoVO info = new InfoVeiculoVO();

        JSONObject jsonObj = new JSONObject(json);
        info.setMensagem(jsonObj.has("mensagem") ? jsonObj.getString("mensagem") : null);

        if (info.getMensagem() != null && !info.getMensagem().isEmpty() && info.getMensagem().equals("sucesso")) {
            JSONObject jsonObjDados = ((JSONObject) jsonObj.get("dados_veiculo"));
            info.setAno(jsonObjDados.has("ano_fabricacao") ? jsonObjDados.getString("ano_fabricacao") : "Não informado");
            info.setCombustivel(jsonObjDados.has("combustivel") ? jsonObjDados.getString("combustivel") : "Não informado");
            info.setCor(jsonObjDados.has("cor") ? jsonObjDados.getString("cor") : "Não informado");
            info.setMarca(jsonObjDados.has("marca") ? jsonObjDados.getString("marca") : "Não informado");
            info.setModelo(jsonObjDados.has("modelo") ? jsonObjDados.getString("modelo") : "Não informado");
        }
        return info;
    }
}
