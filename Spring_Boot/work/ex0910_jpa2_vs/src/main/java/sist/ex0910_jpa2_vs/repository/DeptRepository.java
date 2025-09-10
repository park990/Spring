package sist.ex0910_jpa2_vs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sist.ex0910_jpa2_vs.store.Dept;

@Repository
public interface DeptRepository extends JpaRepository<Dept,Long> {
    
    Optional<Dept> findByDeptno(Long deptno);
}
