/*    
 *    MediathekView
 *    Copyright (C) 2008   W. Xaver
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import mediathek.Daten;
import mediathek.Konstanten;
import mediathek.controller.filme.filmeImportieren.MediathekListener;
import mediathek.controller.io.ProgrammUpdateSuchen;
import mediathek.daten.DDaten;
import mediathek.gui.PanelVorlage;
import mediathek.gui.dialog.DialogHilfe;
import mediathek.tool.GuiFunktionen;
import mediathek.tool.GuiKonstanten;

public class PanelEinstellungen extends PanelVorlage {

    public PanelEinstellungen(DDaten d) {
        super(d);
        initComponents();
        ddaten = d;
        init();
        jCheckBoxEchtzeit.addActionListener(new BeobCheckBox());
        jSpinnerDownload.addChangeListener(new BeobSpinnerDownload());
        String[] theme = new String[GuiKonstanten.THEME.length];
        for (int i = 0; i < GuiKonstanten.THEME.length; ++i) {
            theme[i] = GuiKonstanten.THEME[i][0];
        }
        jComboBoxLook.setModel(new DefaultComboBoxModel(theme));
        if (Daten.system[Konstanten.SYSTEM_LOOK_NR].equals("")) {
            Daten.system[Konstanten.SYSTEM_LOOK_NR] = "1";
        }
        jComboBoxLook.setSelectedIndex(Integer.parseInt(Daten.system[Konstanten.SYSTEM_LOOK_NR]));
        jComboBoxLook.addActionListener(new BeobLook());
        jButtonHilfe.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new DialogHilfe(null, true, "\n"
                        + "Dieser Text wird als User-Agent\n"
                        + "an den Webserver übertragen. Das enstpricht\n"
                        + "der Kennung die auch die Browser senden.").setVisible(true);
            }
        });
        jRadioButtonAuto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setUserAgent();
            }
        });
        jRadioButtonManuel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setUserAgent();
            }
        });
        jTextFieldUserAgent.getDocument().addDocumentListener(new BeobUserAgent());
        Daten.addAdListener(new MediathekListener(MediathekListener.EREIGNIS_ANZAHL_DOWNLOADS, PanelEinstellungen.class.getSimpleName()) {

            @Override
            public void ping() {
                init();
            }
        });
        jCheckBoxSuchen.addActionListener(new BeobCheckBoxSuchen());
        jButtonSuchen.addActionListener(new BeobSuchen());
    }

    private void init() {
        jCheckBoxSuchen.setSelected(Boolean.parseBoolean(Daten.system[Konstanten.SYSTEM_UPDATE_SUCHEN_NR]));
        jCheckBoxEchtzeit.setSelected(Boolean.parseBoolean(Daten.system[Konstanten.SYSTEM_ECHTZEITSUCHE_NR]));
        // UserAgent
        jTextFieldAuto.setText(Konstanten.USER_AGENT_DEFAULT);
        jTextFieldUserAgent.setText(Daten.system[Konstanten.SYSTEM_USER_AGENT_NR]);
        if (Daten.isUserAgentAuto()) {
            jRadioButtonAuto.setSelected(true);
        } else {
            jRadioButtonManuel.setSelected(true);
        }
        jTextFieldUserAgent.setEditable(jRadioButtonManuel.isSelected());
        // Rest
        if (Daten.system[Konstanten.SYSTEM_MAX_DOWNLOAD_NR].equals("")) {
            jSpinnerDownload.setValue(1);
            Daten.system[Konstanten.SYSTEM_MAX_DOWNLOAD_NR] = "1";
        } else {
            jSpinnerDownload.setValue(Integer.parseInt(Daten.system[Konstanten.SYSTEM_MAX_DOWNLOAD_NR]));
        }
    }

    private void setUserAgent() {
        Daten.setGeaendert();
        if (jRadioButtonAuto.isSelected()) {
            Daten.setUserAgentAuto();
        } else {
            Daten.setUserAgentManuel(jTextFieldUserAgent.getText());
        }
        jTextFieldUserAgent.setEditable(jRadioButtonManuel.isSelected());
    }

    /** This method is called from within the constructor to
     *  initialize the form.
     *  WARNING: Do NOT modify this code. The content of this method is
     *  always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxLook = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jSpinnerDownload = new javax.swing.JSpinner();
        jCheckBoxEchtzeit = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldUserAgent = new javax.swing.JTextField();
        jButtonHilfe = new javax.swing.JButton();
        jRadioButtonAuto = new javax.swing.JRadioButton();
        jRadioButtonManuel = new javax.swing.JRadioButton();
        jTextFieldAuto = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jCheckBoxSuchen = new javax.swing.JCheckBox();
        jButtonSuchen = new javax.swing.JButton();

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Look and Feel:");

        jComboBoxLook.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("max. parallele Downloads beim Laden der Abos:");

        jSpinnerDownload.setModel(new javax.swing.SpinnerNumberModel(1, 1, 9, 1));

        jCheckBoxEchtzeit.setText("Echtzeitsuche im Suchfeld \"Titel/Thema\"");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxLook, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxEchtzeit)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerDownload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 74, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxLook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinnerDownload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxEchtzeit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("User-Agent"));

        jButtonHilfe.setText("Hilfe");

        buttonGroup1.add(jRadioButtonAuto);
        jRadioButtonAuto.setText("Auto:");

        buttonGroup1.add(jRadioButtonManuel);

        jTextFieldAuto.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonManuel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUserAgent))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonAuto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAuto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonHilfe)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jRadioButtonAuto)
                    .addComponent(jTextFieldAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonHilfe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jRadioButtonManuel)
                    .addComponent(jTextFieldUserAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonHilfe, jTextFieldAuto, jTextFieldUserAgent});

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Programmupdate"));

        jCheckBoxSuchen.setText("Einmal am Tag nach einer neuen Programmversion suchen");

        jButtonSuchen.setText("Jetzt suchen");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxSuchen)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButtonSuchen)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jCheckBoxSuchen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSuchen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonHilfe;
    private javax.swing.JButton jButtonSuchen;
    private javax.swing.JCheckBox jCheckBoxEchtzeit;
    private javax.swing.JCheckBox jCheckBoxSuchen;
    private javax.swing.JComboBox jComboBoxLook;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButtonAuto;
    private javax.swing.JRadioButton jRadioButtonManuel;
    private javax.swing.JSpinner jSpinnerDownload;
    private javax.swing.JTextField jTextFieldAuto;
    private javax.swing.JTextField jTextFieldUserAgent;
    // End of variables declaration//GEN-END:variables

    private class BeobSpinnerDownload implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent arg0) {
            Daten.system[Konstanten.SYSTEM_MAX_DOWNLOAD_NR] =
                    String.valueOf(((Number) jSpinnerDownload.getModel().getValue()).intValue());
            DDaten.setGeaendert();
            Daten.notifyMediathekListener(MediathekListener.EREIGNIS_ANZAHL_DOWNLOADS, PanelEinstellungen.class.getSimpleName());
        }
    }

    private class BeobUserAgent implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            tus();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            tus();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            tus();
        }

        private void tus() {
            Daten.setGeaendert();
            Daten.setUserAgentManuel(jTextFieldUserAgent.getText());
        }
    }

    private class BeobCheckBox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Daten.setGeaendert();
            Daten.system[Konstanten.SYSTEM_ECHTZEITSUCHE_NR] = Boolean.toString(jCheckBoxEchtzeit.isSelected());
        }
    }

    private class BeobLook implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (GuiFunktionen.setLook(ddaten.mediathekGui, Integer.parseInt(String.valueOf(jComboBoxLook.getSelectedIndex())))) {
                Daten.system[Konstanten.SYSTEM_LOOK_NR] = String.valueOf(jComboBoxLook.getSelectedIndex());
            } else {
                Daten.system[Konstanten.SYSTEM_LOOK_NR] = "1";
                jComboBoxLook.setSelectedIndex(1);
                GuiFunktionen.setLook(ddaten.mediathekGui, 1);
            }

        }
    }

    private class BeobCheckBoxSuchen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Daten.system[Konstanten.SYSTEM_UPDATE_SUCHEN_NR] = Boolean.toString(jCheckBoxSuchen.isSelected());
        }
    }

    private class BeobSuchen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ProgrammUpdateSuchen().checkVersion(ddaten, true);
        }
    }
}
