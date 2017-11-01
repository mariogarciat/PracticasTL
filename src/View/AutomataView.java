/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Model.Automata;
import Model.Gramatica;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.stream.Stream;

/**
 *
 * @author mario.garciat
 */
public class AutomataView extends javax.swing.JFrame {

    /**
     * Creates new form AutomataView
     */
    public AutomataView() {
        initComponents();
        
        getContentPane().setBackground(Color.LIGHT_GRAY);
        
        try {
            //gramatica = new Gramatica("gramatica_lineal_derecha.txt");
            //gramatica = new Gramatica("gramatica_especial.txt");
            //gramatica = new Gramatica("gram.txt");
            gramatica = new Gramatica("wea.txt");
            gramatica.organizarGramatica();
        } catch (Exception ex) {
            Logger.getLogger(AutomataView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String auxEst[] = {"ESTADO"};
        String auxVal[] = {"Validación"};
        String[] nEstados = (String[]) gramatica.getNoTerminales().toArray(new String[gramatica.getNoTerminales().size()]);
        String[] nSimbolos = (String[]) gramatica.getTerminales().toArray(new String[gramatica.getTerminales().size()]);
        
        List<String> list =  new ArrayList<String>();
        Collections.addAll(list, nSimbolos); 
        list.remove("_");
        nSimbolos = list.toArray(new String[list.size()]);
        
        producciones = gramatica.getProducciones();
        
        String[] simbolos = Stream.of(auxEst,nSimbolos,auxVal).flatMap(Stream::of).toArray(String[]::new);
        
        String vect[] = new String[nEstados.length];
        String matriz[][] = new String[nEstados.length][nSimbolos.length+2];
       
        int l = 0, h = 0;
        while (l < nEstados.length) {
            System.out.println(nEstados[l]);
            Model.Produccion pro = producciones.get(h);
            if(buscar(pro.getLadoIzq().getNoTerminales().first(), vect) == false){
                matriz[l][0] = pro.getLadoIzq().getNoTerminales().first();
                vect[l] = pro.getLadoIzq().getNoTerminales().first();
                l=l+1;
            } 
            h=h+1;
            
        }
        nEstados = gramatica.getNoTerminalesOrganizados().toArray(nEstados);
        int k = 0;
        int z = 0;
        int w = 1;
        vect = new String[nSimbolos.length];
        while (z < nEstados.length){
            while (w <= nSimbolos.length){
                Model.Produccion produccion = producciones.get(k);
                if (!produccion.getLadoIzq().getNoTerminales().first().equals(nEstados[z])){
                    z = z + 1;
                    w = 1;
                    vect = new String[nSimbolos.length];
                }
                if(buscar(produccion.getLadoDer().getTerminales().first(), vect) == false ){
                    vect[w-1] = produccion.getLadoDer().getTerminales().first();
                    if(produccion.getLadoDer().getTerminales().first().equals("_")){                                
                        matriz[z][simbolos.length-1] = "1";
                        k +=1;
                        w = w-1;
                    }else{
                        matriz[z][w] = produccion.getLadoDer().getNoTerminales().first();
                        matriz[z][simbolos.length-1] = "0";
                        k +=1;
                    }
                }else{
                    int nz = buscarEnMatriz(matriz, produccion.getLadoIzq().getNoTerminales().first());
                    int nw = buscarEnVector(produccion.getLadoDer().getTerminales().first(), simbolos);
                    matriz[nz][nw] = matriz[nz][nw] + "," + produccion.getLadoDer().getNoTerminales().first();
                    k = k + 1;
                }
                w = w+1;
                if(w > nSimbolos.length){w = w-1;}
                if(k == producciones.size()){break;}
            }
            break;
        }
//        matriz = new String[4][4]; 
//        String[] sim = {"estado", "cero", "uno", "validacion"}; 
        //String matriz[][] = {{"sa", "fdsfa", "dfae"}, {"sa", "fdsfa", "dfae"}, {"sa", "fdsfa", "dfae"}};
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                simbolos
        ));
        btnVerificar.setEnabled(false);
        
        
    }

    
    String[] nEstados;
    //String[] producciones;
    ArrayList<Model.Produccion> producciones;
    String nSimbolos[];
    String matriz[][];
    String nombres[];
    String siguiente0[];
    String siguiente1[];
    String siguientes[];
    Model.Gramatica gramatica;
    int valida[];
    Model.Estado estado;
    ArrayList<Model.Estado> arreglo = new ArrayList<Model.Estado>();
    ArrayList<String> estados;
    ArrayList<Integer> aceptacion;
    ArrayList<String> simbolos;
    ArrayList<ArrayList<String>> transiciones;
    Model.Automata automata1;

