package com.tempos21.bas.ui.activity;

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
import com.tempos21.bas.sdk.sdk.constants.Constants;
import com.tempos21.bas.sdk.sdk.data.Museo;
import com.tempos21.bas.sdk.sdk.data.MuseumData;
import com.tempos21.bas.sdk.sdk.data.MuseumDataTransfer;
import com.tempos21.bas.sdk.sdk.data.Planta;
import com.tempos21.bas.sdk.sdk.data.Planta0;
import com.tempos21.bas.sdk.sdk.data.Planta1;
import com.tempos21.bas.sdk.sdk.data.Planta2;
import com.tempos21.bas.sdk.sdk.task.SendMessageThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.gson.Gson;
import com.tempos21.bas.R;
import com.tempos21.bas.bean.PlaceInfo;
import com.tempos21.bas.service.LocationBeaconsService;
import com.tempos21.bas.ui.fragment.DrawerFragment;
import com.tempos21.bas.ui.fragment.MuseumsListFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        createData();

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

    private void createData() {
        ArrayList<Museo> museos = new ArrayList<Museo>();
        Museo museo_cosmo = new Museo();
        museo_cosmo.setName("CosmoCaixa");
        museo_cosmo.setDescription("El CosmoCaixa Barcelona és un museu de ciència de l'Obra Social \"la Caixa\" amb seu a Barcelona. Fou inaugurat l'any 2004 després de la remodelació del seu predecessor, el Museu de la Ciència de Barcelona, inaugurat el 1981. ");
        museo_cosmo.setImage(R.drawable.home_img_cosmocaixa);
        museos.add(museo_cosmo);

        Museo museo_forum = new Museo();
        museo_forum.setName("Caixaforum Barcelona");
        museo_forum.setDescription("CaixaForum, el centro social y cultural de la Obra Social \"la Caixa\", está ubicado en uno de los principales edificios modernistas de Barcelona. Se trata de una fábrica textil singular, ubicada en los pies de la montaña de Montjuïc, que el empresario Casimir Casaramona encargó al arquitecto Puig i Cadafalch, uno de los tres arquitectos catalanes más representativos del modernismo, contemporáneo de Domènech i Montaner y Antoni Gaudí. El edificio es una pieza única de la arquitectura modernista industrial catalana de principios del siglo XX.");
        museo_forum.setImage(R.drawable.home_img_forumbarcelona);
        museo_forum.setComprada(true);
        museos.add(museo_forum);

        Museo museo_madrid = new Museo();
        museo_madrid.setName("Caixaforum Madrid");
        museo_madrid.setDescription("CaixaForum, el centro cultural y social de la Obra Social \"la Caixa\", constituye una plataforma de divulgación coherente con las inquietudes y necesidades culturales y sociales para todos los públicos.\n" +
                " \n" +
                "Gracias a su localización en el paseo del Prado, próximo al Museo Nacional Centro de Arte Reina Sofía, al Museo Thyssen-Bornemisza y al Museo del Prado, CaixaForum se suma a la oferta cultural que la ciudad de Madrid posee en esta zona. El centro es un espacio concebido para todo tipo de público, con una amplia oferta cultural, social y educativa, donde el visitante puede disfrutar de exposiciones, talleres, conferencias, cursos y conciertos.\n" +
                "\n" +
                "La Obra Social \"la Caixa\" ha rehabilitado la antigua central eléctrica del Mediodía para alojar CaixaForum Madrid. Para ello, ha confiado el diseño arquitectónico al prestigioso equipo profesional de Jacques Herzog y Pierre de Meuron, de Basilea, Suiza, ganadores del premio Pritzker de arquitectura.");
        museo_madrid.setImage(R.drawable.home_img_forummadrid);
        museos.add(museo_madrid);

        MuseumData.getInstance().setMuseos(museos);

        List<Planta> plantas = new ArrayList<Planta>();
        plantas.add(new Planta0());
        plantas.add(new Planta1());
        plantas.add(new Planta2());

        MuseumData.getInstance().setPlantas(plantas);
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
