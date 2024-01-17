package com.proj2_calc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import org.mozilla.javascript.Context;
//import org.mozilla.javascript.Scriptable;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;



public class DisplayFragment extends Fragment {

    TextView tv_result,tv_solution;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_result = (TextView) view.findViewById(R.id.tv_result);
        tv_solution = (TextView) view.findViewById(R.id.tv_solution);

    }

    protected void displayReceivedData(String message)
    {
        String dataCal ="";

        if(message.equals("C"))
        {
            tv_solution.setText("");
            tv_result.setText("0");
        }
        else if(message.equals("CE"))
        {
            if(tv_solution.getText().length()>0) {
                String value = tv_solution.getText().subSequence(0, tv_solution.getText().length() - 1).toString();
                tv_solution.setText(value);
            }
        }

        else if (message.equals("√"))
        {
            tv_solution.setText(tv_solution.getText().toString().replace("√",""));

            if(tv_solution.getText().toString().length()>0)
            {
                tv_result.setText(String.valueOf(
                        (new BigDecimal( Math.sqrt(Double.valueOf(tv_solution.getText().toString())))).round(new MathContext(4))
                ));
                tv_solution.setText("");
            }
            else
            {
                tv_solution.setText("");
                tv_result.setText("Err");
            }
        }














        else if (message.equals("="))
        {
            if(isLastCharOperator(tv_solution.getText().toString()))
            {
                tv_result.setText("Err");
            }
            else
            {




                try {

                     Context context = Context.enter();
                    context.setOptimizationLevel(-1);
                    Scriptable scope = context.initStandardObjects();



                    Object result = context.evaluateString(scope, tv_solution.getText().toString(), "<cmd>", 1, null);
                    if(result.toString().endsWith(".0"))
                        result = result.toString().replace(".0","");

                    tv_result.setText(String.valueOf(result));

                    if(tv_solution.getText().toString().contains("%"))
                        tv_solution.setText("");

                }catch (Exception e){
                    tv_result.setText("Err");
                }
            }//end else




        }

        else {
            dataCal = tv_solution.getText().toString();


             if (message.equals("•"))
                message = ".";
            else if (message.equals("−"))
                message = "-";


           else  if(message.equals("÷"))
                message = "/";
            else if (message.equals("×"))
                message = "*";

             else if (message.equals("+"))
                 message = "+";
            else if (message.equals("±"))
                message = "-";

            if(dataCal.length()>0) {
                String checkLastChar = String.valueOf(dataCal.charAt(dataCal.length()-1));

                if (message.equals("*") || message.equals("/") || message.equals("-") || message.equals("+"))
                {
                    if (checkLastChar.equals("+") || checkLastChar.equals("-") || checkLastChar.equals("*") || checkLastChar.equals("/")) {
                        dataCal = dataCal.substring(0, dataCal.length() - 1).toString();
                    }
                }
            }

            dataCal = dataCal + message;
            tv_solution.setText(dataCal);
        }
    }

    private boolean isLastCharOperator(String str)
    {
        char lastChar = str.charAt(str.length()-1);

        if(lastChar == '/' ||
                lastChar == '*' ||
                lastChar == '+' ||
                lastChar == '-' )
            return  true;
        else
            return false;


    }

}