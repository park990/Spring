package mybatis.dao;
import mybatis.vo.BbsVO;
import mybatis.vo.CommVO;
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

    // 총 게시물의 수를 반환
    public  int getTotalCount(String bname) {
        return ss.selectOne("bbs.totalCount", bname);
    }

    // 목록 반환
    public BbsVO[] getList(String bname, int begin, int end) {
        BbsVO[] ar = null;
        Map<String, String> map = new HashMap<>();
        map.put("bname", bname);
        map.put("begin", String.valueOf(begin));
        map.put("end", String.valueOf(end));
        List<BbsVO> list = ss.selectList("bbs.list", map);
        if (list != null&& list.size()>0) {
            ar = new BbsVO[list.size()];
            list.toArray(ar);

        }
        return ar;
    }
    // 저장
    public int add(BbsVO bvo){
        return ss.insert("bbs.add",bvo);
    }
    // 기본키르 인자로 게시물 가져오기
    public BbsVO getBbs(String idx){
        return ss.selectOne("bbs.see",idx);
    }
    // 수정
    public int udt(CommVO cvo){
        return ss.update("bbs.udt",cvo);
    }
    // 삭제
    public int delBbs(String b_idx){
        return ss.update("bbs.del",b_idx);
    }
    /*조회수 증가*/
    public int hit(String b_idx){
        return ss.update("bbs.hit",b_idx);
    }
}
