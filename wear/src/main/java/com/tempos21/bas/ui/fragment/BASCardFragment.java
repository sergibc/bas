package com.tempos21.bas.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.wearable.view.CardScrollView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tempos21.bas.R;

/**
 * Created by sergibc on 25/10/14.
 */
public class BASCardFragment extends Fragment {

    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_CONTENT = "ARG_CONTENT";

    private ViewGroup mRootView;
    private TextView cardTitle;
    private TextView cardContent;


    public static BASCardFragment newInstance(String title, String content) {
        BASCardFragment f = new BASCardFragment();

        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_CONTENT, content);

        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.bas_card_fragment, null);

        CardScrollView cardScrollView =
                (CardScrollView) mRootView.findViewById(R.id.card_scroll_view);
        cardScrollView.setCardGravity(Gravity.BOTTOM);
		cardScrollView.setExpansionEnabled(true);

        cardTitle = (TextView) mRootView.findViewById(R.id.card_title);
        cardContent = (TextView) mRootView.findViewById(R.id.card_content);

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String title = getArguments().getString(ARG_TITLE);
        String content = getArguments().getString(ARG_CONTENT);

        cardTitle.setText(title);
		cardTitle.setTextColor(getResources().getColor(R.color.colorAccent));
		cardContent.setText(content);
    }
}
