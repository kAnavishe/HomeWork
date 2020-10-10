package com.example.android.task03;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentDog extends Fragment {
    public FragmentDog() {
        Log.d("Maks", "FragmentFirst Creation");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("Frag 01 oCreateView", container + "");
        return inflater.inflate(R.layout.fragment_dog, container, false);
    }
}