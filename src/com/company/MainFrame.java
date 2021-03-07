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
    //Lists
    public JList list;
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
        PriorityQueue<Comment> pq = Comment.querry("", 1000);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Grading Tool");        //made this 'JFrame' instead of 'MainFrame'
                frame.setSize(w,h);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                makeMenuBar(frame);

                // refresh button
                makeButton_Refesh(frame, "Refresh Comments");
                makeCommentField(frame, pq);

            }
        });
    }

    public void makeCommentField(JFrame frame, PriorityQueue<Comment> pq) {
//        DefaultListModel<String> l1 = new DefaultListModel<>();
//        l1.addElement("Item1");
//        l1.addElement("Item2");
//        l1.addElement("Item3");
//        l1.addElement("Item4");

        //@todo make JList show
        list = new JList(pq.toArray());
        add(list);
        list.setBounds(100, 100,100, 100);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);



        JPanel panel = new JPanel();
        panel.add(list);
        frame.add(panel);


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
    /**
     * Creates Button
     * Used to finish and export comments
     *
     * @param frame Jframe from instantiation
     */
    public void makeButton_Refesh(JFrame frame, String name) {
        Comment cmnt = new Comment();


        JButton button = new JButton(name);
        frame.add(button, BorderLayout.SOUTH);
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
}
