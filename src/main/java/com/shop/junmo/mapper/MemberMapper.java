package com.shop.junmo.mapper;

import com.shop.junmo.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/* xml 내용을 가져오기 위해 매퍼로 등록.
   여기에 있는 메서드 명은 memberMapper.xml에 있는 sql문의 id 값과 일치해야 한다. */
@Mapper // Mapper로 등록시킨다.
public interface MemberMapper {

    // 멤버 정보 저장
    public void saveMemberInfo(MemberDTO dto) throws Exception;

    // 멤버 리스트 전체 출력
    public List<MemberDTO> getAllMemberList();

}
