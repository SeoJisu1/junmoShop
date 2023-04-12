package com.shop.junmo.controller;

import com.shop.junmo.dto.MemberDTO;
import com.shop.junmo.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MemberContoller {

    @Resource
    private MemberService memberService; // 호출 시 MemberServiceImpl이 딸려들어옴

    @RequestMapping("/member/list")
    public ModelAndView listMember() {
        ModelAndView mav = new ModelAndView();

        List<MemberDTO> list = memberService.getAllMemberList();

        mav.addObject("list", list);
        mav.setViewName("memberList");

        return mav;
    }

    @RequestMapping("/member/save")
    public ModelAndView insertMember() {

//        memberService.saveMemberInfo();

        return new ModelAndView("registMember");
    }

}
