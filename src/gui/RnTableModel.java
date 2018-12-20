package gui;

import javax.swing.table.AbstractTableModel;

public class RnTableModel extends AbstractTableModel {

    private Object[][] data;
    private Object [] columns =  {"Betrieb / Zeit zum Vollfahren in min bei", "0s WZ", "15s WZ", "30s WZ", "45s WZ", "60s WZ", "75s WZ", "90s WZ", "105s WZ", "120s WZ", "135s WZ", "150s WZ", "180s WZ", "210s WZ", "240s WZ", "300s WZ"};

    void initialize(){
        data = new Object[2][16];
        fillFields(2, 16);
    }

    void reinitializeForCity(int numberOfTracks, int times){
        data = new Object[numberOfTracks][times+1];
        fillFields(numberOfTracks, (times+1));
    }

    private void fillFields(int rows, int columns){
        for(int i=0;i<rows; i++){
            for(int j=0;j<columns;j++){
                if(j==0){
                    data[i][j] = "Betrieb Stadt Richtung";
                }
                else
                    data[i][j] = 0;
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public void setValueAt(Object value, int row, int column){
        data[row][column] = value;
    }

    public String getColumnName(int col){
        return columns[col].toString();
    }

    public Class getColumnClass(int col){
        return getValueAt(0,col).getClass();
    }
}
