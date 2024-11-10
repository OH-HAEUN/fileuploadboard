package com.oheproject.fileuploadboard.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAO {

    @Autowired
    private SqlSession sqlSession;

    // 게시글 작성
    public void write(BoardDTO boardDTO) throws Exception {
        sqlSession.insert("com.oheproject.fileuploadboard.board.BoardMapper.insertBoard", boardDTO);
    }

    public void fileUpload(FileDTO fileDTO) {
        sqlSession.insert("com.oheproject.fileuploadboard.board.BoardMapper.insertFile", fileDTO);
    }

    // 게시글 목록
    public List<BoardDTO> list(BoardDTO boardDTO) {
        return sqlSession.selectList("com.oheproject.fileuploadboard.board.BoardMapper.boardList", boardDTO);
    }

    // 게시글 상세 페이지
    public BoardDTO view(int id) {
        return sqlSession.selectOne("com.oheproject.fileuploadboard.board.BoardMapper.boardView", id);
    }

    public List<FileDTO> fileList(int id) {
        return sqlSession.selectList("com.oheproject.fileuploadboard.board.BoardMapper.fileList", id);
    }

    public String fileDownload(String filename) {
        return sqlSession.selectOne("com.oheproject.fileuploadboard.board.BoardMapper.fileDownload", filename);
    }

    // 게시글 삭제
    public void delete(int id) {
        sqlSession.delete("com.oheproject.fileuploadboard.board.BoardMapper.deleteBoard", id);
    }

    // 파일 삭제
    public void deleteFile(int id) {
        sqlSession.delete("com.oheproject.fileuploadboard.board.BoardMapper.deleteFile", id);
    }

    public void updateBoard(BoardDTO boardDTO) {
        sqlSession.update("com.oheproject.fileuploadboard.board.BoardMapper.updateBoard", boardDTO);
    }

    public void updateBoardFileDel(String fileuuid) {
        sqlSession.delete("com.oheproject.fileuploadboard.board.BoardMapper.updateBoardFileDel", fileuuid);
    }

    public int countBoard() {
        return sqlSession.selectOne("com.oheproject.fileuploadboard.board.BoardMapper.countBoard");
    }

    public List<BoardDTO> selectBoard(PageDTO pageDTO) {
        return sqlSession.selectList("com.oheproject.fileuploadboard.board.BoardMapper.selectBoard", pageDTO);
    }

    public int updateDownloadClk(int id) {
        return sqlSession.selectOne("com.oheproject.fileuploadboard.board.BoardMapper.updateDownloadClk", id);
    }
}
