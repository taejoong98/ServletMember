package tae.member.service;

import java.util.ArrayList;

import tae.member.dto.MemberDTO;


public interface MemberService {
	
	public ArrayList<MemberDTO> memberSelectAll();
	public MemberDTO memberSelect(String umail);
	public MemberDTO memberInsert(MemberDTO memberDTO);
	public MemberDTO memberUpdate(MemberDTO memberDTO);
	public MemberDTO memberDelete(String umail, String upw);
}
