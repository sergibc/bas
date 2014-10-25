package com.tempos21.cieguitos.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergibc.sdk.data.ItemSelectable;
import com.example.sergibc.sdk.data.Museo;
import com.tempos21.cieguitos.R;

import java.util.List;

/**
 * Created by Bernat on 25/10/2014.
 */
public class MuseumsSimpleRectangularAdapter extends SimpleRectangularAdapter<Museo> {

	public MuseumsSimpleRectangularAdapter(Context context, List<Museo> objects) {
		super(context, objects);
	}
}
