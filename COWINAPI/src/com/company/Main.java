package com.company;

import no.tornado.databinding.support.jxdatepicker.JXDatePickerBridge;
import no.tornado.databinding.support.jxdatepicker.JXDatePickerUIBridgeProvider;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Main {
static TreeMap<String,String> states = new TreeMap<>();
static TreeMap<String,String> city  = new TreeMap<>();
//static String[][] data = new String[10][];
    public static void main(String[] args) throws IOException, ParseException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

//MobileOTP.confirmOTP("2978ef852a8e50e7b073fc0292ac0fb58503ba0a14147cca795c73a9b9316987","00a55d9d-f435-4a40-b85e-66044180f1b6");

//        MobileOTP.Certificate("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiI5YzcxNzY4NS0yMTZkLTQ0ZTYtYTIzYy0zMjhlMTM3ZDkwZGUiLCJ1c2VyX3R5cGUiOiJCRU5FRklDSUFSWSIsInVzZXJfaWQiOiI5YzcxNzY4NS0yMTZkLTQ0ZTYtYTIzYy0zMjhlMTM3ZDkwZGUiLCJtb2JpbGVfbnVtYmVyIjo5ODEzODQyNDI3LCJiZW5lZmljaWFyeV9yZWZlcmVuY2VfaWQiOjEyMjAzMTUyNzMxOTYxLCJ0eG5JZCI6IjAwYTU1ZDlkLWY0MzUtNGE0MC1iODVlLTY2MDQ0MTgwZjFiNiIsImlhdCI6MTYyMzc2NzUxNywiZXhwIjoxNjIzNzY4NDE3fQ.sH2OvIZqJkUz-ZI0uSvmLL0jYkh0vmr4wlIQ8Vj5Fh4");

//        MobileOTP.sendOTP();

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        States.getStates(states);

        show();

//        System.out.println(OTP_to_SHA.sha256("889909"));

    }

    public static void show()
    {

        JFrame frame = new JFrame("COWIN REGISTRATION");

        JComboBox combo = new JComboBox();
        combo.setBounds(120,100,200,30);

        JComboBox city1 = new JComboBox();
        city1.setBounds(120,150,200,30);

        combo.setVisible(false);
        city1.setVisible(false);


        JTextField field = new JTextField("dd-mm-yyyy");
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                field.setText("");
            }
        });

        JButton submit = new JButton("Submit");
        submit.setBounds(160,360,100,40);

        JRadioButton b1 = new JRadioButton();
        JLabel l1 = new JLabel();
        l1.setText("18+");
        l1.setBounds(140,252,50,30);
        JRadioButton b2 = new JRadioButton();
        b1.setBounds(170,255,35,25);

        JLabel l2 = new JLabel();
        l2.setText("45+");
        l2.setBounds(240,252,50,30);
        b2.setBounds(270,255,35,25);
        b1.setActionCommand("18");
        b2.setActionCommand("45");
        ButtonGroup g = new ButtonGroup();
        g.add(b1);
        g.add(b2);


        JRadioButton v1 = new JRadioButton();
        JLabel vv1 = new JLabel();
        vv1.setText("Covishield");
        v1.setActionCommand("COVISHIELD");
        v1.setBounds(170,285,35,25);
        vv1.setBounds(90,282,100,30);

        JRadioButton v2 = new JRadioButton();
        JLabel vv2 = new JLabel();
        vv2.setText("Covaxin");
        v2.setActionCommand("COVAXIN");
        vv2.setBounds(210,282,80,30);
        v2.setBounds(270,285,35,25);

        ButtonGroup g2 = new ButtonGroup();
        g2.add(v1);
        g2.add(v2);


        JRadioButton d1 = new JRadioButton();
        JLabel dd1 = new JLabel();
        dd1.setText("Dose 1");
        d1.setActionCommand("available_capacity_dose1");
        d1.setBounds(170,320,35,25);
        dd1.setBounds(110,318,100,30);

        JRadioButton d2 = new JRadioButton();
        JLabel dd2 = new JLabel();
        dd2.setText("Dose 2");
        d2.setActionCommand("available_capacity_dose2");
        dd2.setBounds(210,318,80,30);
        d2.setBounds(270,320,35,25);
        ButtonGroup g3 = new ButtonGroup();
        g3.add(d1);
        g3.add(d2);

        JButton search_by_pin = new JButton("Search By Pin");
        search_by_pin.setBounds(50,20,150,35);

        JTextField pin = new JTextField("Enter Pin");
        pin.setBounds(140,130,150,30);
        pin.setVisible(false);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dose = g3.getSelection().getActionCommand();
                    String vaccine = g2.getSelection().getActionCommand();
                    String age = g.getSelection().getActionCommand();
                    String data[][];
                    if(pin.isVisible())
                    data = ByPin.bypin(pin.getText(), field.getText(),age,vaccine,dose);
                    else
                    data =   ByDistrict.getData(field.getText(),city.get(String.valueOf(city1.getSelectedItem())),age,vaccine,dose);
                    table(data);
                } catch (IOException | ParseException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        field.setBounds(140,200,150,30);

        for(Map.Entry<String,String> entry : states.entrySet())
        {
            combo.addItem(entry.getKey());
        }

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(combo.getSelectedItem());
                String code = states.get(String.valueOf(combo.getSelectedItem()));
                try {

                    city1.removeAllItems();
                    city.clear();
                    City.getCity(code,city);

                    for(Map.Entry<String,String> entry : city.entrySet())
                    {
                        city1.addItem(String.valueOf(entry.getKey()));
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });

//        System.out.println(str);


        pin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pin.setText("");
            }
        });


        JButton search_by_city = new JButton("Search By City");
        search_by_city.setBounds(220,20,150,35);

        search_by_city.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pin.setVisible(false);
                combo.setVisible(true);
                city1.setVisible(true);
            }

        });


        JDatePicker datePicker = new JDatePicker();
        datePicker.setBounds(10,300,100,30);


        search_by_pin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(str);
                pin.setVisible(true);
                combo.setVisible(false);
                city1.setVisible(false);
            }
        });

        JButton download = new JButton("Download Vaccine Certificate");
        download.setBounds(90,420,250,50);

        download.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mobile();
            }
        });

        frame.add(download);
        frame.add(pin);
        frame.add(search_by_city);
        frame.add(search_by_pin);
        frame.add(d1);
        frame.add(dd1);
        frame.add(d2);
        frame.add(dd2);
        frame.add(vv1);
        frame.add(vv2);
        frame.add(v1);
        frame.add(v2);
        frame.add(l1);
        frame.add(l2);
        frame.add(b1);
        frame.add(b2);
        frame.add(submit);
        frame.add(field);
        frame.add(city1);
        frame.add(combo);
        frame.setLayout(null);
        frame.setSize(450,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
    }

    public static void mobile()
    {
        JFrame frame = new JFrame("OTP Registration");

        JTextField m = new JTextField("Enter Mobile Number");
        m.setBounds(150,50,200,45);
        m.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                m.setText("");
            }
        });


        JButton submit = new JButton("Submit");
        submit.setBounds(150,150,200,50);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = m.getText();
                try {
                  String txn =  MobileOTP.sendOTP(str);
                    System.out.println("TSXXS -> "+txn);
                    check_otp(txn);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

                frame.dispose();
            }
        });

        frame.add(m);
        frame.add(submit);
        frame.setLayout(null);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void check_otp(String txn)
    {
        JFrame frame = new JFrame("Validate OTP");

        JTextField m = new JTextField("Enter OTP");
        m.setBounds(150,50,200,45);
        m.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                m.setText("");
            }
        });


        JButton submit = new JButton("Submit");
        submit.setBounds(150,150,200,50);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String str = OTP_to_SHA.sha256(m.getText());
                    String token = MobileOTP.confirmOTP(str,txn);
                    beneficiary(token);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                frame.dispose();
            }
        });



        frame.add(submit);
        frame.add(m);
        frame.setLayout(null);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void beneficiary(String token)
    {
        JFrame frame = new JFrame("Download Certificate");

        JTextField m = new JTextField("Enter Beneficiary ID");
        m.setBounds(150,50,200,45);
        m.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                m.setText("");
            }
        });


        JButton submit = new JButton("Submit");
        submit.setBounds(150,150,200,50);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MobileOTP.Certificate(token,m.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                frame.dispose();
            }
        });



        frame.add(submit);
        frame.add(m);
        frame.setLayout(null);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    public static void table(String[][] data)
    {
        JFrame f = new JFrame();

        // Frame Title
        f.setTitle("Data Table");

        // Data to be displayed in the JTable

        // Column Names
        String[] columnNames = { "Name", "Address", "Capacity","Dose 1","Dose 2","Min Age","Vaccine" };

        // Initializing the JTable
        JTable j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);
        j.setDefaultEditor(Object.class,null);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size

        f.setSize(1200, 500);
        // Frame Visible = true
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }
}
