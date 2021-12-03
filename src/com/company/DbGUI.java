package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DbGUI extends JFrame{
    private JTextField input_id = new JTextField("", 5);
    private JTextField input_perc = new JTextField("", 5);
    private JLabel label_id = new JLabel("ID:");
    private JLabel label_perc = new JLabel("Percent");
    private EmployeeModel empm = new EmployeeModel();
    private JTable table = new JTable(empm);
    private JButton exec = new JButton("Execute");
    private JPanel panel = new JPanel(new GridLayout(3,3,2,2));
    JScrollPane scrollPane = new JScrollPane(table);

    public DbGUI() {
        super("Table GUI");
        this.setBounds(0, 0, 600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        panel.add(label_id);
        panel.add(label_perc);
        panel.add(input_id);
        panel.add(input_perc);
        panel.add(exec);
        container.add(panel, BorderLayout.SOUTH);
        exec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empm.delete();
                String id = input_id.getText();
                String perc = input_perc.getText();
                empm.get_date(id, perc);
                container.add(scrollPane);
            }
        });

    }
}
