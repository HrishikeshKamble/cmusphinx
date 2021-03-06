package edu.cmu.sphinx.tools.corpus;

import java.util.ArrayList;
import java.util.List;

/**
 * A Word object describes an individual instance of a word in an Utterance.  It decribes a spelling (which can be mapped
 * to a pronunciation) which is associated with a region of audio data.
 */
public class Word implements Comparable {

    RegionOfAudioData regionOfAudioData;
    Utterance utterance;
    String spelling;

    public RegionOfAudioData getRegionOfAudioData() {
        return regionOfAudioData;
    }

    public void setRegionOfAudioData(RegionOfAudioData regionOfAudioData) {
        this.regionOfAudioData = regionOfAudioData;
    }

    public Utterance getUtterance() {
        return utterance;
    }

    private String getUtteranceShortName() {
        String s = utterance.getRegionOfAudioData().getAudioDatabase().getPcm().getPcmFile();
        String s1[] = s.split("_");
        //System.out.println("+++");
        //for( String s3 : s1 ) {
        //    System.out.println("["+s3+"]");
        //}
        String s4[] = s1[2].split("\\W");
        //for( String s5 : s4 ) {
        //    System.out.println("["+s5+"]");
        //}
        return s4[0];
    }

    public String toString() {
//        return CorpusBrowser.hex2Unicode(spelling) + " " + getUtteranceShortName() + " " + getBeginTime() + " " + getEndTime();
        return spelling + " " + getUtteranceShortName() + " " + getBeginTime() + " " + getEndTime();
    }

    public int getBeginTime() {
        return regionOfAudioData.getBeginTime();
    }

    public int getEndTime() {
        return regionOfAudioData.getBeginTime();
    }

    public Word() {
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public void setUtterance(Utterance utterance) {
        this.utterance = utterance;
    }

    public List<String> getCharacters() {
        String s = spelling;
        List<String> r = new ArrayList<String>();

        if (s.startsWith("<")) {
            r.add(s);
        } else {
            while (s.length() > 0) {
                String head = s.substring(0, 4);
                s = s.substring(4);
                r.add(head);
            }
        }
        return r;
    }

    public int compareTo(Object o) {
        return spelling.compareTo(((Word) o).spelling);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Word word = (Word) o;

        if (regionOfAudioData != null ? !regionOfAudioData.equals(word.regionOfAudioData) : word.regionOfAudioData != null)
            return false;
        if (spelling != null ? !spelling.equals(word.spelling) : word.spelling != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (regionOfAudioData != null ? regionOfAudioData.hashCode() : 0);
        result = 29 * result + (spelling != null ? spelling.hashCode() : 0);
        return result;
    }
}
