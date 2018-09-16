package com.undecode.goduettocompanion.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.undecode.goduettocompanion.R;

public class NotificationFragment extends Fragment
{
    public static NotificationFragment newInstance()
    {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    public static NotificationFragment newInstance(Bundle bundle)
    {
        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        return view;
    }
}
