package kasirq.ui.form.transactions;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javaswingdev.form.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import kasirq.model.CartItem;
import kasirq.model.ClassRoom;
import kasirq.model.PaymentMethod;
import kasirq.model.Product;
import kasirq.model.TransactionRequest;
import kasirq.model.User;
import kasirq.service.ProductService;
import kasirq.service.ReferenceService;
import kasirq.service.TransactionService;
import kasirq.util.CurrencyUtil;

public class Transactions extends javax.swing.JPanel {

    private final List<CartItem> cartItems = new ArrayList<>();
    private final ProductService productService = new ProductService();
    
    private BigDecimal price;

    User user;

    public Transactions(User user) {
        initComponents();
        tbSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                loadProducts();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                loadProducts();
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        });
        
        tblProducts.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                onProductSelected();
            }
        });


        this.user = user;
        setupProductTable();
        loadProducts();
        loadPaymentMethod();
        loadClass();
        refreshCartTable();
    }
    
    
    private void loadPaymentMethod() {
        try {
            cbPayment.removeAllItems();

            ReferenceService service = new ReferenceService();
            List<PaymentMethod> methods = service.getPaymentMethods();

            cbPayment.setItems(methods);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to load payment methods\n" + e.getMessage());
        }
    }

    private void loadClass() {
        try {
            cbClass.removeAllItems();

            ReferenceService service = new ReferenceService();
            List<ClassRoom> classes = new ArrayList<>();
            classes.add(null);
            classes.addAll(service.getClasses());
            cbClass.setItems(classes);


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to load classes\n" + e.getMessage());
        }
    }
    
    private void setupProductTable() {

        String[] columns = {
            "ID",
            "ImagePath",
            "Product",
            "Price",
            "Stock"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblProducts.setModel(model);
        tblProducts.getColumnModel().getColumn(0).setMinWidth(0);
        tblProducts.getColumnModel().getColumn(0).setMaxWidth(0);
        tblProducts.getColumnModel().getColumn(0).setPreferredWidth(0);

        
        tblProducts.getColumnModel().getColumn(1).setMinWidth(0);
        tblProducts.getColumnModel().getColumn(1).setMaxWidth(0);
        tblProducts.getColumnModel().getColumn(1).setPreferredWidth(0);

    }
    
    private void onProductSelected() {

        int row = tblProducts.getSelectedRow();
        if (row < 0) return;

        String imagePath = tblProducts.getValueAt(row, 1).toString();
        String productName = tblProducts.getValueAt(row, 2).toString();
        BigDecimal price = new BigDecimal(
                tblProducts.getValueAt(row, 3).toString()
        );
        
        this.price = price;

        tbName.setText(productName);
        tbPrice.setText(CurrencyUtil.toRupiah(price));
        tbQuantity.setText("1");

        loadProductImage(imagePath);
    }
    
    private void loadProductImage(String imagePath) {
        try {
            image.setImage("/kasirq/" + imagePath);

        } catch (Exception e) {
            image.setImage(null);
        }
    }
    
    private void loadProducts() {

    try {
        String keyword = tbSearch.getText().trim();

        List<Product> products =
                productService.getProducts(keyword);

        DefaultTableModel model =
                (DefaultTableModel) tblProducts.getModel();
        model.setRowCount(0);

        for (Product p : products) {
            model.addRow(new Object[]{
                p.getId(),        // hidden
                p.getImagePath(),
                p.getName(),
                p.getPrice(),
                p.getStock()
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel6 = new javaswingdev.swing.RoundPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        tbQuantity = new javaswingdev.roundedtextfield.RoundedTextField();
        btnAdd = new javaswingdev.roundedbutton.RoundedButton();
        jLabel30 = new javax.swing.JLabel();
        tbPrice = new javaswingdev.roundedtextfield.RoundedTextField();
        jLabel36 = new javax.swing.JLabel();
        tbName = new javaswingdev.roundedtextfield.RoundedTextField();
        image = new javaswingdev.imagepanel.ImagePanel();
        roundPanel7 = new javaswingdev.swing.RoundPanel();
        jLabel27 = new javax.swing.JLabel();
        tbSearch = new javaswingdev.roundedtextfield.RoundedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javaswingdev.swing.table.Table();
        roundPanel8 = new javaswingdev.swing.RoundPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        tbBuyer = new javaswingdev.roundedtextfield.RoundedTextField();
        btnSave = new javaswingdev.roundedbutton.RoundedButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCart = new javaswingdev.swing.table.Table();
        jLabel32 = new javax.swing.JLabel();
        lblTotalItems = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        cbPayment = new javaswingdev.roundedcombobox.RoundedComboBox();
        cbClass = new javaswingdev.roundedcombobox.RoundedComboBox();
        jLabel38 = new javax.swing.JLabel();

        setOpaque(false);

        roundPanel6.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel6.setRound(10);

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Add to Cart");

        jLabel26.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Select a product form the list");

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Quantity");

        tbQuantity.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tbQuantity.setToolTipText("");
        tbQuantity.setPlaceholder("Enter the quantity");

        btnAdd.setText("Add to Cart");
        btnAdd.setHideActionText(true);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Price");

        tbPrice.setEditable(false);
        tbPrice.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tbPrice.setToolTipText("");
        tbPrice.setPlaceholder("Price");

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Product Name");

        tbName.setEditable(false);
        tbName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tbName.setToolTipText("");
        tbName.setPlaceholder("Name");

        image.setBackground(new java.awt.Color(204, 204, 204));
        image.setMinimumSize(new java.awt.Dimension(0, 100));

        javax.swing.GroupLayout imageLayout = new javax.swing.GroupLayout(image);
        image.setLayout(imageLayout);
        imageLayout.setHorizontalGroup(
            imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        imageLayout.setVerticalGroup(
            imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tbName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbQuantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addComponent(image, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(8, 8, 8)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel7.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel7.setRound(10);

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Available Products");

        tbSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbSearch.setToolTipText("");
        tbSearch.setPlaceholder("Search products.....");

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Product", "Price", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProducts);

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tbSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        roundPanel8.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel8.setRound(10);

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Transaction Cart");

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Buyer Name (Optional)");

        tbBuyer.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tbBuyer.setToolTipText("");
        tbBuyer.setPlaceholder("");

        btnSave.setText("Save Transaction");
        btnSave.setHideActionText(true);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblCart);

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Total Items:");

        lblTotalItems.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalItems.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalItems.setText("3");

        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(40, 167, 69));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Total:");

        lblTotalPrice.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTotalPrice.setForeground(new java.awt.Color(40, 167, 69));
        lblTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalPrice.setText("Rp 55.000");

        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Payment Method");

        jLabel38.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("Buyer Class (Optioinal)");

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbBuyer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(lblTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addComponent(lblTotalItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cbClass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(lblTotalItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(lblTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tbBuyer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        int row = tblProducts.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a product first");
            return;
        }

        int productId = Integer.parseInt(tblProducts.getValueAt(row, 0).toString());
        String productName = tblProducts.getValueAt(row, 2).toString();
        BigDecimal price = new BigDecimal(tblProducts.getValueAt(row, 3).toString());
        int stock = Integer.parseInt(tblProducts.getValueAt(row, 4).toString());

        int qty;
        try {
            qty = Integer.parseInt(tbQuantity.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity");
            return;
        }

        if (qty <= 0 || qty > stock) {
            JOptionPane.showMessageDialog(this, "Stock not enough");
            return;
        }

        // cek sudah ada di cart
        for (CartItem item : cartItems) {
            if (item.getProductId() == productId) {
                item.setQty(item.getQty() + qty);
                refreshCartTable();
                return;
            }
        }

        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setProductName(productName);
        item.setQty(qty);
        item.setPrice(price);

        cartItems.add(item);
        refreshCartTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void refreshCartTable() {
        
        tbName.setText("");
        tbPrice.setText("");
        tbQuantity.setText("");

        DefaultTableModel model =
            (DefaultTableModel) tblCart.getModel();
        model.setRowCount(0);

        int totalItems = 0;
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cartItems) {
            model.addRow(new Object[]{
                item.getProductName(),
                item.getQty(),
                item.getPrice(),
                item.getSubtotal()
            });

            totalItems += item.getQty();
            total = total.add(item.getSubtotal());
        }

        lblTotalItems.setText(String.valueOf(totalItems));
        lblTotalPrice.setText(CurrencyUtil.toRupiah(total));
    }

    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
      
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cart is empty");
            return;
        }

        PaymentMethod pm = (PaymentMethod) cbPayment.getSelectedItem();
        ClassRoom cls = (ClassRoom) cbClass.getSelectedItem();

        if (pm == null) {
            JOptionPane.showMessageDialog(this, "Please select payment method");
            return;
        }

        try {
            TransactionRequest req = new TransactionRequest();
            req.setUserId(user.getId());

            if (tbBuyer.getText() != "") {
                req.setBuyerName(tbBuyer.getText().trim());
            } else {
                req.setBuyerName(null);
            }
            
            // OPTIONAL
            if (cls != null) {
                req.setBuyerClassId(cls.getId());
            } else {
                req.setBuyerClassId(null);
            }

            req.setPaymentMethodId(pm.getId());
            req.setItems(cartItems);

            new TransactionService().saveTransaction(req);

            JOptionPane.showMessageDialog(this, "Transaction saved");
            tbBuyer.setText("");
            cartItems.clear();
            refreshCartTable();
            loadProducts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.roundedbutton.RoundedButton btnAdd;
    private javaswingdev.roundedbutton.RoundedButton btnSave;
    private javaswingdev.roundedcombobox.RoundedComboBox cbClass;
    private javaswingdev.roundedcombobox.RoundedComboBox cbPayment;
    private javaswingdev.imagepanel.ImagePanel image;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotalItems;
    private javax.swing.JLabel lblTotalPrice;
    private javaswingdev.swing.RoundPanel roundPanel6;
    private javaswingdev.swing.RoundPanel roundPanel7;
    private javaswingdev.swing.RoundPanel roundPanel8;
    private javaswingdev.roundedtextfield.RoundedTextField tbBuyer;
    private javaswingdev.roundedtextfield.RoundedTextField tbName;
    private javaswingdev.roundedtextfield.RoundedTextField tbPrice;
    private javaswingdev.roundedtextfield.RoundedTextField tbQuantity;
    private javaswingdev.roundedtextfield.RoundedTextField tbSearch;
    private javaswingdev.swing.table.Table tblCart;
    private javaswingdev.swing.table.Table tblProducts;
    // End of variables declaration//GEN-END:variables
}
