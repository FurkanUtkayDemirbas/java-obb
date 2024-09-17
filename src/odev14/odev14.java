package odev14;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;



public class odev14 extends JFrame {
    private JPanel panel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTable table1;
    private JTextField textField1;
    private JButton button1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton silmeButton;
    private JButton eklemeButton;
    private JButton guncellemeButton;

    DefaultTableModel model= new DefaultTableModel();

    Object[] kolonlar = {"Numara","Ad","Soyad","Email"};

    Object[] satirlar = new Object[4];

    DefaultComboBoxModel cmodel = new DefaultComboBoxModel();
    DefaultComboBoxModel cmodel2 = new DefaultComboBoxModel();

    odev14() {
        add(panel);
        setSize(700, 600);
        setTitle("Odev14");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        veritabani.baglan();
        String sql = "select * from ogrenci";
        tabloguncelle(sql);
        comboboxyukle(sql);



        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String deger=textField1.getText();
                tabloguncelle(deger);


            }
        });
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String sql = "select * from ogrenci where numara = " + comboBox1.getSelectedItem();
                tabloguncelle(sql);

            }
        });
        comboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String sql = "select * from ogrenci where ad = " + "'" + comboBox2.getSelectedItem().toString() + "'";
                tabloguncelle(sql);

            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int a = table1.getSelectedRow();
                textField2.setText(table1.getValueAt(a,0).toString());
                textField3.setText(table1.getValueAt(a,1).toString());
                textField4.setText(table1.getValueAt(a,2).toString());
                textField5.setText(table1.getValueAt(a,3).toString());

            }
        });
        silmeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ad = "delete from ogrenci where numara ="+textField2.getText();
                veritabani.sil(ad);
                String sql = "select * from ogrenci";
                tabloguncelle(sql);
            }
        });
        eklemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ekle = "insert into ogrenci (numara,ad,soyad,email) values("+
                        textField2.getText()+","+
                        "'" + textField3.getText()+"',"+
                        "'" + textField4.getText()+"',"+
                        "'" + textField5.getText()+"')";

                veritabani.ekle(ekle);
                String sql = "select * from ogrenci";
                tabloguncelle(sql);
            }
        });
        guncellemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ekle = "update ogrenci set "+
                        "ad='" + textField3.getText()+"',"+
                        "soyad='" + textField4.getText()+"',"+
                        "email='" + textField5.getText()+"'" +
                        "where numara="+textField2.getText();

                veritabani.guncelle(ekle);
                String sql = "select * from ogrenci";
                tabloguncelle(sql);



            }
        });
    }

     void comboboxyukle(String sql) {
         ResultSet rs = veritabani.listele(sql);
         model.setRowCount(0);
         model.setColumnCount(0);
         model.setColumnIdentifiers(kolonlar);


         try {

             while (rs.next()) {
                 satirlar[0] = rs.getInt("numara");
                 cmodel.addElement(satirlar[0]);
                 satirlar[1] = rs.getString("ad");
                 cmodel2.addElement(satirlar[1]);
                 satirlar[2] = rs.getString("soyad");
                 satirlar[3] = rs.getString("email");
                 model.addRow(satirlar);
             }
             comboBox1.setModel(cmodel);
             comboBox2.setModel(cmodel2);
         } catch (SQLException e) {
             System.out.println("Veritabanı hata verdi");
         }


     }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                odev14 odevim = new odev14();
                odevim.setVisible(true);
            }
        });
    }

    void tabloguncelle(String sql)
    {
        ResultSet rs = veritabani.listele(sql);
        model.setRowCount(0);
        model.setColumnCount(0);
        model.setColumnIdentifiers(kolonlar);


        try {

            while (rs.next()) {
                satirlar[0] = rs.getInt("numara");
                satirlar[1] = rs.getString("ad");
                satirlar[2] = rs.getString("soyad");
                satirlar[3] = rs.getString("email");
                model.addRow(satirlar);
            }
           table1.setModel(model);
        } catch (SQLException e) {
            System.out.println("Veritabanı hata verdi");
        }
    }


}
