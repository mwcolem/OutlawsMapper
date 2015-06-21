package com.charlestonrugby.mattcoleman.outlawsmapper;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.CameraPosition;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private static final LatLng CHARLESTON =
            new LatLng(32.7833, -79.9333);
    private static final LatLng PRACTICE =
            new LatLng(32.7997811,-79.9604277);
    private static final LatLng HILTON_HEAD=
            new LatLng(32.1894928,-80.7488113);
    private static final LatLng ASHEVILLE=
            new LatLng(35.538932,-82.5654054);
    private static final LatLng CHARLOTTE=
            new LatLng(35.2031535,-80.8395259);
    private static final LatLng COLUMBIA=
            new LatLng(34.0375089,-80.9375649);
    private static final LatLng PINES=
            new LatLng(35.1907804,-79.4049955);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        UiSettings ui = mMap.getUiSettings();
//        mMap.addMarker(new MarkerOptions().position(CHARLESTON).title("Marker"));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(CHARLESTON) // Sets the center of the map to
                .zoom(10f)
                .build();    // Creates a CameraPosition from the builder
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        ui.setZoomControlsEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CameraPosition cp;
        switch (item.getItemId()) {
            case R.id.action_practice:
                cp = new CameraPosition.Builder()
                        .target(PRACTICE)
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(PRACTICE).title("Practice"));
                break;
            case R.id.action_hilton_head:
                cp = new CameraPosition.Builder()
                        .target(HILTON_HEAD)
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(HILTON_HEAD).title("Hilton Head"));
                break;
            case R.id.action_asheville:
                cp = new CameraPosition.Builder()
                        .target(ASHEVILLE)
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(ASHEVILLE).title("Asheville"));
                break;
            case R.id.action_charlotte:
                cp = new CameraPosition.Builder()
                        .target(CHARLOTTE)
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(CHARLOTTE).title("Charlotte"));
                break;
            case R.id.action_columbia:
                cp = new CameraPosition.Builder()
                        .target(COLUMBIA)
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(COLUMBIA).title("Columbia"));
                break;
            case R.id.action_pines:
                cp = new CameraPosition.Builder()
                        .target(PINES)
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(PINES).title("Southern Pines"));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
