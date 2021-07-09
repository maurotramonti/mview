package mview;

import javax.swing.*;
import java.awt.event.*;



class LeftArrowHandler extends MView implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (aDirectoryOpened) {
            if (dirImgIndex > 0) {
                changeImage(currentDir + slash + dirImgPaths[--dirImgIndex]);
                photosList.setSelectedIndex(dirImgIndex);
            }
        }
    }
}

class RightArrowHandler extends MView implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (aDirectoryOpened) {
            try {
                changeImage(currentDir + slash + dirImgPaths[++dirImgIndex]);
                photosList.setSelectedIndex(dirImgIndex);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                dirImgIndex = 0;
                changeImage(currentDir + slash + dirImgPaths[dirImgIndex]);
                photosList.setSelectedIndex(dirImgIndex);
            }
        }
    }
}
