package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.CrudVO;


public interface CrudMapper {
	public List<CrudVO> list();
	public void create(CrudVO vo);
	public CrudVO read(int bno);
	public int update(CrudVO vo);
	public int delete(int bno);
}
