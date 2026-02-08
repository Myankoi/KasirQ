package kasirq.ui.form.user;


import java.util.List;
import javaswingdev.form.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import kasirq.model.Role;
import kasirq.model.User;
import kasirq.service.RoleService;
import kasirq.service.UserService;

public class Users extends javax.swing.JPanel {
    
    private final UserService userService = new UserService();
    private Integer selectedUserId = null;


    public Users() {
        initComponents();
        setupUserTable();
        loadUsers();
        loadRoles();

        tblProducts.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                onUserSelected();
            }
        });
    }
    
    private void setupUserTable() {

        String[] columns = {
            "ID",
            "Username",
            "First Name",
            "Last Name",
            "RoleID",
            "Role",
            "Phone"
        };


        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblProducts.setModel(model);

        // hide ID
        tblProducts.getColumnModel().getColumn(0).setMinWidth(0);
        tblProducts.getColumnModel().getColumn(0).setMaxWidth(0);
        
        tblProducts.getColumnModel().getColumn(4).setMinWidth(0);
        tblProducts.getColumnModel().getColumn(4).setMaxWidth(0);
    }
    
    private void loadRoles() {
        try {
            cbRole.removeAllItems();

            RoleService service = new RoleService();
            List<Role> roles = service.getRoles();

            cbRole.setItems(roles);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Failed to load roles\n" + e.getMessage());
        }
    }

    
    private void loadUsers() {
        try {
            DefaultTableModel model =
                (DefaultTableModel) tblProducts.getModel();
            model.setRowCount(0);

            List<User> users = userService.getUsers();
            for (User u : users) {
               String roleName = u.getRoleId() == 1 ? "Admin" : "Cashier";
               model.addRow(new Object[]{
                    u.getId(),
                    u.getUsername(),
                    u.getFirstname(),
                    u.getLastname(),
                    u.getRoleId(),
                    roleName,
                    u.getPhoneNumber()
                });


            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void onUserSelected() {
        int row = tblProducts.getSelectedRow();
        if (row < 0) return;

        selectedUserId = Integer.parseInt(
            tblProducts.getValueAt(row, 0).toString()
        );

        tbUsername.setText(tblProducts.getValueAt(row, 1).toString());
        tbFirstName.setText(tblProducts.getValueAt(row, 2).toString());
        tbLastName.setText(tblProducts.getValueAt(row, 3).toString());
        cbRole.setSelectedById(Integer.parseInt(tblProducts.getValueAt(row, 4).toString()));

        tbPhonenumber.setText(
            tblProducts.getValueAt(row, 6).toString()
        );

        // password jangan ditampilin
        tbPassword.setText("");
        tbPassword.setPlaceholder("Leave blank to keep current password");

    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel7 = new javaswingdev.swing.RoundPanel();
        jLabel27 = new javax.swing.JLabel();
        tbSearch = new javaswingdev.roundedtextfield.RoundedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javaswingdev.swing.table.Table();
        btnAdd = new javaswingdev.roundedbutton.RoundedButton();
        roundPanel6 = new javaswingdev.swing.RoundPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        tbFirstName = new javaswingdev.roundedtextfield.RoundedTextField();
        btnSave = new javaswingdev.roundedbutton.RoundedButton();
        jLabel30 = new javax.swing.JLabel();
        tbPassword = new javaswingdev.roundedtextfield.RoundedTextField();
        jLabel36 = new javax.swing.JLabel();
        tbUsername = new javaswingdev.roundedtextfield.RoundedTextField();
        jLabel31 = new javax.swing.JLabel();
        tbLastName = new javaswingdev.roundedtextfield.RoundedTextField();
        cbRole = new javaswingdev.roundedcombobox.RoundedComboBox();
        jLabel37 = new javax.swing.JLabel();
        tbPhonenumber = new javaswingdev.roundedtextfield.RoundedTextField();
        jLabel32 = new javax.swing.JLabel();

        setOpaque(false);

        roundPanel7.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel7.setRound(10);

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("User Management");

        tbSearch.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tbSearch.setToolTipText("");
        tbSearch.setPlaceholder("Search users...");

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Image", "Product Name", "Category", "Price", "Actions"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProducts);

        btnAdd.setText("+ Add New User");
        btnAdd.setHideActionText(true);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tbSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tbSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addGap(16, 16, 16))
        );

        roundPanel6.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel6.setRound(10);

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Manage User");

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("First Name");
        jLabel29.setToolTipText("");

        tbFirstName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tbFirstName.setToolTipText("");
        tbFirstName.setPlaceholder("First Name");

        btnSave.setText("Save");
        btnSave.setHideActionText(true);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Password");

        tbPassword.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tbPassword.setToolTipText("");
        tbPassword.setPlaceholder("Password");

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Username");

        tbUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tbUsername.setToolTipText("");
        tbUsername.setPlaceholder("Username");

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Last Name");

        tbLastName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tbLastName.setToolTipText("");
        tbLastName.setPlaceholder("Last Name");

        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Role");

        tbPhonenumber.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tbPhonenumber.setToolTipText("");
        tbPhonenumber.setPlaceholder("Phone Number");

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Phone Number");

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbRole, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbFirstName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbLastName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbPhonenumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbPhonenumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(roundPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (tbFirstName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "First name required");
            return;
        }

        if (tbUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username required");
            return;
        }

        try {
            User u = new User();
            u.setFirstname(tbFirstName.getText().trim());
            u.setLastname(tbLastName.getText().trim());
            u.setPhoneNumber(tbPhonenumber.getText().trim());

            Role role = (Role) cbRole.getSelectedItem();
            if (role == null) {
                JOptionPane.showMessageDialog(this, "Select role");
                return;
            }

            u.setRoleId(role.getId());


            String passwordInput = tbPassword.getText().trim();

            if (selectedUserId == null) {
                // ===== ADD USER =====
                if (passwordInput.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Password required");
                    return;
                }

                u.setUsername(tbUsername.getText().trim());
                u.setPassword(passwordInput);

                userService.add(u);
                JOptionPane.showMessageDialog(this, "User added");

            } else {
                // ===== UPDATE USER =====
                u.setId(selectedUserId);

                if (!passwordInput.isEmpty()) {
                    // update + password
                    u.setPassword(passwordInput);
                    userService.update(u, true);
                } else {
                    // update tanpa password
                    userService.update(u, false);
                }

                JOptionPane.showMessageDialog(this, "User updated");
            }

            clearForm();
            loadUsers();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed
    private void clearForm() {
        tbUsername.setText("");
        tbPassword.setText("");
        tbPassword.setPlaceholder("Password");
        tbFirstName.setText("");
        tbLastName.setText("");
        tbPhonenumber.setText("");
        cbRole.setSelectedIndex(-1);
        selectedUserId = null;
        tblProducts.clearSelection();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.roundedbutton.RoundedButton btnAdd;
    private javaswingdev.roundedbutton.RoundedButton btnSave;
    private javaswingdev.roundedcombobox.RoundedComboBox cbRole;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JScrollPane jScrollPane1;
    private javaswingdev.swing.RoundPanel roundPanel6;
    private javaswingdev.swing.RoundPanel roundPanel7;
    private javaswingdev.roundedtextfield.RoundedTextField tbFirstName;
    private javaswingdev.roundedtextfield.RoundedTextField tbLastName;
    private javaswingdev.roundedtextfield.RoundedTextField tbPassword;
    private javaswingdev.roundedtextfield.RoundedTextField tbPhonenumber;
    private javaswingdev.roundedtextfield.RoundedTextField tbSearch;
    private javaswingdev.roundedtextfield.RoundedTextField tbUsername;
    private javaswingdev.swing.table.Table tblProducts;
    // End of variables declaration//GEN-END:variables
}
