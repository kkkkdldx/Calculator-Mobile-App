package com.proj2_calc;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

/**
 * create an instance of this fragment.
 */
public class BottomFragment extends Fragment

{


    SendMessage SM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom,container,false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        declareButtons(view, R.id.btn_plus);
        declareButtons(view, R.id.btn_minus);
        declareButtons(view, R.id.btn_modulus);
        declareButtons(view, R.id.btn_divide);
        declareButtons(view, R.id.btn_sqroot);
        declareButtons(view, R.id.btn_multiply);
        declareButtons(view, R.id.btn_clearlast);
        declareButtons(view, R.id.btn_clear);
        declareButtons(view, R.id.btn_dot);
        declareButtons(view, R.id.btn_plus_minus);
        declareButtons(view, R.id.btn_equal);
        declareButtons(view, R.id.btn_0);
        declareButtons(view, R.id.btn_1);
        declareButtons(view, R.id.btn_2);
        declareButtons(view, R.id.btn_3);
        declareButtons(view, R.id.btn_4);
        declareButtons(view, R.id.btn_5);
        declareButtons(view, R.id.btn_6);
        declareButtons(view, R.id.btn_7);
        declareButtons(view, R.id.btn_8);
        declareButtons(view, R.id.btn_9);
        declareButtons(view, R.id.btn_0);
    }

    void declareButtons(View view, int id)
    {
        MaterialButton btn = view.findViewById(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SM.sendData(btn.getText().toString());

            }
        });
    }

    interface SendMessage {
        void sendData(String message);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in getting data. Please try again");
        }

    }
}