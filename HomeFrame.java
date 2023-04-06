import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.sql.Timestamp;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import UnitTest.SerialCom;
import UnitTest.Simulation;

import java.io.InputStream;
import com.fazecast.jSerialComm.SerialPort;

public class HomeFrame extends JFrame implements ActionListener {

    /**
     * Expected layout:
     * ********************************
     * * * *
     * * * *
     * * Graph * Data Panel *
     * * * *
     * * * *
     * * * *
     * ********************************
     * * Console Panel *
     * * *
     * ********************************
     */

    // Initalisation of Panels
    JPanel dataPanel = new JPanel(new BorderLayout()); // Panel for data
    JPanel consolPanel = new JPanel(new BorderLayout()); // Panel for consol
    JPanel masterPanel = new JPanel(new BorderLayout()); // Panel wgich containt data + console + graph
    JPanel graphPanel = new JPanel(new BorderLayout()); // Panel for live graph
    JPanel bodydataPanel = new JPanel(new BorderLayout());
    JPanel middPanel = new JPanel();

    GridLayout dataGridLayout = new GridLayout(0, 2); // Grid for data
    JPanel clutchRow = new JPanel(new BorderLayout()); // Row for display clutch buttons
    JPanel modeRow = new JPanel(new BorderLayout()); // w for display mode
    JPanel dataGrid = new JPanel();

    // Initalisation of componants
    Choice mode = new Choice();
    JButton modeButton = new JButton("Select mode");
    JLabel dataPanelLabel = new JLabel("Live Data Window");
    JToggleButton clutch1Button = new JToggleButton("Clutch 1", false); // False = off or non-geared
    JToggleButton clutch2Button = new JToggleButton("Clutch 2", false); // False = off or non-geared
    JLabel speedLabel = new JLabel("    Rotation speed (tr.min): ");
    JLabel speedValue = new JLabel("000 tr.min");
    JLabel tensionLabel = new JLabel("    Output tension (V): ");
    JLabel tensionValue = new JLabel("00 V ");
    JLabel currentLabel = new JLabel("    Output current (mA): ");
    JLabel currentValue = new JLabel("000 mA");
    JTextArea console = new JTextArea(10, 135);
    JLabel consoleLabel = new JLabel("Console");
    JScrollPane scroll = new JScrollPane(console);
    private TimeSeries series;

    Timer timer = new Timer(1000, null);
    boolean timerState = false;
    String dataComplete;

    // Class constructor
    HomeFrame() {
        console.setEditable(true);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setTimer();
        // set color
        setColor();
        // Position of dataPanel
        setPosition();
        // Adding components
        addComponents();
        // Adding action
        addActionEvent();
        // Adding button in middPanel
        middPanel.add(mode);
        middPanel.add(modeButton);
        // Creation of Graph
        initGraph();
        // Adding Graph and dataPanel to Midd Panel
        middPanel.add(graphPanel);
        middPanel.add(dataPanel);
        // Adding Panels to MasterPanel
        masterPanel.add(middPanel);
        masterPanel.add(consolPanel, BorderLayout.PAGE_END);

        Container container = getContentPane(); // Trick pour pouvoir instancier dans la class main
        container.add(masterPanel);
    }

    public void initGraph() {
        this.series = new TimeSeries("Random Data", Millisecond.class);
        TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        JButton button = new JButton("Add New Data Item");
        button.setActionCommand("ADD_DATA");
        button.addActionListener(this);
        graphPanel.add(chartPanel);
        // graphPanel.add(button, BorderLayout.SOUTH);
    }

    public JFreeChart createChart(XYDataset dataset) {
        JFreeChart result = ChartFactory.createTimeSeriesChart(
                "Output power",
                "Time",
                "Power (W)",
                dataset,
                false,
                true,
                false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000.0); // 60 seconds
        axis = plot.getRangeAxis();
        axis.setRange(0.0, 15.0);
        return result;
    }

    public void setColor() {
        console.setBackground(Color.BLACK);
        console.setForeground(Color.white);
        consolPanel.setBackground(Color.LIGHT_GRAY);
        dataPanel.setBackground(Color.LIGHT_GRAY);
        dataGrid.setBackground(Color.white);
        clutchRow.setBackground(Color.white);
        dataPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        graphPanel.setBackground(Color.RED);
        // consoleLabel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        // modeButton.setFont(new Font("Arial", Font.PLAIN, 12));

    }

    public void setPosition() {
        middPanel.setLayout(null);
        dataPanel.setBounds(750, 80, 350, 300);
        graphPanel.setBounds(10, 40, 600, 400);
        mode.setBounds(500, 2, 150, 30);
        modeButton.setBounds(650, 2, 150, 30);
    }

    public void addActionEvent() {
        // adding Action listener to components
        clutch1Button.addActionListener(this);
        clutch2Button.addActionListener(this);
        modeButton.addActionListener(this);
    }

