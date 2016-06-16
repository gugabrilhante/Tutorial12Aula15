package gustavo.brilhante.tutorial12aula15;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Gustavo on 16/06/2016.
 */
public class MyLocationListener implements LocationListener {

    public Location lastLocation;

    public Location location01,location02,location03,location04;


    public void setLocation01() {
        this.location01 = lastLocation;
    }

    public void setLocation02() {
        this.location02 = lastLocation;
    }

    public void setLocation03() {
        this.location03 = lastLocation;
    }

    public void setLocation04() {
        this.location04 = lastLocation;
    }

    

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
