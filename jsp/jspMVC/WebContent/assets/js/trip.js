var marker, infowindow;
var container = document.getElementById("map");
var myLatLng = new kakao.maps.LatLng(37.5012743, 127.039585);
var options = {
  center: myLatLng,
  level: 3,
};

var root = "http://localhost:8080/board6_final";

var temp = "";
var map = new kakao.maps.Map(container, options);

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

// HTML5의 geolocation으로 사용할 수 있는지 확인합니다
if (navigator.geolocation) {
  // GeoLocation을 이용해서 접속 위치를 얻어옵니다
  navigator.geolocation.getCurrentPosition(function (position) {
    var lat = position.coords.latitude, // 위도
      lon = position.coords.longitude; // 경도

    (myLatLng = new kakao.maps.LatLng(lat, lon)), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
      (message = '<div style="padding:5px;">나여기있어요</div>'); // 인포윈도우에 표시될 내용입니다

    // 마커와 인포윈도우를 표시합니다
    displayMarker(myLatLng, message);
  });
} else {
  // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

  (myLatLng = new kakao.maps.LatLng(37.5012743, 127.039585)),
    (message = "geolocation을 사용할수 없어요..");

  displayMarker(myLatLng, message);
}

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(myLatLng, message, markerImage) {
  // 마커를 생성합니다
  marker = new kakao.maps.Marker({
    map: map,
    position: myLatLng,
    image: markerImage, // 마커이미지 설정
  });

  var iwContent = message, // 인포윈도우에 표시할 내용
    iwRemoveable = true;

  // 인포윈도우를 생성합니다
  infowindow = new kakao.maps.InfoWindow({
    content: iwContent,
    removable: iwRemoveable,
  });

  // 인포윈도우를 마커위에 표시합니다
  // infowindow.open(map, marker);

  // 지도 중심좌표를 접속위치로 변경합니다
  map.setCenter(myLatLng);

  // 마커에 마우스오버 이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "mouseover", function () {
    // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
    infowindow.open(map, marker);
  });

  // 마커에 마우스아웃 이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "mouseout", function () {
    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
    infowindow.close();
  });
}

// 지점 선택시 지도 이동.
let offices = document.querySelectorAll(".accordion-body");
offices.forEach(function (office) {
  office.addEventListener("click", function () {
    viewMarker(this);
  });
});

function viewMarker(office) {
  officeLatLng = officePosition[office.textContent];
  marker.setMap(null);
  myLatLng = new kakao.maps.LatLng(officeLatLng.lat, officeLatLng.lng);
  let message = `<div style="padding:5px;">${office.textContent}</div>`;
  const imageSrc = "../assets/img/ssafy_logo.png"; // 마커이미지의 주소입니다
  let imageSize = new kakao.maps.Size(50, 54); // 마커이미지의 크기입니다
  let imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

  // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
  var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

  displayMarker(myLatLng, message, markerImage);
}

function changeSido(){
	let selectSido = document.querySelector("#search-sido").value;
	let selextBox = document.querySelector("#search-gugun");
	fetch(root+"/tripinfo?action=gugunList&sidoCode="+selectSido)
	.then((response) => response.json())
	.then((data) => makeOption(data));
}

function makeOption(data) {
  	let sel = document.querySelector("#search-gugun");
  	sel.innerHTML = '<option value="0" selected>구/군</option>';
  	data.forEach((gugun) => {
	    let opt = document.createElement("option");
	    opt.setAttribute("value", gugun.gugunCode);
	    opt.appendChild(document.createTextNode(gugun.gugunName));
	    sel.appendChild(opt);
	  });
}



// 검색 버튼을 누르면..
// 지역, 유형, 검색어 얻기.
// 위 데이터를 가지고 공공데이터에 요청.
// 받은 데이터를 이용하여 화면 구성.
document.getElementById("btn-search").addEventListener("click", () => {
  let searchUrl = `http://localhost:8080/board6_final/tripinfo?action=searchAttraction`;

  let sidoCode = document.getElementById("search-sido").value;
  let gugunCode = document.getElementById("search-gugun").value;
  let contentTypeId = document.getElementById("search-content-id").value;
  let keyword = document.getElementById("search-keyword").value;
  
  searchUrl += `&sidoCode=${sidoCode}`;
  searchUrl += `&gugunCode=${gugunCode}`;
  searchUrl += `&contentTypeId=${contentTypeId}`;
  searchUrl += `&keyword=${keyword}`;
  
  fetch(searchUrl)
    .then((response) => response.json())
    .then((data) => makeList(data));
});

