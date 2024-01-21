package com.example.treedbtest.repository

import com.example.treedbtest.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
}