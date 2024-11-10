package com.oheproject.fileuploadboard.board;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    // 게시글 작성
    public void write(BoardDTO boardDTO, String writer);

    public void fileUpload(BoardDTO boardDTO, FileDTO fileDTO, MultipartFile file);

    // 게시판 목록
    public List<BoardDTO> list(BoardDTO boardDTO);

    // 게시글 상세 페이지
    public BoardDTO view(int id);

    // 게시글 상세 페이지 - 업로드 파일
    public List<FileDTO> filelist(int id);

    // 첨부 파일 다운 로드 클릭
    public int updateDownloadClk(int id);

    // 게시글 삭제
    public void delete(int id);

    // 게시글 수정
    public void update(BoardDTO boardDTO, String[] DelFiles);

    // 게시물 총 갯수
    public int countBoard();

    // 페이징 처리 게시글 조회
    public List<BoardDTO> selectBoard(PageDTO page);
}
