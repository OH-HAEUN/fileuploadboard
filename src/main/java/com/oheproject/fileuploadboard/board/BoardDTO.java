package com.oheproject.fileuploadboard.board;

public class BoardDTO {

    private int id;
    private String title;

    private String memo;

    private String writer;

    private String writerid;

    private String createdate;

    private String updatedate;

    private String file_expiry_date;

    private int download_limit;

    private int download_click;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMemo() {
        return memo;
    }

    public String getWriter() {
        return writer;
    }

    public String getWriterid() {
        return writerid;
    }

    public String getCreateddate() {
        return createdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public String getFile_expiry_date() {
        return file_expiry_date;
    }

    public int getDownload_limit() {
        return download_limit;
    }

    public int getDownload_click() {
        return download_click;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setWriterid(String writerid) {
        this.writerid = writerid;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public void setFile_expiry_date(String file_expiry_date) {
        this.file_expiry_date = file_expiry_date;
    }

    public void setDownload_limit(int download_limit) {
        this.download_limit = download_limit;}

    public void setDownload_click(int download_click) {
        this.download_click = download_click;}
    }
