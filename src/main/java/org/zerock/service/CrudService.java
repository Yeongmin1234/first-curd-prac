package org.zerock.service;

import java.util.Date;
import java.util.List;

import org.zerock.domain.CrudVO;
import org.zerock.domain.BoardAttachVO;

import lombok.Data;

public interface CrudService {
	public List<CrudVO> list();
	public void create(CrudVO vo);
	public CrudVO read(int bno);
	public int update(CrudVO vo);
	public int delete(int bno);
	public List<BoardAttachVO> getAttachList(int bno);
}
