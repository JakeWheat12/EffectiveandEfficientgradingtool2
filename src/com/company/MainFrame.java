package com.company;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @todo: description
 *
 * @author Jake Wheat, Hyungsuk Kim
 */
public class MainFrame extends JFrame {

    // Constants
    protected final String TITLE = "Grading Tool"; // title of the app
    protected final String[] EXTENSION = new String[]{"pdf", "docx"}; // menubar constant
    protected final String[] DESCRIPTION = new String[]{"PDF (*.pdf)", "Word Document (*.docx)"}; // menubar constant
    // JLists
    public JList list_refreshed;
    public JList list_selected;
    // Menu bars
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem[] _file, _subFile;
    // Buttons
    public JButton button_RC;
    public JButton button_Finalize;
    public JButton button_Delete;
    public JButton button_Confirm;
    // TextArea
    public TextArea tArea;


    /**
     * Empty constructor
     */
    public MainFrame(){

    }

    /**
     * Constructor
     *
     * @param title
     */
    public MainFrame(String title) {
        super(title);

        setLayout(new BorderLayout());

    }

    /**  Creates the first window to be displayed.
     *
     * @param w
     * @param h
     */
    public void makeFrame(int w,  int h) {
        // create priority queue
        PriorityQueue<Comment> pq = Comment.querry("", 1000);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(TITLE);        //made this 'JFrame' instead of 'MainFrame'
                // new Color(82, 76, 76)
                frame.getContentPane().setBackground(new Color(82, 76, 76)); // main frame is white for viewing layout purpose
                frame.setSize(w,h);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);

                //makes 3 bottom buttons' panel
                JPanel panel_bottom = new JPanel();
                panel_bottom.setBackground(new Color(82, 76, 76));


                // left panel
                JPanel panel_left = new JPanel();
                panel_left.setBackground(new Color(82, 76, 76));

//                panel_left.setBackground(new Color(82, 55, 173));

                // right panel
                JPanel panel_right = new JPanel();
                panel_right.setBackground(new Color(82, 76, 76));
//                panel_right.setBackground(new Color(0, 0, 0));

                // right sub panels
                JPanel panel_right_sub1 = new JPanel();
                JPanel panel_right_sub2 = new JPanel();
                JPanel panel_right_sub3 = new JPanel();
                panel_right_sub1.setLayout(new BoxLayout(panel_right_sub1, BoxLayout.X_AXIS));
                panel_right_sub2.setLayout(new BoxLayout(panel_right_sub2, BoxLayout.X_AXIS));
                panel_right_sub3.setLayout(new BoxLayout(panel_right_sub3, BoxLayout.X_AXIS));
                panel_right_sub1.setBackground(Color.WHITE);
                panel_right_sub2.setBackground(Color.GREEN);
                panel_right_sub3.setBackground(Color.CYAN);

                panel_right.setLayout(new BoxLayout(panel_right, BoxLayout.Y_AXIS));

                // menubar creation
                makeMenuBar(frame);

                // Bottom
                makeButton_Refresh(panel_bottom, "Refresh Comments");
                makeButton_Finalize(panel_bottom, "Finalize");
                makeButton_Delete(panel_bottom, "Delete");

                // Left
                makeCommentField(frame, panel_left ,pq);

                // Right
                makeUserCommentField(panel_right_sub1);
                makeUC_Button(panel_right_sub2, "Confirm");
                makeSelectedComment(panel_right_sub3);

                // add sub panels
                panel_right.add(panel_right_sub1);
                panel_right.add(panel_right_sub2);
                panel_right.add(panel_right_sub3);

                // add panels
                frame.add(panel_left, BorderLayout.CENTER);
                frame.add(panel_right, BorderLayout.EAST);
                frame.add(panel_bottom, BorderLayout.SOUTH);

