
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
import java.util.List;
 
public class ERTester extends JFrame implements ActionListener
{
 
 //------------------------------------------------------------------------
// Data fields
//------------------------------------------------------------------------
 
    // Constants
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private static final int ROWS = 20;
    private static final int COLS = 40;
    private static final String FAIR = "Fair";
    private static final String SERIOUS = "Serious";
    private static final String CRITICAL = "Critical";
    private static final String SCHEDULE = "Schedule a patient";
    private static final String SHOW_ALL = "Show waiting list";
    private static final String TREAT_ONE = "Treat the next patient";
    private static final String TREAT_ALL = "Treat all patients";
   
    // GUI objects
    private JLabel nameLabel;
    private JLabel complaintLabel;
    private JLabel priorityLabel;
    private JTextField nameField;
    private JTextField complaintField;
   
    private JRadioButton fairButton;
    private JRadioButton criticalButton;
    private JRadioButton seriousButton;
   
    private JButton treatOneButton;
    private JButton treatAllButton;
    private JButton scheduleButton;
    private JButton showAllButton;
    private JButton nextButton;
       
    private JPanel labelPanel;
    private JPanel textPanel;
    private JPanel priorityPanel;
    private JPanel actionPanel;
    private JPanel outputPanel;
   
    private JTextArea outputArea;
   
    // ERManager object
    private ERManager er;
    private String currentPriority;
 
  /**
    *  Construct the frame and instantiate the emergency room
    */
   public ERTester()
   {
        er = new ERManager();
        currentPriority = FAIR;
       
        // GUI set-up
        setupPanel();
 
        // set-up frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("West Emergency Room");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
   
    /**
     *  Respond to a command
     *  @param event the event/command received
     */
    public void actionPerformed(ActionEvent event)
    {
        if (event.getActionCommand().equals(SCHEDULE))
        {
            schedule();
        }
        else if (event.getActionCommand().equals(TREAT_ONE))
        {
            treatNext();
        }
        else if (event.getActionCommand().equals(TREAT_ALL))
        {
            treatAll();
        }
        else if (event.getActionCommand().equals(SHOW_ALL))
        {
            showWaitingRoom();
        }
        else if (event.getActionCommand().equals(FAIR)
                    || event.getActionCommand().equals(SERIOUS)
                    || event.getActionCommand().equals(CRITICAL))
        {
            currentPriority = event.getActionCommand();
        }
        else
        {
            outputArea.append("Unknown Entry\n");
        }
    }
 
    /**
     *  Run this tester
     */
    public static void main(String[] args)
    {
        ERTester test = new ERTester();
    }
   
   /**
    *  Schedule a new patient
    */
   private void schedule()
   {
        String name = nameField.getText().trim();
        String complaint = complaintField.getText().trim();
        if (name.length() > 0 && complaint.length() > 0)
        {
            /**************************************************/
            /* Add statements to add patient to ER here                         */
            /**************************************************/
            er.addPatient(new Patient(name, getPriorityRating(currentPriority), complaint));
            outputArea.append(name + " is added to the " + currentPriority + " list\n");
 
            nameField.setText("");
            complaintField.setText("");
        }
        else
        {
            String error;
            if (complaint.length() != 0)
                error = "Please enter patient name";
            else if (name.length() != 0)
                error = "Please enter problem description";
            else
                error = "Please enter patient name and problem description";
            JOptionPane.showMessageDialog(this, error);
        }
   }
   
    /** Get the PriorityRating equivalent of priority
     *   @param priority string representation of the priority
     *   @return corresponding PriorityRating
     */
    private PriorityRating getPriorityRating(String priority)
    {
        if (priority.equals(FAIR))
            return PriorityRating.LOW_PRIORITY;
        else if (priority.equals(SERIOUS))
            return PriorityRating.MEDIUM_PRIORITY;
        else
            return PriorityRating.HIGH_PRIORITY;
    }
   
    /** Treat the next patient by priority
     */
    private void treatNext()
    {
        if(er.hasPatients()){
            outputArea.append("Treating: " + er.treatNextPatient() + "\n");
        }else{
        JOptionPane.showMessageDialog(this, "No patients avaliable to treat");
        }
    }
   
    /** Treat all patients until there are no more patients to treat
     */
    private void treatAll()
    {
        if(!er.hasPatients()){
            JOptionPane.showMessageDialog(this, "No patients avaliable to treat");
        }else{
        outputArea.append("\nTreating:\n");
        while(er.hasPatients()){
            outputArea.append(er.treatNextPatient() + "\n");
        }
    }
    }
 
    /** Show all patients waiting to be treated
     */
    private void showWaitingRoom()
    {
        if(!er.hasPatients()){
            JOptionPane.showMessageDialog(this, "No patients avaliable to treat");
        }else{
        Patient[] iter = er.getWaitingPatients();
        outputArea.append("\nIn waiting room:\n");
        for(Patient p : iter){
            outputArea.append(p.toString() + "\n");
        }
        outputArea.append("\n\n");
        }
    }
 
    /**
     *  Set-up the panels for action buttons, data entry, radio buttons,
     *  and output area
     */
    private void setupPanel()
    {
        setupActionPanel();
        setupDataPanel();
        setupButtonPanel();
        setupOutputPanel();
        setLayout(new BorderLayout());
        add(actionPanel, BorderLayout.PAGE_START);
        add(labelPanel, BorderLayout.LINE_START);
        add(textPanel, BorderLayout.CENTER);
        add(priorityPanel, BorderLayout.LINE_END);
        add(outputPanel, BorderLayout.PAGE_END);
    }
 
    /**
     *  Set-up panel for action buttons
     */
    private void setupActionPanel()
    {
        scheduleButton = new JButton(SCHEDULE);
        scheduleButton.setEnabled(true);
        scheduleButton.addActionListener(this);
        scheduleButton.setActionCommand(SCHEDULE);
       
        showAllButton = new JButton(SHOW_ALL);
        showAllButton.setEnabled(true);
        showAllButton.addActionListener(this);
        showAllButton.setActionCommand(SHOW_ALL);
       
        treatOneButton = new JButton(TREAT_ONE);
        treatOneButton.setEnabled(true);
        treatOneButton.addActionListener(this);
        treatOneButton.setActionCommand(TREAT_ONE);
       
        treatAllButton = new JButton(TREAT_ALL);
        treatAllButton.setEnabled(true);
        treatAllButton.addActionListener(this);
        treatAllButton.setActionCommand(TREAT_ALL);
       
        actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(0, 4));
        actionPanel.add(scheduleButton);
        actionPanel.add(showAllButton);
        actionPanel.add(treatOneButton);
        actionPanel.add(treatAllButton);
    }
 
