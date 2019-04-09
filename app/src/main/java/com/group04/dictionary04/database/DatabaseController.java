package com.group04.dictionary04.database;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.group04.dictionary.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.Language;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Entry;
import com.group04.dictionary04.model.default_Language;
import com.group04.dictionary04.model.default_Vocabulary;

public class DatabaseController {
    private final String dbKey = "DATABASE";
    private final String rootKey = "dictionary04";
    private SharedPreferences reader = null;
    private SharedPreferences.Editor editor = null;

    /*
        TODO Use this in your activity to save and restore the database
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        Dictionary d = dbController.getCurrentDatabase();
        dbController.saveCurrentDatabase(d);
     */

    /*
        TODO Use this in your activity to save a test Database to the local storage
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.saveTestDatabase();
        Dictionary d = dbController.getCurrentDatabase();
     */

    public DatabaseController(Context context) {
        editor = context.getSharedPreferences("DATABASE", context.MODE_PRIVATE).edit();
        reader = context.getSharedPreferences("DATABASE", context.MODE_PRIVATE);
    }

    public void saveTestDatabase() {
        default_Dictionary dict = new default_Dictionary();

        dict.addTranslation("Auto", "car", LanguageIdentifier.DE, LanguageIdentifier.EN);
        dict.addTranslation("Haus", "house", LanguageIdentifier.DE, LanguageIdentifier.EN);
        dict.addTranslation("Rucksack", "backpack", LanguageIdentifier.DE, LanguageIdentifier.EN);
        dict.addTranslation("Aufzug", "elevator", LanguageIdentifier.DE, LanguageIdentifier.EN);

        dict.addTranslation("Auto", "coche", LanguageIdentifier.DE, LanguageIdentifier.SP);
        dict.addTranslation("Haus", "casa", LanguageIdentifier.DE, LanguageIdentifier.SP);
        dict.addTranslation("Rucksack", "mochila", LanguageIdentifier.DE, LanguageIdentifier.SP);
        dict.addTranslation("Aufzug", "ascensor", LanguageIdentifier.DE, LanguageIdentifier.SP);

        Gson gson = new Gson();
        String dictString = gson.toJson(dict);
        editor.putString("dictionary04", dictString);
        editor.commit();
    }

    public void saveCurrentDatabase(default_Dictionary dict) {
        Gson gson = new Gson();
        String dictString = gson.toJson(dict);
        editor.putString("dictionary04", dictString);
        editor.commit();
    }

    public default_Dictionary getCurrentDatabase() {
        try {
            Gson gson = new Gson();
            String dictString = reader.getString("dictionary04", null);
            default_Dictionary d = gson.fromJson(dictString, default_Dictionary.class);
            if(d == null)
            {
                d = new default_Dictionary();
            }
            return d;
        } catch(Exception e) {
            return null;
        }
    }


}
