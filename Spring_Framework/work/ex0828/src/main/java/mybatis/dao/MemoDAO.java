package mybatis.dao;

import mybatis.vo.MemoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemoDAO {

    @Autowired
    private SqlSessionTemplate ss;

    public MemoVO[] getTotal() {
        List<MemoVO> list = ss.selectList("memo.total");
        MemoVO[] ar = null;
        if (list != null && list.size() > 0) {
            ar = new MemoVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }// getTotal의 마지막

    public int add(MemoVO vo){
        return ss.insert("memo.add",vo);
    }




}
