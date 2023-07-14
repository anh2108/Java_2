/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ASSIGNMENT;

import Lab5_6.Bai_lab;
import com.sun.source.tree.BreakTree;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Soundbank;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class ASS_FORM extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form ASS_FORM
     */
    ArrayList<ASS_PACKAGE> listDoan = new ArrayList<>();

    public ASS_FORM() {
        initComponents();
        themds();
        loadTable(listDoan);
        Thread t = new Thread(this);
        t.start();

    }

    private void clearForm() {
        txtmada.setText("");
        txttenda.setText("");
        txtsoluong.setText("");
        txtsotien.setText("");

    }

    public void themds() {

        listDoan.add(new ASS_PACKAGE(1, "Bánh mì xíu mại", 1, new BigDecimal(30000), 2, 1, 2, new BigDecimal(30000)));
        listDoan.add(new ASS_PACKAGE(2, "Bánh mì nhân thịt nướng", 2, new BigDecimal(30000), 1, 1, 1, new BigDecimal(30000)));
        listDoan.add(new ASS_PACKAGE(3, "Bánh mì nhân xúc xích", 3, new BigDecimal(70000), 4, 2, 2, new BigDecimal(140000)));
        listDoan.add(new ASS_PACKAGE(4, "Bánh mì nhân gà nướng tiêu", 1, new BigDecimal(30000), 1, 1, 2, new BigDecimal(30000)));
        listDoan.add(new ASS_PACKAGE(5, "Bánh mì nhân cà ri ", 1, new BigDecimal(30000), 2, 1, 2, new BigDecimal(30000)));
        listDoan.add(new ASS_PACKAGE(6, "Bánh mig nhân thịt nguội", 3, new BigDecimal(70000), 4, 2, 2, new BigDecimal(140000)));
        listDoan.add(new ASS_PACKAGE(7, "Bánh mì pate trứng", 0, new BigDecimal(40000), 2, 1, 2, new BigDecimal(80000)));
        listDoan.add(new ASS_PACKAGE(8, "Bánh mì kẹp chả lụa", 1, new BigDecimal(30000), 2, 1, 2, new BigDecimal(30000)));
        listDoan.add(new ASS_PACKAGE(9, "Bánh mì bơ ruốc", 1, new BigDecimal(30000), 2, 1, 2, new BigDecimal(30000)));
        listDoan.add(new ASS_PACKAGE(10, "Bánh mì bơ ruốc", 1, new BigDecimal(30000), 2, 1, 2, new BigDecimal(30000)));

    }

    public void dongia() {

        int chon = cbbchonthem.getSelectedIndex();

        BigDecimal sizeMoney = moneybaseonsize(cbbsize.getSelectedIndex());
        if (chon == 0) {

            txtdongia.setText(String.valueOf(sizeMoney.add(BigDecimal.valueOf(10000))));

        } else if (chon == 1) {

            txtdongia.setText(String.valueOf(sizeMoney.add(BigDecimal.valueOf(20000))));

        } else if (chon == 2) {

            txtdongia.setText(String.valueOf(sizeMoney.add(BigDecimal.valueOf(30000))));

        } else if (chon == 3) {

            txtdongia.setText(String.valueOf(sizeMoney.add(BigDecimal.valueOf(40000))));
        } else if (chon == 4) {

            txtdongia.setText(String.valueOf(sizeMoney.add(BigDecimal.valueOf(0))));
        }
    }

    public BigDecimal moneybaseonsize(int selected) {
        if (selected == 0) {
            return BigDecimal.valueOf(10000);
        } else if (selected == 1) {
            return BigDecimal.valueOf(20000);
        } else if (selected == 2) {
            return BigDecimal.valueOf(30000);
        } else if (selected == 3) {
            return BigDecimal.valueOf(40000);
        }
        return BigDecimal.ZERO;
    }

    public ASS_PACKAGE getFormData() {

        int masp = tbbang.getRowCount() + 1;
        BigDecimal soTien = BigDecimal.ZERO;
        BigDecimal donGia = BigDecimal.ZERO;
        int soluong = 0;

        if (txttenda.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Bạn cần nhập tên sản phẩm");
            return null;
        }
        try {
            soluong = Integer.parseInt(txtsoluong.getText());
            if (soluong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                return null;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên và không được bỏ trống");
            return null;
        }

        int sizeSp = cbbsize.getSelectedIndex();
        try {
            soTien = BigDecimal.valueOf(Double.parseDouble(txtsotien.getText()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        try {
            donGia = BigDecimal.valueOf(Double.parseDouble(txtdongia.getText()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String tensp = txttenda.getText();
        int chonthem = cbbchonthem.getSelectedIndex();
        int dogion = cbbdogion.getSelectedIndex();

        return new ASS_PACKAGE(masp, tensp, sizeSp, soTien, chonthem, dogion, soluong, donGia);
    }

    public String returnSize(int size) {
        if (size == 0) {
            return "Vỏ bánh mì than tre";
        } else if (size == 1) {
            return "Vỏ bánh mì baguette";
        } else if (size == 2) {
            return "Vỏ bánh mì bông";
        } else if (size == 3) {
            return "Vỏ bánh mì truyền thống";
        }
        return null;
    }

    public String returnDogin(int dogion) {

        if (dogion == 0) {
            return ("Giòn vừa");
        }
        if (dogion == 1) {
            return ("Giòn 70%");
        }
        if (dogion == 2) {
            return ("Giòn 100%");

        }
        return null;
    }

    public String returnTopping(int topping) {

        if (topping == 0) {
            return ("Coca");
        }
        if (topping == 1) {
            return ("Pepsi");
        }
        if (topping == 2) {
            return ("1 Coca + Thêm nhân");
        }
        if (topping == 3) {
            return ("1 Pepsi + 1 Trái cây tráng miệng");
        }
        if (topping == 4) {

            return ("Không chọn thêm");
        }

        return null;
    }

    public void timtheoten() {
        String tentim = txttimkiem.getText();
        if (tentim.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this,"Bạn hãy điền nội dung tìm kiếm");
            loadTable(listDoan);
        }

        ArrayList<ASS_PACKAGE> listtim = new ArrayList<>();
        for (ASS_PACKAGE doan : listDoan) {
            if (doan.getTenSp().contains(tentim)) {
                listtim.add(doan);
                loadTable(listtim);
            }
          
        }
        loadTable(listtim);
        return;
    }

    public void loadTable(ArrayList<ASS_PACKAGE> listx) {

        DefaultTableModel model = (DefaultTableModel) tbbang.getModel();
        model.setColumnCount(0);
        model.addColumn("Mã đồ ăn");
        model.addColumn("Tên đồ ăn");
        model.addColumn("Size đồ ăn ");
        model.addColumn("Độ giòn");
        model.addColumn("Chọn thêm");
        model.addColumn("Đơn giá");
        model.addColumn("Số lượng");
        model.addColumn("Tổng tiền");
        model.setRowCount(0);

        for (ASS_PACKAGE a : listx) {
            Object[] data = {
                a.getMaSp(),
                a.getTenSp(),
                returnSize(a.getSizeSp()),
                returnDogin(a.getDogion()),
                returnTopping(a.getChonthem()),
                a.getDownGia(),
                a.getSoLuong(),
                a.getSoTien()
            };

            model.addRow(data);

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jblql = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmada = new javax.swing.JTextField();
        txttenda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtsotien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JTextField();
        txtdongia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbbsize = new javax.swing.JComboBox<>();
        btnluu = new javax.swing.JButton();
        btnhienthi = new javax.swing.JButton();
        btncapnhat = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnthem = new javax.swing.JButton();
        btnthoat = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbbang = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cbbchonthem = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbbdogion = new javax.swing.JComboBox<>();
        txtClock = new javax.swing.JTextField();
        btntimkiem = new javax.swing.JButton();
        txttimkiem = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jblql.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jblql.setForeground(new java.awt.Color(0, 0, 0));
        jblql.setText("QUẢN LÍ BÁNH MÌ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Mã đồ ăn");

        txtmada.setEditable(false);
        txtmada.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txttenda.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txttenda.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txttendaCaretUpdate(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Tên đồ ăn");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Vỏ Bánh");

        txtsotien.setEditable(false);
        txtsotien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsotien.setText("0");
        txtsotien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsotienActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Tổng tiền");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Số lượng");

        txtsoluong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsoluong.setText("0");
        txtsoluong.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtsoluongCaretUpdate(evt);
            }
        });
        txtsoluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsoluongActionPerformed(evt);
            }
        });
        txtsoluong.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtsoluongPropertyChange(evt);
            }
        });

        txtdongia.setEditable(false);
        txtdongia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtdongia.setText("10000");
        txtdongia.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtdongiaCaretUpdate(evt);
            }
        });
        txtdongia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdongiaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Đơn giá");

        cbbsize.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cbbsize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vỏ bánh mì than tre", "Vỏ bánh mì baguette", "Vỏ bánh mì bông", "Vỏ bánh mì truyền thống", " ", " " }));
        cbbsize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbsizeItemStateChanged(evt);
            }
        });
        cbbsize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbsizeActionPerformed(evt);
            }
        });

        btnluu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        btnhienthi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnhienthi.setText("Hiển thị");
        btnhienthi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhienthiActionPerformed(evt);
            }
        });

        btncapnhat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btncapnhat.setText("Cập nhật");
        btncapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapnhatActionPerformed(evt);
            }
        });

        btnxoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnthem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnthoat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnthoat.setText("Thoát");
        btnthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatActionPerformed(evt);
            }
        });

        tbbang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "null", "null"
            }
        ));
        tbbang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbbang);
        if (tbbang.getColumnModel().getColumnCount() > 0) {
            tbbang.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Thêm Topping");

        cbbchonthem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbbchonthem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Coca", "1 Pepsi", "1 Coca + Thêm nhân", "1 Pepsi + 1 Trái cây tráng miệng", "Không chọn thêm", " " }));
        cbbchonthem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbchonthemItemStateChanged(evt);
            }
        });
        cbbchonthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbchonthemActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Độ giòn vỏ");

        cbbdogion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cbbdogion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giòn  vừa", "Giòn 70%", "Giòn 100%", " " }));

        txtClock.setBackground(new java.awt.Color(255, 255, 255));
        txtClock.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtClock.setForeground(new java.awt.Color(0, 0, 0));
        txtClock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClock.setText("00:00");

        btntimkiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btntimkiem.setText("Tìm kiếm");
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        txttimkiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Tìm kiếm đa thuộc tính");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbdogion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbsize, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmada)
                    .addComponent(txtsoluong))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                        .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txttenda, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbchonthem, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btntimkiem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jblql, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(306, 306, 306))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnluu)
                .addGap(18, 18, 18)
                .addComponent(btnhienthi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnthem)
                .addGap(18, 18, 18)
                .addComponent(btncapnhat)
                .addGap(24, 24, 24)
                .addComponent(btnxoa)
                .addGap(66, 66, 66))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtClock, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnthoat))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(34, 34, 34)
                        .addComponent(txtsotien, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jblql, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbchonthem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbdogion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(101, 101, 101))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btntimkiem))
                                .addGap(29, 29, 29)
                                .addComponent(jButton1)
                                .addGap(36, 36, 36)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnluu)
                            .addComponent(btnhienthi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncapnhat)
                            .addComponent(btnthem)
                            .addComponent(btnxoa)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsotien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClock, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthoat))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatActionPerformed
        // TODO add your handling code here:
        JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thoát?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        System.exit(WIDTH);

    }//GEN-LAST:event_btnthoatActionPerformed

    private void cbbsizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbsizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbsizeActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("List_do_an.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println(listDoan.size());
            oos.writeObject(listDoan);
            oos.close();
            JOptionPane.showMessageDialog(this, "Lưu thành công");

        } catch (FileNotFoundException ex) {
            System.out.println("Khong tim thay file");
        } catch (IOException ex) {
            ex.printStackTrace();

        }


    }//GEN-LAST:event_btnluuActionPerformed

    private void cbbsizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbsizeItemStateChanged

        dongia();

    }//GEN-LAST:event_cbbsizeItemStateChanged

    public void hienthi() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("List_do_an.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listDoan = (ArrayList<ASS_PACKAGE>) ois.readObject();
            System.out.println(listDoan.size());
            ois.close();
            loadTable(listDoan);
        } catch (FileNotFoundException ex) {
            System.out.println("Không tìm thấy file");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Lỗi đọc file");
        } catch (ClassNotFoundException ex) {
            System.out.println("Không tìm thấy class");
        }

    }

    private void btnhienthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhienthiActionPerformed
        hienthi();

    }//GEN-LAST:event_btnhienthiActionPerformed

    private void txtsoluongPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtsoluongPropertyChange
        System.out.println(txtsoluong.getText());
    }//GEN-LAST:event_txtsoluongPropertyChange

    private void txtsoluongCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtsoluongCaretUpdate

        if (txtdongia.getText().equalsIgnoreCase("")) {
            return;
        } else {
            try {
                int sl = Integer.parseInt(txtsoluong.getText());
                if (sl < 0) {
                    return;
                } else {
                    BigDecimal dongia = new BigDecimal(txtdongia.getText());
                    BigDecimal sotien = dongia.multiply(BigDecimal.valueOf(sl));
                    txtsotien.setText(sotien.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_txtsoluongCaretUpdate

    private void txtdongiaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtdongiaCaretUpdate
        if (txtdongia.getText().equalsIgnoreCase("")) {
            return;
        } else {
            try {
                int sl = Integer.parseInt(txtsoluong.getText());
                if (sl < 0) {
                    return;
                } else {
                    BigDecimal dongia = new BigDecimal(txtdongia.getText());
                    BigDecimal sotien = dongia.multiply(BigDecimal.valueOf(sl));
                    txtsotien.setText(sotien.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_txtdongiaCaretUpdate

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:

        ASS_PACKAGE PK = getFormData();

        if (PK != null) {
            JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm bản ghi?", "Xác nhận thêm", JOptionPane.YES_NO_OPTION);
            listDoan.add(PK);

            loadTable(listDoan);
            clearForm();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        }

    }//GEN-LAST:event_btnthemActionPerformed

    private void txtsotienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsotienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsotienActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) tbbang.getModel();

        int xoa = tbbang.getSelectedRow();

        if (xoa != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa bản ghi?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            listDoan.remove(xoa);
            model.removeRow(xoa);

            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else {

            int xoahet = tbbang.getRowCount();
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa hết các bản ghi?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (xoahet > 0) {

                for (int i = xoahet - 1; i >= 0; i--) {
                    listDoan.get(i).setSizeSp(2);
                    listDoan.remove(i);
                    model.removeRow(i);

                }
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            }

        }

    }//GEN-LAST:event_btnxoaActionPerformed

    private void tbbangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbangMouseClicked
        // TODO add your handling code here:

        int chon = tbbang.getSelectedRow();

        if (chon == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn bản ghi nào!");
            return;
        }

        String ma = tbbang.getValueAt(chon, 0).toString();
        txtmada.setText(ma);
        String ten = tbbang.getValueAt(chon, 1).toString();
        txttenda.setText(ten);
        cbbsize.setSelectedItem(tbbang.getValueAt(chon, 2));
        BigDecimal dongia = BigDecimal.valueOf(Long.parseLong(tbbang.getValueAt(chon, 7).toString()));
        txtdongia.setText(String.valueOf(dongia));
        try {
            int soluong = Integer.parseInt(tbbang.getValueAt(chon, 6).toString());
            txtsoluong.setText(String.valueOf(soluong));
        } catch (NumberFormatException ex) {
            // Xử lý lỗi ở đây
            ex.printStackTrace();
            System.out.println("Lỗi: Không thể chuyển đổi giá trị thành số nguyên");
        }
        BigDecimal tt = BigDecimal.valueOf(Long.parseLong(tbbang.getValueAt(chon, 5).toString()));
        txtsotien.setText(String.valueOf(tt));
        cbbchonthem.setSelectedItem(tbbang.getValueAt(chon, 4));
        cbbdogion.setSelectedItem(tbbang.getValueAt(chon, 3));


    }//GEN-LAST:event_tbbangMouseClicked

    private void cbbchonthemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbchonthemItemStateChanged
        // TODO add your handling code here:

        dongia();

    }//GEN-LAST:event_cbbchonthemItemStateChanged

    private void cbbchonthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbchonthemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbchonthemActionPerformed

    private void txtsoluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsoluongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsoluongActionPerformed

    private void txtdongiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdongiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdongiaActionPerformed

    private void btncapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapnhatActionPerformed

        getFormData();
        int ma = Integer.parseInt(txtmada.getText());
        for (ASS_PACKAGE doan : listDoan) {
            if (doan.getMaSp() == ma) {
                 JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn cập nhật lại?","Xác nhận", JOptionPane.YES_NO_OPTION);
                doan.setTenSp(txttenda.getText());
                doan.setSizeSp(cbbsize.getSelectedIndex());
                doan.setChonthem(cbbchonthem.getSelectedIndex());
                doan.setSoLuong(Integer.parseInt(txtsoluong.getText()));
                doan.setDownGia(new BigDecimal(txtdongia.getText()));
                doan.setDogion(cbbdogion.getSelectedIndex());
                doan.setSoTien(new BigDecimal(txtsotien.getText()));
               JOptionPane.showMessageDialog(this,"Cập nhật thành công");
            }
        }
        loadTable(listDoan);
    }//GEN-LAST:event_btncapnhatActionPerformed

    private void txttendaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txttendaCaretUpdate
        // TODO add your handling code here:       
//        timtheoten();
    }//GEN-LAST:event_txttendaCaretUpdate

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        // TODO add your handling code here:
        
        timtheoten();

        
    }//GEN-LAST:event_btntimkiemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         DefaultTableModel m = (DefaultTableModel) tbbang.getModel();
        
        TableRowSorter<DefaultTableModel> x = new TableRowSorter<DefaultTableModel>(m);
        
        tbbang.setRowSorter(x);
        
        x.setRowFilter(RowFilter.regexFilter(txttimkiem.getText()));
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ASS_FORM.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ASS_FORM.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ASS_FORM.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ASS_FORM.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ASS_FORM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncapnhat;
    private javax.swing.JButton btnhienthi;
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnthoat;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbchonthem;
    private javax.swing.JComboBox<String> cbbdogion;
    private javax.swing.JComboBox<String> cbbsize;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jblql;
    private javax.swing.JTable tbbang;
    private javax.swing.JTextField txtClock;
    private javax.swing.JTextField txtdongia;
    private javax.swing.JTextField txtmada;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txtsotien;
    private javax.swing.JTextField txttenda;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables

    public void test() {

//        DefaultTableModel m = (DefaultTableModel) tbbang.getModel();
//        
//        TableRowSorter<DefaultTableModel> x = new TableRowSorter<DefaultTableModel>(m);
//        
//        tbbang.setRowSorter(x);
//        
//        x.setRowFilter(RowFilter.regexFilter(, indices));
    }

    @Override
    public void run() {
        while (true) {
            try {
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
                txtClock.setText(format.format(now));

                String jblqlText = jblql.getText();
                String rotatedText = jblqlText.substring(jblqlText.length() - 1) + jblqlText.substring(0, jblqlText.length() - 1);
                jblql.setText(rotatedText);

                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ASS_FORM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
