package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_type.model.Product_typeService;
import com.product_type.model.Product_typeVO;
import static java.util.Comparator.comparing;

@WebServlet("/product/ProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 3 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        List<ProductVO> carList = (session.getAttribute("carList") == null) ? new LinkedList<ProductVO>() : (List<ProductVO>) session.getAttribute("carList");
        List<Integer> countList = (session.getAttribute("countList") == null) ? new LinkedList<Integer>() : (List<Integer>) session.getAttribute("countList");
        int carTotal = 0;

        ///////////////////////////////////////////////////// 加入購物車/////////////////////////////////////////////////////////////////
        if ("ADD_TO_CAR".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            ProductVO productVO = (ProductVO) session.getAttribute("productVO");
            if (carList.contains(productVO)) {
                int count = countList.get(carList.indexOf(productVO)).intValue();
                count += Integer.valueOf(request.getParameter("product_account"));
                countList.set(carList.indexOf(productVO), new Integer(count));
            }
            else {
                carList.add(productVO);
                countList.add(new Integer(request.getParameter("product_account")));
            }
            for (int i = 0; i < carList.size(); i++) {
                carTotal += carList.get(i).getPrice() * countList.get(i);
            }
            session.setAttribute("carList", carList);
            session.setAttribute("countList", countList);
            session.setAttribute("carTotal", new Integer(carTotal));

            request.getRequestDispatcher(String.valueOf(session.getAttribute("location"))).forward(request, response);

        }
        ///////////////////////////////////////////////////// 刪除購物車一項物品/////////////////////////////////////////////////////////////////
        else if ("DELETE_FROM_CAR".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            String pro_no = request.getParameter("pro_no");
            for (int i = 0; i < carList.size(); i++) {
                if (pro_no.equals(carList.get(i).getPro_no())) {
                    carList.remove(i);
                    countList.remove(i);
                }
            }
            for (int i = 0; i < carList.size(); i++) {
                carTotal += carList.get(i).getPrice() * countList.get(i);
            }
            session.setAttribute("carList", carList);
            session.setAttribute("countList", countList);
            session.setAttribute("carTotal", new Integer(carTotal));
            request.getRequestDispatcher("/front_end/mall/checkout.jsp").forward(request, response);
        }
        ///////////////////////////////////////////////////// 清空購物車/////////////////////////////////////////////////////////////////
        else if ("CLEAR".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            if (carList != null) {
                carList.removeAll(carList);
                countList.removeAll(countList);
                carTotal = 0;
                session.setAttribute("carList", carList);
                session.setAttribute("countList", countList);
                session.setAttribute("carTotal", new Integer(carTotal));
            }
            request.getRequestDispatcher("/front_end/mall/mallIndex.jsp").forward(request, response);
        }
        ///////////////////////////////////////////////////// 申請上架商品/////////////////////////////////////////////////////////////////
        else if ("ADD".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            List<String> error = new ArrayList<String>();
            ProductVO productVO = new ProductVO();

            String seller_no = String.valueOf(session.getAttribute("mem_no"));
            String pro_desc = request.getParameter("pro_desc");
            String price = request.getParameter("price");
            String amount = request.getParameter("amount");
            String protype_no = request.getParameter("protype_no");
            String pro_name = request.getParameter("pro_name").trim();
            byte[] data = null;
            if (pro_name.equals("")) {
                error.add("請輸入商品名稱");
            }
            if (price.equals("")) {
                error.add("請輸入價格");
            }
            if (price.equals("")) {
                error.add("請輸入庫存數量");
            }
            if (protype_no == null) {
                error.add("請選擇商品種類");
            }
            Part part = request.getPart("img");
            if (part == null) {
                error.add("請上傳商品影像");
            }
            else {
                InputStream inputStream = part.getInputStream();
                data = new byte[inputStream.available()];
                inputStream.read(data);
            }

            if (!error.isEmpty()) {
                for (String msg : error) {
                    System.out.println(msg);
                }
                request.getRequestDispatcher("/front_end/mall/productManage.jsp").forward(request, response);
            }
            else {
                productVO.setPro_name(pro_name);
                productVO.setSeller_no(seller_no);
                productVO.setPro_desc(pro_desc);
                productVO.setPrice(Integer.valueOf(price));
                productVO.setAmount(Integer.valueOf(amount));
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                productVO.setPro_date(timestamp);
                productVO.setProtype_no(protype_no);
                productVO.setImg(data);
                productVO.setStatus("0");
                productVO.setTimes(0);
                productVO.setScore(0);

                ProductService productService = new ProductService();
                productService.addProduct(productVO);
                System.out.println("ADD SUCESS");
                request.getRequestDispatcher("/Front_end/mall/productManage.jsp").forward(request, response);
            }
        }
        else if ("ADD_AJAX".equals(action)) {
            response.setContentType("text/html; charset=utf-8");
            List<String> error = new ArrayList<String>();
            ProductVO productVO = new ProductVO();

            String seller_no = String.valueOf(session.getAttribute("mem_no"));
            String pro_desc = request.getParameter("pro_desc");
            String price = request.getParameter("price");
            String amount = request.getParameter("amount");
            String protype_no = request.getParameter("protype_no");
            String pro_name = request.getParameter("pro_name").trim();
            byte[] data = null;
            if (pro_name.equals("")) {
                error.add("請輸入商品名稱");
            }
            if (price.equals("")) {
                error.add("請輸入價格");
            }
            if (price.equals("")) {
                error.add("請輸入庫存數量");
            }
            if (protype_no == null) {
                error.add("請選擇商品種類");
            }
            Part part = request.getPart("img");
            if (part == null) {
                error.add("請上傳商品影像");
            }
            else {
                InputStream inputStream = part.getInputStream();
                data = new byte[inputStream.available()];
                inputStream.read(data);
            }

            if (!error.isEmpty()) {
                for (String msg : error) {
                    System.out.println(msg);
                }
                request.getRequestDispatcher("/Front_end/mall/productManage.jsp").forward(request, response);
            }
            else {
                productVO.setPro_name(pro_name);
                productVO.setSeller_no(seller_no);
                productVO.setPro_desc(pro_desc);
                productVO.setPrice(Integer.valueOf(price));
                productVO.setAmount(Integer.valueOf(amount));
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                productVO.setPro_date(timestamp);
                productVO.setProtype_no(protype_no);
                productVO.setImg(data);
                productVO.setStatus("0");
                productVO.setTimes(0);
                productVO.setScore(0);
                ProductService productService = new ProductService();
                productService.addProduct(productVO);
                
                PrintWriter printWriter=response.getWriter();
                printWriter.println("OK");
                printWriter.close();
            }
        }
        ///////////////////////////////////////////////////// 下架商品/////////////////////////////////////////////////////////////////
        else if ("OFF".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            String pro_no = request.getParameter("pro_no");
            List<ProductVO> productList = (List<ProductVO>) session.getAttribute("productList");
            ProductService productService = new ProductService();
            for (ProductVO productVO : productList) {
                if (productVO.getPro_no().equals(pro_no)) {
                    productVO.setStatus("2");
                    productService.updateProduct(productVO);
                    request.getRequestDispatcher("/Front_end/mall/productManage.jsp").forward(request, response);
                }
            }
        }
        ///////////////////////////////////////////////////// 上架商品/////////////////////////////////////////////////////////////////
        else if ("ON".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            String pro_no = request.getParameter("pro_no");
            List<ProductVO> productList = (List<ProductVO>) session.getAttribute("productList");
            ProductService productService = new ProductService();
            for (ProductVO productVO : productList) {
                if (productVO.getPro_no().equals(pro_no)) {
                    productVO.setStatus("1");
                    productService.updateProduct(productVO);
                    request.getRequestDispatcher("/front_end/mall/productManage.jsp").forward(request, response);
                }
            }
        }
        else if ("UPDATE".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            ProductService productService=new ProductService();
            String pro_no=request.getParameter("pro_no");
            ProductVO productVO = productService.getOneByPK(pro_no);
            if (!request.getParameter("pro_name").equals("")) {
                productVO.setPro_name(request.getParameter("pro_name"));
            }
            if (!request.getParameter("pro_desc").equals("")) {
                productVO.setPro_desc(request.getParameter("pro_desc"));
            }
            if (!request.getParameter("price").equals("")) {
                productVO.setPrice(Integer.valueOf(request.getParameter("price")));
            }
            if (!request.getParameter("amount").equals("")) {
                productVO.setAmount(Integer.valueOf(request.getParameter("amount")));
            }

            if (request.getPart("img") != null && (request.getPart("img").getContentType().indexOf("image") != -1)) {
                Part part = request.getPart("img");
                InputStream inputStream = part.getInputStream();
                byte[] data = new byte[inputStream.available()];
                inputStream.read(data);
                productVO.setImg(data);
            }
            productService.updateProduct(productVO);
            request.getRequestDispatcher("/front_end/mall/productManage.jsp").forward(request, response);
        }
        else if ("UPDATE_AJAX".equals(action)) {
            response.setContentType("text/html; charset=utf-8");
            ProductService productService=new ProductService();
            String pro_no=request.getParameter("pro_no");
            ProductVO productVO = productService.getOneByPK(pro_no);
            if (!request.getParameter("pro_name").equals("")) {
                productVO.setPro_name(request.getParameter("pro_name"));
            }
            if (!request.getParameter("pro_desc").equals("")) {
                productVO.setPro_desc(request.getParameter("pro_desc"));
            }
            if (!request.getParameter("price").equals("")) {
                productVO.setPrice(Integer.valueOf(request.getParameter("price")));
            }
            if (!request.getParameter("amount").equals("")) {
                productVO.setAmount(Integer.valueOf(request.getParameter("amount")));
            }

            if (request.getPart("img") != null && (request.getPart("img").getContentType().indexOf("image") != -1)) {
                Part part = request.getPart("img");
                InputStream inputStream = part.getInputStream();
                byte[] data = new byte[inputStream.available()];
                inputStream.read(data);
                productVO.setImg(data);
            }
            productService.updateProduct(productVO);
            PrintWriter printWriter=response.getWriter();
            printWriter.print("OK");
            printWriter.close();
        }
        ///////////////////////////////////////////////////// 審核商品/////////////////////////////////////////////////////////////////
        else if ("ABLE".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            ProductService productService = new ProductService();
            ProductVO productVO = productService.getOneByPK(request.getParameter("pro_no"));
            productVO.setStatus("1");
            productService.updateProduct(productVO);
            request.getRequestDispatcher("/back_end/productPreview.jsp").forward(request, response);
        }
        else if ("DISABLE".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            ProductService productService = new ProductService();
            ProductVO productVO = productService.getOneByPK(request.getParameter("pro_no"));
            productVO.setStatus("3");
            productService.updateProduct(productVO);
            request.getRequestDispatcher("/back_end/productPreview.jsp").forward(request, response);
        }
        ////////////////////////////////////////AJAX版換頁/////////////////////////////////////////////////////////////
        else if ("CHANGE_AJAX".equals(action)) {
            response.setContentType("text/html; charset=utf-8");
            int nowPage = Integer.valueOf(request.getParameter("nowPage"));
            int itemsCount = Integer.valueOf(request.getParameter("itemsCount"));
            String now_Pro_Type = request.getParameter("now_Pro_Type");
            String now_Order_Type = request.getParameter("now_Order_Type");
            ProductService productService = new ProductService();
            Product_typeService product_typeService = new Product_typeService();
            List<ProductVO> productList = productService.getSome(nowPage, itemsCount, now_Pro_Type, now_Order_Type);
            List<Product_typeVO> product_typeList = product_typeService.getAll();

            List<String> proTypeList = new ArrayList<String>();
            List<String> pronoList = new ArrayList<String>();
            List<String> pronameList = new ArrayList<String>();
            List<Integer> priceList = new ArrayList<Integer>();
            for (Product_typeVO product_typeVO : product_typeList) {
                proTypeList.add(product_typeVO.getType_name());
            }
            for (ProductVO productVO : productList) {
                pronoList.add(productVO.getPro_no());
                pronameList.add(productVO.getPro_name());
                priceList.add(productVO.getPrice());
            }
            int allCount = productService.getTypeAllCount(now_Pro_Type);
            int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
            session.setAttribute("productList", productList);

            PrintWriter printWriter = response.getWriter();
            Gson gson = new Gson();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("pronoList", gson.toJsonTree(pronoList));
            jsonObject.add("pronameList", gson.toJsonTree(pronameList));
            jsonObject.add("priceList", gson.toJsonTree(priceList));
            jsonObject.add("proTypeList", gson.toJsonTree(proTypeList));
            jsonObject.addProperty("totalPages", totalPages);
            printWriter.println(gson.toJson(jsonObject));
            printWriter.close();
        }
        else if ("CLEAR_AJAX".equals(action)) {
            response.setContentType("text/html; charset=utf-8");
            if (carList != null) {
                carList.removeAll(carList);
                countList.removeAll(countList);
                carTotal = 0;
                session.setAttribute("carList", carList);
                session.setAttribute("countList", countList);
                session.setAttribute("carTotal", new Integer(carTotal));
            }
        }
        else if ("ADD_TO_CAR_AJAX".equals(action)) {
            response.setContentType("text/html; charset=utf-8");
            ProductVO productVO = (ProductVO) session.getAttribute("productVO");
            if (carList.contains(productVO)) {
                int count = countList.get(carList.indexOf(productVO)).intValue();
                count += Integer.valueOf(request.getParameter("product_account"));
                countList.set(carList.indexOf(productVO), new Integer(count));
            }
            else {
                carList.add(productVO);
                countList.add(new Integer(request.getParameter("product_account")));
            }
            for (int i = 0; i < carList.size(); i++) {
                carTotal += carList.get(i).getPrice() * countList.get(i);
            }
            session.setAttribute("carList", carList);
            session.setAttribute("countList", countList);
            session.setAttribute("carTotal", new Integer(carTotal));
            PrintWriter printWriter = response.getWriter();
            printWriter.print(carTotal);
            printWriter.close();
        }
        else if ("CHANGE_LAMBDA".equals(action)) {
            response.setContentType("text/html; charset=utf-8");
            ProductService productService = new ProductService();
            List<ProductVO> orignList = productService.getAllNoDescAndImg();
            int nowPage = Integer.valueOf(request.getParameter("nowPage"));
            int itemsCount = Integer.valueOf(request.getParameter("itemsCount"));
            String now_Pro_Type = request.getParameter("now_Pro_Type");
            String now_Order_Type = request.getParameter("now_Order_Type");
            int allCount; 
            Comparator<ProductVO> byMethod = comparing(ProductVO::getPro_no);
            if(now_Pro_Type.equals("0")){
                allCount=orignList.size();
            }
            else{
                allCount=(int) orignList.stream().filter(product -> product.getProtype_no().equals(now_Pro_Type)).count();
                orignList=orignList.stream().filter(product -> product.getProtype_no().equals(now_Pro_Type)).collect(Collectors.toList());
            }
            
            if (now_Order_Type.equals("1")) {
                byMethod = comparing(ProductVO::getPro_name);
            }
            else if (now_Order_Type.equals("2")) {
                byMethod = comparing(ProductVO::getPro_date);
            }
            else if (now_Order_Type.equals("3")) {
                byMethod = comparing(ProductVO::getPro_date).reversed();
            }
            else if (now_Order_Type.equals("4")) {
                byMethod = comparing(ProductVO::getPrice);
            }
            else if (now_Order_Type.equals("5")) {
                byMethod = comparing(ProductVO::getPrice).reversed();
            }
            else if (now_Order_Type.equals("6")) {
                byMethod = comparing(ProductVO::getSeller_no);
            }
            int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
            orignList=orignList.stream().sorted(byMethod).collect(Collectors.toList());
            List<ProductVO>productList=new ArrayList<ProductVO>();
            int start=(nowPage-1)*itemsCount;
            int end=(nowPage*itemsCount>orignList.size())?orignList.size():nowPage*itemsCount;
            for(int i=start;i<end;i++){
                productList.add(orignList.get(i));
            }
            Product_typeService product_typeService = new Product_typeService();
            List<Product_typeVO> product_typeList = product_typeService.getAll();
            PrintWriter printWriter=response.getWriter();
            Gson gson=new Gson();
            JsonObject jsonObject=new JsonObject();
            jsonObject.add("productList", gson.toJsonTree(productList));
            jsonObject.add("totalPages", gson.toJsonTree(totalPages));
            jsonObject.add("product_typeList", gson.toJsonTree(product_typeList));
            printWriter.print(gson.toJson(jsonObject));
            printWriter.close();
        }
        else if ("onOrOff".equals(action)) {
            response.setContentType("text/html; charset=utf-8");
            String pro_no=request.getParameter("pro_no");
            ProductService productService =new ProductService();
            ProductVO productVO=productService.getOneByPK(pro_no);
            if(productVO.getStatus().equals("2")){
                productVO.setStatus("1");
            }
            else if(productVO.getStatus().equals("1")){
                productVO.setStatus("2");
            }
            productService.updateProduct(productVO);
            PrintWriter printWriter =response.getWriter();
            printWriter.println(productVO.getStatus());
            System.out.println(productVO.getStatus());
            printWriter.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected List<ProductVO> filterAndOrder(List<ProductVO> orginList,String protype,String ordertype) {
        return null;
    }
}
