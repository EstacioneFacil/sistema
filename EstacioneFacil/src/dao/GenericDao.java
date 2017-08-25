package dao;

import util.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import model.vo.FiltroVO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class GenericDao<T extends Serializable> {

    private Session session;
    private final Class<T> persistentClass;

    public GenericDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        if (this.session == null || !this.session.isOpen()) {
            this.session = HibernateUtil.getSession();
        }
        return this.session;
    }

    protected void save(T entity) {
        getSession().getTransaction().begin();
        getSession().saveOrUpdate(entity);
        getSession().getTransaction().commit();
        getSession().close();
    }

    protected void update(T entity) {
        getSession().getTransaction().begin();
        getSession().merge(entity);
        getSession().getTransaction().commit();
        getSession().close();
    }

    protected void delete(T entity) {
        getSession().getTransaction().begin();
        getSession().delete(entity);
        getSession().getTransaction().commit();
        getSession().close();
    }

    public List<T> findAll() throws Exception {
        return getSession().createCriteria(persistentClass).list();
    }

    public T findByName(String nome) {
        return (T) getSession().createCriteria(persistentClass)
                .add(Restrictions.eq("nome", nome).ignoreCase()).uniqueResult();
    }

    public T findById(long id) {
        return (T) getSession().createCriteria(persistentClass)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    
    //NAO IMPLEMENTADO
//    public Disjunction montarFiltro(Criteria crit, FiltroVO filtroVO) {
//        //retorna nome de todas colunas da entidade passada por parametro
//        String[] colunas = HibernateUtil.sessionFactory.getClassMetadata(persistentClass).getPropertyNames();
//        Criterion[] predicates = new Criterion[colunas.length + 1];
//
//        //acrescenta a coluna id manualmente pois nao aparece na busca a cima
//        int cont = 0;
////        predicates[cont] = Restrictions.sqlRestriction("id LIKE '%"+filtroVO.getPesquisa()+"%' ");
//        for (int i = 0; i < colunas.length; i++) {
//            cont++;
//            predicates[cont] = Restrictions.like(colunas[i], filtroVO.getPesquisa(), MatchMode.ANYWHERE);
//        }
//        return Restrictions.or(predicates);
//    }
}
