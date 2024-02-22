package englishnote;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;
import java.util.ArrayList;

public class frameAddWord extends frameOptionCommon implements ActionListener {
    frameAddWord() {
        new frameOptionCommon();
        setTitle("Add Word");
        dispose();
        setParameter(0);
        setPane(0);

        // create new arrayLists
        word_Added = new ArrayList<>();
        pannel_Added = new ArrayList<>();
        pane_AddWordAdded = new ArrayList<>();

        // set icon image to btn_AddBox
        image_icon_add = new ImageIcon("..\\englishnote\\src\\main\\java\\Icons\\icon_add.png");

        // create new paneAddWord
        pane_AddWord = new paneAddWord();
        pane_AddWord.setAddButton(btn_AddBox);
        pane_AddWord.setLocation(0, 0);
        pane_ShowAdd.add(pane_AddWord.getPaneAddWord());

        // create new JButton to add new paneAddWord
        btn_AddBox = new JButton(image_icon_add);
        btn_AddBox.setBackground(Color.WHITE);
        y_AddBox = pane_AddWord.getHeight() / 2 - height_AddBox / 2;
        btn_AddBox.setBounds(x_AddBox, y_AddBox, width_AddBox, height_AddBox);
        btn_AddBox.setFocusable(false);
        btn_AddBox.setEnabled(false);
        btn_AddBox.addActionListener(this);
        btn_AddBox.setMnemonic('A');

        // add btn_AddBox to paneAddWord
        pane_AddWord.addToPane(btn_AddBox);

        // add paneAddWord to ArrayList<JPane>
        pannel_Added.add(pane_AddWord.getPaneAddWord());

        // add paneAddWord to ArrayList<paneAddWord>
        pane_AddWordAdded.add(pane_AddWord);

        // set submit button and add button to paneAddWord
        pane_AddWord.setSubmitButton(btn_Submit);
        pane_AddWord.setAddButton(btn_AddBox);

        if (listWord != null) {
            // listWord = new listWord();
            setListWord(listWord);
        }

        setVisible(true);
    }

    // set List word to this.Listword
    public void setListWord(listWord listWord) {
        frameAddWord.listWord = listWord;
        super.setList(listWord);
    }

    private void setParameter(int y_add) {
        // parameter panel_ShowAdd
        width_pane_ShowAdd = width_FrameOptionCommon;
        height_pane_ShowAdd = height_FrameOptionCommon * 70 / 100 + y_add;
        x_pane_ShowAdd = 0;
        y_pane_ShowAdd = 0;

        // parameter panel_Submit
        width_pane_Submit = width_FrameOptionCommon;
        height_pane_Submit = height_FrameOptionCommon - height_pane_ShowAdd + y_add;
        x_pane_Submit = 0;
        y_pane_Submit = height_pane_ShowAdd;

        // parameter button_Submit
        width_btn_Submit = 100;
        height_btn_Submit = 30;
        x_btn_Submit = width_pane_Submit / 2 - width_btn_Submit / 2;
        y_btn_Submit = 50 + y_add;
    }

    // create initial of pane_showAdd, pane_Submit and btn_Submit
    private void setPane(int y_add) {
        // create pane_ShowAdd
        pane_ShowAdd = new JPanel();
        pane_ShowAdd.setBounds(x_pane_ShowAdd, y_pane_ShowAdd, width_pane_ShowAdd, height_pane_ShowAdd + y_add);
        pane_ShowAdd.setLayout(null);
        add(pane_ShowAdd, this);

        // create pane_Submit
        pane_Submit = new JPanel();
        pane_Submit.setLayout(null);
        pane_Submit.setBounds(x_pane_Submit, y_pane_Submit, width_pane_Submit, height_pane_Submit + y_add);
        add(pane_Submit, this);

        // create btn_Submit
        btn_Submit = new JButton("Thêm");
        btn_Submit.addActionListener(this);
        btn_Submit.setBounds(x_btn_Submit, y_btn_Submit, width_btn_Submit, height_btn_Submit);
        btn_Submit.setFocusable(false);
        btn_Submit.setEnabled(false);
        pane_Submit.add(btn_Submit);
        btn_Submit.setMnemonic('s');
    }

