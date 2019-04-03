package com.androidstudy.mpesa.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidstudy.mpesa.R;


public class ProgressDialogFragment extends DialogFragment {

    private static final String ARG_TITLE= "title";
    private static final String ARG_MESSAGE= "message";

    String title = "";
    String message = "";

    public ProgressDialogFragment() {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        setArguments(args);
    }

    public static ProgressDialogFragment newInstance(String title, String message) {
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loading_view, container);
        TextView tvTitle =  view.findViewById(R.id.tvProgress_title);
        TextView tvMessage = view.findViewById(R.id.tvProgress_message);

        title = getArguments().getString(ARG_TITLE);
        message = getArguments().getString(ARG_MESSAGE);


        tvTitle.setText(title);
        tvMessage.setText(message);

        return view;
    }

}