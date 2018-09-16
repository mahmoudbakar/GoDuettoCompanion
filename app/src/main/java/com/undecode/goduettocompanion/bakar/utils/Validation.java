package com.undecode.goduettocompanion.bakar.utils;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.undecode.goduettocompanion.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation
{
    private Context context;
    public Validation(Context context)
    {
        this.context = context;
    }

    public boolean isEmailValid(String email)
    {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
        {
            isValid = true;
        } else
        {

        }
        return isValid;
    }

    public boolean isEmpty(EditText editText, String message)
    {
        if (editText.getText().toString().length() > 0)
        {
            return false;
        }else
        {
            Toast.makeText(context, message+" "+context.getString(R.string.cannot_be_empty), Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public boolean isEmpty(EditText editText, String message, Animator animator)
    {
        if (editText.getText().toString().length() > 0)
        {
            return false;
        }else
        {
            Toast.makeText(context, message+" "+context.getString(R.string.cannot_be_empty), Toast.LENGTH_SHORT).show();
            animator.vibrateView(editText);
            return true;
        }
    }
}