    private void addWordBox() {
        if (height_FrameOptionCommon >= height_Screen - 150) {
            JOptionPane.showMessageDialog(this, "Full Option");
            btn_AddBox.setEnabled(false);
            return;
        }

        // remove label "Word", label "Meaning" and button add box from paneAddWord
        pannel_Added.get(pannel_Added.size() - 1).remove(pane_AddWord.getLabelAddWord());
        pannel_Added.get(pannel_Added.size() - 1).remove(pane_AddWord.getLabelAddMeaning());
        pannel_Added.get(pannel_Added.size() - 1).remove(btn_AddBox);

        // set start y location to create new paneAddWoed
        y_AddNewBox += pane_AddWord.getHeight();
        pane_AddWord = new paneAddWord();
        pane_AddWord.setLocation(0, y_AddNewBox);

        // create new btn_AddBox
        btn_AddBox = new JButton(image_icon_add);
        btn_AddBox.setBackground(Color.WHITE);
        y_AddBox = pane_AddWord.getHeight() / 2 - height_AddBox / 2;
        btn_AddBox.setBounds(x_AddBox, y_AddBox, width_AddBox, height_AddBox);
        btn_AddBox.setFocusable(false);
        btn_AddBox.setMnemonic('a');

        // add action listener to btn_AddBox
        btn_AddBox.addActionListener(this);

        pane_AddWord.addToPane(btn_AddBox);

        // set btn_Submit and btn_AddBox to new paneAddWord
        pane_AddWord.setSubmitButton(btn_Submit);
        pane_AddWord.setAddButton(btn_AddBox);

        // setEnable to turn off btn_AddBox and btn_Submit
        btn_AddBox.setEnabled(false);
        btn_Submit.setEnabled(false);

        pane_ShowAdd.add(pane_AddWord.getPaneAddWord());

        // add new paneAddWord to ArrayList<JPannel>
        pannel_Added.add(pane_AddWord.getPaneAddWord());

        // add new paneAddWord to ArrayList<paneAddWord>
        pane_AddWordAdded.add(pane_AddWord);

        reload(y_AddNewBox, pane_AddWord);

        repaint();
    }

    // reload the size of frameAddWord
    private void reload(int height_add, paneAddWord pane_AddWord) {
        if (height_add > maxTempt) {
            getContentPane().removeAll();
            maxTempt = height_add;
            setParameter(pane_AddWord.getHeight());
            setPane(pane_AddWord.getHeight());

            // set btn_Submit and btn_AddBox to new paneAddWord
            pane_AddWord.setSubmitButton(btn_Submit);
            pane_AddWord.setAddButton(btn_AddBox);

            // setEnable to turn off btn_AddBox and btn_Submit
            btn_AddBox.setEnabled(false);
            btn_Submit.setEnabled(false);

            int i = 0;
            while (i < pannel_Added.size()) {
                pane_ShowAdd.add(pannel_Added.get(i));
                i++;
            }
            resize(pane_AddWord.getHeight());
        }
    }

    // setArray word before save
    private void setArrayWord() {
        int number = 0;
        while (number < pane_AddWordAdded.size()) {
            word_Added.add(pane_AddWordAdded.get(number).getWordAdded().get(0));
            number++;
        }
    }

    // solve when btn_Submit was clicked
    private void addSubmit() {
        setArrayWord();
        setArrayAdd(word_Added); // set word_Added to frameOptionCommon
        saveData();
        dispose();

        image_icon = new ImageIcon("..\\englishnote\\src\\main\\java\\Icons\\nezuko cute.png");
        JOptionPane.showMessageDialog(this, "Saved", "Information", JOptionPane.YES_OPTION, image_icon);

        new frameAddWord();
    }

    // ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == btn_AddBox) {
            addWordBox();
        }

        if (e.getSource() == btn_Submit) {
            addSubmit();
        }
    }

    // WindowListener
    @Override
    public void windowClosing(WindowEvent e) {

        // show dialog save data if text unequal "" and btn_add wás not clicked
        if (!pane_AddWordAdded.get(0).getTextWord().getText().equals("")
                && !pane_AddWordAdded.get(0).getTextMeaning().getText().equals("")) {
            String[] message = { "Save", "Not save", "Wait" };
            ImageIcon icon = new ImageIcon("..\\englishnote\\src\\main\\java\\Icons\\nezuko question.png");

            int selected = JOptionPane.showOptionDialog(this, "Save?", "Information", 0,
                    JOptionPane.QUESTION_MESSAGE, icon,
                    message, message[0]);

            if (selected == 0) {
                setArrayWord();
                setArrayAdd(word_Added);
                saveData();
            } else if (selected == 1) {

            } else if (selected == 2) {
                Frame_error.setVisible(true);
            } else {
                windowClosing(e);
            }
        }
    }

    private JFrame Frame_error;
    private static listWord listWord;

    private JPanel pane_ShowAdd;
    private int width_pane_ShowAdd;
    private int height_pane_ShowAdd = height_FrameOptionCommon * 70 / 100;
    private int x_pane_ShowAdd;
    private int y_pane_ShowAdd;

    private JPanel pane_Submit;
    private int width_pane_Submit;
    private int height_pane_Submit;
    private int x_pane_Submit;
    private int y_pane_Submit;

    private JButton btn_Submit;
    private int width_btn_Submit;
    private int height_btn_Submit;
    private int x_btn_Submit;
    private int y_btn_Submit;

    private paneAddWord pane_AddWord;

    private JButton btn_AddBox;
    private int height_AddBox = 25;
    private int width_AddBox = 25;
    private int x_AddBox = 5;
    private int y_AddBox;

    private int y_AddNewBox = 0;
    private int maxTempt = height_pane_ShowAdd;
    private ImageIcon image_icon_add;

    private ArrayList<JPanel> pannel_Added;
    private ArrayList<String> word_Added;
    private ArrayList<paneAddWord> pane_AddWordAdded;

    private ImageIcon image_icon;
}
