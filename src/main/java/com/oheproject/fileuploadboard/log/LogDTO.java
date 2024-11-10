package com.oheproject.fileuploadboard.log;

public class LogDTO {
    private String username;
    private String filename;

    private String downloaddate;

    private String ipaddress;

    public String getUsername() {
        return username;
    }

    public String getFilename() {
        return filename;
    }

    public String getDownloaddate() {
        return downloaddate;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setDownloaddate(String downloaddate) {
        this.downloaddate = downloaddate;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
}
