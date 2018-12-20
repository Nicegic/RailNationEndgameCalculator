package gui;

import cal.CapacityOverview;
import entities.City;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {

    private JLabel title = new JLabel ("Endspiel Hilfs-Tool"), tplayers = new JLabel("Anzahl Spieler:"), tcapacity=new JLabel("zu erreichende Menge in der Stadt"), tdemand = new JLabel("Verbrauch der Stadt (in %)");
    private JTextField players, citycapacity, demand;
    private JPanel frame = new JPanel();
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JButton calculate = new JButton("berechne");
    private JProgressBar progress;
    private RnTableModel tableModel;
    private CapacityOverview overview;

    public Window(){
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
        frame.add(container,gbc);
        pack();
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(1024,720));
        add(frame, BorderLayout.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title, BorderLayout.NORTH);
        frame.setLayout(gbl);
        JComboBox<City> cities = new JComboBox<>();
        cities.addItem(City.Boston);
        gbc.gridx=0;gbc.gridy=0;
        gbc.gridwidth=1;
        frame.add(cities);
        gbc.gridx=1;
        tplayers.setLabelFor(players);
        frame.add(tplayers,gbc);
        gbc.gridx=2;
        players = new JTextField();
        players.setColumns(4);
        frame.add(players,gbc);
        gbc.gridx=3;
        tcapacity.setLabelFor(citycapacity);
        frame.add(tcapacity,gbc);
        gbc.gridx=4;
        citycapacity = new JTextField();
        citycapacity.setColumns(10);
        frame.add(citycapacity,gbc);
        gbc.gridx=5;
        tdemand.setLabelFor(demand);
        frame.add(tdemand, gbc);
        gbc.gridx=6;
        demand = new JTextField();
        demand.setColumns(4);
        frame.add(demand, gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        frame.add(calculate, gbc);
        calculate.addActionListener(this);
        gbc.gridx=5;
        progress = new JProgressBar(0,100);
        progress.setValue(0);
        progress.setStringPainted(true);
        frame.add(progress, gbc);
        initializeTable();
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getActionCommand().equals("berechne")){
            int iplayers=0, icapacity=0, idemand=0;
            try {
                iplayers = Integer.parseInt(players.getText());
                icapacity = Integer.parseInt(citycapacity.getText());
                idemand = Integer.parseInt(demand.getText());
            }catch(NumberFormatException nfe){
                System.err.println("Fehler: entweder " + players.getText() + " oder " + citycapacity.getText() + " ist keine gewollte Zahl!");
            }
            if(iplayers!=0&&icapacity!=0&&idemand!=0){
                if(overview!=null&&overview.isAlive()) {

                }else {
                    overview = new CapacityOverview(City.Boston, this);
                    progress.setMaximum(overview.numberOfTracks);
                    overview.enableProgressUpdate(progress);
                    overview.changeNumberOfPlayers(iplayers);
                    overview.setCitycapacity(icapacity);
                    overview.setCurrentCityDemand(idemand / 100.0);
                    //overview.calculate();
                    overview.start();
                }
            }
        }
    }

    public void calulcationDone(Object [][] durations){
        populateTable(durations, durations[0].length);
    }
}
