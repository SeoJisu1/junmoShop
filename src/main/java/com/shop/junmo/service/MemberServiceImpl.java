package com.shop.junmo.service;

import com.shop.junmo.dto.MemberDTO;
import com.shop.junmo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// MemberService 인터페이스를 구현한 클래스
@Service // 객체 생성
public class MemberServiceImpl implements MemberService{

    @Autowired // MemberMapper에 있는 sql문을 MemberServiceImpl로 읽어와서 의존성 주입하여 객체를 생성 한것
    private MemberMapper memberMapper;

    /*
       경로 : MemberController -> MemberService(I) -> MemberServiceImpl(C) -> MemberMapper(I) -> memberMapper.xml
    */

    @Override
    public void saveMemberInfo(MemberDTO dto) throws Exception {
        memberMapper.saveMemberInfo(dto);
    }

    @Override
    public List<MemberDTO> getAllMemberList() {
        return memberMapper.getAllMemberList();
    }

    @Override
    public int memberIdDupl(String member_id) {
        return memberMapper.memberIdDupl(member_id);
    }

//    @Override
//    public MemberDTO memberLogin(String member_id, String password) throws Exception {
//        return memberMapper.memberLogin(member_id, password);
//    }
    @Override
    public MemberDTO memberLogin(MemberDTO dto) throws Exception {
        return memberMapper.memberLogin(dto);
    }
}
