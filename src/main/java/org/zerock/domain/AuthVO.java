package org.zerock.domain;

import lombok.Data;

@Data
public class AuthVO {
//유저 아이디, 권한
    private String userid;
    private String auth;
}