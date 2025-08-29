package mybatis.dao;

import mybatis.vo.BbsVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BbsDAO {
    @Autowired
    private SqlSessionTemplate ss;

    public BbsVO[] list(String bname, int begin, int end) {
        Map<String,String> m = new HashMap<>();
        m.put("bname",bname);
        m.put("begin",String.valueOf(begin));
        m.put("end",String.valueOf(end));

        List<BbsVO> list = ss.selectList("bbs.list  ",m);
        BbsVO[] ar =null;
        if (list != null && list.size() > 0){
            ar = new BbsVO[list.size()];
            list.toArray(ar);
        }
            return ar;
    }

    public int getTotal(String bname){
        return ss.selectOne("bbs.getTotal",bname);
    }


}
