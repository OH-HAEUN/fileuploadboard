package com.oheproject.fileuploadboard.board;

import com.oheproject.fileuploadboard.user.UserDAO;
import com.oheproject.fileuploadboard.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
@PropertySources(@PropertySource("classpath:/application.properties"))
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Environment environment;

    @Override
    public void write(BoardDTO boardDTO, String writer) {

        UserDTO userDTO = userDAO.userInfo(writer);
        String writerName = "[" + userDTO.getCompany() + "] " + userDTO.getName();

        boardDTO.setWriterid(writer);
        boardDTO.setWriter(writerName);

        try {
            boardDAO.write( boardDTO );
        } catch (Exception e) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public void fileUpload(BoardDTO boardDTO, FileDTO fileDTO, MultipartFile file) {

//        String projectPath = "/C:/Java/files/";
        String projectPath = environment.getProperty( "file.filepath" );
//        LocalDateTime m1 = LocalDateTime.now().plusMinutes( 1 );
        LocalDateTime m1 = LocalDateTime.now().plusDays( 30 );
        UUID uuid = UUID.randomUUID();

        if (file != null) {
            if (file.getOriginalFilename() != "") {
                String fileName = uuid + "_" + file.getOriginalFilename();
                File saveFile = new File( projectPath, fileName );

            try {
                file.transferTo( saveFile );
            } catch (IOException e) {
                throw new RuntimeException( e );
            }
            fileDTO.setId( boardDTO.getId() );
            fileDTO.setFileorigname(file.getOriginalFilename());
            fileDTO.setFilename( fileName );
            fileDTO.setFileuuid( uuid.toString() );
//            fileDTO.setExpire_date(Timestamp.valueOf(m1));
        }
            boardDAO.fileUpload( fileDTO );
        }
    }

    @Override
    public List<BoardDTO> list(BoardDTO boardDTO) {
        return boardDAO.list( boardDTO );
    }

    @Override
    public BoardDTO view(int id) {
        return boardDAO.view(id);
    }

    @Override
    public List<FileDTO> filelist(int id) {
        return boardDAO.fileList(id);
    }

    @Override
    public int updateDownloadClk(int id) {
        return boardDAO.updateDownloadClk(id);
    }

    @Override
    public void delete(int id) {
        String projectPath = environment.getProperty( "file.filepath" );

        List<FileDTO> fileDTO = boardDAO.fileList(id);

        for (int i=0; i<fileDTO.size(); i++) {
            String filename = fileDTO.get(i).getFilename();
            File file = new File(projectPath, filename);
            file.delete();
        }

        boardDAO.deleteFile(id);
        boardDAO.delete(id);
    }

    @Override
    public void update(BoardDTO boardDTO, String[] DelFiles) {

//        String projectPath = "/C:/Java/files/";
        String projectPath = environment.getProperty( "file.filepath" );

        UUID uuid = UUID.randomUUID();

        List<String> delFilesList = Arrays.asList(DelFiles);

        for (int i = 0; i < delFilesList.size(); i++) {
            boardDAO.updateBoardFileDel(delFilesList.get(i));
        }
//        if (file != null) {
//            if (file.getOriginalFilename() != "") {
//                String fileName = uuid + "_" + file.getOriginalFilename();
//
//                File saveFile = new File( projectPath, fileName );
//
//
//                try {
//                    file.transferTo( saveFile );
//                } catch (IOException e) {
//                    throw new RuntimeException( e );
//                }
////                boardDTO.setFileorigname(file.getOriginalFilename());
////                boardDTO.setFilename( fileName );
////                boardDTO.setFilepath( "/files/" + fileName );
//            }
//        }

        boardDAO.updateBoard(boardDTO);

//        boardDAO.updateFile(fileDTO);
    }

    @Override
    public int countBoard() {
        return boardDAO.countBoard();
    }

    @Override
    public List<BoardDTO> selectBoard(PageDTO pageDTO) {
        return boardDAO.selectBoard(pageDTO);
    }
}
