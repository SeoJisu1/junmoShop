package com.shop.junmo.controller;

import com.shop.junmo.dto.MemberDTO;
import com.shop.junmo.service.MemberService;
import com.shop.junmo.util.SHA256;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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

        ModelAndView mav = new ModelAndView("redirect:/");
        
        try {
            if(memberDTO.getMember_id() != "") {
                SHA256 sha256 = new SHA256();
                String crytogram = sha256.encrypt(memberDTO.getPassword());

                memberDTO.setPassword(crytogram);
                memberDTO.setAddress(memberDTO.getAddress() + " " + memberDTO.getAddrsDetail());

                memberService.saveMemberInfo(memberDTO);

            } else {
                System.out.println("db 저장 오류");
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

    @PostMapping("/member/duplication")
    public int idDuplication(Model model, MemberDTO memberDTO) {
        int result = memberService.memberIdDupl(memberDTO.getMember_id());
        model.addAttribute("result", result);

        return result;
    }

    @RequestMapping("/member/login.do")
    public ModelAndView loginPage(Model model, MemberDTO memberDTO) {
        model.addAttribute("memberDTO", memberDTO);
        return new ModelAndView("login");
    }

    @PostMapping("/member/login/loginExe.do")
    public ModelAndView loginExecute(@ModelAttribute("memberDTO") MemberDTO memberDTO,
                                     Model model,
                                     HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        SHA256 sha256 = new SHA256();

        try {
            // 비밀번호 암호화 비교
            String crytogram = sha256.encrypt(memberDTO.getPassword());
            memberDTO.setPassword(crytogram);

            MemberDTO loginMember = memberService.memberLogin(memberDTO);

            if(loginMember == null) {
                System.out.println("아이디 또는 비밀번호 불일치");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("loginMember", loginMember);
                mav.setViewName("redirect:/");
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return mav;
    }

}
