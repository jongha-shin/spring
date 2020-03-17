package bit.com.a.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.PdsDao;
import bit.com.a.model.PdsDto;

@Repository
public class PdsDaoImpl implements PdsDao {

		@Autowired
		SqlSessionTemplate sqlsession;
		
		private String ns = "Pds.";

		@Override
		public List<PdsDto> getPdsList() {
			return sqlsession.selectList(ns+"getPdsList");
		}

		@Override
		public boolean uploadPds(PdsDto dto) {
			int count = sqlsession.insert(ns+"uploadPds", dto);
			return count>0?true:false;
		}

		@Override
		public PdsDto getPds(int seq) {
			return sqlsession.selectOne(ns+"getPds", seq);
		}

		@Override
		public void updatePds(PdsDto pdsdto) {
			sqlsession.update(ns+"updatepds", pdsdto);
		}
		
		
}
