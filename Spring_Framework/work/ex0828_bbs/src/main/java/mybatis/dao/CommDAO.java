package mybatis.dao;
import mybatis.vo.CommVO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommDAO {
    @Autowired
    private SqlSessionTemplate ss;

    // 댓글 저장
    public int add(String writer, String content,
                          String pwd, String ip, String b_idx) {
        CommVO cvo = new CommVO();
        cvo.setWriter(writer);
        cvo.setContent(content);
        cvo.setPwd(pwd);
        cvo.setIp(ip);
        cvo.setB_idx(b_idx);

        return  ss.insert("comm.add", cvo);
    }
}
