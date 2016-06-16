package gustavo.brilhante.tutorial12aula15;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public boolean isGPSEnabled = false;
    public boolean isNetworkEnabled = false;
    public boolean canGetLocation = false;

    Location mlocation;
    public static double latitude = 0.0;
    public static double longitude = 0.0;

    LocationManager locationManager;

    MyLocationListener listener;

    Button btnLocation01,btnLocation02,btnLocation03,btnLocation04;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; //10 metros

    private static final long MIN_TIME_BW_UPDATES = 1000 * 30 * 1; //15 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLocation01 = (Button) findViewById(R.id.location01);
        btnLocation01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnLocation02 = (Button) findViewById(R.id.location02);
        btnLocation02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnLocation03 = (Button) findViewById(R.id.location03);
        btnLocation03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnLocation04 = (Button) findViewById(R.id.location04);
        btnLocation04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    public Location getLocation() {
        try {
            locationManager = (LocationManager) this
                    .getSystemService(LOCATION_SERVICE);

            listener = new MyLocationListener();

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return null;
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, listener);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        mlocation = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (mlocation != null) {
                            latitude = mlocation.getLatitude();
                            longitude = mlocation.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (mlocation == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, listener);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            mlocation = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (mlocation != null) {
                                latitude = mlocation.getLatitude();
                                longitude = mlocation.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mlocation;
    }


}