var positions; // marker 배열.
var imgs; // img 배열
function makeList(data) {
  document.querySelector("#table-info").setAttribute("style", "display: ;");
  let trips = data;
  let tripList = ``;
  positions = [];
  imgs = [];
  trips.forEach((area) => {
    imgs.push(area.firstImage);
    tripList += `
      <tr onclick="moveCenter(${area.latitude}, ${area.longitude});">
        <td><img src="${area.firstImage}" width="100px"></td>
        <td>${area.title}</td>
        <td>${area.addr1} ${area.addr2}</td>
        <td>${area.latitude}</td>
        <td>${area.longitude}</td>
      </tr>
    `;
    
	console.log(area);
	let markerInfo = {
		addr1: area.addr1,
		addr2: area.addr2,
		contentId: area.contentId,
		contentTypeId: area.contentTypeId,
		firstImage: area.firstImage,
		firstImage2: area.firstImage2,
		gugunCode: area.gugunCode,
		sidoCode: area.sidoCode,
		title: area.title,
		latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
    };
    positions.push(markerInfo);
  });
  document.getElementById("trip-list").innerHTML = tripList;
  displayMarker();
}

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
    level: 5, // 지도의 확대 레벨
  };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

function getInfo() {
  // 지도의 현재 중심좌표를 얻어옵니다 
  var center = map.getCenter();

  // 지도의 현재 레벨을 얻어옵니다
  var level = map.getLevel();

  // 지도타입을 얻어옵니다
  var mapTypeId = map.getMapTypeId();

  // 지도의 현재 영역을 얻어옵니다 
  var bounds = map.getBounds();

  // 영역의 남서쪽 좌표를 얻어옵니다 
  var swLatLng = bounds.getSouthWest();

  // 영역의 북동쪽 좌표를 얻어옵니다 
  var neLatLng = bounds.getNorthEast();

  // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
  var boundsStr = bounds.toString();

  var info = {
    center: center,
    level: level,
    mapTypeId: mapTypeId,
    bounds: bounds,
    swLatLng: swLatLng,
    neLatLng: neLatLng,
    boundsStr: boundsStr
  }

  var message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
  message += '경도 ' + center.getLng() + ' 이고 <br>';
  message += '지도 레벨은 ' + level + ' 입니다 <br> <br>';
  message += '지도 타입은 ' + mapTypeId + ' 이고 <br> ';
  message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
  message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';

  return info;
}

// 마우스 드래그로 지도 이동이 완료되었을 때 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'dragend', function () {

  // 지도 중심좌표를 얻어옵니다 
  var latlng = map.getCenter();
  // 드래그 이동시 해당 맵 좌표 얻어오기
  var info = getInfo();
  // 주소-좌표 변환 객체를 생성합니다
  var geocoder = new kakao.maps.services.Geocoder();
});


function displayMarker() {
  // // 마커 이미지의 이미지 주소입니다
  // var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
  if (positions == undefined) {
    return;
  }

  for (var i = 0; i < positions.length; i++) {
    // 마커 이미지의 이미지 크기 입니다
    var imageSrc = imgs[i];
    if (imageSrc == "") {
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
      });
    } else {
      var imageSize = new kakao.maps.Size(40, 40);
      imageOption = { offset: new kakao.maps.Point(positions[i].latlng) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커 이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(imgs[i], imageSize);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
      });
    }


    // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    var content =	
    	`<div class="row customoverlay">` +
	    		`<div class= "col" ><button id="btn-hotplace" class="btn btn-danger" type="button" onclick="registHotplace(${i})">핫플등록</button>`+`</div>`+
		    	`<div class=" col" ><a  target="_blank">`+`<span class="title">${positions[i].title}</span>`+`</a>`+`</div>`+
		`</div>`;

    // 커스텀 오버레이가 표시될 위치입니다 
    var position = positions[i].latlng;

    // 커스텀 오버레이를 생성합니다
    var customOverlay = new kakao.maps.CustomOverlay({
      map: map,
      position: position,
      content: content,
//      xAnchor: 0.1,
      yAnchor: 0.1
    });
  }

  // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
  map.setCenter(positions[0].latlng);

}


// 핫플레이스 등록
function registHotplace(index){
	console.log(positions[index])
	let placeInfo = positions[index];
	fetch(root+"/tripinfo?action=registHotplace&contentId="+placeInfo.contentId)
		.then((response) => (console.log(response)));
}

function addDetail(info) {
  var content = '<div style="width:50px;height:50px">' +

    +'</div>';
}

function moveCenter(lat, lng) {
  map.setCenter(new kakao.maps.LatLng(lat, lng));
}

// 지도 이동 시 주변 관광지 정보 검색
function searchNearTripInfo() {
  let searchUrl = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;

  let areaCode = document.getElementById("search-area").value;
  let contentTypeId = document.getElementById("search-content-id").value;
  let keyword = document.getElementById("search-keyword").value;

  if (parseInt(areaCode)) searchUrl += `&areaCode=${areaCode}`;
  if (parseInt(contentTypeId)) searchUrl += `&contentTypeId=${contentTypeId}`;
  if (!keyword) {
    alert("검색어 입력 필수!!!");
    return;
  } else searchUrl += `&keyword=${keyword}`;

  fetch(searchUrl)
    .then((response) => response.json())
    .then((data) => makeList(data));
}
