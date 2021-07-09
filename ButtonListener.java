package mview;

import java.awt.event.*;

class ButtonListener extends MView implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().compareTo("Next") == 0) {
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
        else if (e.getActionCommand().compareTo("Back") == 0) {
            if (aDirectoryOpened) {
                if (dirImgIndex > 0) {
                    changeImage(currentDir + slash + dirImgPaths[--dirImgIndex]);
                    photosList.setSelectedIndex(dirImgIndex);
                }
            }
        }
    }
}