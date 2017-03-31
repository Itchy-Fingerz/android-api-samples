package com.eegeo.apisamples;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.eegeo.mapapi.EegeoApi;
import com.eegeo.mapapi.EegeoMap;
import com.eegeo.mapapi.MapView;
import com.eegeo.mapapi.camera.CameraPosition;
import com.eegeo.mapapi.map.OnMapReadyCallback;

import java.util.Locale;

public class QueryCameraActivity extends AppCompatActivity {

    private MapView m_mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EegeoApi.init(this, getString(R.string.eegeo_api_key));

        setContentView(R.layout.query_camera_example_activity);
        m_mapView = (MapView) findViewById(R.id.query_camera_mapview);
        m_mapView.onCreate(savedInstanceState);

        m_mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final EegeoMap map) {


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final CameraPosition cameraPosition = map.getCameraPosition();
                        double latitude = (cameraPosition.target != null) ? cameraPosition.target.latitude : 0.0;
                        double longitude = (cameraPosition.target != null) ? cameraPosition.target.longitude : 0.0;
                        new AlertDialog.Builder(QueryCameraActivity.this)
                                .setTitle("Camera Position")
                                .setMessage(String.format(Locale.getDefault(), "LatLng: %.2f, %.2f Zoom: %d",
                                        latitude, longitude, (int) cameraPosition.zoom))
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }, 500);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        m_mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        m_mapView.onDestroy();
    }
}
