package when_how.hero.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class LongToDateHandler implements TypeHandler<Date> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Date parameter,
			JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			ps.setLong(i, 0);
			return;
		}
		ps.setLong(i, parameter.getTime() / 1000);
	}

	@Override
	public Date getResult(ResultSet rs, String columnName) throws SQLException {
		return new Date(rs.getLong(columnName) * 1000);
	}

	@Override
	public Date getResult(ResultSet rs, int columnIndex) throws SQLException {
		return new Date(rs.getLong(columnIndex) * 1000);
	}

	@Override
	public Date getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return new Date(cs.getLong(columnIndex) * 1000);
	}

}
