package com.example.app_dictionary_ev;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {

    private EditText edtSearch;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchbox); // Đảm bảo layout đúng tên

        edtSearch = findViewById(R.id.edt_search);
        listView = findViewById(R.id.listView); // Đảm bảo ID đúng với ListView

        // Khi nhấn vào EditText, hiển thị ListView
        edtSearch.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                listView.setVisibility(View.VISIBLE);
            }
        });

        // Khi bấm ra ngoài, ẩn ListView
        edtSearch.setOnClickListener(v -> listView.setVisibility(View.VISIBLE));

        listView.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                listView.setVisibility(View.GONE);
            }
        });
    }
}
