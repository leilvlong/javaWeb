package com.tomcatservlet.start;






import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import org.apache.commons.io.IOUtils;

@WebServlet("/uptest")
@MultipartConfig
public class UPServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Part upfile = request.getPart("upfile");
        String submittedFileName = upfile.getSubmittedFileName();
        ServletContext servletContext = getServletContext();
        //String dwl = servletContext.getRealPath("dwl");
        URL dwl = servletContext.getResource("dwl");
        String path = dwl.getPath();
        System.out.println(servletContext.getResource("TWL"));

        upfile.write(path+submittedFileName);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());


        //1.获取请求参数filename的值
        String filename = request.getParameter("filename");
        //使用ServletContext动态获取资源文件的路径
        ServletContext servletContext = getServletContext();
        InputStream is = servletContext.getResourceAsStream("dwl/" + filename);

        // 对浏览器处理
        String encodeFilname = null;
        String header = request.getHeader("user-agent");
        if (header.contains("Firefox")) {
            //我们应该使用Base64进行编码
            encodeFilname = base64Encode(filename);
        } else {
            //非火狐浏览器都是使用的URLDecoder进行解码的，所以能够将URLEncoder编码的内容解码
            encodeFilname = URLEncoder.encode(filename, "UTF-8");
        }
        // 设置header
        response.setHeader("content-disposition", "attachment;filename=" + encodeFilname);

        ServletOutputStream out = response.getOutputStream();

        /*int len;
        for(byte[] bytes=new byte[1024*8]; -1 != (len=is.read(bytes));){
            out.write(bytes,0,len);
        }*/
        IOUtils.copy(is,out);

        //关流
        out.close();
        is.close();
    }

    //使用Base64对中文字符串进行编码
    private String base64Encode(String fileName) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        fileName = encoder.encodeToString(fileName.getBytes("UTF-8"));
        fileName = "=?utf-8?B?" + fileName + "?=";
        return fileName;
    }
}
