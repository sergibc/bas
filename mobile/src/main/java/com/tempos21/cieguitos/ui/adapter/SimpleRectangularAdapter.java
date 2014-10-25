package com.tempos21.cieguitos.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergibc.sdk.data.ItemSelectable;
import com.tempos21.cieguitos.R;

import java.util.List;

/**
 * Created by Bernat on 25/10/2014.
 */
public abstract class SimpleRectangularAdapter<K extends ItemSelectable> extends ArrayAdapter<K> {
	private int itemSelected = -1;
	private final LayoutInflater inflater;

	public SimpleRectangularAdapter(Context context, List<K> objects) {
		super(context, 0, objects);
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = inflater.inflate(R.layout.simple_rectangular, parent, false);

		K item = getItem(position);

		if (item != null) {
			ImageView image = (ImageView) v.findViewById(R.id.image);
			ImageView imageBlue = (ImageView) v.findViewById(R.id.imageBlue);
			TextView text = (TextView) v.findViewById(R.id.text);

			if (itemSelected == position) {
				imageBlue.setVisibility(View.VISIBLE);
			} else {
				imageBlue.setVisibility(View.INVISIBLE);
			}

			image.setImageResource(item.getImage());
			text.setText(item.getTitle());
		}
		return v;
	}
	public void setItemSelected(int itemSelected) {
		this.itemSelected = itemSelected;
		notifyDataSetChanged();
	}
}
