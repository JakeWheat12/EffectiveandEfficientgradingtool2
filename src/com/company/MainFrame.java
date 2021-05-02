package com.company;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.io.File;
import java.io.IOException;

/**
 * @todo: description
 *
 * @author Jake Wheat, Hyungsuk Kim
 */
public class MainFrame extends JFrame {

    // JFrame
    JFrame frame;
    // Constants
    protected final String TITLE = "Comment Generator"; // title of the app //TODO(DU) update the name
    protected final String EXTENSION = "pdf"; // menubar constant
    protected final String DESCRIPTION = "PDF (*.pdf)"; // menubar constant   //TODO (DU): get ride of WORD?
    // JLists
    public JList list_refreshed;
    public JList list_selected;
    // JSCrollPanes
    public JScrollPane scroll_refreshed, scroll_selected;
    // Menu bars
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem[] menuItems;  //items in the menu bar
    // Buttons
    public JButton button_RC;
    public JButton button_Clear;
    public JButton button_Finalize;
    public JButton button_Delete;
    public JButton button_Add;
    public JButton filler;
    // Labels
    public JLabel label_commentField;
    public JLabel label_selectedField;
    // TextField
    public TextField tField;
    // JPanels
    public JPanel panel_left, panel_right, panel_bottom;
    public JPanel panel_right_sub1, panel_right_sub2, panel_right_sub3;
    // DefaultListModels
    DefaultListModel<Comment> commentList;
    // booleans
    private boolean first = true;

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

    /**
     * Creates the first window to be displayed.
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
                panel_bottom.setBackground(Color.DARK_GRAY);


                // left panel
                panel_left = new JPanel();
                // @ todo setAligntment has no effect (??)
//                panel_left.setAlignmentX(Component.CENTER_ALIGNMENT);
//                panel_left.setAlignmentY(Component.BOTTOM_ALIGNMENT);
                panel_left.setLayout(new BoxLayout(panel_left, BoxLayout.Y_AXIS));
                panel_left.setBackground(Color.DARK_GRAY);

//                panel_left.setBackground(new Color(82, 55, 173));

                // right panel
                panel_right = new JPanel();
                panel_right.setBackground(Color.DARK_GRAY);
//                panel_right.setBackground(new Color(0, 0, 0));

                // right sub panels
                panel_right_sub1 = new JPanel();
                panel_right_sub2 = new JPanel();
                panel_right_sub3 = new JPanel();
                panel_right_sub1.setLayout(new BoxLayout(panel_right_sub1, BoxLayout.LINE_AXIS));
                panel_right_sub2.setLayout(new BoxLayout(panel_right_sub2, BoxLayout.LINE_AXIS));
                panel_right_sub3.setLayout(new BoxLayout(panel_right_sub3, BoxLayout.LINE_AXIS));
                panel_right_sub1.setBackground(Color.yellow);
                panel_right_sub2.setBackground(Color.GREEN);
                panel_right_sub3.setBackground(Color.CYAN);

                panel_right.setLayout(new BoxLayout(panel_right, BoxLayout.Y_AXIS));

                // ************************ method calls ************************

                // menubar creation
                makeMenuBar();

                // Bottom
                makeButton_Refresh();
                makeButton_Finalize();
                makeButton_Delete();

                // Left
                makeCommentFieldLabel();
                makeCommentField(pq);

                // Right
                makeUserCommentField();
                makeButtonUC_Clear();
                makeAdd_Button();
                makeSelectedComment();

                // add sub panels
                panel_right.add(panel_right_sub1);
                panel_right.add(panel_right_sub2);
                panel_right.add(panel_right_sub3);

                // add panels
                frame.add(panel_left, BorderLayout.CENTER);
                frame.add(panel_right, BorderLayout.EAST);
                frame.add(panel_bottom, BorderLayout.SOUTH);

                frame.setVisible(true);
            }
        });
    }

    // ************************* Left Panel *************************

    /**
     * Creates Comment Field for users to choose comments from
     *
     * @param pq
     *
     * @todo do something with parameter
     */
    public void makeCommentField(PriorityQueue<Comment> pq) {

        DefaultListModel<String> mode = new DefaultListModel<>();

        ArrayList<String> test = new ArrayList<>();

        for (int i=0; i<50; i++) {
            test.add("test " + i);
        }
        list_refreshed = new JList(test.toArray());
        list_refreshed.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        list_refreshed.setBackground(Color.gray);
        list_refreshed.setForeground(Color.white);
        list_refreshed.setFont(new Font("courrier new", Font.PLAIN, 14));
        list_refreshed.setLayoutOrientation(JList.VERTICAL);
        list_refreshed.setVisibleRowCount(45);
        scroll_refreshed = new JScrollPane(list_refreshed);

        //Mouse Listener Interface
        list_refreshed.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (first){
                    first = false;
                    commentList.remove(0);
                }
                commentList.addElement(new Comment(test.get(list_refreshed.getSelectedIndex())));
            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel_left.add(scroll_refreshed);
    }

