package com.company;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MainFrame("Grading Tool");
                frame.setSize(w,h);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                makeMenuBar(frame);

                // refresh button
                makeButton(frame, "Refresh Comments" ,w/2-50, h-50, 100, 100);
                textArea = new JTextArea(1, 1);
                textArea.setSize(10, 10);
                frame.add(textArea);
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
            }
        });
    }
    /**
     * Creates Button
     * Used to finish and export comments
     *
     * @param frame Jframe from instantiation
     */
    public void makeButton(JFrame frame, String name , int x, int y, int w, int h) {
        Comment cmnt = new Comment();
        PriorityQueue<Comment> pq = Comment.querry("", 1000);

        JButton button = new JButton(name);
        button.setBounds(x, y, w, h);
        frame.add(button, BorderLayout.SOUTH);
        button.addActionListener(new ActionListener() {

            //@todo connect to database
            //Button clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                // test code
                if (pq.peek() == null)
                    textArea.append("Queue Empty\n");
                else textArea.append(pq.poll().getText() + "\n");
            }
        });
    }
}
