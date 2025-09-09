package sist.ex0909_jpa1_vs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sist.ex0909_jpa1_vs.store.ProductJPO;

@Repository
public interface ProductRepository extends JpaRepository<ProductJPO,Long>{
    
    
}
