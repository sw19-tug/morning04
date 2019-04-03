package com.group04.dictionary04.Language;

import com.group04.dictionary.enums.LanguageIdentifier;
import com.group04.dictionary04.model.default_Language;
import com.group04.dictionary04.model.default_Vocabulary;

import org.junit.Before;
import org.junit.Test;

public class LanguageUnitTest {
    default_Language lang;

    final static int init_entries = 10;
    @Before
    public void setup() {
        lang.setLangName(LanguageIdentifier.EN);
        lang.setDisplayName("English");

        for (int i = 0; i < init_entries; i++){
            lang.addVocabulary(lang.getDisplayName() + "_voc_" + i);
        }
        assert lang.getVocabularies().size() == init_entries : "Unexpecte number of Voc Entries!\n";
    }

    @Test
    public void getVocByIDTest() {
        String id = "EN-0007";
        default_Vocabulary voc = lang.getVocByID(id);
        assert id.equals(voc.getId()) : "Language: Found Voc-ID does not match requested\n";
    }

}
