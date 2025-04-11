package com.example.app_dictionary_ev;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {
    private EditText edtSearch;
    private ImageView iconHeart;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hiển thị màn hình Splash trước
        setContentView(R.layout.mh_batdau);

        // Sau 3 giây, chuyển sang giao diện chính
        new Handler().postDelayed(() -> {
            showMainScreen();
        }, 3000);
    }

    private void showMainScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#0066FF"));
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.searchbox);

        // Lấy ID của layout chính
        View mainLayout = findViewById(R.id.main_layout);

        if (mainLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Ánh xạ các thành phần
        edtSearch = findViewById(R.id.edt_search);
        iconHeart = findViewById(R.id.icon_heart);
        listView = findViewById(R.id.listView);

        // Ẩn danh sách ban đầu
        //listView.setVisibility(View.GONE);

        // Xóa nội dung khi nhấn ❌
        iconHeart.setOnClickListener(v -> {
            edtSearch.setText("");
            edtSearch.clearFocus();
            listView.setVisibility(View.GONE);
        });

        // Hiện danh sách khi nhập chữ
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    listView.setVisibility(View.VISIBLE);
                } else {
                    listView.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}
