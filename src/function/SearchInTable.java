/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassFunction;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author MINHPHUONG
 */
public class SearchInTable {
    public JTable jtable = null;
    public TableRowSorter<TableModel> sortTable = null;
    public SearchInTable(JTable jtable, TableRowSorter<TableModel> sortTable)
    {
        this.jtable = jtable;
        this.sortTable = sortTable;
        jtable.setRowSorter(sortTable);
    }
    //public TableRowSorter<TableModel> sortTable = new TableRowSorter<>(jtable.getModel());
    
    public void SearchAndFilter(String text)
    {
        text.toUpperCase();
        if(text.trim().length() == 0)
        {
            sortTable.setRowFilter(null);
        }
        else{
            try{
            sortTable.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)"+text));
            }
            catch (Exception ex)
            {}
        }
    }
}
