package mybatis.dao;

import mybatis.vo.DataVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataDAO {
    @Autowired
    private SqlSessionTemplate ss;

    public int add(DataVO dvo){
        return ss.insert("data.add",dvo);
    }
}
