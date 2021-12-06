package org.zerock.domain;

import lombok.Data;

@Data
public class AttachFileDTO {

	private String fileName;
	//�����̸�	
	private String uploadPath;
	//���Ͼ��ε� ���
	private String uuid;
	//uuid
	private boolean image;

}