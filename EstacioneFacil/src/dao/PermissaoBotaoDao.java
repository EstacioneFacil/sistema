package dao;

import java.util.List;
import model.Menu;
import model.PermissaoBotao;
import model.vo.PermissaoBotaoVO;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

public class PermissaoBotaoDao extends GenericDao<PermissaoBotao> {

    public PermissaoBotaoDao() {
        super();
    }
    
    public void gravar(PermissaoBotao permissaoBotao) {
        if (permissaoBotao.getId() == null) {
            save(permissaoBotao);
        } else {
            update(permissaoBotao);
        }
    }
    
    public List<PermissaoBotao> buscarPermissaoBotoes(Long idPermissao) {
        Criteria crit = getSession().createCriteria(PermissaoBotao.class);
        crit.add(Restrictions.eq("idPermissao", idPermissao));
        return crit.list();
    }

    public List<PermissaoBotaoVO> buscarPermissoesPorTela(String tela) {
        SQLQuery sqlQuery = getSession().createSQLQuery("select b.nome as nome, pb.permissao from permissao_botao pb "
                + "inner join menu_botao mb on pb.menu_botao_id = mb.id "
                + "inner join botao b on mb.botao_id = b.id "
                + "inner join menu m on mb.menu_id = m.id "
                + "where m.classe = '"+tela+"'");
        
        sqlQuery.addScalar("nome", StringType.INSTANCE);
        sqlQuery.addScalar("permissao", BooleanType.INSTANCE);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(PermissaoBotaoVO.class));
        
        return sqlQuery.list();
    }
}
