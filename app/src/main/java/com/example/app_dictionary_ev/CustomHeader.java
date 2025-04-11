package com.example.app_dictionary_ev;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomHeader extends ConstraintLayout {
    private ImageView ivBack;
    private TextView tvTitle;
    public CustomHeader(Context context){
        super(context);
        init(context);
    }
    public CustomHeader(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.custom_header,this,true);
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);

        ivBack.setOnClickListener(v -> {
            if (context instanceof androidx.appcompat.app.AppCompatActivity) {
                ((androidx.appcompat.app.AppCompatActivity) context).onBackPressed();
            }
        });
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }
}
