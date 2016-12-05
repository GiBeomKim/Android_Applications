package kr.rexkim.univnotification;

import java.util.HashMap;
import com.google.android.gms.maps.model.LatLng;


public final class Constants {
    public static final HashMap<String, LatLng> ZONES = new HashMap<String, LatLng>();
    static {
        ZONES.put("Myoungi Univ.", new LatLng(37.2222753, 127.186292));
        ZONES.put("Home", new LatLng(37.403872, 126.944728));
        ZONES.put("Test", new LatLng(37.229277, 127.188003));
    }
}
