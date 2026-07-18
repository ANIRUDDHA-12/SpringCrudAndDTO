package com.firstProjectDemo.first_api.repository;

import com.firstProjectDemo.first_api.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
