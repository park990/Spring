package sist.ex0910_jpa1_vs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sist.ex0910_jpa1_vs.store.Category1JPO;

@Repository
public interface CategoryRepository extends JpaRepository<Category1JPO,Integer> {
    
}
