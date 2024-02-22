package englishnote;

import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class panelOption extends JPanel implements ActionListener {

    panelOption(int width_Frame, int height_Frame, int width_PanelSearch, int height_PanelSearch) {
        setParameter(width_Frame, height_Frame, height_PanelSearch, width_PanelSearch);
        setBounds(x_PanelOption, y_PanelOption, width_PanelOption, height_PanelOption);
        setLayout(null);

        pannel_WrapButton = new JPanel();
        pannel_WrapButton.setBounds(x_WrapButton, y_WrapButton, width_WrapButton, height_WrapButton);
        pannel_WrapButton.setLayout(new GridLayout(2, 2, 10, 10));
        pannel_WrapButton.setBackground(new Color(0, 0, 0, 0));

        btn_AddWord = new JButton("Thêm từ vựng");
        btn_AddWord.setFocusable(false);
        btn_AddWord.addActionListener(this);

        btn_Edit = new JButton("Chỉnh sửa");
        btn_Edit.setFocusable(false);
        btn_Edit.addActionListener(this);

        pannel_WrapButton.add(btn_AddWord);
        pannel_WrapButton.add(btn_Edit);

        add(pannel_WrapButton);
    }

    public void setListWord(listWord ListWord) {
        this.listWord = ListWord;
    }

    private void setParameter(int width_Frame, int height_Frame, int height_PanelSearch, int width_PanelSearch) {
        // parameter panelOption;
        x_PanelOption = 0;
        y_PanelOption = height_PanelSearch;
        width_PanelOption = width_Frame;
        height_PanelOption = height_Frame - height_PanelSearch;

        // parameter wrap_Button
        height_WrapButton = 100;
        width_WrapButton = 300;
        x_WrapButton = width_PanelOption / 2 - width_WrapButton / 2;
        y_WrapButton = height_PanelOption / 2 - height_WrapButton / 2 - 100;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == btn_AddWord) {
            frame_AddWord = new frameAddWord();
            frame_AddWord.setListWord(listWord);
        }

        if (e.getSource() == btn_Edit) {
            frame_Edit = new frameEdit(listWord);
            frame_Edit.setVisible(true);
        }
    }

    private int x_PanelOption;
    private int y_PanelOption;
    private int width_PanelOption;
    private int height_PanelOption;

    private JButton btn_AddWord;
    private JButton btn_Edit;
    private JPanel pannel_WrapButton;
    private int height_WrapButton;
    private int width_WrapButton;
    private int x_WrapButton;
    private int y_WrapButton;

    private frameAddWord frame_AddWord;
    private frameEdit frame_Edit;

    private listWord listWord;
}
