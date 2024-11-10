package com.oheproject.fileuploadboard.board;

import com.oheproject.fileuploadboard.log.LogDTO;
import com.oheproject.fileuploadboard.log.LogService;
import com.oheproject.fileuploadboard.user.UserDTO;
import com.oheproject.fileuploadboard.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;
    @Autowired
    private Environment environment;
    @Autowired
    private LogService logService;

    // 게시글 작성
    @GetMapping("/write")
    public String boardWrite() {
        return "BoardWrite";
    }

    // 게시글 작성 프로세스
    @PostMapping("/writepro")
    public String boardWritePro(BoardDTO boardDTO, FileDTO fileDTO, @RequestParam("file") List<MultipartFile> multipartFileList, Principal principal) {

        String writer = principal.getName();

        boardService.write( boardDTO, writer );

        for (int i = 0; i < multipartFileList.size(); i++) {
            boardService.fileUpload( boardDTO, fileDTO, multipartFileList.get( i ) );
        }

        return "redirect:/list";
    }

    // 게시글 목록
    @GetMapping("/list")
    public String boardList(PageDTO pageDTO, Model model, Principal principal, @RequestParam(value = "page", required = false) String nowPage
            , @RequestParam(value = "cntPerPage", required = false) String cntPerPage) {

        if (principal != null) {
            String username = principal.getName();
            UserDTO userDTO = userService.currentName( username );

            model.addAttribute( "principal", principal );
            model.addAttribute( "currentName", userDTO.getName() );
            model.addAttribute( "currentCompany", userDTO.getCompany() );
        }

        int total = boardService.countBoard();
        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "15";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "15";
        }

        pageDTO = new PageDTO( total, Integer.parseInt( nowPage ), Integer.parseInt( cntPerPage ) );
        model.addAttribute( "paging", pageDTO );
        model.addAttribute( "list", boardService.selectBoard( pageDTO ) );

//        model.addAttribute( "list", boardService.list( boardDTO ) );

        return "BoardList";
    }

    // 게시글 상세 페이지
    @GetMapping("/view/{id}")
    public String boardView(@PathVariable Integer id, Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute( "principal", principal.getName() );
        }
        model.addAttribute( "board", boardService.view( id ) );
        model.addAttribute( "file", boardService.filelist( id ) );

        return "BoardView";
    }

    // 첨부 파일 다운로드 처리
//    @RequestMapping("/fileDownload/{file}")
//    public void fileDownload(@PathVariable String file, HttpServletRequest request, HttpServletResponse response, LogDTO logDTO, Principal principal) throws IOException {
//
//            File f = new File( environment.getProperty( "file.filepath" ), file );
//            String fileorigname = boardService.fileDownload( file );
//            String username = principal.getName();
//            String ipaddress = request.getRemoteAddr();
//
//            response.setContentType( "application/download" );
//            response.setContentLength( (int) f.length() );
//            response.setHeader( "Content-disposition", "attachment;filename=\"" + fileorigname + "\"" );
//
//            OutputStream os = response.getOutputStream();
//            FileInputStream fis = new FileInputStream( f );
//            FileCopyUtils.copy( fis, os );
//            fis.close();
//            os.close();
//
//            try {
//                logService.downloadLog( logDTO, username, file, ipaddress );
//            } catch (Exception e) {
//                throw new RuntimeException( e );
//            }
//    }

    @RequestMapping("downloadZip/{id}")
    public void downloadZip(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, LogDTO logDTO, Principal principal) throws UnsupportedEncodingException, ParseException {

        String username = principal.getName();
        String ipaddress = request.getRemoteAddr();

        BoardDTO boardDTO = boardService.view(id);

        String title = boardDTO.getTitle();

        String file_expiry_date_s = boardDTO.getFile_expiry_date();
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
        Date file_expiry_date = formatter.parse( file_expiry_date_s );
        Date now = new Date();

        int downloadlimit = boardDTO.getDownload_limit();
        int downloadclick = boardDTO.getDownload_click();

        if (now.before( file_expiry_date )) {
            if ((downloadlimit - downloadclick) > 0) {

                response.setStatus( HttpServletResponse.SC_OK );
                response.setContentType( "application/zip" );
                response.setHeader( "Content-disposition", "attachment; filename=\"" + URLEncoder.encode( title, "UTF-8" ) + ".zip\"" );

                try {
                    ZipOutputStream zos = new ZipOutputStream( response.getOutputStream() );

                    List<FileDTO> fileDTO = boardService.filelist( id );

                    String filepath = environment.getProperty( "file.filepath" );

                    List<File> fileList = fileDTO.stream().map( fileInfo -> {
                        return new File( filepath + "/" + fileInfo.getFilename() );
                    } ).collect( Collectors.toList() );

                    for (File file : fileList) {
                        zos.putNextEntry( new ZipEntry( "test/" + file.getName().substring( 37 ) ) );
                        FileInputStream fis = new FileInputStream( file );

                        StreamUtils.copy( fis, zos );

                        fis.close();
                        zos.closeEntry();
                    }

                    zos.close();

                    try {
                        logService.downloadLog( logDTO, username, title, ipaddress );
                    } catch (Exception e) {
                        throw new RuntimeException( e );
                    }

                    boardService.updateDownloadClk( id );

                } catch (IOException e) {
                    throw new RuntimeException( e );
                }
            }
            else {
                try {
                    response.setContentType("text/html; charset=utf-8");
                    PrintWriter w = response.getWriter();
                    w.write("<script>alert('다운로드 횟수를 초과하였습니다.');history.go(-1);</script>");
                    w.flush();
                    w.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                w.write("<script>alert('다운로드 기한이 지났습니다.');history.go(-1);</script>");
                w.flush();
                w.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 게시글 삭제
    @GetMapping("/delete/{id}")
    public String boardDelete(@PathVariable Integer id, Model model) {
        boardService.delete( id );
        model.addAttribute( "message", "삭제되었습니다." );
        model.addAttribute( "url", "/list" );

        return "message";
    }

    // 게시글 수정
    @GetMapping("/modify/{id}")
    public String boardModify(@PathVariable Integer id, Model model) {
        model.addAttribute( "board", boardService.view( id ) );
        model.addAttribute( "file", boardService.filelist( id ) );

        return "BoardModify";
    }

    // 게시글 수정 프로세스
    @PostMapping("/update/{id}")
    public String boardUpdate(@PathVariable Integer id, BoardDTO boardDTO, FileDTO fileDTO, Model model, @RequestParam(value="fileDelList[]") String[] delFiles, @RequestParam("file") List<MultipartFile> multipartFileList, Principal principal) throws Exception {

//        BoardDTO boardTemp = (BoardDTO) boardService.view( id );
//        boardTemp.setTitle( boardDTO.getTitle() );
//        boardTemp.setMemo( boardDTO.getMemo() );

        boardService.update( boardDTO, delFiles );

        String fname = multipartFileList.get(0).getOriginalFilename();

        if(!fname.equals("")) {
            for (int i = 0; i < multipartFileList.size(); i++) {
                boardService.fileUpload( boardDTO, fileDTO, multipartFileList.get( i ) );
            }
        }

        model.addAttribute( "message", "글 수정이 완료되었습니다." );
        model.addAttribute( "url", "/view/" + id );

        return "message";
    }
    
    // 회원정보
    @GetMapping("/mypage")
    public String userInfo(Model model, Principal principal) {

        if (principal == null) {

            model.addAttribute("message", "접근권한이 없습니다.");
            return "message";
        }

        String username = principal.getName();

        model.addAttribute("userInfo", userService.userInfo( username ));

        return "UserInfo";
    }
}
