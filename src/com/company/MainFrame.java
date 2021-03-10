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
    protected final String[] EXTENSION = new String[]{"pdf", "docx"};
    protected final String[] DESCRIPTION = new String[]{"PDF (*.pdf)", "Word Document (*.docx)"};
    // Lists
    public JList list_refreshed;
    public JList list_selected;
    // Menu bars
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem[] _file, _subFile;

    // Container
    Container c;
    JTextArea textArea;

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
     */
    public void makeFrame(int w,  int h) {
        // create priority queue
        PriorityQueue<Comment> pq = Comment.querry("", 1000);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Grading Tool");        //made this 'JFrame' instead of 'MainFrame'
                // new Color(82, 76, 76)
                frame.getContentPane().setBackground(Color.WHITE);
                frame.setSize(w,h);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);

                //makes 3 bottom buttons' panel
                JPanel panel = new JPanel();
                panel.setBackground(new Color(82, 76, 76));
                frame.add(panel, BorderLayout.SOUTH);

                // menubar creation
                makeMenuBar(frame);

                //Call the create field, textArea and button methods
                makeButton_Refresh(frame, panel, "Refresh Comments");
                makeButton_Finalize(frame, panel, "Finalize");
                makeButton_Delete(frame, panel, "Delete");
                makeCommentField(frame, pq);
                makeUserCommentField(frame);

                // frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public void makeCommentField(JFrame frame, PriorityQueue<Comment> pq) {

        list_refreshed = new JList(pq.toArray());
        //@todo set a size that's rescalalble
        list_refreshed.setPreferredSize(new Dimension(300, frame.getHeight()));

        //@todo list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); is not working
        list_refreshed.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        list_refreshed.setLayoutOrientation(JList.VERTICAL);
        list_refreshed.setVisibleRowCount(-1);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(82, 00, 76));
        panel.add(list_refreshed);
        frame.add(panel, BorderLayout.WEST);


    }


    public void makeUserCommentField(JFrame frame) {

        // @todo get rid of scroller
        TextArea tArea = new TextArea(10, 25);
        tArea.setText("Enter Custom Comment...");

        JPanel panel = new JPanel();
        panel.setBackground(new Color(82, 00, 76));
        panel.add(tArea);


        makeUC_Button(frame, panel, "Confirm");

        frame.add(panel, BorderLayout.EAST);
    }

    //User made comments button
    // @todo make it appear below makeUserCommentField
    public void makeUC_Button(JFrame frame, JPanel panel, String name) {

        JButton button = new JButton(name);

        panel.add(button, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            //@todo send it to the purple box
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    //Finalize button
    public void makeButton_Finalize(JFrame frame, JPanel panel, String name) {
        JButton button = new JButton(name);
        panel.add(button);
    }

    //Delete button
    public void makeButton_Delete(JFrame frame, JPanel panel, String name) {
        JButton button = new JButton(name);
        panel.add(button);
    }
    public void makeSelectedComment(JFrame frame, JPanel panel) {
        ArrayList<Comment> cmnt = new ArrayList<>();
        list_selected = new JList(cmnt.toArray());

        panel.add(list_selected);
        frame.add(panel, BorderLayout.SOUTH);
    }

    /**
     * Creates "Refresh Comments" button
     * Used to refresh comments on Jlist
     *
     * @param frame Jframe from instantiation
     */
    public void makeButton_Refresh(JFrame frame, JPanel panel, String name) {
        Comment cmnt = new Comment();

        JButton button = new JButton(name);

        panel.add(button, BorderLayout.WEST);
        button.addActionListener(new ActionListener() {

            //@todo connect to database

            //Button clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                // test code
//              @todo make JList refresh on button push
            }
        });
    }


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
