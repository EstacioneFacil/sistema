package model.util;

import config.ConfiguracaoSistema;
import java.util.Properties;
import model.Anexo;
import model.Area;
import model.Auditoria;
import model.AuditoriaDetalhe;
import model.Botao;
import model.GrupoPermissao;
import model.Menu;
import model.MenuBotao;
import model.Movimentacao;
import model.Permissao;
import model.PermissaoBotao;
import model.TabelaPreco;
import model.TipoVeiculo;
import model.Usuario;
import model.Vaga;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

    public static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
            if (ConfiguracaoSistema.getLogin() != null) {
                properties.setProperty("hibernate.connection.username", ConfiguracaoSistema.getLogin());
                properties.setProperty("hibernate.connection.password", "postgres");
            }
            configuration.setProperties(properties);
            
            //mapeando classes
            configuration.addAnnotatedClass(Anexo.class);
            configuration.addAnnotatedClass(Area.class);
            configuration.addAnnotatedClass(Botao.class);
            configuration.addAnnotatedClass(Auditoria.class);
            configuration.addAnnotatedClass(AuditoriaDetalhe.class);
            configuration.addAnnotatedClass(GrupoPermissao.class);
            configuration.addAnnotatedClass(Menu.class);
            configuration.addAnnotatedClass(MenuBotao.class);
            configuration.addAnnotatedClass(Movimentacao.class);
            configuration.addAnnotatedClass(Permissao.class);
            configuration.addAnnotatedClass(PermissaoBotao.class);
            configuration.addAnnotatedClass(TabelaPreco.class);
            configuration.addAnnotatedClass(TipoVeiculo.class);
            configuration.addAnnotatedClass(Usuario.class);
            configuration.addAnnotatedClass(Vaga.class);
            
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build(); 
            
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
    
    public void close() {
        if (getSession() != null && getSession().isOpen()) {
            getSession().close();
        }
    }
}
