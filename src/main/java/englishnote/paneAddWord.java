package englishnote;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

public class paneAddWord extends frameOptionCommon implements KeyListener {
    paneAddWord() {
        panel_AddWord = new JPanel(); // create panel addword
        panel_AddWord.setLayout(null);
        // panel_AddWord.setBackground(Color.BLACK);

        // create and set atribute of text field add word box
        txt_AddWordBox = new JTextField();
        txt_AddWordBox.setBounds(x_txt_AddWordBox, y_txt_AddWordBox, width_AddWordBox, height_AddWordBox);
        txt_AddWordBox.addKeyListener(this);

        // create and set atribute of text field add meaning box
        txt_AddMeaningBox = new JTextField();
        txt_AddMeaningBox.setBounds(x_txt_AddMeaningBox, y_txt_AddMeaningBox, width_AddMeaningBox,
                height_AddMeaningBox);
        txt_AddMeaningBox.addKeyListener(this);

        // create label add word
        lb_AddWord = new JLabel("Word");
        lb_AddWord.setBounds(x_lb_AddWord, y_lb_AddWord, width_lb_AddWord, height_lb_AddWord);

        // create label add meaning
        lb_AddMeaning = new JLabel("Meaning");
        lb_AddMeaning.setBounds(x_lb_AddMeaning, y_lb_AddMeaning, width_lb_AddMeaning, height_lb_AddMeaning);

        panel_AddWord.add(txt_AddMeaningBox);
        panel_AddWord.add(txt_AddWordBox);
        panel_AddWord.add(lb_AddWord);
        panel_AddWord.add(lb_AddMeaning);

    }

    // set submit button to btn_Submit
    public void setSubmitButton(JButton button) {
        btn_Submit = button;
    }

    // set add button to btn_Add
    public void setAddButton(JButton button) {
        btn_Add = button;
    }

    private void setArrayWord() {
        String add_word;

        add_word = getTextWord().getText() + "@"
                + getTextMeaning().getText();

        if (word_Added.size() > 0) {
            word_Added.remove(word_Added.get(word_Added.size() - 1));
            word_Added.add(0, add_word);
        } else
            word_Added.add(add_word);

        int number = 0;
        while (number < word_Added.size()) {
            number++;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // set keytped for txt_AddWordBox
        if (e.getSource() == txt_AddWordBox) {
            System.out.println(txt_AddWordBox.getText());
            if (e.getKeyChar() >= 97 && e.getKeyChar() <= 122 || e.getKeyChar() >= 65 && e.getKeyChar() <= 90)
                is_WordText = true;
            else if (!txt_AddWordBox.getText().equals(""))
                is_WordText = true;
            else if (txt_AddWordBox.getText().equals(""))
                is_WordText = false;
        }

        // set keytped for txt_AddMeaningBox
        if (e.getSource() == txt_AddMeaningBox) {
            if (e.getKeyChar() >= 97 && e.getKeyChar() <= 122 || e.getKeyChar() >= 65 && e.getKeyChar() <= 90)
                is_MeaningText = true;
            else if (!txt_AddMeaningBox.getText().equals(""))
                is_MeaningText = true;
            else if (txt_AddMeaningBox.getText().equals(""))
                is_MeaningText = false;
        }

        // setEnable for btn_Submit or btn_Add
        if (is_WordText == true && is_MeaningText == true) {
            btn_Submit.setEnabled(true);
            btn_Add.setEnabled(true);
            // setArrayWord();

        } else {
            btn_Submit.setEnabled(false);
            btn_Add.setEnabled(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        // set keytped for txt_AddWordBox
        if (e.getSource() == txt_AddWordBox) {
            if (e.getKeyChar() >= 97 && e.getKeyChar() <= 122 || e.getKeyChar() >= 65 && e.getKeyChar() <= 90)
                is_WordText = true;
            else if (txt_AddWordBox.getText().equals("")) {
                if (word_Added.size() > 0) {
                    word_Added.remove(word_Added.size() - 1);
                }
            }

        }

        // set keytped for txt_AddMeaningBox
        if (e.getSource() == txt_AddMeaningBox) {
            if (e.getKeyChar() >= 97 && e.getKeyChar() <= 122 || e.getKeyChar() >= 65 && e.getKeyChar() <= 90)
                is_MeaningText = true;
            else if (txt_AddMeaningBox.getText().equals(""))
                if (word_Added.size() > 0) {
                    word_Added.remove(word_Added.size() - 1);
                }
        }

        // setEnable for btn_Submit or btn_Add
        if (is_WordText == true && is_MeaningText == true) {
            setArrayWord();
        }
    }

    public JLabel getLabelAddWord() {
        return lb_AddWord;
    }

    public JLabel getLabelAddMeaning() {
        return lb_AddMeaning;
    }

    public JTextField getTextWord() {
        return txt_AddWordBox;
    }

    public JTextField getTextMeaning() {
        return txt_AddMeaningBox;
    }

    public void setLocation(int x, int y) {
        panel_AddWord.setBounds(x, y, width_panel_AddWord, height_pannel_AddWord);
    }

    public JPanel getPaneAddWord() {
        return panel_AddWord;
    }

    public void addToPane(JButton btn) {
        panel_AddWord.add(btn);
    }

    public ArrayList<String> getWordAdded() {
        return word_Added;
    }

    public int getHeight() {
        return height_pannel_AddWord;
    }

    public JPanel panel_AddWord;
    private final int width_panel_AddWord = width_FrameOptionCommon - 10;
    private final int height_pannel_AddWord = height_FrameOptionCommon / 4;

    private JTextField txt_AddWordBox;
    private final int width_AddWordBox = 100;
    private final int height_AddWordBox = 25;
    private final int x_txt_AddWordBox = width_FrameOptionCommon / 12;
    private final int y_txt_AddWordBox = height_pannel_AddWord / 2 - height_AddWordBox / 2;

    private JTextField txt_AddMeaningBox;
    private final int width_AddMeaningBox = 200;
    private final int height_AddMeaningBox = 25;
    private final int x_txt_AddMeaningBox = width_FrameOptionCommon / 3 + 20;
    private final int y_txt_AddMeaningBox = height_pannel_AddWord / 2 - height_AddWordBox / 2;

    private JLabel lb_AddWord;
    private int width_lb_AddWord = 50;
    private int height_lb_AddWord = 30;
    private int x_lb_AddWord = 60;
    private int y_lb_AddWord = 10;

    private JLabel lb_AddMeaning;
    private int width_lb_AddMeaning = 50;
    private int height_lb_AddMeaning = 30;
    private int x_lb_AddMeaning = 230;
    private int y_lb_AddMeaning = 10;

    private JButton btn_Add;
    private JButton btn_Submit;

    private boolean is_WordText = false;
    private boolean is_MeaningText = false;

    private ArrayList<String> word_Added = new ArrayList<>();
}
