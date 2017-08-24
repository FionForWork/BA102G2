package com.place.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.engine.SessionImplementor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.com.model.ComService;
import com.com.model.ComVO;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;
import com.placeview.model.PlaceViewService;
import com.placeview.model.PlaceViewVO;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.javafx.geom.PickRay;

import oracle.net.aso.i;
import sun.nio.cs.ext.MacArabic;
import sun.tools.jar.resources.jar;

@WebServlet("/place/PlaceServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 3 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PlaceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("MAP_CHANGE".equals(action)) {
            String south = request.getParameter("south");
            String west = request.getParameter("west");
            String north = request.getParameter("north");
            String east = request.getParameter("east");
            JSONArray resultArray=new JSONArray();
            if("place".equals(request.getParameter("type"))){
                PlaceService placeService = new PlaceService();
                List<PlaceVO> placeList = placeService.getRange(south, west, north, east);
                for (PlaceVO placeVO : placeList) {
                    Set<PlaceViewVO> placeViewSet = placeVO.getPlaceViewSet();
                    if(placeViewSet.size()!=0){
                        Iterator<PlaceViewVO> iterator = placeViewSet.iterator();
                        PlaceViewVO placeViewVO = (PlaceViewVO) iterator.next();
                        HashMap<String, String> map=new HashMap<String, String>();
                        map.put("pla_no", placeVO.getPla_no());
                        map.put("name", placeVO.getName());
                        map.put("lat", placeVO.getLat());
                        map.put("lng", placeVO.getLng());
                        map.put("view_no", placeViewVO.getView_no());
                        resultArray.put(map);
                    }
                }
            }
            else if("com".equals(request.getParameter("type"))){
                ComService comService=new ComService();
                List<ComVO> comList=comService.getLocation(west, east, south, north);
                for (ComVO comVO : comList) {
                    HashMap<String, String> map=new HashMap<String, String>();
                    map.put("com_no", comVO.getCom_no());
                    map.put("name", comVO.getName());
                    map.put("lat", comVO.getLat());
                    map.put("lng", comVO.getLon());
                    map.put("desc", comVO.getCom_desc());
                    map.put("phone", comVO.getPhone());
                    map.put("loc", comVO.getLoc());
                    resultArray.put(map);
                }
            }
            
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(resultArray.toString());
            printWriter.close();
        }
        else if ("MANAGEMENT_CHANGE".equals(action)) {
            int nowPage = Integer.valueOf(request.getParameter("nowPage"));
            int itemsCount = Integer.valueOf(request.getParameter("itemsCount"));
            PlaceService placeService = new PlaceService();
            List<PlaceVO> placeList = placeService.getPage(nowPage, itemsCount);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("placeList", placeList);
                printWriter.println(jsonObject);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            printWriter.close();
        }
        else if ("ADD_PLACE".equals(action)) {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("text/html; charset=utf-8");
            String name = new String(request.getParameter("addName").getBytes("ISO-8859-1"), "utf-8");
            String addr = new String(request.getParameter("addAddr").getBytes("ISO-8859-1"), "utf-8");
            String desc = new String(request.getParameter("addDesc").getBytes("ISO-8859-1"), "utf-8");
            PlaceService placeService = new PlaceService();
            PlaceVO placeVO = new PlaceVO();
            Collection<Part> parts = request.getParts();
            Set<PlaceViewVO> viewSet = new LinkedHashSet<PlaceViewVO>();
            for (Part part : parts) {
                if (part.getContentType() != null) {
                    if (part.getName().equals("addImg")) {
                        InputStream inputStream = part.getInputStream();
                        PlaceViewVO placeViewVO = new PlaceViewVO();
                        placeViewVO.setPlaceVO(placeVO);
                        placeViewVO.setImg(toByteArray(inputStream));
                        viewSet.add(placeViewVO);
                    }
                }
            }
            placeVO.setPlaceViewSet(viewSet);
            placeVO.setName(name);
            placeVO.setAddr(addr);
            placeVO.setPla_desc(desc);
            String preUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=";
            String endUrl = "&sensor=false&language=zh-TW&key=AIzaSyBzbntmAuGW16US8FK_QIoDNXOPlspRjNw";
            URL url = null;
            url = new URL(preUrl + addr + endUrl);
            URLConnection urlConnection = url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream(), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                stringBuffer.append(read);
            }
            try {
                JSONObject jsonObject = new JSONObject(stringBuffer.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                String lng = jsonArray.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng").toString();
                String lat = jsonArray.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat").toString();
                placeVO.setLat(lat);
                placeVO.setLng(lng);

            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            placeService.insertPlace(placeVO);
            request.getRequestDispatcher("/Back_end/place/placeManagement.jsp").forward(request, response);
        }
        else if ("DELETE".equals(action)) {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            String pla_no = request.getParameter("pla_no");
            PlaceService placeService = new PlaceService();
            placeService.deletePlace(pla_no);
            PrintWriter printWriter=response.getWriter();
            printWriter.print("ok");
            printWriter.close();
        }
        else if ("UPDATE_PLACE".equals(action)) {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("text/html; charset=utf-8");
            String pla_no = request.getParameter("pla_no");
            PlaceService placeService=new PlaceService();
            PlaceVO placeVO=placeService.getOnePlace(pla_no);
            Collection<Part> parts = null;
            Set<PlaceViewVO>placeViewSet=placeVO.getPlaceViewSet();
            if (request.getParts() != null) {
                parts = request.getParts();
                for (Part part : parts) {
                    if (part.getContentType() != null) {
                        if (part.getName().equals("updateImg") && part.getSize() != 0) {
                            InputStream inputStream = part.getInputStream();
                            PlaceViewVO placeViewVO = new PlaceViewVO();
                            placeViewVO.setPlaceVO(placeVO);
                            placeViewVO.setImg(toByteArray(inputStream));
                            placeViewSet.add(placeViewVO);
                        }
                    }
                }
            }
            placeVO.setPlaceViewSet(placeViewSet);
            if (!request.getParameter("updateName").equals("")) {
                String name = new String(request.getParameter("updateName").getBytes("ISO-8859-1"), "utf-8");
                placeVO.setName(name);
            }
            if (!request.getParameter("updateAddr").equals("")) {
                String addr = new String(request.getParameter("updateAddr").getBytes("ISO-8859-1"), "utf-8");
                String preUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=";
                String endUrl = "&sensor=false&language=zh-TW&key=AIzaSyBzbntmAuGW16US8FK_QIoDNXOPlspRjNw";
                URL url = null;
                url = new URL(preUrl + addr + endUrl);
                URLConnection urlConnection = url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream(), "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                String read;
                while ((read = bufferedReader.readLine()) != null) {
                    stringBuffer.append(read);
                }
                try {
                    JSONObject jsonObject = new JSONObject(stringBuffer.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    String lng = jsonArray.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng").toString();
                    String lat = jsonArray.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat").toString();
                    placeVO.setAddr(addr);
                    placeVO.setLat(lat);
                    placeVO.setLng(lng);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            if (!request.getParameter("updateDesc").equals("")) {
                String desc = new String(request.getParameter("updateDesc").getBytes("ISO-8859-1"), "utf-8");
                placeVO.setPla_desc(desc);
            }
            placeService.updatePlace(placeVO);
            request.getRequestDispatcher("/Back_end/place/placeUpdate.jsp?pla_no=" + pla_no).forward(request, response);
        }
        else if("DELETE_PLACEVIEW".equals(action)){
            PlaceViewService placeViewService=new PlaceViewService();
            placeViewService.deleteByFK(request.getParameter("pla_no"));
            PrintWriter printWriter=response.getWriter();
            printWriter.println("OK");
            printWriter.close();
        }
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
