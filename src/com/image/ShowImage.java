package com.image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.membership.Membership;

import com.com.model.ComService;
import com.com.model.ComVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.placeview.model.PlaceViewService;
import com.placeview.model.PlaceViewVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.sun.accessibility.internal.resources.accessibility;
import com.sun.javafx.image.impl.ByteIndexed.ToByteBgraAnyConverter;

import jdk.internal.org.xml.sax.InputSource;

@WebServlet("/image/ShowImage")
public class ShowImage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("image/gif");
        byte[] buf=null;
        ServletOutputStream servletOutputStream = response.getOutputStream();
        if (request.getParameter("pro_no") != null) {
            ProductService productService = new ProductService();
            String pro_no = request.getParameter("pro_no");
            ProductVO productVO=productService.getOneByPK(pro_no);
            buf=productVO.getImg();
        }
        else if (request.getParameter("view_no") != null) {
            PlaceViewService placeViewService = new PlaceViewService();
            String view_no = request.getParameter("view_no");
            PlaceViewVO placeViewVO =placeViewService.getOneByPK(view_no);
            buf=placeViewVO.getImg();
        }
        else if(request.getParameter("mem_no") != null){
            MemService memService=new MemService();
            String mem_no=request.getParameter("mem_no");
            MemVO memVO=memService.getOneMem(mem_no);
            buf=memVO.getPicture();
        }
        else if(request.getParameter("com_no") != null){
            ComService comService=new ComService();
            String com_no=request.getParameter("com_no");
            ComVO comVO=comService.getOneCom(com_no);
            buf=comVO.getLogo();
        }
        if(buf==null){
            String noImgPath="/Front_end/Resource/img/noImg.gif";
            FileInputStream fileInputStream=new FileInputStream(getServletContext().getRealPath(noImgPath));
            buf=toByteArray(fileInputStream);
        }
        servletOutputStream.write(buf);
        servletOutputStream.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = 0;
        byte[] bytes = new byte[1024];
        while ((length = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, length);
        }
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
}
