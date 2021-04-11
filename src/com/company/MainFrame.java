package com.company;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

    // JFrame
    JFrame frame;
    // Constants
    protected final String TITLE = "Grading Tool"; // title of the app
    protected final String[] EXTENSION = new String[]{"pdf", "docx"}; // menubar constant
    protected final String[] DESCRIPTION = new String[]{"PDF (*.pdf)", "Word Document (*.docx)"}; // menubar constant
    // JLists
    public JList list_refreshed;
    public JList list_selected;
    // JSCrollPanes
    public JScrollPane scroll_refreshed, scroll_selected;
    // Menu bars
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem[] _file, _subFile;
    // Buttons
    public JButton button_RC;
    public JButton button_Finalize;
    public JButton button_Delete;
    public JButton button_Confirm;
    // TextField
    public TextField tField;
    // JPanels
    public JPanel panel_left, panel_right, panel_bottom;
    public JPanel panel_right_sub1, panel_right_sub2, panel_right_sub3;
    // DefeaultListModels
    DefaultListModel<Comment> commentList;


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
        PriorityQueue<Comment> pq = Comment.query("", 1000);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame(TITLE);        //made this 'JFrame' instead of 'MainFrame'
                // new Color(82, 76, 76)
                frame.getContentPane().setBackground(Color.DARK_GRAY); // main frame is white for viewing layout purpose
                frame.setSize(w,h);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                //makes 3 bottom buttons' panel
                panel_bottom = new JPanel();
                panel_bottom.setBackground(Color.black);


                // left panel
                panel_left = new JPanel();
                panel_left.setBackground(Color.ORANGE);

//                panel_left.setBackground(new Color(82, 55, 173));

                // right panel
                panel_right = new JPanel();
                panel_right.setBackground(Color.blue);
//                panel_right.setBackground(new Color(0, 0, 0));

                // right sub panels
                panel_right_sub1 = new JPanel();
                panel_right_sub2 = new JPanel();
                panel_right_sub3 = new JPanel();
                panel_right_sub1.setLayout(new BoxLayout(panel_right_sub1, BoxLayout.X_AXIS));
                panel_right_sub2.setLayout(new BoxLayout(panel_right_sub2, BoxLayout.X_AXIS));
                panel_right_sub3.setLayout(new BoxLayout(panel_right_sub3, BoxLayout.X_AXIS));
                panel_right_sub1.setBackground(Color.yellow);
                panel_right_sub2.setBackground(Color.GREEN);
                panel_right_sub3.setBackground(Color.CYAN);

                panel_right.setLayout(new BoxLayout(panel_right, BoxLayout.Y_AXIS));

                // menubar creation
                makeMenuBar();

                // Bottom
                makeButton_Refresh("Refresh Comments");
                makeButton_Finalize("Finalize");
                makeButton_Delete("Delete");

                // Left
                makeCommentField(pq);

                // Right
                makeUserCommentField();
                makeUC_Button("Confirm");
                makeSelectedComment();

                // add sub panels
                panel_right.add(panel_right_sub1);
                panel_right.add(panel_right_sub2);
                panel_right.add(panel_right_sub3);

                // add panels
                frame.add(panel_left, BorderLayout.CENTER);
                frame.add(panel_right, BorderLayout.EAST);
                frame.add(panel_bottom, BorderLayout.SOUTH);


//                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    // ************************* Left Panel *************************

    /**
     *
     * @param pq
     */
    //@todo Make JScrollPane appear
    public void makeCommentField(PriorityQueue<Comment> pq) {

        DefaultListModel<String> mode = new DefaultListModel<>();

        ArrayList<String> test = new ArrayList<>();

        for (int i=0; i<50; i++) {
            test.add("test");
        }
        list_refreshed = new JList(test.toArray());
        //@todo set a size that's rescalable
        list_refreshed.setPreferredSize(new Dimension(400,900));

        //@todo list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); is not working
        list_refreshed.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        list_refreshed.setLayoutOrientation(JList.VERTICAL);
        list_refreshed.setVisibleRowCount(45);

        scroll_refreshed = new JScrollPane(list_refreshed);
        panel_left.add(scroll_refreshed);
    }

    // ************************* Right Panel *************************

    // 1
    /**
     * Creates user custom comment field
     */
    public void makeUserCommentField() {

        // @todo get rid of scroller
        tField = new TextField("Test", 5);
        tField.setText("Enter Custom Comment...");
        tField.setText("Override");
        panel_right_sub1.add(tField, BoxLayout.X_AXIS);
        panel_right_sub1.setBackground(Color.CYAN);
        panel_right_sub1.setPreferredSize(new Dimension(100, 100));
        panel_right_sub1.setMaximumSize(new Dimension(300, 300));   //

        JTextField tt = new JTextField();
    }

    // 2
    /**
     * Creates confirm button for user made comments
     * @param name
     */
    // @todo make it appear below makeUserCommentField
    public void makeUC_Button(String name) {

        button_Confirm = new JButton(name);
        panel_right_sub2.add(button_Confirm, BoxLayout.X_AXIS);
        button_Confirm.addActionListener(new ActionListener() {

            // temp boolean
            boolean first = true;
            // Sends the User Comment to makeSelectedComment
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tField.getText().length() == 0) { // if empty string aka error, skip
                    return;
                }
                if (first){
                    first = false;
                    commentList.remove(0);
                    commentList.addElement(new Comment(tField.getText()));
                    tField.setText("");
                }
                else {
                    commentList.addElement(new Comment(tField.getText()));
                    tField.setText(""); // reset the textfield
                }
            }
        });
    }

    // 3

    //@todo figure out how to update
    /**
     * Creates selected, added comments JList
     */
    public void makeSelectedComment() {

        commentList = new DefaultListModel<>();

        commentList.addElement(new Comment("                                                                                                                    "));
        list_selected = new JList((commentList));
        // @todo size
//        list_selected.setPreferredSize(new Dimension(300, 750));
        list_selected.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list_selected.setLayoutOrientation(JList.VERTICAL);
        list_selected.setVisibleRowCount(-1);
        scroll_selected = new JScrollPane(list_selected);

//        panel_right_sub3.add(list_selected, BoxLayout.X_AXIS);
        panel_right_sub3.add(scroll_selected);


//        list_selected.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//
//            }
//        });

    }
    //  ************************* Bottom Buttons *************************

    /**
     * Creates "Refresh Comments" button
     * Used to refresh comments on Jlist
     */
    public void makeButton_Refresh(String name) {
        Comment cmnt = new Comment();

        button_RC = new JButton(name);

        panel_bottom.add(button_RC, BorderLayout.WEST);
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
    public void makeButton_Finalize(String name) {
        button_Finalize = new JButton(name);
        panel_bottom.add(button_Finalize);
    }

    //Delete button
    public void makeButton_Delete(String name) {
        button_Delete = new JButton(name);
        panel_bottom.add(button_Delete);

        // deletes from SelectedComment
        button_Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // array of index for deletion
                int[] indices = list_selected.getSelectedIndices();
                for (int i=indices.length-1; i>=0; i--) { // deletes the selected comments
                    commentList.remove(indices[i]);
                }
            }
        });
    }

    // ************************* Menu bar *************************

    /**
     * Creates menu bars
     * Uses JFileChooser API for opening & saving
     */
    // @todo implement correctly after rough GUI design
    public void makeMenuBar() {
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
