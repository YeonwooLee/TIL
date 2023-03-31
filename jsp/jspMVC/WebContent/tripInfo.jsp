<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
  <%@ include file="head.jsp" %>
  <body>
  	<%@ include file="header.jsp" %>
    <main class="container">
      <div class="center-content">
        <div class="col-md-9" style="width: 80%; margin: 0 auto">
          <div class="alert alert-primary mt-3 text-center fw-bold" role="alert">전국 관광지 정보</div>
          <!-- 관광지 검색 start -->
          <form class="d-flex my-3" onsubmit="return false;" role="search">
           	<select name="search-sido" id="search-sido" onchange=changeSido() class="form-select me-2">
				<option value="0" selected>시/도</option> 
			  	<c:forEach items="${sidoList }" var="sido">
		  		<option value="${sido.sidoCode}">${sido.sidoName }</option>
			 	</c:forEach>
			</select>
			<select name="search-gugun" id="search-gugun" class="form-select me-2">
				<option value="0" selected>구/군</option>
			</select>
            <select id="search-content-id" class="form-select me-2">
              <option value="0" selected>관광지 유형</option>
              <option value="12">관광지</option>
              <option value="32">숙박</option>
              <option value="39">음식점</option>
              <option value="14">문화시설</option>
              <option value="15">축제공연행사</option>
              <option value="25">여행코스</option>
              <option value="38">쇼핑</option>
              <option value="28">레포츠</option>
            </select>
            <input id="search-keyword" class="form-control me-2" type="search" placeholder="검색어" aria-label="검색어" />
            <button id="btn-search" class="btn btn-outline-success" type="button">검색</button>
          </form>
          <!-- kakao map start -->
          <div id="map" class="mt-3" style="width: 100%; height: 500px;"></div>
          <!-- kakao map end -->
          <div class="row">
            <table id="table-info" class="table table-striped" style="display: none">
              <thead>
                <tr>
                  <th>대표이미지</th>
                  <th>관광지명</th>
                  <th>주소</th>
                  <th>위도</th>
                  <th>경도</th>
                </tr>
              </thead>
              <tbody id="trip-list"></tbody>
            </table>
          </div>
          <!-- 관광지 검색 end -->
        </div>
      </div>
    </main>
    <footer class="footer">
      <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">개인정보 처리방침</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">이용 약관</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">오시는 길</a></li>
      </ul>
      <p class="text-center text-muted">&copy; SSAFY</p>
    </footer>
	﻿<%@ include file="loginModal.jsp" %>
	﻿<%@ include file="signUpModal.jsp" %>
    <script type="text/javascript" src="assets/js/trip.js"></script> 
   	<script type="text/javascript" src="assets/js/login.js"></script> 
  </body>
</html>

