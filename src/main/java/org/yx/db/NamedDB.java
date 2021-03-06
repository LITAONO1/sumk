/**
 * Copyright (C) 2016 - 2017 youtongluan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yx.db;

import java.util.List;
import java.util.Map;

import org.yx.db.sql.MapedSqlBuilder;
import org.yx.db.visit.Visitors;
import org.yx.exception.SumkException;

/**
 * 这里的sql，占位符不是?，而是#{**}，里面的name，与map的key要一一对应，忽略大小写<BR>
 * 推荐通过常量或其它方式引用sql
 * 
 * @author 游夏
 *
 */
public class NamedDB {

	/**
	 * 
	 * @param sql
	 *            这里的sql，占位符不是?，而是#{**}，里面的name，与map的key要一一对应，忽略大小写
	 * @param map
	 *            参数
	 * @return sql执行结果
	 */
	public static int execute(String sql, Map<String, Object> map) {
		try {
			return Visitors.modifyVisitor.visit(new MapedSqlBuilder(sql, map));
		} catch (Exception e) {
			throw SumkException.create(e);
		}
	}

	/**
	 * 
	 * @param sql
	 *            这里的sql，占位符不是?，而是#{**}，里面的name，与map的key要一一对应，忽略大小写
	 * @param map
	 *            参数
	 * @return 结果集
	 */
	public static List<Map<String, Object>> list(String sql, Map<String, Object> map) {
		try {
			return Visitors.queryVisitor.visit(new MapedSqlBuilder(sql, map));
		} catch (Exception e) {
			throw SumkException.create(e);
		}
	}

	/**
	 * 
	 * @param sql
	 *            这里的sql，占位符不是?，而是#{**}，里面的name，与map的key要一一对应，忽略大小写
	 * @param map
	 *            参数
	 * @return 结果集
	 */
	public static List<?> singleColumnList(String sql, Map<String, Object> map) {
		try {
			return Visitors.singleListQueryVisitor.visit(new MapedSqlBuilder(sql, map));
		} catch (Exception e) {
			throw SumkException.create(e);
		}
	}

	/**
	 * @param sql
	 *            这里的sql，占位符不是?，而是#{**}，里面的name，与map的key要一一对应，忽略大小写
	 * @param map
	 *            参数
	 * @return 记录数
	 */
	public static int count(String sql, Map<String, Object> map) {
		try {
			List<?> list = Visitors.singleListQueryVisitor.visit(new MapedSqlBuilder(sql, map));
			Number n = (Number) list.get(0);
			return n.intValue();
		} catch (Exception e) {
			throw SumkException.create(e);
		}
	}

}
