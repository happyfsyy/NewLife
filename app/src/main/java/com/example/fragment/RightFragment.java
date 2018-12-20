package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newlife.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RightFragment extends Fragment {
    private static final String TAG = "RightFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,">>>>>>>>onCreateView()>>>>>>");
        View view=inflater.inflate(R.layout.fragment_right_layout,container,false);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e(TAG,">>>>>>>>onAttach()>>>>>>");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,">>>>>>>>onCreate()>>>>>>");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,">>>>>>>>onActivityCreated()>>>>>>");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,">>>>>>>>onStart()>>>>>>");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,">>>>>>>>onResume()>>>>>>");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,">>>>>>>>onPause()>>>>>>");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,">>>>>>>>onStop()>>>>>>");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,">>>>>>>>onDestroyView()>>>>>>");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,">>>>>>>>onDestroy()>>>>>>");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG,">>>>>>>>onDetach()>>>>>>");

    }
}
