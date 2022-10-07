package com.greedy.semi.member.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MemberDTO implements UserDetails {

    private String memberId;
    private String memberPwd;
    private String name;
    private Date birthday;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String memberStatus;
    private String memberRole;

    public MemberDTO() {
    }

   

    public MemberDTO(String memberId, String memberPwd, String name, Date birthday, String gender, String email,
			String phone, String address, String memberStatus, String memberRole) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.memberStatus = memberStatus;
		this.memberRole = memberRole;
	}



	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", memberPwd=" + memberPwd + ", name=" + name + ", birthday="
				+ birthday + ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", memberStatus=" + memberStatus + ", memberRole=" + memberRole + "]";
	}



	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public String getMemberPwd() {
		return memberPwd;
	}



	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Date getBirthday() {
		return birthday;
	}



	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getMemberStatus() {
		return memberStatus;
	}



	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}



	public String getMemberRole() {
		return memberRole;
	}



	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}



	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(memberRole));
        return roles;
    }

    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true; // 만료되지 않음
    }

    // 계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true; // 잠기지 않음
    }

    // 비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 만료되지 않음
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true; // 활성화
    }
}
