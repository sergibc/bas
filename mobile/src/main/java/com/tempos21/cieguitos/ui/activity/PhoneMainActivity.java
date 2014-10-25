package com.tempos21.cieguitos.ui.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.estimote.sdk.Region;
import com.example.sergibc.sdk.constants.Constants;
import com.example.sergibc.sdk.data.MuseumDataTransfer;
import com.example.sergibc.sdk.task.SendMessageThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.gson.Gson;
import com.tempos21.cieguitos.R;
import com.tempos21.cieguitos.bean.PlaceInfo;
import com.tempos21.cieguitos.service.LocationBeaconsService;
import com.tempos21.cieguitos.ui.fragment.DrawerFragment;
import com.tempos21.cieguitos.ui.fragment.MuseumsListFragment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class PhoneMainActivity extends LocationBeaconsActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener,
        MessageApi.MessageListener,
        TextToSpeech.OnInitListener {

    private static final String TAG = PhoneMainActivity.class.getSimpleName();

    private GoogleApiClient mGoogleApiClient;

    //    private TextToSpeech textToSpeech;
    private MediaPlayer mediaPlayer;

    private boolean paused = false;
    private int currentTime;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        configPlayServices();

//        textToSpeech = new TextToSpeech(getApplicationContext(), this);
        mediaPlayer = new MediaPlayer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.menuContent, new DrawerFragment());
        ft.replace(R.id.content, new MuseumsListFragment());
        ft.commit();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onPause() {
//        if (null != textToSpeech) {
//            textToSpeech.stop();
//            textToSpeech.shutdown();
//        }
        if (null != mediaPlayer) {
            mediaPlayer.stop();
        }
        super.onPause();
    }

    @Override
    protected Map<Region, PlaceInfo> createRegions() {
        Map<Region, PlaceInfo> map = new HashMap<Region, PlaceInfo>();
        map.put(new Region(Constants.REGION_BLUE, "B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1000, 1), new PlaceInfo(Constants.REGION_BLUE));
        map.put(new Region(Constants.REGION_PINK, "B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1000, 2), new PlaceInfo(Constants.REGION_PINK));
        map.put(new Region(Constants.REGION_GREEN, "B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1000, 3), new PlaceInfo(Constants.REGION_GREEN));
        return map;
    }

    @Override
    protected void onRegionEntered(PlaceInfo placeInfo) {
//        Toast.makeText(this, placeInfo.getText(), Toast.LENGTH_SHORT).show();
        // TODO Should be region-location(obra) mapping
        // Send location (floor, collection, obra) to the watch and play file
        // We have only two eBacons, so we hardcoded some code
        String region = placeInfo.getText();
        if (Constants.REGION_BLUE.equals(region)) {
//            playOrPause(Constants.FILE_DIANA, false);
//            sendFloorMessage(Constants.FLOOR_2);
            sendCollectionMessage(Constants.FLOOR_2, Constants.COLLECTION_2);
        } else if (Constants.REGION_PINK.equals(region)) {
            playOrPause(Constants.FILE_ERMITA, false);
        } else {
            playOrPause(Constants.FILE_OLIMPO, false);
        }
    }

    private void findViews() {

    }

    private void configPlayServices() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Wearable.MessageApi.addListener(mGoogleApiClient, this);
        Toast.makeText(this, "Connected!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.sendButton:
                sendMessage();
                break;
        }*/
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        if (Constants.BAS_WEAR_PATH.equals(messageEvent.getPath())) {
            final String message = new String(messageEvent.getData());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // TODO parse data
                }
            });
        } else if (Constants.BAS_ACTION_PLAYER_PATH.equals(messageEvent.getPath())) {
            final String message = new String(messageEvent.getData());
            playOrPause(message, false);
        }
    }

    /**
     * When smartphone detects an eBacon of Floor
     *
     * @param floor
     */
    private void sendFloorMessage(int floor) {
        MuseumDataTransfer dataTransfer = new MuseumDataTransfer();
        dataTransfer.setPlanta(floor);
        dataTransfer.setExpo(0);
        dataTransfer.setObra(0);
        Gson gson = new Gson();
        String eBaconInfo = gson.toJson(dataTransfer);
        SendMessageThread thread = new SendMessageThread(mGoogleApiClient, Constants.BAS_PHONE_FLOOR_PATH, eBaconInfo);
        thread.start();
    }

    /**
     * When smartphone detects an eBacon of Collection
     *
     * @param collection
     */
    private void sendCollectionMessage(int floor, int collection) {
        MuseumDataTransfer dataTransfer = new MuseumDataTransfer();
        dataTransfer.setPlanta(floor);
        dataTransfer.setExpo(collection);
        dataTransfer.setObra(0);
        Gson gson = new Gson();
        String eBaconInfo = gson.toJson(dataTransfer);
        SendMessageThread thread = new SendMessageThread(mGoogleApiClient, Constants.BAS_PHONE_COLLECTION_PATH, eBaconInfo);
        thread.start();
    }

    @Override
    public void onInit(int status) {
//        // TODO get user language
//        if (status != TextToSpeech.ERROR) {
//            Locale spanish = new Locale("es", "ES");
//            textToSpeech.setLanguage(spanish);
//        }
    }

    private void playOrPause(String filename, boolean forzePlay) {
//        if (null != textToSpeech) {
//            if (textToSpeech.isSpeaking()) {
//                textToSpeech.stop();
//            } else {
//                speak();
//            }
//        }
        stopService(new Intent(getApplicationContext(), LocationBeaconsService.class));
        if (!forzePlay && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            paused = true;
            currentTime = mediaPlayer.getCurrentPosition();
        } else {
            if (!forzePlay && paused) {
                mediaPlayer.seekTo(currentTime);
                mediaPlayer.start();
            } else {
                mediaPlayer.pause();
                playFile(filename);
            }
            paused = false;
        }
    }

    private void speak() {
//        if (null != textToSpeech) {
//            StringBuilder text = new StringBuilder();
////            for (int i = 0; i < 15; i++) {
////                text = text.append("¿Dónde está el sushi? " + i);
////            }
//            String museu = "Diana, reconocible por la diadema en forma de luna que orna su cabeza, acomete la caza de varios ciervos con lanzas y perros, acompañada por varias ninfas. Se aprecia a simple vista el cambio que hizo Rubens en la posición del brazo derecho de la diosa. (Texto extractado de Vergara, A.; Pérez Preciado, J. J.: Rubens, Guía de Exposición, Museo Nacional del Prado, 2010, p.40).La decoración de la Torre de la Parada, en cuyo proyecto también participaron otros autores como Velázquez, fue el mayor encargo que Rubens recibió de Felipe IV. A partir de 1636 se enviaron desde Amberes a Madrid más de sesenta obras para esta casa de recreo situada en los montes del Pardo. La mayor parte de las escenas narraban las pasiones de los dioses, según fueron descritas en las Metamorfosis del poeta romano Ovidio y otras fuentes clásicas. Para llevar a cabo un proyecto tan amplio, Rubens realizó pequeños bocetos sobre tabla, donde capta la esencia moral de las historias y las actitudes de los personajes. Estos bocetos sirvieron de base para la elaboración de los lienzos definitivos. \n" +
//                    "\n" +
//                    "El Museo del Prado conserva diez de los bocetos de Rubens, nueve de ellos donados en 1889 por la duquesa de Pastrana, y uno adquirido en el año 2000. El Prado también conserva la mayor parte de los cuadros ejecutados a partir de los bocetos, entre ellos los realizados por el propio Rubens o por su taller (muchos de los cuadros fueron pintados por otros artistas).";
//            text = text.append(museu);
////            textToSpeech.synthesizeToFile(text.toString(),null,"/sdcard/bes/mysound.wav");
////            textToSpeech.speak(text.toString(), TextToSpeech.QUEUE_FLUSH, null);
//            File dir = Environment.getExternalStoragePublicDirectory(Constants.MEDIA_FILES_DIR);
//            if(dir.exists() || dir.mkdirs()) {
//                String filename = "/" + Constants.FILE_DIANA;
//                if (textToSpeech.synthesizeToFile(text.toString(), null, dir + filename) != TextToSpeech.SUCCESS) {
//                    Log.e(TAG, "File not created");
//                }
//            } else {
//                Log.e(TAG, "Folder not exists");
//            }
//        }

    }

    private void playFile(String filename) {
        File dir = Environment.getExternalStoragePublicDirectory(Constants.MEDIA_FILES_DIR);
        File file = new File(dir.getPath() + "/" + filename);
        if (file.exists()) {
            mediaPlayer = MediaPlayer.create(this, Uri.fromFile(file));
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    currentTime = 0;
                    paused = false;
                    startService(new Intent(getApplicationContext(), LocationBeaconsService.class));
                }
            });
        }
    }

}
