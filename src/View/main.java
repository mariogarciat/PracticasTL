package View;

import control.Reader;
import control.Txt_gramatica_parser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class main extends javax.swing.JFrame {

    public main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonCargar = new javax.swing.JButton();
        jButtonSimplificar = new javax.swing.JButton();
        jButtonAutomata = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaGramatica = new javax.swing.JTextArea();
        jButtonBorrar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jTextFileName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonCargar.setText("Cargar ");
        jButtonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarActionPerformed(evt);
            }
        });

        jButtonSimplificar.setText("Simplificar");
        jButtonSimplificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSimplificarActionPerformed(evt);
            }
        });

        jButtonAutomata.setText("Autómata");

        jLabel1.setText("Gramática");

        jTextAreaGramatica.setColumns(20);
        jTextAreaGramatica.setRows(5);
        jScrollPane1.setViewportView(jTextAreaGramatica);

        jButtonBorrar.setText("Borrar");
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jTextFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFileNameActionPerformed(evt);
            }
        });

        jLabel2.setText("File Name: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel2))
                                    .addComponent(jTextFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jButtonGuardar))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jButtonSimplificar)
                        .addGap(26, 26, 26)
                        .addComponent(jButtonAutomata)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGuardar)
                        .addGap(0, 82, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAutomata)
                    .addComponent(jButtonSimplificar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarActionPerformed
        if (jTextAreaGramatica.getText().isEmpty()) {
            // crea el JFileChooser
            final JFileChooser fc = new JFileChooser();

            // modifica dónde va a estar el directorio cuando se abra la ventana
            final String dir = System.getProperty("user.dir"); // retorna directorio del proyecto
            File currentFile = new File(dir); // crea un objeto tipo File con el path del proyecto
            fc.setCurrentDirectory(currentFile);

            // filtra los archivos que se muestran en el FileChooser. Sólo muestra archivos de texto
            FileFilter filter = new FileNameExtensionFilter("TXT File", "txt");
            fc.setFileFilter(filter);

            //retorna qué pasó con la ventana del FileChooser
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) { //verifica que se le haya dado click al botón "abrir"
                currentFile = fc.getSelectedFile();
                try {
                    mostarArchivoCargado(currentFile);
                } catch (IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Actualmente hay texto en el área de trabajo.\n\n"
                    + "Primero borre el área actual de trabajo y luego intente de nuevo cargar el archivo.");
        }
    }//GEN-LAST:event_jButtonCargarActionPerformed

    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed
        if (jTextAreaGramatica.getText().compareTo("") == 0) {
            return;
        }

        JFrame frame = new JFrame();
        String[] options = new String[2];
        options[0] = new String("Sí");
        options[1] = new String("No");
        String mensaje = "¿Está seguro que desea borrar\nel espacio de trabajo acutal?";
        int respuesta = JOptionPane.showOptionDialog(this, mensaje, "", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        // respuesta = 1 sí elige NO
        // respuesta = 0 sí elige SÍ
        if (respuesta == 0) {
            jTextAreaGramatica.setText(null);
        }

    }//GEN-LAST:event_jButtonBorrarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (jTextAreaGramatica.getText().compareTo("") != 0) {
            if (jTextFileName.getText().compareTo("") != 0) {
                // método para crear archivo
                // método para escribir en un archivo de texto
                JOptionPane.showMessageDialog(this, "Gramática guardada");
            } else {
                JOptionPane.showMessageDialog(this, "Asígnele un nombre al archivo");
            }
        } else {
            JOptionPane.showMessageDialog(this, "El área de trabajo está vacía");
        }

    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jTextFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFileNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFileNameActionPerformed

    private void jButtonSimplificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSimplificarActionPerformed

    }//GEN-LAST:event_jButtonSimplificarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAutomata;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonCargar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonSimplificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaGramatica;
    private javax.swing.JTextField jTextFileName;
    // End of variables declaration//GEN-END:variables

    public JButton getjButton1() {
        return jButtonCargar;
    }

    public void setjButton1(JButton jButton1) {
        this.jButtonCargar = jButton1;
    }

    public JButton getjButton2() {
        return jButtonSimplificar;
    }

    public void setjButton2(JButton jButton2) {
        this.jButtonSimplificar = jButton2;
    }

    public JButton getjButton3() {
        return jButtonAutomata;
    }

    public void setjButton3(JButton jButton3) {
        this.jButtonAutomata = jButton3;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTextArea getjTextArea1() {
        return jTextAreaGramatica;
    }

    public void setjTextArea1(JTextArea jTextArea1) {
        this.jTextAreaGramatica = jTextArea1;
    }

    private void mostarArchivoCargado(File archivo) throws FileNotFoundException, IOException {
        ArrayList<String> renglonesTexto = new ArrayList<>();

        FileReader f = new FileReader(archivo);
        renglonesTexto = Txt_gramatica_parser.getProductionArrayList_(f);

        jTextAreaGramatica.setText(renglonesTexto.get(0));
        for (int i = 1; i < renglonesTexto.size(); i++) {
            String textoActual = jTextAreaGramatica.getText();
            jTextAreaGramatica.setText(textoActual + "\n" + renglonesTexto.get(i));
        }
    }

}
