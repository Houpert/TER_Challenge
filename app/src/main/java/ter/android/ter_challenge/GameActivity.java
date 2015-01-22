package ter.android.ter_challenge;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Domain.Action;
import Domain.Trait;


public class GameActivity extends ActionBarActivity implements SensorEventListener {

    private final int nbOddsSquare = 4;
    private final int nbSquare = 4;

    Trait[][] plateau = new Trait[nbSquare][nbOddsSquare];

    private final String TAG = "Debug -- ";
    private int height;
    private int width;

    private static int MS_ONE_SEC = 1000;
    private long gameTime = 10 * MS_ONE_SEC;
    private int soundVolume= 4;
    private MediaPlayer mp;

    private GridLayout gameLayout;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private boolean player = false;

    private SensorManager sm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        initLogic();
        initSensor();



        gameLayout = (GridLayout) findViewById(R.id.gridLayout);
        gameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //gesture detector to detect swipe.


                int touchX = (int) event.getX();
                int touchY = (int) event.getY();

                squareDetector(touchX, touchY);
                return true;//always return true to consume event
            }
        });
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

                System.out.println(gameTime/MS_ONE_SEC);

                if(gameTime==0){
                    gameTime = 10 * MS_ONE_SEC;
                    soundVolume = 4;
                    if(player){
                        player = false;
                    }else {
                        player = true;
                    }
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


    private void initLogic() {
        plateau[0][0]=new Trait(false);
        plateau[0][1]=new Trait(false);
        plateau[1][0]=new Trait(false);
        plateau[1][2]=new Trait(false);
        plateau[2][1]=new Trait(false);
        plateau[2][3]=new Trait(false);
        plateau[3][2]=new Trait(false);
        plateau[3][3]=new Trait(false);

        Trait  tH = new Trait(false);
        Trait  tB = new Trait(false);
        Trait  tD = new Trait(false);
        Trait  tG = new Trait(false);

        plateau[0][2]= tH;
        plateau[1][1]= tH;
        plateau[0][3]= tG;
        plateau[2][0]= tG;
        plateau[1][3]= tD;
        plateau[3][0]= tD;
        plateau[2][2]= tB;
        plateau[3][1]= tB;

    }

    private void drawTrait(Action action, int nbPlateau){
        plateau[nbPlateau][action.ordinal()].setDraw(true);
    }


    private void squareDetector(int touchX, int touchY) {
    }


    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        int sensor = event.sensor.getType();
        float [] values = event.values;
        synchronized (this) {
            if (sensor == Sensor.TYPE_MAGNETIC_FIELD) {
                float magField_x = values[0];
                float magField_y = values[1];
                float magField_z = values[2];
            }
        }
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





}