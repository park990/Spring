package com.review.ex0919_jaeyoon_jwt_important.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.review.ex0919_jaeyoon_jwt_important.domain.member.entity.MemEntity;

@Repository
public interface MemRepository extends JpaRepository<MemEntity,Long>{
    Optional<MemEntity> findByPid(String pId);
}
