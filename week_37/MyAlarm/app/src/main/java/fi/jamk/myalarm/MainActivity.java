package fi.jamk.myalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends Activity implements TimePickerDialog.OnTimeSetListener{

    private int notification_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void timeDialog(View view) {
        TimeDialogFragment timeDialog = new TimeDialogFragment();
        timeDialog.show(getFragmentManager(), "");
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //System.out.println(hourOfDay + " " + minute);
        EditText selectedTime = (EditText)findViewById(R.id.selectedTime);
        selectedTime.setText(hourOfDay + ":" + minute);

        createNotification(Notification.VISIBILITY_PUBLIC, "Wake up !!!");
    }

    private void createNotification(int visibility, String text) {
        // create a new notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ptm)
                        .setContentTitle("My Timer")
                        .setContentText(text)
                        .setVisibility(visibility);

        notification_id++;

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotifyMgr.notify(notification_id, mBuilder.build());
    }
}
