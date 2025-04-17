package com.example.app_dictionary_ev.data.db;

import android.content.Context;

import androidx.room.Room;

import com.example.app_dictionary_ev.data.model.DictionaryEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.List;

public class DatabaseInitializer {
    public static void populateDatabase(Context context) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "dictionary-db").build();

        new Thread(() -> {
            // Đọc file JSON từ assets
            try (InputStream is = context.getAssets().open("anhviet.json")) {
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                String json = new String(buffer, "UTF-8");

                // Parse JSON
                Gson gson = new Gson();
                List<DictionaryEntry> entries = gson.fromJson(json, new TypeToken<List<DictionaryEntry>>(){}.getType());

                // Lưu vào Room
                db.dictionaryDao().insertAll(entries);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
