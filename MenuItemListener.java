package mview;

import javax.imageio.*;
import javax.swing.filechooser.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageFilter;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

class MenuItemListener extends MView implements ActionListener  {
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().compareTo("Open") == 0) {
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()); // gets all image file exts
            fc.addChoosableFileFilter(imageFilter);
            fc.setAcceptAllFileFilterUsed(false);

            int r = fc.showOpenDialog(frame);
            if (r == JFileChooser.APPROVE_OPTION) {
                photoListListener.disablEvents();
                changeImage(fc.getSelectedFile().getAbsolutePath());
                aDirectoryOpened = false;
                photosList.removeAllItems();
                photosList.addItem("No directory opened.");

            } 
        }

        else if (e.getActionCommand().compareTo("Open folder") == 0) {
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setAcceptAllFileFilterUsed(false);
            int r = fc.showOpenDialog(frame);
            if (r == JFileChooser.APPROVE_OPTION) {
                if (aDirectoryNeverOpened) photosList.addItemListener(photoListListener);
                aDirectoryNeverOpened = false;
                photoListListener.disablEvents();
                currentDir = new String(fc.getSelectedFile().getAbsolutePath());
                
                File dir = new File(currentDir);     
                dirImgPaths = dir.list(new FNFilter());

                aDirectoryOpened = true;   
                dirImgIndex = 0; 
                changeImage(currentDir + slash + dirImgPaths[dirImgIndex]);
                photosList.removeAllItems();
                for (String str : dirImgPaths) {
                    photosList.addItem(str);
                }
                photosList.setSelectedIndex(dirImgIndex);
                photoListListener.enablEvents();
            }
        }
        else if (e.getActionCommand().compareTo("Exit") == 0) {
            System.exit(0);
        }
        else if (e.getActionCommand().compareTo("Version") == 0) {
            JOptionPane.showMessageDialog(frame, "Version: MView 1.0 (build 22621)\nAuthor: Mauro Tramonti");
        }
    }
}

class FNFilter implements FilenameFilter {
    public boolean accept(File dir, String fileName) {
        return fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".gif") || fileName.endsWith(".jpeg");
    }
}