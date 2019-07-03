var position = new kakao.maps.LatLng(33.450701, 126.570667);
var map = new kakao.maps.Map(document.getElementById('kakaoMap'), {
	center: position,
	level: 3
});

var clusterer = new kakao.maps.MarkerClusterer({
    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
    minLevel: 10 // 클러스터 할 최소 지도 레벨 
});
clusterer.addMarker(
	new kakao.maps.Marker({
		position: new kakao.maps.LatLng(33.450701, 126.570667)
	})
);

var content ='<div class="customoverlay">'+
				'<a href="https://map.kakao.com/link/map/18059921" target="_blank">'+
					'<span class="title">카카오 스페이스닷원</span>'+
					'<span class="title">'+
						'<span>제주특별자치도 제주시 첨단로 242</span'+
					'</span>'+
				'</a>'+
			 '</div>';

var customOverlay = new kakao.maps.CustomOverlay({
	position : position,
	content : content
});

customOverlay.setMap(map);