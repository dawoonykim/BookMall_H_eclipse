package com.bookMall.member.vo;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component(value = "memberVO")
public class MemberVO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberGender;
    private String tel1;
    private String tel2;
    private String tel3;
    private String hp1;
    private String hp2;
    private String hp3;
    private String smsStsYn;
    private String email1;
    private String email2;
    private String emailStsYn;
    private String zipCode;
    private String roadAddress;
    private String jibunAddress;
    private String namujiAddress;
    private String memberBirthY;
    private String memberBirthM;
    private String memberBirthD;
    private String memberBirthGn;
    private Date joinDate;
    private String delYn;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    public String getHp1() {
        return hp1;
    }

    public void setHp1(String hp1) {
        this.hp1 = hp1;
    }

    public String getHp2() {
        return hp2;
    }

    public void setHp2(String hp2) {
        this.hp2 = hp2;
    }

    public String getHp3() {
        return hp3;
    }

    public void setHp3(String hp3) {
        this.hp3 = hp3;
    }

    public String getSmsStsYn() {
        return smsStsYn;
    }

    public void setSmsStsYn(String smsStsYn) {
        this.smsStsYn = smsStsYn;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmailStsYn() {
        return emailStsYn;
    }

    public void setEmailStsYn(String emailStsYn) {
        this.emailStsYn = emailStsYn;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getRoadAddress() {
        return roadAddress;
    }

    public void setRoadAddress(String roadAddress) {
        this.roadAddress = roadAddress;
    }

    public String getJibunAddress() {
        return jibunAddress;
    }

    public void setJibunAddress(String jibunAddress) {
        this.jibunAddress = jibunAddress;
    }

    public String getNamujiAddress() {
        return namujiAddress;
    }

    public void setNamujiAddress(String namujiAddress) {
        this.namujiAddress = namujiAddress;
    }

    public String getMemberBirthY() {
        return memberBirthY;
    }

    public void setMemberBirthY(String memberBirthY) {
        this.memberBirthY = memberBirthY;
    }

    public String getMemberBirthM() {
        return memberBirthM;
    }

    public void setMemberBirthM(String memberBirthM) {
        this.memberBirthM = memberBirthM;
    }

    public String getMemberBirthD() {
        return memberBirthD;
    }

    public void setMemberBirthD(String memberBirthD) {
        this.memberBirthD = memberBirthD;
    }

    public String getMemberBirthGn() {
        return memberBirthGn;
    }

    public void setMemberBirthGn(String memberBirthGn) {
        this.memberBirthGn = memberBirthGn;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }
}
