package com.example.puzzlegame.basededatos.typeconverters;

import androidx.room.TypeConverter;

import com.example.puzzlegame.model.Language;

import static com.example.puzzlegame.model.Language.*;

public class LanguageConverter {
    @TypeConverter
    public static Language toLanguage(int language) {
        if (language == EN.getCode()){
            return EN;
        } else if (language == ES.getCode()){
            return ES;
        } else if (language == CAT.getCode()){
            return CAT;
        } else {
            throw new IllegalArgumentException("Language not recognized");
        }
    }

    @TypeConverter
    public static int toInteger(Language language) {
        return language.getCode();
    }
}