                // frame.pack();
                frame.setVisible(true);
            }
        });
    }

    // ************************* Left Panel *************************

    /**
     *
     * @param frame
     * @param panel_left
     * @param pq
     */
    //@todo get rid of JFrame reference
    public void makeCommentField(JFrame frame, JPanel panel_left, PriorityQueue<Comment> pq) {

        DefaultListModel<Comment> mode = new DefaultListModel<>();

        list_refreshed = new JList(pq.toArray());
        //@todo set a size that's rescalalble
        list_refreshed.setPreferredSize(new Dimension(300, frame.getHeight()));

        //@todo list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); is not working
        list_refreshed.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        list_refreshed.setLayoutOrientation(JList.VERTICAL);
        list_refreshed.setVisibleRowCount(-1);

        panel_left.add(list_refreshed);


    }

    // ************************* Right Panel *************************

    /**
     * Creates user custom comment field
     * @param frame
     * @param panel_right
     */
    public void makeUserCommentField(JPanel panel_right_sub1) {

        // @todo get rid of scroller
        tArea = new TextArea(10, 25);
        tArea.setText("Enter Custom Comment...");
        panel_right_sub1.add(tArea, BoxLayout.X_AXIS);


    }

    /**
     * Creates confirm button for user made comments
     * @param frame
     * @param panel_right
     * @param name
     */
    // @todo make it appear below makeUserCommentField
    public void makeUC_Button(JPanel panel_right_sub2, String name) {

        button_Confirm = new JButton(name);
        panel_right_sub2.add(button_Confirm, BoxLayout.X_AXIS);
        button_Confirm.addActionListener(new ActionListener() {
            //@todo send it to the purple box
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void makeSelectedComment(JPanel panel_right_sub3) {
        // temporary code
        ArrayList<String> cmnt = new ArrayList<>();
        //@todo size
        cmnt.add("Test 111111111111111111111111111111111111111111111111111111");
        cmnt.add("Test 2222222222222222222222222222222222222222222222222222");
        cmnt.add("Test 3333333333333333333333333333333333333333333333333333");
        for (int i=0; i<40; i++) {
            int random= (int)(Math.random()*100);
            cmnt.add("" + random);
        }


        list_selected = new JList(cmnt.toArray());
        list_selected.setPreferredSize(new Dimension(300, 750));
        list_selected.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list_selected.setLayoutOrientation(JList.VERTICAL);
        list_selected.setVisibleRowCount(-1);
        panel_right_sub3.add(list_selected, BoxLayout.X_AXIS);
    }
    //  ************************* Bottom Buttons *************************

    /**
     * Creates "Refresh Comments" button
     * Used to refresh comments on Jlist
     *
     * @param frame Jframe from instantiation
     */
    public void makeButton_Refresh(JPanel panel, String name) {
        Comment cmnt = new Comment();

        button_RC = new JButton(name);

        panel.add(button_RC, BorderLayout.WEST);
        button_RC.addActionListener(new ActionListener() {

            //@todo connect to database

            //Button clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                // test code
//              @todo make JList refresh on button push

            }
        });
    }
    //Finalize button
    public void makeButton_Finalize(JPanel panel, String name) {
        button_Finalize = new JButton(name);
        panel.add(button_Finalize);
    }

    //Delete button
    public void makeButton_Delete(JPanel panel, String name) {
        button_Delete = new JButton(name);
        panel.add(button_Delete);
    }

    // ************************* Menu bar *************************

    /**
     * Creates menu bars
     * Uses JFileChooser API for opening & saving
     *
     * @param frame Jframe from instantiation
     */
    // @todo implement correctly after rough GUI design
    public void makeMenuBar(JFrame frame) {
        menuBar = new JMenuBar();
        menu = new JMenu("File");

        _file = new JMenuItem[3];
        _file[0] = new JMenuItem("New"); // @To do: not sure what this should open
        _file[1] = new JMenuItem("Save"); // saves progress
        _file[2] = new JMenuItem("Save as"); // Options : PDF, Word document


        for(int i=0; i<_file.length; i++) {
            menu.add(_file[i]);
        }
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        // Save as

        _file[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                FileFilter filter1 = new FileNameExtensionFilter(DESCRIPTION[0], EXTENSION[0]);
                FileFilter filter2 = new FileNameExtensionFilter(DESCRIPTION[1], EXTENSION[1]);
                fc.setAcceptAllFileFilterUsed(false);
                fc.setFileFilter(filter1);
                fc.setFileFilter(filter2);

                //@todo implement saveDialog
                int option = fc.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {

                    File fileToSave = fc.getSelectedFile();
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                }
            }
        });
    }



    // public void make_



}
