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

    // Menubars
    private JMenuBar menuBar;
    private JMenu menu, subMenu;
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

//        final JTextArea textArea = new JTextArea();
//        JButton button = new JButton("button");

//        c = getContentPane();
//        c.add(textArea);
//        c.add(button, BorderLayout.SOUTH);

        // test code
//        Comment cmnt = new Comment();
//        PriorityQueue<Comment> pq = Comment.querry("", 1000);

//        JButton button = new JButton("button");
//        button.setBounds(650, 650, 100, 50);
//        button.addActionListener(new ActionListener() {
//            //Button clicked
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // test code
//                if (pq.peek() == null)
//                    textArea.append("Queue Empty\n");
//                else textArea.append(pq.poll().getText() + "\n");
//            }
//        });
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
                makeButton(frame, w/2-50, h-150, 100, 50);
                textArea = new JTextArea(10, 10);
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
        subMenu = new JMenu("New");

        _file = new JMenuItem[3];
        _file[0] = new JMenuItem("Open"); // @To do: not sure what this should open
        _file[1] = new JMenuItem("Save"); // saves progress
        _file[2] = new JMenuItem("Save as"); // Options : PDF, Word document

        // Submenu of Open
        _subFile = new JMenuItem[2];
        _subFile[0] = new JMenuItem("Word document");
        _subFile[1] = new JMenuItem("Text file");

        for(int i=0; i<_subFile.length; i++) {
            subMenu.add(_subFile[i]);
        }
        menu.add(subMenu);
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

                fc.showSaveDialog(frame);
            }
        });
    }

    public void makeButton(JFrame frame, int x, int y, int w, int h) {
        Comment cmnt = new Comment();
        PriorityQueue<Comment> pq = Comment.querry("", 1000);

        JButton button = new JButton("button");
        button.setBounds(x, y, w, h);
        frame.add(button);
        button.addActionListener(new ActionListener() {
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
