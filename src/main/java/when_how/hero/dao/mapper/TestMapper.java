package when_how.hero.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TestMapper {

	public List<Map> test();

	public int insertTest2(@Param("userId") long user_id, @Param("couponId") String coupon_id, @Param("orderId") long order_id, @Param("status") int status);
	
}
