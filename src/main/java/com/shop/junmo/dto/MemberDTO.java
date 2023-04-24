package com.shop.junmo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String member_id;
    private String address;
    private String email;
    private String name;
    private String password;
    private String phone;

}