    /**
     * Creates a Label of description for CommentField
     */
    public void makeCommentFieldLabel() {

        label_commentField = new JLabel("Choose your comments");
        label_commentField.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        label_commentField.setForeground(Color.WHITE);
        panel_left.add(label_commentField);
    }

    // ************************* Right Panel *************************

    // 1
    /**
     * Creates user custom comment field
     */
    public void makeUserCommentField() {

        tField = new TextField("Test", 5);
        tField.setText("Enter Custom Comment...");

        panel_right_sub1.add(tField);
        panel_right_sub1.setBackground(Color.CYAN);
        panel_right_sub1.setPreferredSize(new Dimension(100, 100));
        panel_right_sub1.setMaximumSize(new Dimension(300, 300));

        tField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    commentList.addElement(new Comment(tField.getText()));
                    tField.setText("");
                }
            }
        });

        JTextField tt = new JTextField();
    }

    // 2
    /**
     * Creates Add button for user made comments
     *
     */
    // @todo make it appear below makeUserCommentField
    public void makeAdd_Button() {

        button_Add = new JButton("add");
        panel_right_sub2.add(button_Add, BoxLayout.X_AXIS);
        button_Add.addActionListener(new ActionListener() {

            // Sends the User Comment to makeSelectedComment
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tField.getText().length() == 0) { // if empty string aka error, skip
                    tField.requestFocusInWindow();
                    return;
                }
                // reset the textfield
                if (first){
                    first = false;
                    commentList.remove(0);
                }
                commentList.addElement(new Comment(tField.getText()));
                tField.setText("");
                tField.requestFocusInWindow();
            }
        });
    }
    public void makeButtonUC_Clear() {
        button_Clear = new JButton("Clear");
        panel_right_sub2.add(button_Clear, BoxLayout.X_AXIS);
        // deletes from SelectedComment
        button_Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tField.setText(""); // reset the textfield
                tField.requestFocusInWindow();
            }
        });
    }

    // 3

    /**
     * Creates selected, added comments JList
     */
    public void makeSelectedComment() {

        commentList = new DefaultListModel<>();

        // cheesed
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
    }

    public void makeSelectedCommentLabel() {
        label_commentField = new JLabel("Choose your comments");
        label_commentField.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        label_commentField.setForeground(Color.WHITE);
        panel_left.add(label_commentField);
    }


    //  ************************* Bottom Buttons *************************

    /**
     * Creates "Refresh Comments" button
     * Used to refresh comments on Jlist
     */
    public void makeButton_Refresh() {
        Comment cmnt = new Comment();

        button_RC = new JButton("Refresh");

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
    public void makeButton_Finalize() {
        button_Finalize = new JButton("Finalize");
        panel_bottom.add(button_Finalize);

        button_Finalize.addActionListener(new ActionListener() {

            //Button clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter(DESCRIPTION, EXTENSION);
                fc.setAcceptAllFileFilterUsed(false);
                fc.setFileFilter(filter);

                int option = fc.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {

                    // temp for getting directory
                    File fileToSave = fc.getSelectedFile();

                    // save as pdf
                    try {
                        CreatePDF.createPDF_saveAs(fileToSave.getAbsolutePath(), Helper.toArrayList(commentList));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                }
            }
        });
    }

    //Delete button
    public void makeButton_Delete() {
        button_Delete = new JButton("Delete");
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
        menu = new JMenu("General");    //TODO(DU) update the name

        menuItems = new JMenuItem[2];
        menuItems[0] = new JMenuItem("Sign Up"); // allow users to create a new account
        menuItems[1] = new JMenuItem("Save as"); // Options : PDF, Word document


        for(int i=0; i<menuItems.length; i++) {
            menu.add(menuItems[i]);
        }
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        //user sign up
        menuItems[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Save as
        menuItems[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter(DESCRIPTION, EXTENSION);
                fc.setAcceptAllFileFilterUsed(false);
                fc.setFileFilter(filter);

                int option = fc.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {

                    // temp for getting directory
                    File fileToSave = fc.getSelectedFile();

                    // save as pdf
                    try {
                        CreatePDF.createPDF_saveAs(fileToSave.getAbsolutePath(), Helper.toArrayList(commentList));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                }
            }
        });
    }



    // public void make_


    public static void main(String args[]) {
        MainFrame frame = new MainFrame();
        frame.makeFrame(800, 800);
    }

}