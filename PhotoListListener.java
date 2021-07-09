package mview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class PhotoListListener extends MView implements ItemListener {
    boolean active;

    public void enablEvents() {
        active = true;
    }

    public void disablEvents() {
        active = false;
    }

    public void itemStateChanged(ItemEvent e) {
        if (active) {
            changeImage(currentDir + slash + photosList.getSelectedItem().toString());
            dirImgIndex = photosList.getSelectedIndex();
        }
    }
}