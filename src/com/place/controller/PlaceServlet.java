package com.place.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;
import com.placeview.model.PlaceViewService;

@WebServlet("/place/PlaceServlet")
public class PlaceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public PlaceServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if ("AJAX".equals(action)) {
            String south = request.getParameter("south");
            String west = request.getParameter("west");
            String north = request.getParameter("north");
            String east = request.getParameter("east");

            PlaceService placeService = new PlaceService();
            PlaceViewService placeViewService =new PlaceViewService();
            List<PlaceVO> placeList = placeService.getSome(south, west, north, east);
            List<String> viewnoList = new ArrayList<String>();
            if(placeList!=null){
                for (int i = 0; i < placeList.size(); i++) {
                    List<String>viewnoListOrigin=placeViewService.getAllByFk(placeList.get(i).getPla_no());
                    viewnoList.add(viewnoListOrigin.get(0));
                }
            }
            Gson gson = new Gson();
            JsonObject jsonObject=new JsonObject();
            jsonObject.add("placeList", gson.toJsonTree(placeList));
            jsonObject.add("viewnoList", gson.toJsonTree(viewnoList));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
//            List<String> viewnoList = new ArrayList<String>();
//            List<String> planoList = new ArrayList<String>();
//            List<String> planameList = new ArrayList<String>();
//            List<String> latList = new ArrayList<String>();
//            List<String> lngList = new ArrayList<String>();
//            response.setContentType("text/html;charset=utf-8");
//            PrintWriter printWriter = response.getWriter();
//            if (placeList != null) {
//                for (int i = 0; i < placeList.size(); i++) {
//                    List<String>viewnoListOrigin=placeViewService.getAllByFk(placeList.get(i).getPla_no());
//                    planoList.add(placeList.get(i).getPla_no());
//                    viewnoList.add(viewnoListOrigin.get(i));
//                    planameList.add(placeList.get(i).getName());
//                    latList.add(placeList.get(i).getLat());
//                    lngList.add(placeList.get(i).getLng());
//                }
//                session.setAttribute("placeList", placeList);
//                
//                Gson gson = new Gson();
//                JsonObject jsonObject = new JsonObject();
//                jsonObject.add("planoList", gson.toJsonTree(planoList));
//                jsonObject.add("viewnoList", gson.toJsonTree(viewnoList));
//                jsonObject.add("planameList", gson.toJsonTree(planameList));
//                jsonObject.add("latList", gson.toJsonTree(latList));
//                jsonObject.add("lngList", gson.toJsonTree(lngList));
                printWriter.println(gson.toJson(jsonObject));
                printWriter.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
