package mybatis.service;

import mybatis.dao.EmpDAO;
import mybatis.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class empService implements empMapper{

    @Autowired
    EmpDAO EmpDAO;

    @Override
    public EmpVO[] all() {

        return EmpDAO.all();
    }

    @Override
    public EmpVO[] search(String type, String value) {
        Map<String,String> m = new HashMap<>();
        m.put("searchType",type);
        m.put("searchValue",value);

        return EmpDAO.search(m);
    }

}
