
import dao.AreaDao;
import dao.TipoVeiculoDao;
import java.util.List;
import model.Area;
import model.TipoVeiculo;
import org.apache.log4j.Logger;


public class Main {
    public static void main(String[] args) {
        
        
        AreaDao areaDao = new AreaDao();
        TipoVeiculoDao tipoVeiculoDao = new TipoVeiculoDao();
        
        Area a1 = new Area();
        a1.setId(10L);
        a1.setDescricao("Área 2");
        areaDao.gravar(a1);
//        
        try {
            List<Area> areas = areaDao.findAll();
            System.out.println("Áreas:");
            for (Area area : areas) {
                System.out.println(area.getDescricao());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
        
        TipoVeiculo tv1 = new TipoVeiculo();
        tv1.setDescricao("Carro");
        tipoVeiculoDao.gravar(tv1);
        
        TipoVeiculo tv2 = new TipoVeiculo();
        tv2.setDescricao("Moto");
        tipoVeiculoDao.gravar(tv2);
        
        try {
            List<TipoVeiculo> tipos = tipoVeiculoDao.findAll();
            System.out.println("Tipos:");
            for (TipoVeiculo tipoVeiculo : tipos) {
                System.out.println(tipoVeiculo.getDescricao());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
