package com.ssafy.controller;

import java.io.*;
import java.util.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.dto.AttractionInfo;
import com.ssafy.dto.Gugun;
import com.ssafy.service.TripInfoService;
import com.ssafy.service.TripInfoServiceImpl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/tripinfo")
public class TripInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TripInfoService tripInfoService = TripInfoServiceImpl.getTripInfoService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String path = "";
		
		if("sidoList".equals(action)) {
			path = sidoList(request, response);
			forward(request, response, path);
		}else if("gugunList".equals(action)) {
			gugunList(request, response);//Gugun JSON객체리스트 만들어서 리턴
		}else if("searchAttraction".equals(action)) {
			searchAttraction(request, response);
		}else if("registHotplace".equals(action)) {
			registHotplace(request, response);
		}
	}

	
	private void registHotplace(HttpServletRequest request, HttpServletResponse response) {
		int contentId = Integer.parseInt(request.getParameter("contentId"));
		try {
			AttractionInfo info = tripInfoService.getAttractionInfo(contentId);
			boolean isComplete = tripInfoService.registHotplace(info);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void searchAttraction(HttpServletRequest request, HttpServletResponse response)  throws IOException {
		int sidoCode = Integer.parseInt(request.getParameter("sidoCode"));
		int gugunCode = Integer.parseInt(request.getParameter("gugunCode"));
		int contentTypeId = Integer.parseInt(request.getParameter("contentTypeId"));
		String keyword = request.getParameter("keyword");
				
		try {
			List<AttractionInfo> list = tripInfoService.findTripInfo(sidoCode, gugunCode, contentTypeId, keyword);			
			JSONArray jArray = new JSONArray();//배열이 필요할때
			List<Map<String, Object>> mapList = new ArrayList<>();
			for(AttractionInfo info: list) {
				JSONObject json = new JSONObject();
				
				json.put("contentTypeId", info.getContentTypeId());
				json.put("contentId", info.getContentId());
				json.put("title", info.getTitle());
				json.put("addr1", info.getAddr1());
				json.put("addr2", info.getAddr2());
				json.put("zipcode", info.getZipcode());
				json.put("tel", info.getTel());
				json.put("firstImage", info.getFirstImage());
				json.put("firstImage2", info.getFirstImage2());
				json.put("readCount", info.getReadCount());
				json.put("sidoCode", info.getSidoCode());
				json.put("gugunCode", info.getGugunCode());
				json.put("latitude", info.getLatitude());
				json.put("longitude", info.getLongitude());
				jArray.add(json);
			}			
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jArray);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void gugunList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int sidoCode = Integer.parseInt(request.getParameter("sidoCode"));

		try {
			List<Gugun> list = tripInfoService.getGugun(sidoCode);
			JSONArray jArray = new JSONArray();//배열이 필요할때
			List<Map<String, Object>> mapList = new ArrayList<>();
			for(Gugun gugun: list) {
				JSONObject json = new JSONObject();
				json.put("gugunCode", gugun.getGugunCode());
				json.put("gugunName", gugun.getGugunName());
				json.put("sidoCode", gugun.getSidoCode());
				jArray.add(json);
			}			
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jArray);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private String sidoList(HttpServletRequest request, HttpServletResponse response){

		try {
			request.setAttribute("sidoList", tripInfoService.getSido());
			request.setAttribute("allGugunList", tripInfoService.getAllGugun());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "/tripInfo.jsp";
	}


	private void forward(HttpServletRequest request, HttpServletResponse response, String path) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