    /**
     *  Set-up panel to enter patient data
     */
    private void setupDataPanel()
    {
        nameLabel = new JLabel("Patient name", JLabel.CENTER);
        nameField = new JTextField("", 30);
        nameField.setEditable(true);
       
        complaintLabel = new JLabel("Complaint", JLabel.LEFT);
        complaintField = new JTextField("", 30);
        complaintField.setEditable(true);
       
        labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(2, 1));
        labelPanel.add(nameLabel);
        labelPanel.add(complaintLabel);
 
        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));
        textPanel.add(nameField);
        textPanel.add(complaintField);
    }
 
    /**
     *  Set-up panel to choose patient priority
     */
    private void setupButtonPanel()
    {
        priorityLabel = new JLabel("Priority", JLabel.LEFT);
       
        fairButton = new JRadioButton(FAIR);
        fairButton.setEnabled(true);
        fairButton.addActionListener(this);
        fairButton.setActionCommand(FAIR);
        fairButton.setSelected(true);
       
        seriousButton = new JRadioButton(SERIOUS);
        seriousButton.setEnabled(true);
        seriousButton.addActionListener(this);
        seriousButton.setActionCommand(SERIOUS);
        seriousButton.setSelected(false);
 
        criticalButton = new JRadioButton(CRITICAL);
        criticalButton.setEnabled(true);
        criticalButton.addActionListener(this);
        criticalButton.setActionCommand(CRITICAL);
        criticalButton.setSelected(false);
       
        ButtonGroup priorityButtons = new ButtonGroup();
        priorityButtons.add(fairButton);
        priorityButtons.add(seriousButton);
        priorityButtons.add(criticalButton);
 
        priorityPanel = new JPanel();
        priorityPanel.setLayout(new GridLayout(4, 1));
        priorityPanel.add(priorityLabel);
        priorityPanel.add(fairButton);
        priorityPanel.add(seriousButton);
        priorityPanel.add(criticalButton);
    }
 
    /**
     *  Set-up output panel with a text area
     */
    private void setupOutputPanel()
    {
        outputArea = new JTextArea(ROWS, COLS);
        outputArea.setText("Welcome to PWSH Emergency Room\n\n");
        outputArea.setEditable(false);
       
        outputPanel = new JPanel();
        outputPanel.add(new JScrollPane(outputArea));      
    }
 
 
}
