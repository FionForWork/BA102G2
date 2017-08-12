package com.place.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.jni.Local;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.mysql.jdbc.Buffer;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;
import com.placeview.model.PlaceViewService;
import com.placeview.model.PlaceViewVO;
import com.sun.org.apache.xml.internal.security.utils.UnsyncBufferedOutputStream;
import com.sun.xml.internal.bind.Locatable;

@WebServlet("/place/PlaceServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 3 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PlaceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("AJAX".equals(action)) {
            String south = request.getParameter("south");
            String west = request.getParameter("west");
            String north = request.getParameter("north");
            String east = request.getParameter("east");

            PlaceService placeService = new PlaceService();
            PlaceViewService placeViewService = new PlaceViewService();
            List<PlaceVO> placeList = placeService.getRange(south, west, north, east);
            List<String> viewnoList = new ArrayList<String>();
            if (placeList != null) {
                for (int i = 0; i < placeList.size(); i++) {
                    List<String> viewnoListOrigin = placeViewService.getAllByFk(placeList.get(i).getPla_no());
                    viewnoList.add(viewnoListOrigin.get(0));
                }
            }
            Gson gson = new Gson();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("placeList", gson.toJsonTree(placeList));
            jsonObject.add("viewnoList", gson.toJsonTree(viewnoList));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(gson.toJson(jsonObject));
            printWriter.close();
        }
        else if ("CHANGE_AJAX".equals(action)) {
            int nowPage = Integer.valueOf(request.getParameter("nowPage"));
            int itemsCount = Integer.valueOf(request.getParameter("itemsCount"));
            PlaceService placeService = new PlaceService();
            List<PlaceVO> placeList = placeService.getPage(nowPage, itemsCount);
            Gson gson = new Gson();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(gson.toJson(placeList));
            printWriter.close();
        }
        else if ("ADD_PLACE".equals(action)) {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("text/html; charset=utf-8");
            String name = new String(request.getParameter("addName").getBytes("ISO-8859-1"), "utf-8");
            String addr = new String(request.getParameter("addAddr").getBytes("ISO-8859-1"), "utf-8");
            String desc = new String(request.getParameter("addDesc").getBytes("ISO-8859-1"), "utf-8");
            Collection<Part> parts = request.getParts();
            List<PlaceViewVO> viewList = new ArrayList<PlaceViewVO>();
            for (Part part : parts) {
                if (part.getContentType() != null) {
                    if (part.getName().equals("addImg")) {
                        InputStream inputStream = part.getInputStream();
                        PlaceViewVO placeViewVO = new PlaceViewVO();
                        placeViewVO.setImg(toByteArray(inputStream));
                        viewList.add(placeViewVO);
                    }
                }
            }
            PlaceVO placeVO = new PlaceVO();
            PlaceService placeService = new PlaceService();
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

            placeService.addPlace(placeVO, viewList);
            request.getRequestDispatcher("/Back_end/place/placeManagement.jsp").forward(request, response);
        }
        else if ("DELETE".equals(action)) {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("text/html; charset=utf-8");
            String pla_no = request.getParameter("pla_no");
            PlaceService placeService = new PlaceService();
            placeService.deletePlace(pla_no);
            request.getRequestDispatcher("/Back_end/place/placeManagement.jsp").forward(request, response);
        }
        else if ("UPDATE_PLACE".equals(action)) {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("text/html; charset=utf-8");
            String pla_no = request.getParameter("pla_no");
            Collection<Part> parts = null;
            PlaceViewService placeViewService = new PlaceViewService();
            if (request.getParts() != null) {
                parts = request.getParts();
                for (Part part : parts) {
                    if (part.getContentType() != null) {
                        if (part.getName().equals("updateImg")&&part.getSize()!=0) {
                            InputStream inputStream = part.getInputStream();
                            PlaceViewVO placeViewVO = new PlaceViewVO();
                            placeViewVO.setPla_no(pla_no);
                            placeViewVO.setImg(toByteArray(inputStream));
                            placeViewService.insert(placeViewVO);
                        }
                    }
                }
            }

            PlaceService placeService = new PlaceService();
            PlaceVO placeVO = placeService.getOnePlace(pla_no);
            if (!request.getParameter("updateName").equals("")) {
                String name = new String(request.getParameter("updateName").getBytes("ISO-8859-1"), "utf-8");
                placeVO.setName(name);
            }
            if (!request.getParameter("updateAddr").equals("")) {
                String addr = new String(request.getParameter("updateAddr").getBytes("ISO-8859-1"), "utf-8");
                System.out.println(addr);
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

            }
            if (!request.getParameter("updateDesc").equals("")) {
                String desc = new String(request.getParameter("updateDesc").getBytes("ISO-8859-1"), "utf-8");
                placeVO.setPla_desc(desc);
            }
            placeService.updatePlace(placeVO);
            request.getRequestDispatcher("/Back_end/place/placeUpdate.jsp?pla_no=" + pla_no).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private byte[] toByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length = 0;
        byte[] bytes = new byte[1024];
        while ((length = is.read(bytes)) != -1) {
            baos.write(bytes, 0, length);
        }
        baos.close();
        return baos.toByteArray();
    }

}
