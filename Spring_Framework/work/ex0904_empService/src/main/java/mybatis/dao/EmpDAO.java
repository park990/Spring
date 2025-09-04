package mybatis.dao;

import mybatis.vo.EmpVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class EmpDAO {
    @Autowired
    SqlSessionTemplate ss;

    public EmpVO[] all() {
        EmpVO[] ar = null;
        List<EmpVO> list = ss.selectList("emp.all");
        System.out.println("Arararar"+list.size());
        if (list != null) {
            ar = new EmpVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }

    public EmpVO[] search(Map<String,String> m) {
        EmpVO[] ar = null;
        List<EmpVO> list = ss.selectList("emp.search",m);
        if (list != null) {
            ar = new EmpVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }

}
