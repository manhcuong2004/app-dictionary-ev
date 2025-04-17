package com.example.app_dictionary_ev;

import java.util.Date;

public class TranslationItem {
    private String sourceText;
    private String translatedText;
    private Date date;
    private boolean isExpanded;

    public TranslationItem(String sourceText, String translatedText, Date date) {
        this.sourceText = sourceText;
        this.translatedText = translatedText;
        this.date = date;
        this.isExpanded = false;

    }

    public String getSourceText() {
        return sourceText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public Date getDate() {
        return date;
    }
    public boolean isExpanded() {
        return isExpanded;
    }
    public void toggleExpanded() {
        isExpanded = !isExpanded;
    }
}
