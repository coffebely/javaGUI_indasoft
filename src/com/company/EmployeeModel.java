package com.company;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeModel extends AbstractTableModel {
    private int ColumnCount = 6;
    private ArrayList<String[]> data;
    public void delete(){
        data.clear();
    }
    public EmployeeModel(){
        data = new ArrayList<String[]>();
        for(int i = 0; i < data.size(); i++){
            data.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return ColumnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = data.get(rowIndex);
        return rows[columnIndex];
    }
    public void addDate(String []row){
        String[] rowTable = new String[getRowCount()];
        rowTable = row;
        data.add(rowTable);
    }
    public String getColumnName(int ColumnIndex){
        switch (ColumnIndex){
            case 0: return "ID";
            case 1: return "DEPARTMENT ID";
            case 2: return "CHIFF ID";
            case 3: return "NAME";
            case 4: return "SALARY";
            case 5: return "OLD SALARY";
        }
        return "";
    }
    public void get_date(String id, String perc){
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String connectionUrl = "jdbc:sqlserver://DESKTOP-R4MCKO6\\SQLEXPRESS;databaseName=test;user=coffe.bely;password=123456";
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("EXEC UPDATESALARYFORDEPARTMENT " + id + ", " + perc);
            while (r.next()) {
                String[] row = {
                        r.getString(1),
                        r.getString(2),
                        r.getString(3),
                        r.getString(4),
                        r.getString(5),
                        r.getString(6)
                };
                addDate(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