    //Método que guarda el contenido de la tabla en una matriz de nFilas x 4 colúmnas    
    public String[][] guardarEnMatriz() {
        int i = 0;
        int j = 0;
        int nRow = jTable1.getRowCount();
        int nCol = jTable1.getColumnCount();
        String[][] matriz = new String[nRow][nCol];
        for (i = 0; i < nRow; i++) {
            for (j = 0; j < nCol; j++) {
//                if(jTable1.getValueAt(i, j) != null){
//                    //matriz[i][j] = (String) jTable1.getValueAt(i, j);
//                    matriz[i][j] = (String)jTable1.getValueAt(nRow, nCol);
//                    
//                }else{
//                    matriz[i][j] = "";
//                    
//                }
                matriz[i][j] = (String)jTable1.getValueAt(i, j);
                System.out.println(matriz[i][j]);
            }
        }
        return matriz;
    }
    
    //Método para realizar modificaciónes en la jTable, las modificaciones
    //dependerán del contenido de la matriz
    public void modificarTabla(String[][] matriz, String[] simbolos) {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                simbolos
        ));
    }
    
    public String[][] crearMatriz(ArrayList<String> estados, String[] simbolos, ArrayList<Integer> aceptacion, ArrayList<ArrayList<String>> transiciones) {
        String[][] matriz = new String[estados.size()][simbolos.length];
        for (int i = 0; i < estados.size(); i++) {
            matriz[i][0] = estados.get(i);
            for (int j = 1; j < simbolos.length-1; j++) {
                matriz[i][j] = transiciones.get(i).get(j-1);
            }
            matriz[i][simbolos.length-1] = aceptacion.get(i).toString();
        }
        return matriz;
    }
    
    public boolean buscar(String a, String[] v) {
        boolean encontrado = false;
        for (int i = 0; i < v.length; i++) {
            if (a.equalsIgnoreCase(v[i])) {
                encontrado = true;
                return encontrado;
            }
        }
        return encontrado;
    }
    
    public int buscarEnVector(String a, String[] v) {
        int k = 0;
        for (int i = 0; i < v.length; i++) {
            if(a.equalsIgnoreCase(v[i])){
                k = i;
                return k;
            }
        }
        return k;
    }
    
    public int buscarEnMatriz(String[][] m, String a){
        int k = 0;
        
        for (int i = 0; i < m.length; i++) {
            if(a.equalsIgnoreCase(m[i][0])){
                k = i;
                return k;
            }
        }
        return k;
    }
    
    public boolean revisaValida(int[] vector) {
        boolean acepta = true;
        for (int i = 0; i < vector.length; i++) {
            if ((vector[i] != 0) && (vector[i] != 1)) {
                acepta = false;
                JOptionPane.showMessageDialog(null, "Debe ingresar 0 o 1 en la colúmna de validación."
                        + "\nError con: " + vector[i] + " en fila " + i + " columna 3", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return acepta;
    }
    
    public boolean revisaTrancisiones0(String[] vector1, String[] vector2) {
        boolean acepta = false;
        for (int j = 0; j < vector2.length; j++) {
            if (buscar(vector1[j], vector2) == true) {
                acepta = true;
                return acepta;
            } else {
                JOptionPane.showMessageDialog(null, "La transición debe hacerse hacia uno de los estados."
                        + "\nError con: " + vector1[j] + " en fila " + j + " columna 1", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return acepta;
    }
    
    public boolean revisaTrancisiones1(String[] vector1, String[] vector2) {
        boolean acepta = false;
        for (int j = 0; j < vector1.length; j++) {
            if (buscar(vector1[j], vector2) == true) {
                acepta = true;
                return acepta;
            } else {
                JOptionPane.showMessageDialog(null, "La transición debe hacerse hacia uno de los estados."
                        + "\nError con: " + vector1[j] + " en fila " + j + " columna 1", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return acepta;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSimplificar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        btnConvertir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(71, 63, 109));

        btnSimplificar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSimplificar.setText("Simplificar");
        btnSimplificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimplificarActionPerformed(evt);
            }
        });

        btnVolver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnVerificar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVerificar.setText("Verificar hilera");

        btnConvertir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnConvertir.setText("Convertir");
        btnConvertir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConvertirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tabla de Transiciones");

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ESTADO", "0", "1", "ACEPTACIÓN"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btnConvertir)
                        .addGap(46, 46, 46)
                        .addComponent(btnSimplificar)
                        .addGap(56, 56, 56)
                        .addComponent(btnVerificar)
                        .addGap(70, 70, 70)
                        .addComponent(btnVolver))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConvertir)
                    .addComponent(btnSimplificar)
                    .addComponent(btnVerificar)
                    .addComponent(btnVolver))
                .addGap(30, 30, 30))
        );

        jPanel2.setBackground(new java.awt.Color(71, 63, 109));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DESCRIPCIÓN");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel2)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(71, 63, 109));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("EVALUACIÓN DE HILERA");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel3)
                .addContainerGap(122, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimplificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimplificarActionPerformed
        // TODO add your handling code here:
        
        if (!automata1.esVacio()) {

            String auxEst[] = {"ESTADO"};
            String auxVal[] = {"Validación"};
            nSimbolos = (String[]) gramatica.getTerminales().toArray(new String[gramatica.getTerminales().size()]);
            String[] simb = Stream.of(auxEst,nSimbolos,auxVal).flatMap(Stream::of).toArray(String[]::new);
            automata1.removerExtranos();
            automata1.simplificarEquivalentes();

            
            estados = automata1.getEstadosNuevos();
            aceptacion = automata1.getValidaNuevos();
            transiciones = automata1.getTransicionesNuevas();

            jTextArea1.setText(jTextArea1.getText() + "\nAUTÓMATA SIMPLIFICADO\n" + automata1.mostrarAutomata());
            matriz = crearMatriz(estados, simb, aceptacion, transiciones);
            modificarTabla(matriz, simb);
            btnVerificar.setEnabled(true);
            btnSimplificar.setEnabled(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Autómata vacío, no se puede simplificar", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSimplificarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        super.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnConvertirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvertirActionPerformed
        // TODO add your handling code here:
        matriz = guardarEnMatriz();
        
        String auxEst[] = {"ESTADO"};
        String auxVal[] = {"Validación"};
        nSimbolos = (String[]) gramatica.getTerminales().toArray(new String[gramatica.getTerminales().size()]);
        List<String> list =  new ArrayList<String>();
        Collections.addAll(list, nSimbolos); 
        list.remove("_");
        nSimbolos = list.toArray(new String[list.size()]);
        
        String[] simb = Stream.of(auxEst,nSimbolos,auxVal).flatMap(Stream::of).toArray(String[]::new);
        aceptacion = new ArrayList<>();
        
        ArrayList<String> simbolos = new ArrayList<String>();
        ArrayList<String> estados = gramatica.getNoTerminalesOrganizados();
        Collections.addAll(simbolos, nSimbolos); 
        
        ArrayList<String> transicion;
        transiciones = new ArrayList<>();
        
        for (int i = 0; i < estados.size(); i++) {
            aceptacion.add((Integer.parseInt(matriz[i][nSimbolos.length+1])));
            transicion = new ArrayList<>();
            for (int j = 1; j <= nSimbolos.length; j++) {
                transicion.add(matriz[i][j]);
            }
            transiciones.add(transicion);
        }
        automata1 = new Automata(estados, simbolos, aceptacion, transiciones);
        String tipo = automata1.tipoAutomata();
        if (tipo.equals("AFND")) {
            automata1.pasarAFDerterministico();
            estados = automata1.getEstadosNuevos();
            aceptacion = automata1.getValidaNuevos();
            transiciones = automata1.getTransicionesNuevas();
            jTextArea1.setText("AUTÓMATA FINITO NO DETERMINÍSTICO\n" + automata1.mostrarAutomata() + "\n");
            matriz = crearMatriz(estados, simb, aceptacion, transiciones);
            modificarTabla(matriz, simb);
            automata1 = new Model.Automata(estados, simbolos, aceptacion, transiciones);
        }
        jTextArea1.setText(jTextArea1.getText() + "AUTÓMATA FINITO DETERMINÍSTICO\n" + automata1.mostrarAutomata());
        btnSimplificar.setEnabled(true);
    }//GEN-LAST:event_btnConvertirActionPerformed

    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(AutomataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutomataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutomataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutomataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AutomataView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConvertir;
    private javax.swing.JButton btnSimplificar;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
