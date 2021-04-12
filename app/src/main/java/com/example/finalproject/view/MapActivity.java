package com.example.finalproject.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.model.Restaurant;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements MapView.POIItemEventListener {
    final double INIT_LATITUDE = 37.451673;
    final double INIT_LONGITUDE = 126.657229; // 약사 위도, 경도 좌표

    List<Restaurant> resList;
    { // restaurant table hardcoding
        resList = new ArrayList<>();
        resList.add(new Restaurant(0, "무관", 37.451673, 126.657229)); // 약사
        resList.add(new Restaurant(1, "가메이", 37.451693, 126.656838));
        resList.add(new Restaurant(2, "고수찜닭", 37.452030, 126.656254));
        resList.add(new Restaurant(3, "궁중보쌈", 37.451819, 126.656408));
        resList.add(new Restaurant(4, "까사올리브", 37.452227, 126.654147));
        resList.add(new Restaurant(5, "끝집고기", 37.450555, 126.661122));

        resList.add(new Restaurant(6, "내가찜한닭", 37.451494, 126.656174));
        resList.add(new Restaurant(7, "당가네대박삼겹", 37.452389, 126.657659));
        resList.add(new Restaurant(8, "동아리닭갈비", 37.451484, 126.658248));
        resList.add(new Restaurant(9, "명동찌개마을", 37.452060, 126.657074));
        resList.add(new Restaurant(10, "미로곱창", 37.452815, 126.657617));

        resList.add(new Restaurant(11, "시내비골", 37.451161, 126.658268));
        resList.add(new Restaurant(12, "신촌샤브샤브", 37.451649, 126.655850));
        resList.add(new Restaurant(13, "아웃닭", 37.451993, 126.656846));
        resList.add(new Restaurant(14, "우선소곱창", 37.452287, 126.656802));
        resList.add(new Restaurant(15, "인하칼국수", 37.452104, 126.656439));

        resList.add(new Restaurant(16, "족사랑", 37.453438, 126.660023));
        resList.add(new Restaurant(17, "청년다방", 37.451484, 126.657598));
        resList.add(new Restaurant(18, "취엔", 37.451529, 126.656781));
        resList.add(new Restaurant(19, "타이스푼", 37.451781, 126.655947));
        resList.add(new Restaurant(20, "파치", 37.450641, 126.659881));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapView mapView = new MapView(this);
        System.out.println("mapView == " + mapView);
        ViewGroup mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setPOIItemEventListener(this); // this에 MapViewEventListener 구현
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(INIT_LATITUDE, INIT_LONGITUDE), true); // 초기화면 설정
        mapView.setZoomLevel(0, true);
        mapView.zoomIn(true);
        mapView.zoomOut(true); // mapView init set

        for (Restaurant poi : resList) { // Marker define
            // todo 1. if (생성된 방이 있을 때) ~ 진행
            MapPOIItem item = new MapPOIItem();
            item.setTag(0);
            item.setMarkerType(MapPOIItem.MarkerType.BluePin);
            item.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
            item.setItemName(poi.getRestName());
            item.setMapPoint(MapPoint.mapPointWithGeoCoord(poi.getRestLatitude(), poi.getRestLongitude()));

            mapView.addPOIItem(item); // Marker 추가부분
        }
        // todo 2. DB의 SELECT문을 이용하여 존재하는 방의 위치만 표시
        // todo 3. 위, 경도 좌표는 식당정보에서 get함 ~ 만약 안될경우 arrayList에 직접 입력
    }


    // EventListener 구현
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        // item select
    }

    @Deprecated
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        // not use
    }

    @Override // 말풍선 클릭시 호출
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        // todo 1. DB 커넥션 생성
        Intent selectIntent = new Intent(this, MapDetailActivity.class);
        // todo 2. data 전달
        // todo 3. putExtra
        startActivity(selectIntent);
    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
        // long press and drag
    }
}
