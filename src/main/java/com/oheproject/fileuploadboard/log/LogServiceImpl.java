package com.oheproject.fileuploadboard.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDAO logDAO;

    @Override
    public void downloadLog(LogDTO logDTO, String username, String filename, String ipaddress) throws Exception {

        logDTO.setUsername( username );
        logDTO.setFilename( filename );
        logDTO.setIpaddress( ipaddress );

        logDAO.downloadLog(logDTO);
    }
}
