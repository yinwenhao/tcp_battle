package when_how.hero.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class IntegerListTypeHandler implements TypeHandler<List<Integer>> {

	@Override
	public void setParameter(PreparedStatement ps, int i,
			List<Integer> parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			ps.setString(i, "");
		}
		StringBuilder sb = new StringBuilder();
		for (long p : parameter) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(p);
		}
		ps.setString(i, sb.toString());
	}

	@Override
	public List<Integer> getResult(ResultSet rs, String columnName)
			throws SQLException {
		return transferType(rs.getString(columnName));
	}

	@Override
	public List<Integer> getResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return transferType(rs.getString(columnIndex));
	}

	@Override
	public List<Integer> getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return transferType(cs.getString(columnIndex));
	}

	private List<Integer> transferType(String s) {
		List<Integer> result = new ArrayList<Integer>();
		if (s == null || s.isEmpty()) {
			return result;
		}
		String[] numStrList = s.split(",");

		for (String numStr : numStrList) {
			result.add(Integer.parseInt(numStr));
		}
		return result;
	}

}
