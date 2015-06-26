package com.charlestonrugby.mattcoleman.outlawsmapper;

import java.util.HashMap;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;

    private HashMap<String, LatLng> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locations = new HashMap<>();
        locations.put("home", new LatLng(32.7278633,-79.9388181));
        locations.put("practice", new LatLng(32.7997811,-79.9604277));
        locations.put("hilton_head", new LatLng(32.1894928,-80.7488113));
        locations.put("asheville", new LatLng(35.538932,-82.5654054));
        locations.put("charlotte", new LatLng(35.2031535,-80.8395259));
        locations.put("columbia", new LatLng(34.0375089,-80.9375649));
        locations.put("southern_pines", new LatLng(35.1907804,-79.4049955));

        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        populateAddressesFromJSON();
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
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        UiSettings ui = mMap.getUiSettings();
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(32.7833, -79.9333)) // Sets the center of the map to
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
            case R.id.action_home:
                cp = new CameraPosition.Builder()
                        .target(locations.get("home"))
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(locations.get("home")).title("Home Pitch"));
                break;
            case R.id.action_practice:
                cp = new CameraPosition.Builder()
                        .target(locations.get("practice"))
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(locations.get("practice")).title("Practice"));
                break;
            case R.id.action_hilton_head:
                cp = new CameraPosition.Builder()
                        .target(locations.get("hilton_head"))
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(locations.get("hilton_head")).title("Hilton Head"));
                break;
            case R.id.action_asheville:
                cp = new CameraPosition.Builder()
                        .target(locations.get("asheville"))
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(locations.get("asheville")).title("Asheville"));
                break;
            case R.id.action_charlotte:
                cp = new CameraPosition.Builder()
                        .target(locations.get("charlotte"))
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(locations.get("charlotte")).title("Charlotte"));
                break;
            case R.id.action_columbia:
                cp = new CameraPosition.Builder()
                        .target(locations.get("columbia"))
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(locations.get("columbia")).title("Columbia"));
                break;
            case R.id.action_pines:
                cp = new CameraPosition.Builder()
                        .target(locations.get("southern_pines"))
                        .zoom(13f)
                        .build();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                mMap.addMarker(new MarkerOptions().position(locations.get("southern_pines")).title("Southern Pines"));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void populateAddressesFromJSON() {
        JSONHandler handler = new JSONHandler();
        JSONObject addresses = handler.getJSONFromUrl("http://charlestonrugby.com/mobile/addresses.json");
        try{
            JSONArray jsonArray = addresses.getJSONArray("addresses");
            for (int i=0; i<jsonArray.length();i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                String city = row.getString("city");
                if (locations.containsKey(city)) {
                    locations.put(city, new LatLng(row.getDouble("lat"), row.getDouble("long")));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}