package sist.ex0910_jpa2_vs.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sist.ex0910_jpa2_vs.store.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp,Long>{
    
    // 검색된 데이터가 없을 때 null이 아닌 optional.empty()를 반환한다.
    // 그러므로 값이 없을 때 보다 안전하게 처리 된다.
    Optional<Emp> findByEmpno(Long empno); // 사원번호로 검색
    List<Emp> findByDeptno(String deptno); // 부서번호로 검색

    List<Emp> findByJobAndDeptno(String job, String deptno);

    //nativeQuery=true 는 JPQL 이 아닌 순수 sql문을 사용 한다는 뜻.
    @Query(value="select * from emp where job like concat('%',?1,'%') and deptno = :deptno",
    nativeQuery = true)
    List<Emp> findByJobLikeAndDeptno(String job, String deptno);

    List<Emp> findByJobContainingAndDeptno(String job, String deptno);
    List<Emp> findByEnameStartingWith(String ename);

    //문제: 급여가 3000이하인 사원들의 정보를 입사일이 최근 순으로 출력
    List<Emp> findBySalLessThanEqualOrderByHiredateDesc(BigDecimal sal);
    
    
}
