package com.tempos21.bas.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tempos21.bas.sdk.sdk.data.Obra;
import com.tempos21.bas.R;

import java.util.List;

/**
 * Created by Bernat on 25/10/2014.
 */
public class ObrasPlayAdapter extends ArrayAdapter<Obra>{
	private final LayoutInflater inflater;

	public ObrasPlayAdapter(Context context, List<Obra> obras) {
		super(context, 0, obras);

		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = inflater.inflate(R.layout.obra_play_layout, parent, false);

		TextView text = (TextView) v.findViewById(R.id.title);
		ImageView image = (ImageView) v.findViewById(R.id.image);
		ImageView status = (ImageView) v.findViewById(R.id.status);

		Obra item = getItem(position);

		text.setText(item.getTitle());
		image.setImageResource(item.getImage());

		if (item.getStatus() == Obra.Status.PAUSE) {
			status.setImageResource(R.drawable.miscolecciones_icn_pause);
		} else if (item.getStatus() == Obra.Status.STOP) {
			status.setImageResource(R.drawable.miscolecciones_icn_stop);
		} else if (item.getStatus() == Obra.Status.PLAYING) {
			status.setImageResource(R.drawable.miscolecciones_icn_play);
		}

		return v;
	}
}
