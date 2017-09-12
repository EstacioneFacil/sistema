package model.util;

import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Douglas
 */
public class CriteriaUtils {

    public static Criteria entrePeriodos(Criteria crit, String colDataInicio, String colDataFim, Date dataInicio, Date dataFim) {
        if ((dataInicio != null) && (dataFim == null)) {
            crit.add(
                    Restrictions.or(
                            Restrictions.and(Restrictions.le(colDataInicio, dataInicio), Restrictions.isNull(colDataFim)),
                            Restrictions.and(Restrictions.le(colDataInicio, dataInicio), Restrictions.geProperty(colDataFim, colDataInicio))
                    )
            );
        } else if ((dataInicio != null) && (dataFim != null)) {
            crit.add(Restrictions.and(Restrictions.le(colDataInicio, dataFim), Restrictions.or(Restrictions.isNull(colDataFim), Restrictions.ge(colDataFim, dataInicio))));
        }
        return crit;
    }
}
