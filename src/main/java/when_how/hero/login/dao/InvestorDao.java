package when_how.hero.login.dao;

import org.springframework.stereotype.Component;

import when_how.hero.domain.Investor;
import when_how.hero.hibernate.dao.BaseDao;

/**
 * @author when_how
 * 
 */
@Component("investorDao")
public class InvestorDao extends BaseDao<Investor, Integer> implements IInvestorDao {

}