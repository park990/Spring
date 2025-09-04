package mybatis.service;

import mybatis.vo.BbsVO;

public interface BbsMapper{
    BbsVO[] list(String bname, int begin, int end);

}
