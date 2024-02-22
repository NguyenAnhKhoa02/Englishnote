package englishnote;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class panelSearchWord extends JPanel implements ActionListener, KeyListener {

    panelSearchWord(int width_Frame, int height_Frame) {

        setParameter(width_Frame, height_Frame);

        setBounds(x_PanelSearch, y_PanelSearch, width_PanelSearch, height_PanelSearch);
        setLayout(null);

        txt_SearchBox = new JTextField();
        txt_SearchBox.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        txt_SearchBox.setBounds(x_SearchBox, y_SearchBox, width_SearchBox, height_SearchBox);
        txt_SearchBox.addKeyListener(this);

        label_SearchBox = new JLabel("Search");
        label_SearchBox.setBounds(x_label_SearchBox, y_label_SearchBox, width_label_SearchBox, height_label_SearchBox);
        label_SearchBox.setForeground(Color.WHITE);

        button_SearchBox = new JButton("W");
        button_SearchBox.setBounds(x_button_SearchBox, y_button_SearchBox,
                width_button_SearchBox,
                height_button_SearchBox);
        button_SearchBox.setVisible(false);
        button_SearchBox.addActionListener(this);

        add(txt_SearchBox);
        add(label_SearchBox);
        add(button_SearchBox);
    }

    public String getWord() {
        return txt_SearchBox.getText();
    }

    private void setParameter(int widht_Frame, int height_Frame) {
        // parameter PanelSearch
        width_PanelSearch = widht_Frame;
        height_PanelSearch = height_Frame / 5;
        x_PanelSearch = 0;
        y_PanelSearch = 0;

        // parameter txt_SearchBox
        width_SearchBox = 350;
        height_SearchBox = 20;
        x_SearchBox = width_PanelSearch / 2 - width_SearchBox / 2;
        y_SearchBox = height_PanelSearch / 2 - height_SearchBox;

        // parameter label_SearchBox
        final int gap_label_SearchBox = 50;
        width_label_SearchBox = 50;
        height_label_SearchBox = height_SearchBox;
        x_label_SearchBox = x_SearchBox - gap_label_SearchBox;
        y_label_SearchBox = y_SearchBox;

        // parameter button_SearchBox
        final int gap_button_SearchBox = 5;
        width_button_SearchBox = width_SearchBox / 10;
        height_button_SearchBox = height_SearchBox;
        x_button_SearchBox = x_SearchBox + width_SearchBox + gap_button_SearchBox;
        y_button_SearchBox = y_SearchBox;
    }

    public void setStringSearch(String str_Search) {
        this.str_Search = str_Search;
    }

    public void setNumberResult(int number) {
        this.number = number;
    }

    public JButton getButtonSearch() {
        return button_SearchBox;
    }

    public JTextField getTextSearch() {
        return txt_SearchBox;
    }

    public int getWidth() {
        return width_PanelSearch;
    }

    public int getHeight() {
        return height_PanelSearch;
    }

    private String convertTextToStringAccess(String str_Access) {
        String str_Tempt = "";
        for (int number = 0; number < str_Access.length(); number++) {
            if (str_Access.charAt(number) == ' ') {
                str_Tempt += "+";
                continue;
            }

            str_Tempt += str_Access.charAt(number);
        }

        return str_Tempt;
    }

    private void accessBrowser() {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            // specify the protocol along with the URL
            URI oURL = new URI(
                    "https://www.google.com.vn/search?q=" + convertTextToStringAccess(str_Search)
                            + "&hl=vi&sxsrf=APq-WBtawXhbFYcm8zT9xWBbCESAUBbwTw%3A1650197937507&source=hp&ei=sQVcYs3AHLKC2roPlrybyAo&iflsig=AHkkrS4AAAAAYlwTwScuXBg2-wQyvCfxMgr5gxHBb-jU&ved=0ahUKEwiN3fyuipv3AhUygVYBHRbeBqkQ4dUDCAY&uact=5&oq=nhạc+thiền&gs_lcp=Cgdnd3Mtd2l6EAMyCAgAEIAEELEDMggIABCABBCxAzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQguEIAEOgcIIxDqAhAnOgQIIxAnOgsILhCABBCxAxCDAToOCC4QgAQQsQMQxwEQ0QM6CwgAEIAEELEDEIMBOgsILhCABBDHARCjAjoECAAQQzoHCC4Q1AIQQzoMCAAQsQMQgwEQChBDOgcIABCABBAKOgsILhCABBDHARCvAToHCAAQsQMQCjoECC4QCjoECAAQCjoGCAAQChADOgcILhDUAhAKOgcIABCxAxBDOgsILhCABBCxAxDUAjoICC4QgAQQsQM6BQgAEMsBUMcEWP8WYMsXaAlwAHgDgAFkiAG5CpIBBDE1LjGYAQCgAQGwAQo&sclient=gws-wiz");
            try {
                desktop.browse(oURL);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private int width_PanelSearch;
    private int height_PanelSearch;
    private int x_PanelSearch;
    private int y_PanelSearch;

    private JTextField txt_SearchBox;
    private int width_SearchBox;
    private int height_SearchBox;
    private int x_SearchBox;
    private int y_SearchBox;

    private JLabel label_SearchBox;
    private int width_label_SearchBox;
    private int height_label_SearchBox;
    private int x_label_SearchBox;
    private int y_label_SearchBox;

    private JButton button_SearchBox;
    private int width_button_SearchBox;
    private int height_button_SearchBox;
    private int x_button_SearchBox;
    private int y_button_SearchBox;

    private String str_Search = "";
    private int number;

    @Override
    public void actionPerformed(ActionEvent eA) {
        // TODO Auto-generated method stub
        accessBrowser();
    }

    // KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyChar() == 10) {
            if (!str_Search.equals(""))
                accessBrowser();
        }
    }
}
