package mview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MView extends JFrame {
    MView(){}

    static JFrame frame = new JFrame("MView");
    static JLabel imgL = new JLabel("No image opened.", JLabel.CENTER);
    static String currentDir;
    static boolean aDirectoryNeverOpened = true;
    static boolean aDirectoryOpened = false;
    static String[] dirImgPaths = {"No directory opened."};
    static int dirImgIndex;
    static JComboBox photosList = new JComboBox(dirImgPaths);
    static String slash;
    static PhotoListListener photoListListener = new PhotoListListener();

    public static void main(String[] args) {
        String system = System.getProperty("os.name");
        if (system.compareTo("Linux") == 0) slash = "/";
        else if (system.contains("Windows")) slash = "\\";
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");
        JMenuItem menuItems[] = new JMenuItem[3];
        KeyStroke[] accelerators = {KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK), KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK), KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK)};
        String[] menuItemLbls = {new String("Open"), new String("Open folder"), new String("Exit")};
        String[] menuItemActs = {new String("Open"), new String("Open folder"), new String("Exit")};
        String[] btnActs = {"Back", "Next"};
        
        JPanel imgP = new JPanel();
        JPanel btnP = new JPanel();    
        JPanel lstP = new JPanel();

        JButton[] buttons = {new JButton(new ImageIcon("images" + slash + "back.png", "back btn")), new JButton(new ImageIcon("images" + slash + "next.png", "next btn"))};
        lstP.setBounds(0, 0, 100, 60);
        imgP.setLayout(new FlowLayout());
        btnP.setLayout(new FlowLayout());
        lstP.setLayout(new BoxLayout(lstP, BoxLayout.Y_AXIS));

        JLabel lstP_l1 = new JLabel("Browse direcory's photos list.", JLabel.CENTER);
        lstP_l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        lstP_l1.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        photosList.setMaximumSize((new Dimension(150, 20)));

        lstP.add(lstP_l1);
        lstP.add(photosList);
        imgP.add(imgL);


        JScrollPane scrollImg = new JScrollPane(imgP);  
        scrollImg.getVerticalScrollBar().setUnitIncrement(16); 

        for (int i = 0; i < 3; i++) {
            if (i == 2) fileMenu.addSeparator();
            menuItems[i] = new JMenuItem(menuItemLbls[i]);
            menuItems[i].setActionCommand(menuItemActs[i]);
            menuItems[i].addActionListener(new MenuItemListener());
            menuItems[i].setAccelerator(accelerators[i]);
            fileMenu.add(menuItems[i]);
        }

        JMenuItem versionItem = new JMenuItem("Version");
        versionItem.setActionCommand("Version");
        versionItem.addActionListener(new MenuItemListener());
        aboutMenu.add(versionItem);

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        for (int i = 0; i < 2; i++) {
            btnP.add(buttons[i]);
            buttons[i].setActionCommand(btnActs[i]);
            buttons[i].addActionListener(new ButtonListener());            
        }

        buttons[0].registerKeyboardAction(new LeftArrowHandler(), KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), JComponent.WHEN_IN_FOCUSED_WINDOW);
        buttons[1].registerKeyboardAction(new RightArrowHandler(), KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), JComponent.WHEN_IN_FOCUSED_WINDOW);

        imgP.add(imgL);


        frame.getContentPane().add(scrollImg, BorderLayout.CENTER);
        frame.getContentPane().add(btnP, BorderLayout.SOUTH);
        frame.getContentPane().add(lstP, BorderLayout.EAST);


        frame.setJMenuBar(menuBar);
        frame.setSize(700, 600);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void changeImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        imgL.setIcon(icon);
        frame.setTitle("MView - " + path);
        if (imgL.getText().compareTo("") != 0) {
            imgL.setText("");
        }        
    }

}

