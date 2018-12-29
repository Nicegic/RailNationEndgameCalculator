package gui;

import cal.Controller;
import entities.CityName;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishCalGUI extends JPanel implements ActionListener {

    private JLabel tplayers = new JLabel("Anzahl Spieler:"), tcapacity=new JLabel("zu erreichende Menge in der Stadt"), tdemand = new JLabel("Verbrauch der Stadt (in %)"), tdemandTime = new JLabel("Zeit bis zum ersten Stadtverbrauch in min"), trecalculationTime = new JLabel("Zeit bis zur ersten Neuberechnung in min");
    private JTextField players, citycapacity, demand, recalculationTime, demandTime;
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JButton calculate = new JButton("berechne");
    private JProgressBar progress;
    private RnTableModel tableModel;
    private JComboBox<CityName> cities;
    private Controller controller;

    FinishCalGUI(Controller controller){
        this.controller = controller;
        tableModel = new RnTableModel();
        initWindow();
    }

    private void initializeTable(){
        tableModel = new RnTableModel();
        tableModel.initialize();
        JTable table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                return c;
            }
        });
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setAutoCreateRowSorter(true);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=10;
        JScrollPane container = new JScrollPane(table);
        container.setPreferredSize(new Dimension(800,600));
        add(container,gbc);
    }

    private void populateTable(Object[][] durations, int times){
        if(tableModel.getRowCount()!=durations.length+1){
            tableModel.reinitializeForCity(durations.length,times);
        }
        for(int i=0;i<durations.length;i++){
            for(int j=0;j<16;j++){
                tableModel.setValueAt(durations[i][j],i,j);
            }
        }
        tableModel.fireTableStructureChanged();
        progress.setValue(progress.getMaximum());
    }

    private void initWindow(){
        setPreferredSize(new Dimension(1024,720));
        setLayout(gbl);
        cities = new JComboBox<>();
        cities.addItem(CityName.Boston);
        gbc.gridx=0;gbc.gridy=0;
        gbc.gridwidth=1;
        add(cities);
        gbc.gridx=1;
        tplayers.setLabelFor(players);
        add(tplayers,gbc);
        gbc.gridx=2;
        players = new JTextField();
        players.setColumns(4);
        add(players,gbc);
        gbc.gridx=3;
        tcapacity.setLabelFor(citycapacity);
        add(tcapacity,gbc);
        gbc.gridx=4;
        citycapacity = new JTextField();
        citycapacity.setColumns(10);
        add(citycapacity,gbc);
        gbc.gridx=5;
        gbc.gridwidth=2;
        tdemand.setLabelFor(demand);
        add(tdemand, gbc);
        gbc.gridx=7;
        demand = new JTextField();
        demand.setColumns(2);
        add(demand, gbc);
        gbc.gridy=1;
        gbc.gridx=1;
        gbc.gridwidth=3;
        tdemandTime.setLabelFor(demandTime);
        add(tdemandTime, gbc);
        gbc.gridwidth=1;
        gbc.gridx=4;
        demandTime = new JTextField();
        demandTime.setColumns(2);
        add(demandTime,gbc);
        gbc.gridx=5;
        gbc.gridwidth=2;
        trecalculationTime.setLabelFor(recalculationTime);
        add(trecalculationTime, gbc);
        gbc.gridx=7;
        gbc.gridwidth=1;
        recalculationTime = new JTextField();
        recalculationTime.setColumns(2);
        add(recalculationTime, gbc);
        gbc.gridy=2;
        gbc.gridx=3;
        gbc.gridwidth=2;
        add(calculate, gbc);
        calculate.addActionListener(this);
        gbc.gridx=5;
        progress = new JProgressBar(0,100);
        progress.setValue(0);
        progress.setStringPainted(true);
        add(progress, gbc);
        initializeTable();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        JButton button = (JButton) event.getSource();
        if(event.getActionCommand().equals("berechne")){
            int iplayers=0, icapacity=0, idemand=0, irecalculationTime=0, iDemandTime=0;
            try {
                iplayers = Integer.parseInt(players.getText());
                icapacity = Integer.parseInt(citycapacity.getText());
                idemand = Integer.parseInt(demand.getText());
                iDemandTime = Integer.parseInt(demandTime.getText());
                irecalculationTime = Integer.parseInt(recalculationTime.getText());
            }catch(NumberFormatException nfe){
                System.err.println("Fehler: entweder " + players.getText() + ", "+ demandTime.getText()+", "+citycapacity.getText()+", "+demand.getText()+" oder " + recalculationTime.getText() + " ist keine gewollte Zahl!");
            }
            if(iplayers!=0&&icapacity!=0&&idemand!=0){
                if(!controller.calculationRunning){
                    button.setText("abbrechen");
                    controller.setRecalculationTime(irecalculationTime);
                    controller.setCity((CityName)cities.getSelectedItem());
                    progress.setMaximum(controller.numberOfTracks);
                    controller.initializeFinishCalculator(icapacity, iplayers, (idemand/100.0), iDemandTime);
                    controller.startFinishCalculation();
                }

            }
        }else if(event.getActionCommand().equals("abbrechen")){
            controller.stopCalculation();
            button.setText("berechne");
        }
    }

    public void calculationDone(Object [][] durations){
        populateTable(durations, durations[0].length);
        calculate.setText("berechne");
    }

    public JProgressBar enableProgressUpdate(){
        return progress;
    }
}
