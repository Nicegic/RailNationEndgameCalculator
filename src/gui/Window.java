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
    private JComboBox<City> cities;
    private JTextField players, citycapacity, demand;
    private JPanel frame = new JPanel();
    private JTable table;
    private CapacityOverview overview;
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JButton calculate = new JButton("berechne");
    private boolean withTable=false;
    private JScrollPane container;

    public Window(){
        overview = new CapacityOverview(City.Boston);
        initWindow();
    }

    private void populateTable(){
        if(withTable)
            remove(container);
        withTable=false;
        Object [] columns =  {"Betrieb / Zeit zum Vollfahren in min", "0s WZ", "15s WZ", "30s WZ", "45s WZ", "60s WZ", "75s WZ", "90s WZ", "105s WZ", "120s WZ", "135s WZ", "150s WZ", "180s WZ", "210s WZ", "240s WZ", "300s WZ"};
        table = new JTable(overview.getTableData(), columns){
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
        gbc.gridy=2;
        gbc.gridwidth=10;
        container = new JScrollPane(table);
        container.setPreferredSize(new Dimension(800,600));
        frame.add(container,gbc);
        pack();
        withTable=true;
    }

    private void initWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(1024,720));
        add(frame, BorderLayout.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title, BorderLayout.NORTH);
        frame.setLayout(gbl);
        cities = new JComboBox<>();
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
        gbc.gridx=3;
        gbc.gridy=1;
        frame.add(calculate, gbc);
        calculate.addActionListener(this);
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
            if(iplayers!=0&&icapacity!=0){
                overview.changeNumberOfPlayers(iplayers);
                overview.setCitycapacity(icapacity);
                overview.setCurrentCityDemand(idemand/100.0);
                populateTable();
            }
        }
    }
}
