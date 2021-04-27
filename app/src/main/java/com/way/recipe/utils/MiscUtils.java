package com.way.recipe.utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class MiscUtils {
    private static final int DRAWABLE_RIGHT = 2;

    @SuppressLint("ClickableViewAccessibility")
    public static void addEditTextQuickDelete(final EditText editText, final Drawable quickDeleteDrawable) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final Drawable[] drawableList = editText.getCompoundDrawables();
                if (s.length() > 0) {
                    editText.setCompoundDrawablesWithIntrinsicBounds(drawableList[0], drawableList[1], quickDeleteDrawable, drawableList[3]);
                } else {
                    editText.setCompoundDrawablesWithIntrinsicBounds(drawableList[0], drawableList[1], null, drawableList[3]);
                }
            }
        });

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Drawable drawable = editText.getCompoundDrawables()[DRAWABLE_RIGHT];
                if (drawable == null) {
                    return false;
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (event.getRawX() >= (editText.getRight() - drawable.getBounds().width())) {
                        // your action here
                        editText.setText("");
                        return false;
                    }
                }
                return false;
            }
        });
    }
}
