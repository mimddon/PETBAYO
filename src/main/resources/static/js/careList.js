var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

var centerPosition; // 중심좌표를 담을 객체

var geocoder = new kakao.maps.services.Geocoder(); // 주소-좌표 변환 객체를 생성합니다

var myMarker = null;
var myInfowindow = null;

var circle; // 반경원을 표시할 객체

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

// 지도의 현재 확대 레벨을 가져옵니다
var zoomLevel = map.getLevel();
// 각 확대 레벨에 대한 숫자를 정의합니다
var number;

var cmMarker;

var cmInfowindow;

let tableBody = '';

let markers = [];

let infoWindows = [];

let infoWindow;