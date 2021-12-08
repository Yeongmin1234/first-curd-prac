package org.zerock.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.CrudVO;
import org.zerock.domain.BoardAttachVO;
import org.zerock.mapper.CrudMapper;
import org.zerock.mapper.BoardAttachMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class CrudServiceImpl implements CrudService {
	public CrudMapper mapper;
	private BoardAttachMapper attachMapper;
	
	
	@Override
	public List<CrudVO> list() {
		return mapper.list();
	}
	
	@Override
	public void create(CrudVO vo) {
		
		mapper.create(vo);
		
		if(vo.getAttachList()==null || vo.getAttachList().size()<=0 ) {
			return;
		}
			vo.getAttachList().forEach(attach->{
					//��������           =BoardAttachVO
				attach.setBno(vo.getBno());
				attachMapper.insert(attach);
			});
	}
	
	@Override
	public CrudVO read(int bno) {
		System.out.println("�̻����");
		return mapper.read(bno);
	}

	@Override
	public int update(CrudVO vo) {
		
		attachMapper.deleteAll(vo.getBno());
		
		int modifyResult = mapper.update(vo);
		
		if(modifyResult==1 && vo.getAttachList() != null && vo.getAttachList().size() > 0) {
			
			vo.getAttachList().forEach(attach -> {
				System.out.println("�̰� ���� ��");
				attach.setBno(vo.getBno());
				attachMapper.insert(attach);
			});
		}
		System.out.println("������ ���� �ȵ�");
		return modifyResult;
	}

	@Override
	public int delete(int bno) {
		attachMapper.deleteAll(bno);
		return mapper.delete(bno);
	}

	public List<BoardAttachVO> getAttachList(int bno) {
		return attachMapper.findByBno(bno);
	}
}
