package mybatis.dao;

import mybatis.vo.EmpVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpDAO {
    private SqlSessionTemplate ss;
    //톰캣이 구동될 때 이미 생성된 SqlSessionTemplate이 자동으로 ss에 저장되도록 한다.(autowire)

    public SqlSessionTemplate getSs() {
        return ss;
    }

    public void setSs(SqlSessionTemplate ss) {
        this.ss = ss;
    }

    public EmpVO[] getTotal(){
        List<EmpVO> list = ss.selectList("emp.total");
        EmpVO[] ar = null;
        if(list!=null){
            ar= new EmpVO[list.size()];
            list.toArray(ar);
        }

        return ar;
    }

}
