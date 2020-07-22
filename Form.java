package tugaskelompok;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Form extends JFrame implements ActionListener {
    
    private final JList list;
    private final JTextField textFieldNama, textFieldNim;
    private final JButton toList, listToFile, deleteList, readFile;
    private final  JLabel labelNama, labelNim;
    private final DefaultListModel<String> listModel;
    private boolean stop;
    private final File file = new File("./data.txt");
    
    public Form() {        
        setSize(600, 420);
        setTitle("TUGAS KELOMPOK");
        
        //(x,y,w,h)
        
        list = new JList();
        list.setBounds(23, 50, 270, 290);
        add(list);
        
        labelNama = new JLabel("Nama : ");
        labelNama.setBounds(310, 30, 50, 50);
        add(labelNama);
        
        textFieldNama = new JTextField();
        textFieldNama.setBounds(310, 65, 250, 30);
        add(textFieldNama);
        
        labelNim = new JLabel("NIM : ");
        labelNim.setBounds(310, 100, 50, 50);
        add(labelNim);
        
        textFieldNim = new JTextField();
        textFieldNim.setBounds(310, 135, 250, 30);
        add(textFieldNim);
        
        toList = new JButton("TAMBAH DATA KE LIST");
        toList.setBounds(310, 190, 250, 30);
        toList.addActionListener(this);
        add(toList);
        
        listToFile = new JButton("TULIS LIST KE FILE");
        listToFile.setBounds(310, 230, 250, 30);
        listToFile.addActionListener(this);
        add(listToFile);
        
        deleteList = new JButton("HAPUS LIST");
        deleteList.setBounds(310, 270, 250, 30);
        deleteList.addActionListener(this);
        add(deleteList);
        
        readFile = new JButton("BACA FILE");
        readFile.setBounds(310, 310, 250, 30);
        readFile.addActionListener(this);
        add(readFile);
        
        listModel = new DefaultListModel<>();
        stop = false;
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
    }
    
    public static void main(String[] args) {
        Form code = new Form();
        code.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == toList) {
            String nama = textFieldNama.getText();
            String nim = textFieldNim.getText();
            if(nama.equals("") || nama.equals("Tulis Disini") && nim.equals("") || nim.equals("Tulis Disini")) {
                textFieldNama.setText("Tulis Disini");
                textFieldNim.setText("Tulis Disini");
            } else {
                listModel.addElement(nama + " | " + nim);
                list.setModel(listModel);
                textFieldNama.setText("");
                textFieldNim.setText("");
            }
        } else if (ae.getSource() == listToFile) {
            int jmlList = list.getModel().getSize();
            PrintWriter writer = null;

            try {
                 writer = new PrintWriter(file);
                 writer.println(jmlList);
                 for (int i = 0; i < jmlList; i++) {
                     writer.println(list.getModel().getElementAt(i));
                 }
                 JOptionPane.showMessageDialog(this, "Berhasil Menulis Data ke File");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e);
            } finally {
                 writer.close();
            }
        } else if (ae.getSource() == deleteList) {
            listModel.removeAllElements();
            list.setModel(listModel);
        } else if (ae.getSource() == readFile) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                int value = Integer.parseInt(br.readLine());
                String data;
                for (int i = 0; i < value; i++) {
                    data = br.readLine();
                    listModel.addElement(data);
                }
                list.setModel(listModel);

                stop = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "ERROR CATCH " + e);
            } finally {
                try {
                    br.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "ERROR FINALLY " + e);
                }
            }
        }
    }
    
}
