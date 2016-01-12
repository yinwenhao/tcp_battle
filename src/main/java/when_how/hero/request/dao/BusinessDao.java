package when_how.hero.request.dao;

import org.springframework.stereotype.Component;

import when_how.hero.hibernate.dao.BaseDao;
import when_how.hero.request.domain.Business;

/**
 * @author when_how
 * 
 */
@Component("businessDao")
public class BusinessDao extends BaseDao<Business, Integer> implements IBusinessDao {

}