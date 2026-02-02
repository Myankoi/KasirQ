package kasirq.ui.form.transactions;


import javaswingdev.form.*;

public class Transactions extends javax.swing.JPanel {

    public Transactions(String name) {
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel6 = new javaswingdev.swing.RoundPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        tbUsername10 = new javaswingdev.roundedtextfield.RoundedTextField();
        btnLogin7 = new javaswingdev.roundedbutton.RoundedButton();
        roundPanel7 = new javaswingdev.swing.RoundPanel();
        jLabel27 = new javax.swing.JLabel();
        tbUsername12 = new javaswingdev.roundedtextfield.RoundedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javaswingdev.swing.table.Table();
        roundPanel8 = new javaswingdev.swing.RoundPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        tbUsername11 = new javaswingdev.roundedtextfield.RoundedTextField();
        btnLogin8 = new javaswingdev.roundedbutton.RoundedButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javaswingdev.swing.table.Table();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();

        setOpaque(false);

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Add to Cart");

        jLabel26.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Select a product form the list");

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Quantity");

        tbUsername10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbUsername10.setToolTipText("");
        tbUsername10.setPlaceholder("Search products.....");

        btnLogin7.setText("Add to Cart");
        btnLogin7.setHideActionText(true);
        btnLogin7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogin7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbUsername10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogin7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(37, 37, 37)
                .addComponent(jLabel26)
                .addGap(39, 39, 39)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbUsername10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogin7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Available Products");

        tbUsername12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbUsername12.setToolTipText("");
        tbUsername12.setPlaceholder("Search products.....");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Image", "Product", "Price", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(0, 160, Short.MAX_VALUE))
                    .addComponent(tbUsername12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbUsername12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Transaction Cart");

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Quantity");

        tbUsername11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbUsername11.setToolTipText("");
        tbUsername11.setPlaceholder("Search products.....");

        btnLogin8.setText("Save Transaction");
        btnLogin8.setHideActionText(true);
        btnLogin8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogin8ActionPerformed(evt);
            }
        });

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product", "Qty", "Price", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table2);

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Total Items:");

        jLabel33.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("3");

        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Total:");

        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Rp 55.000");

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbUsername11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel8Layout.createSequentialGroup()
                        .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogin8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(roundPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(roundPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(16, 16, 16)))
                        .addContainerGap())
                    .addGroup(roundPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(94, 94, 94)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel8Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addGap(25, 25, 25)))
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(277, 277, 277)
                .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34))
                .addGap(36, 36, 36)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tbUsername11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(256, 256, 256)
                .addComponent(btnLogin8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel8Layout.createSequentialGroup()
                    .addGap(99, 99, 99)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(501, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogin7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogin7ActionPerformed

    private void btnLogin8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogin8ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.roundedbutton.RoundedButton btnLogin7;
    private javaswingdev.roundedbutton.RoundedButton btnLogin8;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javaswingdev.swing.RoundPanel roundPanel6;
    private javaswingdev.swing.RoundPanel roundPanel7;
    private javaswingdev.swing.RoundPanel roundPanel8;
    private javaswingdev.swing.table.Table table1;
    private javaswingdev.swing.table.Table table2;
    private javaswingdev.roundedtextfield.RoundedTextField tbUsername10;
    private javaswingdev.roundedtextfield.RoundedTextField tbUsername11;
    private javaswingdev.roundedtextfield.RoundedTextField tbUsername12;
    // End of variables declaration//GEN-END:variables
}
