package com.shop.junmo.service;

import com.shop.junmo.dto.MemberDTO;

import java.util.List;

// MemberMapper 클래스와 메서드를 똑같이 복사한 서비스 인터페이스
// 의존성을 위한 파일 분리 작업
public interface MemberService {

    // 멤버 정보 저장
    public void saveMemberInfo(MemberDTO dto) throws Exception;

    // 멤버 리스트 전체 출력
    public List<MemberDTO> getAllMemberList();

    // 아이디 중복확인
    public int memberIdDupl(String member_id);

}
