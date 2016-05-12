package com.example.huaming_lin.day18_weichathomepager;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    private TextView tv_show;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_show = (TextView) getView().findViewById(R.id.tv_show);
        Bundle bundle = getArguments();
        String content = bundle.getString("content", "拿不到bundle值");
        tv_show.setText(content);
        int color = bundle.getInt("color", Color.BLACK);
        getView().setBackgroundColor(color);
    }
}
