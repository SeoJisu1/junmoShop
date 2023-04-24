package com.shop.junmo.controller;

import com.shop.junmo.dto.MemberDTO;
import com.shop.junmo.service.MemberService;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/member/regist")
    public ModelAndView registMember(Model model, MemberDTO memberDTO) {

        model.addAttribute("memberDTO", memberDTO);

        return new ModelAndView("registMember");
    }

    @PostMapping("/member/write")
    public ModelAndView insertMember(@ModelAttribute("memberDTO") MemberDTO memberDTO) throws Exception {
        String status = "";
        String message = "";

        ModelAndView mav = new ModelAndView("/");
        
        try {
            if(memberDTO.getMember_id() != "") {
                memberService.saveMemberInfo(memberDTO);

            } else {
                System.out.println("pk 오류");
                status = "fail";
            }

        } catch (Exception e) {
            status = "fail";
            message = e.getMessage();
        }

        Map<String, Object> res = new HashMap<String, Object>();
        res.put("status", status);
        res.put("message", message);
        mav.addObject("result", res);

        return mav;
    }

}
