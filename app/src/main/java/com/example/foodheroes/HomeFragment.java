package com.example.foodheroes;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends Fragment {

    @BindView(R.id.txtUsulPenerima)
    TextView txtUsul;

    @BindView(R.id.imgHandsHelp)
    ImageView imgHands;

    LineChart chrtRelawan;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);

        chrtRelawan = view.findViewById(R.id.chrRelawan);
        chrtRelawan.setDragEnabled(true);
        chrtRelawan.setScaleEnabled(false);
        chrtRelawan.setDescription(null);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 50f));
        yValues.add(new Entry(2, 40f));
        yValues.add(new Entry(3, 70f));

        LineDataSet setRelawan = new LineDataSet(yValues, "Relawan");

        setRelawan.setFillAlpha(110);

        setRelawan.setColor(Color.MAGENTA);
        setRelawan.setLineWidth(2f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setRelawan);

        LineData data = new LineData(dataSets);
        chrtRelawan.setData(data);

        String[] values = new String[] {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu"};

        XAxis xAxis = chrtRelawan.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setGranularity(1);

        return view;
    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter{

        private String[] Xvalues;
        public MyXAxisValueFormatter(String[] xvalues) {
            this.Xvalues = xvalues;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return Xvalues[(int)value];
        }
    }


    @OnClick(R.id.txtUsulPenerima)
    public void goToPenerima2(){
        BottomNavigationView navigationView = getActivity().findViewById(R.id.navRelawan);
        navigationView.setSelectedItemId(R.id.navigation_penerima);

        PenerimaFragment nextFrag= new PenerimaFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, nextFrag, "RelawanFragment")
                .addToBackStack(null)
                .commit();

    }

    @OnClick(R.id.imgHandsHelp)
    public void goToPenerima(){

        BottomNavigationView navigationView = getActivity().findViewById(R.id.navRelawan);
        navigationView.setSelectedItemId(R.id.navigation_penerima);
        
        PenerimaFragment nextFrag= new PenerimaFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, nextFrag, "RelawanFragment")
                .addToBackStack(null)
                .commit();
    }

}
