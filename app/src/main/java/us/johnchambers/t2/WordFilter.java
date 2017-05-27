package us.johnchambers.t2;

import java.util.HashMap;

/**
 * Created by johnchambers on 12/30/16.
 */

public class WordFilter {

    HashMap<String, Boolean> _badWords;

    public WordFilter() {
        loadBadWords();
    }

    public boolean isBad(String currWord) {
        if (_badWords.containsKey(currWord.toLowerCase()))
                return true;
        return false;
    }

    private void loadBadWords() {
        _badWords = new HashMap<>();

        _badWords.put("anal", true);
        _badWords.put("anus", true);
        _badWords.put("arse", true);
        _badWords.put("ass", true);
        _badWords.put("ballsack", true);
        _badWords.put("balls", true);
        _badWords.put("bastard", true);
        _badWords.put("bitch", true);
        _badWords.put("biatch", true);
        _badWords.put("bloody", true);
        _badWords.put("blowjob", true);
        _badWords.put("blow job", true);
        _badWords.put("bollock", true);
        _badWords.put("bollok", true);
        _badWords.put("boner", true);
        _badWords.put("boob", true);
        _badWords.put("bugger", true);
        _badWords.put("bum", true);
        _badWords.put("butt", true);
        _badWords.put("buttplug", true);
        _badWords.put("clitoris", true);
        _badWords.put("cock", true);
        _badWords.put("coon", true);
        _badWords.put("crap", true);
        _badWords.put("cunt", true);
        _badWords.put("damn", true);
        _badWords.put("dick", true);
        _badWords.put("dildo", true);
        _badWords.put("dyke", true);
        _badWords.put("fag", true);
        _badWords.put("feck", true);
        _badWords.put("fellate", true);
        _badWords.put("fellatio", true);
        _badWords.put("felching", true);
        _badWords.put("fuck", true);
        _badWords.put("f u c k", true);
        _badWords.put("fudgepacker", true);
        _badWords.put("fudge packer", true);
        _badWords.put("flange", true);
        _badWords.put("Goddamn", true);
        _badWords.put("God damn", true);
        _badWords.put("hell", true);
        _badWords.put("homo", true);
        _badWords.put("jerk", true);
        _badWords.put("jizz", true);
        _badWords.put("knobend", true);
        _badWords.put("knob end", true);
        _badWords.put("labia", true);
        _badWords.put("lmao", true);
        _badWords.put("lmfao", true);
        _badWords.put("muff", true);
        _badWords.put("nigger", true);
        _badWords.put("nigga", true);
        _badWords.put("omg", true);
        _badWords.put("penis", true);
        _badWords.put("piss", true);
        _badWords.put("poop", true);
        _badWords.put("prick", true);
        _badWords.put("pube", true);
        _badWords.put("pussy", true);
        _badWords.put("queer", true);
        _badWords.put("scrotum", true);
        _badWords.put("sex", true);
        _badWords.put("shit", true);
        _badWords.put("s hit", true);
        _badWords.put("sh1t", true);
        _badWords.put("slut", true);
        _badWords.put("smegma", true);
        _badWords.put("spunk", true);
        _badWords.put("tit", true);
        _badWords.put("tits", true);
        _badWords.put("titties", true);
        _badWords.put("titty", true);
        _badWords.put("tosser", true);
        _badWords.put("turd", true);
        _badWords.put("twat", true);
        _badWords.put("vagina", true);
        _badWords.put("wank", true);
        _badWords.put("whore", true);

    }

}
