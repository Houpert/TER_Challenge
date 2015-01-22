package ter.android.ter_challenge;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Controller.DotsAndBoxes;
import Domain.Action;


public class GameActivity extends ActionBarActivity implements SensorEventListener {

    private final int valueSensor = 3;

    private final String TAG = "Debug -- ";

    private static int MS_ONE_SEC = 1000;
    private long gameTime = 10 * MS_ONE_SEC;
    private int soundVolume= 4;
    private MediaPlayer mp;

    private GridLayout gameLayout;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private boolean player = false;
    private DotsAndBoxes dotsAndBoxes;
    private boolean isTouch = false;
    private int pointClick = 0;

    //private SensorManager sm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        dotsAndBoxes = new DotsAndBoxes();
        dotsAndBoxes.initLogic();

        initSensor();
        //startTimer();
    }

    private void initSensor() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
            List<Sensor> gravSensors = mSensorManager.getSensorList(Sensor.TYPE_GRAVITY);
            for(int i=0; i<gravSensors.size(); i++) {
                if ((gravSensors.get(i).getVendor().contains("Google Inc.")) &&
                        (gravSensors.get(i).getVersion() == 3)){
                    // Use the version 3 gravity sensor.
                    mSensor = gravSensors.get(i);
                }
            }
        }
        else{
            // Use the accelerometer.
            if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
                mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }
            else{
                // Sorry, there are no accelerometers on your device.
                // You can't play this game.
                toastMessage(" You can't play this game, no Sensor");

            }
        }

    }

    private void turnEnd(){
        pointClick = 0;
        isTouch = false;
    }

    private void toastMessage(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void startTimer(){
        mp = MediaPlayer.create(getBaseContext(), R.raw.clock);
        mp.setVolume(soundVolume, soundVolume);
        mp.start();

        Timer timer = new Timer();
        TimerTask tts = new TimerTask() {
            @Override
            public void run() {
                gameTime -= MS_ONE_SEC;
                if(soundVolume<15){
                    soundVolume += 1;
                }
                mp.setVolume(soundVolume,soundVolume);

                AudioManager mgr = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);

                mgr.setStreamVolume(AudioManager.STREAM_MUSIC, soundVolume, 0);

                if(gameTime == 0){
                    dotsAndBoxes.changePlayer();
                    dotsAndBoxes.changeTurn();
                }
            }
        };
        timer.scheduleAtFixedRate(tts,new Date(),1000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        int sensor = event.sensor.getType();
        float [] values = event.values;

        float magField_x = values[0];
        float magField_y= values[1];

        Action act = detectAction(magField_x, magField_y);
        if(isTouch && act != Action.EMPTY){
            //TODO
            //dotsAndBoxes.

            int lineToPrint = dotsAndBoxes.addTrait(act,pointClick);
            if(lineToPrint > 0) {
                Log.v(TAG, "" + isTouch + "--" + act.toString() + "--" + pointClick + "--" + lineToPrint);
                turnEnd();
            }else{
                //Turn not end error user
            }
        }
    }

    private Action detectAction(float x, float y) {
        if(isTouch){
            if(y < -valueSensor)
                return Action.UP;
            else if(y > valueSensor)
                return Action.DOWN;
            else if(x < -valueSensor)
                return Action.RIGHT;
            else if(x > valueSensor)
                return Action.LEFT;
        }
        return Action.EMPTY;
    }


    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    public void touchButton(View view) {
        isTouch = true;
        switch (view.getId()) {
            case R.id.point1:
                pointClick = 1;
                break;
            case R.id.point2:
                pointClick = 2;
                break;
            case R.id.point3:
                pointClick = 3;
                break;
            case R.id.point4:
                pointClick = 4;
                break;
            case R.id.point5:
                pointClick = 5;
                break;
            case R.id.point6:
                pointClick = 6;
                break;
            case R.id.point7:
                pointClick = 7;
                break;
            case R.id.point8:
                pointClick = 8;
                break;
            case R.id.point9:
                pointClick = 9;
                break;
        }
    }
}