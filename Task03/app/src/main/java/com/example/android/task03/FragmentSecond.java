package com.example.android.task03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class FragmentSecond extends Fragment {

    public FragmentSecond() {
    }

    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_second, container, false);
    }
}