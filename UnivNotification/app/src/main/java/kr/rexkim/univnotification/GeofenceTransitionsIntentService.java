package kr.rexkim.univnotification;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

public class GeofenceTransitionsIntentService extends IntentService {
        public GeofenceTransitionsIntentService() {
            super("GeofenceTransitionsIntentService");
        }
        Context mContext;
        @Override
        protected void onHandleIntent(Intent intent) {
            GeofencingEvent event = GeofencingEvent.fromIntent(intent);
            List<Geofence> triggeringGeofences = event.getTriggeringGeofences();
            ArrayList triggeringGeofencesIdsList = new ArrayList();
            for(Geofence geofence : triggeringGeofences)
                triggeringGeofencesIdsList.add(geofence.getRequestId());
            String IDs = TextUtils.join(", ", triggeringGeofencesIdsList);

            int transitiontype = event.getGeofenceTransition();
            if(transitiontype == Geofence.GEOFENCE_TRANSITION_ENTER){
                //alertCheckGPS();
                Log.i("haha","Enter from  " + IDs);
            }


            if(transitiontype == Geofence.GEOFENCE_TRANSITION_EXIT){
                Log.i("haha","Exit from  " + IDs);
            }


        }
    /*
    private void alertCheckGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("위치 서비스가 꺼져있습니다.\n원할한 서비스를 위해 켜주시길 바랍니다.")
                .setCancelable(false)
                .setPositiveButton("GPS 활성화",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    */


}
