package com.oheproject.fileuploadboard.log;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogDAO {

    @Autowired
    private SqlSession sqlSession;

    public void downloadLog(LogDTO logDTO) {
        sqlSession.insert("com.oheproject.fileuploadboard.log.LogMapper.insertDownloadLog", logDTO);
    }

}
