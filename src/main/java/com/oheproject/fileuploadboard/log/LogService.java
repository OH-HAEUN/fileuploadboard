package com.oheproject.fileuploadboard.log;

public interface LogService {

    public void downloadLog(LogDTO logDTO, String username, String filename, String ipaddress) throws Exception;
}
