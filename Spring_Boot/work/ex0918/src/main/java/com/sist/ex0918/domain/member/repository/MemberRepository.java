package com.sist.ex0918.domain.member.repository;

import com.sist.ex0918.domain.member.entity.Member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>{
    Optional<Member> findByMid(String mid);
}
