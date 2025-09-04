package mybatis.service;

import mybatis.vo.EmpVO;

import java.util.List;

public interface empMapper {
    EmpVO[] all();

    EmpVO[] search(String type, String value);
}
