/*    
 *    MediathekView
 *    Copyright (C) 2012   W. Xaver
 *    W.Xaver[at]googlemail.com
 *    http://zdfmediathk.sourceforge.net/
 *    
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mediathek.gui.dialogEinstellungen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import mediathek.Daten;
import mediathek.Konstanten;
import mediathek.Log;
import mediathek.file.GetFile;
import mediathek.gui.beobachter.BeobWeb;
import mediathek.gui.dialog.DialogHilfe;
import mediathek.tool.GuiFunktionen;
import mediathek.tool.GuiFunktionenProgramme;

public class PanelProgrammPfade extends JPanel {

    public JDialog dialog = null;
    private boolean helpModal = false;
    private boolean vlc, flvstreamer, mplayer;

    public PanelProgrammPfade(boolean vvlc, boolean fflvstreamer, boolean mmplayer) {
        initComponents();
        vlc = vvlc;
        flvstreamer = fflvstreamer;
        mplayer = mmplayer;
        init();
        initBeob();
    }

    private void init() {
        jPanelVlc.setVisible(vlc);
        jPanelFlv.setVisible(flvstreamer);
        ///////////////mplayer
        if (Daten.system[Konstanten.SYSTEM_PFAD_VLC_NR].equals("")) {
            Daten.system[Konstanten.SYSTEM_PFAD_VLC_NR] = GuiFunktionenProgramme.getMusterPfadVlc();
        }
        if (Daten.system[Konstanten.SYSTEM_PFAD_FLVSTREAMER_NR].equals("")) {
            Daten.system[Konstanten.SYSTEM_PFAD_FLVSTREAMER_NR] = GuiFunktionenProgramme.getMusterPfadFlv();
        }
        jTextFieldVlc.setText(Daten.system[Konstanten.SYSTEM_PFAD_VLC_NR]);
        jTextFieldFlv.setText(Daten.system[Konstanten.SYSTEM_PFAD_FLVSTREAMER_NR]);
    }

    private void initBeob() {
        jTextFieldVlc.getDocument().addDocumentListener(new BeobDoc());
        jTextFieldFlv.getDocument().addDocumentListener(new BeobDoc());
        jTextFieldUrlVlc.setText(Konstanten.ADRESSE_WEBSITE_VLC);
        jTextFieldUrlVlc.addActionListener(new BeobWeb(Konstanten.ADRESSE_WEBSITE_VLC));
        jTextFieldUrlFlv.setText(Konstanten.ADRESSE_WEBSITE_FLVSTREAMER);
        jTextFieldUrlFlv.addActionListener(new BeobWeb(Konstanten.ADRESSE_WEBSITE_FLVSTREAMER));
        jButtonVlcPfad.addActionListener(new BeobPfad(jTextFieldVlc, true));
        jButtonFlvPfad.addActionListener(new BeobPfad(jTextFieldFlv, true));
        jButtonVlcSuchen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Daten.system[Konstanten.SYSTEM_PFAD_VLC_NR] = "";
                jTextFieldVlc.setText(GuiFunktionenProgramme.getMusterPfadVlc());
            }
        });
        jButtonFlvSuchen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Daten.system[Konstanten.SYSTEM_PFAD_FLVSTREAMER_NR] = "";
                jTextFieldFlv.setText(GuiFunktionenProgramme.getMusterPfadFlv());
            }
        });
        jButtonHilfe.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new DialogHilfe(null, helpModal, new GetFile().getHilfeSuchen(GetFile.PFAD_HILFETEXT_STANDARD_PSET)).setVisible(true);
            }
        });
    }

    private void check() {
        Daten.system[Konstanten.SYSTEM_PFAD_VLC_NR] = jTextFieldVlc.getText();
        Daten.system[Konstanten.SYSTEM_PFAD_FLVSTREAMER_NR] = jTextFieldFlv.getText();
        try {
            if (jTextFieldVlc.getText().equals("")) {
                jTextFieldVlc.setBackground(new Color(255, 200, 200));
            } else if (!new File(Daten.system[Konstanten.SYSTEM_PFAD_VLC_NR]).exists()) {
                jTextFieldVlc.setBackground(new Color(255, 200, 200));
            } else {
                jTextFieldVlc.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.background"));
            }
        } catch (Exception ex) {
            jTextFieldVlc.setBackground(new Color(255, 200, 200));
        }
        try {
            if (jTextFieldFlv.getText().equals("")) {
                jTextFieldFlv.setBackground(new Color(255, 200, 200));
            } else if (!new File(Daten.system[Konstanten.SYSTEM_PFAD_FLVSTREAMER_NR]).exists()) {
                jTextFieldFlv.setBackground(new Color(255, 200, 200));
            } else {
                jTextFieldFlv.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.background"));
            }
        } catch (Exception ex) {
            jTextFieldFlv.setBackground(new Color(255, 200, 200));
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanelVlc = new javax.swing.JPanel();
        jTextFieldVlc = new javax.swing.JTextField();
        jButtonVlcPfad = new javax.swing.JButton();
        jButtonVlcSuchen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldUrlVlc = new javax.swing.JTextField();
        jPanelFlv = new javax.swing.JPanel();
        jTextFieldFlv = new javax.swing.JTextField();
        jButtonFlvPfad = new javax.swing.JButton();
        jButtonFlvSuchen = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldUrlFlv = new javax.swing.JTextField();
        jButtonHilfe = new javax.swing.JButton();

        jPanelVlc.setBorder(javax.swing.BorderFactory.createTitledBorder("Pfad zum VLC-Player auswählen"));

        jButtonVlcPfad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/fileopen_16.png"))); // NOI18N

        jButtonVlcSuchen.setText("suchen");

        jLabel1.setText("Website:");

        jTextFieldUrlVlc.setEditable(false);
        jTextFieldUrlVlc.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextFieldUrlVlc.setForeground(new java.awt.Color(51, 51, 255));
        jTextFieldUrlVlc.setText("http://www.videolan.org/");
        jTextFieldUrlVlc.setBorder(null);

        javax.swing.GroupLayout jPanelVlcLayout = new javax.swing.GroupLayout(jPanelVlc);
        jPanelVlc.setLayout(jPanelVlcLayout);
        jPanelVlcLayout.setHorizontalGroup(
            jPanelVlcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVlcLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelVlcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVlcLayout.createSequentialGroup()
                        .addComponent(jTextFieldVlc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonVlcPfad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonVlcSuchen))
                    .addGroup(jPanelVlcLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUrlVlc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelVlcLayout.setVerticalGroup(
            jPanelVlcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVlcLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelVlcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextFieldVlc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVlcPfad)
                    .addComponent(jButtonVlcSuchen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelVlcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldUrlVlc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelVlcLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonVlcPfad, jTextFieldVlc});

        jPanelFlv.setBorder(javax.swing.BorderFactory.createTitledBorder("Pfad zum flvstreamer auswählen"));

        jButtonFlvPfad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/fileopen_16.png"))); // NOI18N

        jButtonFlvSuchen.setText("suchen");

        jLabel2.setText("Website:");

        jTextFieldUrlFlv.setEditable(false);
        jTextFieldUrlFlv.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextFieldUrlFlv.setForeground(new java.awt.Color(51, 51, 255));
        jTextFieldUrlFlv.setText("https://savannah.nongnu.org/projects/flvstreamer");
        jTextFieldUrlFlv.setBorder(null);

        javax.swing.GroupLayout jPanelFlvLayout = new javax.swing.GroupLayout(jPanelFlv);
        jPanelFlv.setLayout(jPanelFlvLayout);
        jPanelFlvLayout.setHorizontalGroup(
            jPanelFlvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFlvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFlvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFlvLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUrlFlv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 34, Short.MAX_VALUE))
                    .addGroup(jPanelFlvLayout.createSequentialGroup()
                        .addComponent(jTextFieldFlv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonFlvPfad)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonFlvSuchen)
                .addContainerGap())
        );
        jPanelFlvLayout.setVerticalGroup(
            jPanelFlvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFlvLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanelFlvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextFieldFlv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFlvPfad)
                    .addComponent(jButtonFlvSuchen))
                .addGap(18, 18, 18)
                .addGroup(jPanelFlvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldUrlFlv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelFlvLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonFlvPfad, jTextFieldFlv});

        jButtonHilfe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/help_16.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFlv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelVlc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonHilfe, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelVlc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelFlv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonHilfe)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButtonFlvPfad;
    private javax.swing.JButton jButtonFlvSuchen;
    private javax.swing.JButton jButtonHilfe;
    private javax.swing.JButton jButtonVlcPfad;
    private javax.swing.JButton jButtonVlcSuchen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelFlv;
    private javax.swing.JPanel jPanelVlc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldFlv;
    private javax.swing.JTextField jTextFieldUrlFlv;
    private javax.swing.JTextField jTextFieldUrlVlc;
    private javax.swing.JTextField jTextFieldVlc;
    // End of variables declaration//GEN-END:variables

    private class BeobDoc implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            check();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            check();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            check();
        }
    }

    private class BeobPfad implements ActionListener {

        private JTextField textField;
        private boolean datei;

        public BeobPfad(JTextField ttextField, boolean ddatei) {
            textField = ttextField;
            datei = ddatei;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int returnVal;
            JFileChooser chooser = new JFileChooser();
            if (datei) {
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            } else {
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            }
            chooser.setFileHidingEnabled(false);
            if (textField.getText().equals("")) {
                chooser.setCurrentDirectory(new File(GuiFunktionen.getHomePath()));
            } else {
                chooser.setCurrentDirectory(new File(textField.getText()));
            }
            returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    textField.setText(chooser.getSelectedFile().getAbsolutePath());
                } catch (Exception ex) {
                    Log.fehlerMeldung("PanelImportStandardProgramme.BeobPfad", ex);
                }
            }
        }
    }
}
