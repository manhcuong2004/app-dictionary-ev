package com.example.app_dictionary_ev;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Translate extends AppCompatActivity
        implements TranslationHistoryAdapter.OnItemClickListener {

    private EditText sourceTextInput, translatedTextOutput;
    private ImageView volumeIconVi, volumeIconEn;
    private RelativeLayout rotateButton;
    private RecyclerView historyRecyclerView;
    private boolean isVietnameseToEnglish = true;
    private TextToSpeech textToSpeech;
    private List<TranslationItem> historyList = new ArrayList<>();
    private TranslationHistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate);

        // Initialize views
        sourceTextInput = findViewById(R.id.sourceTextInput);
        translatedTextOutput = findViewById(R.id.translatedTextOutput);
        volumeIconVi = findViewById(R.id.volume_icon_vi);
        volumeIconEn = findViewById(R.id.volume_icon_en);
        rotateButton = findViewById(R.id.rotate_button);
        historyRecyclerView = findViewById(R.id.historyRecyclerView);

        CustomHeader customHeader = findViewById(R.id.customHeader);
        customHeader.setTitle("Dịch văn bản");

        // Setup RecyclerView
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new TranslationHistoryAdapter(historyList, this);
        historyRecyclerView.setAdapter(historyAdapter);

        // Event handlers
        rotateButton.setOnClickListener(v -> swapLanguages());

        volumeIconVi.setOnClickListener(v -> speakText(sourceTextInput.getText().toString()));
        volumeIconEn.setOnClickListener(v -> speakText(translatedTextOutput.getText().toString()));

        sourceTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed but required to implement
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed but required to implement
            }

            @Override public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) translateText();
                else translatedTextOutput.setText("");
            }
        });

        // Initialize TTS
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) updateTtsLanguage();
        });
    }

    private void swapLanguages() {
        isVietnameseToEnglish = !isVietnameseToEnglish;
        String source = sourceTextInput.getText().toString();
        String translated = translatedTextOutput.getText().toString();

        sourceTextInput.setText(translated);
        translatedTextOutput.setText(source);
        updateTtsLanguage();

        if (!source.isEmpty()) translateText();
    }

    private void translateText() {
        String text = sourceTextInput.getText().toString();
        // Implement your translation API call here
        // Then call addToHistory(source, translated);
    }

    private void addToHistory(String source, String translated) {
        historyList.add(0, new TranslationItem(source, translated, new Date()));
        historyAdapter.notifyItemInserted(0);
        historyRecyclerView.setVisibility(View.VISIBLE);
    }

    private void speakText(String text) {
        if (!text.isEmpty() && textToSpeech != null) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void updateTtsLanguage() {
        Locale locale = isVietnameseToEnglish ? Locale.ENGLISH : new Locale("vi");
        textToSpeech.setLanguage(locale);
    }

    // RecyclerView item click events
    @Override public void onExpandClick(int position) {
        // Toggle expand/collapse state for the history item
        TranslationItem item = historyList.get(position);
        item.toggleExpanded();
        historyAdapter.notifyItemChanged(position);
    }

    @Override public void onDeleteClick(int position) {
        historyList.remove(position);
        historyAdapter.notifyItemRemoved(position);
        if (historyList.isEmpty()) historyRecyclerView.setVisibility(View.GONE);
    }

    @Override protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

}