package org.zerock.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
//회원정보(기본권한)
    private String userid;
    private String userpw;
    private String userName;
    private String dept;
    private String phone;
    private boolean enabled;
    private Date regDate;
    private Date updateDate;
    private int cate;
    private int boardCnt;
    private int replyCnt;
    private int hitCnt;
    
    private List<AuthVO> authList; //여러개의 사용자 권한
}