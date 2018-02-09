package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVo;

@Repository
public class FileUploadDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(FileVo fileVo) {
		sqlSession.insert("fileupload.add", fileVo);
	}
}
