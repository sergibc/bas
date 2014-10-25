package com.tempos21.cieguitos.ui.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.estimote.sdk.Region;
import com.example.sergibc.sdk.constants.Constants;
import com.example.sergibc.sdk.data.Museo;
import com.example.sergibc.sdk.data.MuseumData;
import com.example.sergibc.sdk.data.Planta;
import com.example.sergibc.sdk.data.Planta0;
import com.example.sergibc.sdk.data.Planta1;
import com.example.sergibc.sdk.data.Planta2;
import com.example.sergibc.sdk.task.SendMessageThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.tempos21.cieguitos.R;
import com.tempos21.cieguitos.bean.PlaceInfo;
import com.tempos21.cieguitos.ui.fragment.DrawerFragment;
import com.tempos21.cieguitos.ui.fragment.MuseumsListFragment;
import com.tempos21.cieguitos.ui.fragment.MyTicketsListFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PhoneMainActivity extends LocationBeaconsActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener,
        MessageApi.MessageListener,
        TextToSpeech.OnInitListener {

    private GoogleApiClient mGoogleApiClient;

    private TextToSpeech textToSpeech;
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		createData();

        findViews();

        configPlayServices();

        textToSpeech = new TextToSpeech(getApplicationContext(), this);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null)  {
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
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
    public void onPause() {
        if (null != textToSpeech) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    @Override
    protected Map<Region, PlaceInfo> createRegions() {
        Map<Region, PlaceInfo> map = new HashMap<Region, PlaceInfo>();
        map.put(new Region("BLUE", "B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1000, 1), new PlaceInfo("BLUE"));
        map.put(new Region("PINK", "B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1000, 2), new PlaceInfo("PINK"));
        map.put(new Region("GREEN", "B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1000, 3), new PlaceInfo("GREEN"));
        return map;
    }

    @Override
    protected void onRegionEntered(PlaceInfo placeInfo) {
        Toast.makeText(this, placeInfo.getText(), Toast.LENGTH_SHORT).show();
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
                speak();
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
        }
    }

    private void sendMessage() {
//        new SendMessageTask().execute();
        SendMessageThread thread = new SendMessageThread(mGoogleApiClient, Constants.BAS_PHONE_PATH, "fromPhone");
        thread.start();
    }

    @Override
    public void onInit(int status) {
        // TODO get user language
        if (status != TextToSpeech.ERROR) {
            Locale spanish = new Locale("es", "ES");
            textToSpeech.setLanguage(spanish);
        }
    }

    private void speak() {
        if (null != textToSpeech) {
            String text = "¿Dónde está el sushi?";
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
