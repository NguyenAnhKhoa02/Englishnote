package englishnote;

import javax.swing.JFrame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.*;

public class frameOptionCommon extends JFrame implements WindowListener {
    frameOptionCommon() {
        setBounds(x_FrameOptionCommon, y_FrameOptionCommon, width_FrameOptionCommon, height_FrameOptionCommon);
        setLayout(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addWindowListener(this);
        setResizable(false);
        setVisible(false);
        dispose();
    }

    protected void setArrayAdd(ArrayList<String> arrAdd) {
        array_Add = arrAdd;
    }

    protected void setList(listWord list) {
        this.list = list;
    }

    // save Data
    protected void saveData() {
        int i = 0;

        while (i < array_Add.size()) {
            list.getArrList().add(array_Add.get(i));
            i++;
        }

        list.setArrList();
    }

    // resize frame when y was changed
    public void resize(int y_add) {
        height_FrameOptionCommon += y_add;
        y_FrameOptionCommon = height_Screen / 2 - height_FrameOptionCommon;
        x_FrameOptionCommon = width_Screen / 2 - width_FrameOptionCommon / 2;
        y_FrameOptionCommon = height_Screen / 2 - height_FrameOptionCommon / 2;

        if (height_FrameOptionCommon >= height_Screen - 150) {
            height_FrameOptionCommon = height_Screen;
            y_FrameOptionCommon = 0;
        }

        setBounds(x_FrameOptionCommon, y_FrameOptionCommon, width_FrameOptionCommon,
                height_FrameOptionCommon + y_add);
    }

    private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    protected final int width_Screen = (int) size.getWidth();
    protected final int height_Screen = (int) size.getHeight();
    protected int width_FrameOptionCommon = 400;
    protected int height_FrameOptionCommon = 400;
    protected int x_FrameOptionCommon = width_Screen / 2 - width_FrameOptionCommon / 2;
    protected int y_FrameOptionCommon = height_Screen / 2 - height_FrameOptionCommon / 2;

    private listWord list;
    private ArrayList<String> array_Add;

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }
}
