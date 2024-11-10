package com.oheproject.fileuploadboard.board;

public class FileDTO {

    private int id;
    private String fileorigname;
    private String filename;
    private String fileuuid;

    public int getId() {
        return id;
    }
    public String getFileorigname() {
        return fileorigname;
    }
    public String getFilename() {
        return filename;
    }

    public String getFileuuid() { return fileuuid;    }

    public void setId(int id) {
        this.id = id;
    }
    public void setFileorigname(String fileorigname) {
        this.fileorigname = fileorigname;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFileuuid(String fileuuid) {
        this.fileuuid = fileuuid;
    }
}
