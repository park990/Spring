package mybatis.dao;

import jsonEx.output.empVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class empDAO {
    @Autowired
    SqlSessionTemplate ss;
    public empVO[] all(){
        List<empVO> list = ss. selectList("emp.all");
        empVO[] ar =null;
        if(list !=null){
            ar=new empVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }

}
