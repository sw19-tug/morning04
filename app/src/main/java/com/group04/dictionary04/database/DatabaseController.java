package com.group04.dictionary04.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Entry;
import com.group04.dictionary04.model.default_Language;
import com.group04.dictionary04.model.default_Vocabulary;

import java.io.*;

public class DatabaseController {
    private final String dbKey = "DATABASE";
    private final String rootKey = "dictionary04";
    private SharedPreferences reader = null;
    private SharedPreferences.Editor editor = null;
    private Context context = null;

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

    public DatabaseController(Context _context) {
        context = _context;
        editor = context.getSharedPreferences("DATABASE", context.MODE_PRIVATE).edit();
        reader = context.getSharedPreferences("DATABASE", context.MODE_PRIVATE);
    }

    public void saveTestDatabase() {
        default_Dictionary dict = new default_Dictionary();

        dict.addTranslation("Auto", "car", "1",LanguageIdentifier.DE, LanguageIdentifier.EN);
        dict.addTranslation("Haus", "house","1",LanguageIdentifier.DE, LanguageIdentifier.EN);
        dict.addTranslation("Rucksack", "backpack", "2",LanguageIdentifier.DE, LanguageIdentifier.EN);
        dict.addTranslation("Aufzug", "elevator", "3",LanguageIdentifier.DE, LanguageIdentifier.EN);

        dict.addTranslation("Auto", "coche", "1",LanguageIdentifier.DE, LanguageIdentifier.SP);
        dict.addTranslation("Haus", "casa", "2", LanguageIdentifier.DE, LanguageIdentifier.SP);
        dict.addTranslation("Rucksack", "mochila", "3", LanguageIdentifier.DE, LanguageIdentifier.SP);
        dict.addTranslation("Aufzug", "ascensor", "1", LanguageIdentifier.DE, LanguageIdentifier.SP);

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

    public void backupDatabase() {
        writeStringAsFile(reader.getString("dictionary04", null), "dict04-backup");
    }

    public void restoreDatabase(File path) {
        String backup = readFileAsString(path);
        editor.putString("dictionary04", backup);
        editor.commit();
    }

    public void clearTesting() {
        //TODO needs implementation once testing database is ready
    }

    public void clearDatabase() {
        saveCurrentDatabase(null);
        Toast.makeText(context, "Database cleared", Toast.LENGTH_LONG).show();
    }


    public void writeStringAsFile(final String fileContents, String fileName) {
        try {
            File f = new File(Environment.getExternalStorageDirectory(), fileName);
            Log.d("testststs", "" + f.getAbsoluteFile());
            FileWriter out = new FileWriter(f);
            out.write(fileContents);
            out.close();
            Toast.makeText(context, "Backup created in file: dict04-backup", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, "Backup-file could not be created", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public String readFileAsString(File fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(fileName));
            while ((line = in.readLine()) != null) stringBuilder.append(line);
            Toast.makeText(context, "Backup-file found successfully", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Backup-file not found", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, "Backup-file could not be read", Toast.LENGTH_LONG).show();
        }

        return stringBuilder.toString();
    }
}
