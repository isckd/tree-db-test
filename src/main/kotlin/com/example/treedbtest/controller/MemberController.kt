package com.example.treedbtest.controller

import com.example.treedbtest.domain.Member
import com.example.treedbtest.service.MemberService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/get/{id}")
    fun getMember(@PathVariable id: Long): Member {
        return memberService.getMember(id)
    }

    @GetMapping("/get/all")
    fun getMembers(): List<Member> {
        return memberService.getMembers()
    }

    @PostMapping("/create/{name}")
    fun createMember(@PathVariable name: String): String {
        memberService.createMember(name)
        return "Success!"
    }

    @DeleteMapping("/delete/{id}")
    fun deleteMember(@PathVariable id: Long): String {
        memberService.deleteMember(id)
        return "Success!"
    }
}