    public void addComponents() {
        // Adding each components to the dataGrid

        mode.add("Manual");
        mode.add("Auto");
        // modeRow.add(modeButton,BorderLayout.EAST);
        // modeRow.add(mode,BorderLayout.WEST);

        dataGrid.setLayout(new GridLayout(3, 2));
        dataGrid.add(speedLabel);
        dataGrid.add(speedValue);
        dataGrid.add(clutch1Button);
        dataGrid.add(clutch2Button);
        dataGrid.add(currentLabel);
        dataGrid.add(currentValue);
        dataGrid.add(tensionLabel);
        dataGrid.add(tensionValue);

        clutchRow.add(clutch1Button, BorderLayout.EAST);
        clutchRow.add(clutch2Button, BorderLayout.WEST);

        dataPanel.add(dataPanelLabel, BorderLayout.PAGE_START);
        dataPanel.add(dataGrid, BorderLayout.CENTER);
        dataPanel.add(clutchRow, BorderLayout.PAGE_END);

        // Adding each components to consolPanel
        consolPanel.add(scroll, BorderLayout.SOUTH);
        consolPanel.add(consoleLabel, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent e) {

        // Coding Part of clutch1Button
        if (e.getSource() == clutch1Button) {
            // console.append write on the console text area
            console.append("[" + new Timestamp(System.currentTimeMillis()) + "] Clutch 1 [State]: "
                    + clutch1Button.isSelected() + "\n");
        }
        // Coding Part of clutch1Button
        if (e.getSource() == clutch2Button) {
            console.append("[" + new Timestamp(System.currentTimeMillis()) + "] Clutch 2 [State]: "
                    + clutch2Button.isSelected() + "\n");
        }
        // Coding Part of modeButton
        if (e.getSource() == modeButton) {
            console.append("[" + new Timestamp(System.currentTimeMillis()) + "] Selected mode: "
                    + mode.getSelectedItem() + "\n");
            if (mode.getSelectedItem() == "Auto") {
                clutch1Button.setEnabled(false);
                clutch2Button.setEnabled(false);
                timerState = true; // Allow the timer for automatic mode (see setTimer())
            }
            if (mode.getSelectedItem() == "Manual") {
                clutch1Button.setEnabled(true);
                clutch2Button.setEnabled(true);
                timerState = false;
            }
        }
        // Coding Part of graph
        if (e.getActionCommand().equals("ADD_DATA")) {
            DecimalFormat d = new DecimalFormat("0.00");

            double tension = 11 + Math.random() * 2; // simulation of 12v
            double current = Math.random() * 100; // simulation of 0;100 mA
            double speed = current * 21;
            double power = tension * (current / 100);
            this.series.add(new Millisecond(), power);
            speedValue.setText(Math.round(speed) + " tr.min");
            currentValue.setText(d.format(current) + "mA");
            tensionValue.setText(d.format(tension) + "V");
            console.append(
                    "[" + new Timestamp(System.currentTimeMillis()) + "] Add +" + d.format(power) + " on chart \n");
        }
    }

    public void setTimer() {
        final Timer timer = new Timer(1000, null);
        final ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timerState == true) {
                    // This follow dataComplte use SimulationTrame, you can use it when you don't
                    // have a Arduino pluged
                    // dataComplete = "["+new
                    // Timestamp(System.currentTimeMillis())+"]"+Simulation.SimulationTrame(mode.getSelectedItem(),
                    // clutch1Button.isSelected(),clutch2Button.isSelected())+"\n";

                    // This follow dataComplete use SerialCom function to read arduino input
                    String input = SerialCom.Read(); // Change Serial port here
                    dataComplete = "[" + new Timestamp(System.currentTimeMillis()) + "]" + System.currentTimeMillis()
                            + ";" + mode.getSelectedItem() + ";" + clutch1Button.isSelected() + ";"
                            + clutch2Button.isSelected() + ";" + input.split(";")[0] + ";" + input.split(";")[1] + ";"
                            + input.split(";")[2] + "\n";
                    console.append(dataComplete);
                    DataManager.savetoCSV(dataComplete); // save data in data.csv with this function
                    updateData(input); //We use input string readed on SerialCom
                }
            }
        };
        timer.addActionListener(listener);
        timer.start();
    }

    public void updateData(String data) { // Update Graph and labels
        if(data.split(";").length == 3){              
         speedValue.setText(data.split(";")[2] + "tr.min");
         currentValue.setText(data.split(";")[1] + "mA");
         tensionValue.setText(data.split(";")[0] + "V");
         double y = Double.parseDouble(data.split(";")[0]) * (Double.parseDouble(data.split(";")[1]) / 100);
         this.series.add(new Millisecond(), y);     
     }
     
    }

    public static void setupSerialCom() {
        SerialPort port = SerialPort.getCommPort("COM20"); // replace with your port name
        port.setBaudRate(9600);
        port.openPort();
        InputStream inputStream = port.getInputStream();
        Scanner scanner = new Scanner(inputStream);

        SerialCom serialCom = new SerialCom(); // création d'une instance de la classe SerialCom
        serialCom.scanner = scanner; // assignation de la variable scanner à la variable membre scanner de l'instance
                                     // de la classe
    }

    public static void main(String[] a) {

        setupSerialCom();
        // Creating object of HomeFrame class and setting some of its properties
        HomeFrame frame = new HomeFrame();
        frame.setTitle("Home BATT_V1.0");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeFrame.class.getResource("/images/Icon.png")));
    }

}