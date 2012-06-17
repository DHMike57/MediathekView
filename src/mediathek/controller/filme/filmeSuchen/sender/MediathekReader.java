/*
 * MediathekView
 * Copyright (C) 2008 W. Xaver
 * W.Xaver[at]googlemail.com
 * http://zdfmediathk.sourceforge.net/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package mediathek.controller.filme.filmeSuchen.sender;

import java.util.Iterator;
import java.util.LinkedList;
import mediathek.Log;
import mediathek.controller.filme.filmeSuchenSender.FilmeSuchenSender;
import mediathek.controller.io.GetUrl;
import mediathek.daten.DatenFilm;
import mediathek.tool.GermanStringSorter;

/**
 *
 * @author
 */
public class MediathekReader implements Runnable {

    String nameSenderFilmliste = ""; // ist der Sendername in der Filmliste, bei ARTE gibt es da 2 Namen,
    String nameSenderMReader = ""; // ist der Name, den der Mediathekreader hat, der ist eindeutig und meist identisch mit dem "nameSenderFilmliste"
    int maxThreadLaufen = 4;
    long wartenSeiteLaden = 500;//ms, Basiswert zu dem dann der Faktor multipliziert wird
    //boolean senderOn = true;
    boolean updateOn = false;
    int threads = 0;
    int max = 0;
    int progress = 0;
    LinkedList<String[]> listeThemen = new LinkedList<String[]>();
    GetUrl getUrlIo;
    FilmeSuchenSender suchen;

    /**
     *
     * @param ddaten
     */
    public MediathekReader(FilmeSuchenSender ssearch, String nameMreader, int ssenderMaxThread, int ssenderWartenSeiteLaden) {
        suchen = ssearch;
        wartenSeiteLaden = ssenderWartenSeiteLaden;
        getUrlIo = new GetUrl(ssenderWartenSeiteLaden);
        nameSenderFilmliste = nameMreader; // ist meist gleich, wenn nicht muss er im MReader geändert werden
        nameSenderMReader = nameMreader;
        maxThreadLaufen = ssenderMaxThread;
    }
    //===================================
    // public 
    //===================================

//    public boolean istSenderAn() {
//        return senderOn;
//    }
//
//    public void setSenderAn(boolean an) {
//        senderOn = an;
//    }
    public boolean checkNameSenderFilmliste(String name) {
        // ist der Name der in der Tabelle Filme angezeigt wird
        // ARTE hat hier 2 Namen: ARTE.DE, ARTE.FR
        return nameSenderMReader.equalsIgnoreCase(name);
    }

    public String[] getNameSenderFilmliste() {
        return new String[]{nameSenderFilmliste};
    }

    public String getNameSenderMreader() {
        return nameSenderMReader;
    }

    @Override
    public void run() {
        //alles laden
        try {
            updateOn = false;
            threads = 0;
            addToList();
        } catch (Exception ex) {
            Log.fehlerMeldung(-397543600, "MediathekReader.run", ex, nameSenderMReader);
        }
    }

    void addToList() {
        //wird überschrieben, hier werden die Filme gesucht
    }

    void setInfo(int feld, String wert) {
        suchen.listeFilmeNeu.setInfo(feld, wert);
    }

    boolean addFilm(DatenFilm film) {
        return suchen.listeFilmeNeu.addFilmVomSender(film);
    }

    boolean istInListe(LinkedList<String[]> liste, String str, int nr) {
        boolean ret = false;
        Iterator<String[]> it = liste.listIterator();
        while (it.hasNext()) {
            if (it.next()[nr].equals(str)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    boolean istInListe(LinkedList<String> liste, String str) {
        boolean ret = false;
        Iterator<String> it = liste.listIterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    synchronized String[] getListeThemen() {
        return listeThemen.pollFirst();
    }

//    boolean themaLaden(String sender, String thema) {
//        boolean ret = true;
//        return ret;
//    }
    // Meldungen
    synchronized void meldungStart(int mmax) {
        max = mmax;
        progress = 0;
        suchen.melden(nameSenderMReader, max, progress, "" /* text */);
    }

    synchronized void meldungAddMax(int mmax) {
        max += mmax;
        suchen.melden(nameSenderMReader, max, progress, "" /* text */);
    }

    synchronized void meldungAddThread() {
        ++threads;
        suchen.melden(nameSenderMReader, max, progress, "" /* text */);
    }

    synchronized void meldungProgress(String text) {
        ++progress;
        suchen.melden(nameSenderMReader, max, progress, text);
    }

    synchronized void meldung(String text) {
        suchen.melden(nameSenderMReader, max, progress, text);
    }

    synchronized void meldungThreadUndFertig() {
        --threads;
        meldungFertig();
    }

    synchronized void meldungFertig() {
        //wird erst ausgeführt wenn alle Threads beendet sind
        if (threads <= 0) { // sonst läuft noch was
            suchen.meldenFertig(nameSenderMReader);
        } else {
            suchen.melden(nameSenderMReader, max, progress, "" /* text */);
        }
    }

    String addsUrl(String pfad1, String pfad2) {
        String ret = "";
        if (pfad1 != null && pfad2 != null) {
            if (!pfad1.equals("") && !pfad2.equals("")) {
                if (pfad1.charAt(pfad1.length() - 1) == '/') {
                    ret = pfad1.substring(0, pfad1.length() - 1);
                } else {
                    ret = pfad1;
                }
                if (pfad2.charAt(0) == '/') {
                    ret += pfad2;
                } else {
                    ret += '/' + pfad2;
                }
            }
        }
        if (ret.equals("")) {
            Log.fehlerMeldung(-469872800, "MediathekReader.addsUrl", pfad1 + " " + pfad2);
        }
        return ret;
    }

    static void listeSort(LinkedList<String[]> liste, int stelle) {
        //Stringliste alphabetisch sortieren
        GermanStringSorter sorter = GermanStringSorter.getInstance();
        if (liste != null) {
            String str1;
            String str2;
            for (int i = 1; i < liste.size(); ++i) {
                for (int k = i; k > 0; --k) {
                    str1 = liste.get(k - 1)[stelle];
                    str2 = liste.get(k)[stelle];
                    // if (str1.compareToIgnoreCase(str2) > 0) {
                    if (sorter.compare(str1, str2) > 0) {
                        liste.add(k - 1, liste.remove(k));
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
