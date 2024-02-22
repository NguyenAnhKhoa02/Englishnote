package englishnote;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class panelResultSearch extends JPanel implements MouseListener {
    panelResultSearch(int width_PanelResult, int x_PanelResult, int y_PanelResult, listWord list) {

        this.x_PanelResult = x_PanelResult;
        this.y_PanelResult = y_PanelResult;
        this.width_PanelResult = width_PanelResult;
        this.list = list;
        setBounds(x_PanelResult, y_PanelResult, width_PanelResult, 0);
        setLayout(null);
        setBackground(Color.CYAN);
        setWordFromList();
        setVisible(true);
    }

    panelResultSearch() {
    }

    public void setHeightFrameProject(int height_FrameProject) {
        this.height_FrameProject = height_FrameProject;
    }

    private void setWordFromList() {
        word_list = list.getArrayListWord();
        meaning_list = list.getArrayListMeaning();
    }

    public ArrayList<JLabel> getLabelResule() {
        return labelResult;
    }

    public void showResult() {
        int i = 0;
        int showed = 0;
        while (i < word_list.size()) {
            if (height_PaneResult >= height_FrameProject - 200) {
                break;
            }

            if (word_list.get(i).toLowerCase().startsWith(search_word.toLowerCase())) {
                btn_Result = new JLabel(word_list.get(i), JLabel.CENTER);
                btn_Result.setLayout(null);
                btn_Result.setOpaque(true);
                btn_Result.setBackground(Color.WHITE);
                btn_Result.setFocusable(false);
                btn_Result.setBorder(
                        BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
                                BorderFactory.createEmptyBorder(0, 0, 1, 0)));

                height_PaneResult += 50;

                if (showed > 0) {
                    y_btn_Result += 50;
                    btn_Result.setBounds(0, y_btn_Result, width_PanelResult, 50);
                    setBounds(x_PanelResult, y_PanelResult, width_PanelResult, height_PaneResult);
                } else {
                    btn_Result.setBounds(0, y_btn_Result, width_PanelResult, 50);
                    setBounds(x_PanelResult, y_PanelResult, width_PanelResult, height_PaneResult);
                    showed++;
                }

                labelResult.add(btn_Result);
                wordShowed.add(word_list.get(i));
                meaningShowed.add(meaning_list.get(i));

                result_Find++;
            }
            i++;
        }

        i = 0;
        while (i < labelResult.size()) {
            labelResult.get(i).addMouseListener(this);
            add(labelResult.get(i));
            i++;
        }
    }

    public int getNumberResult() {
        return result_Find;
    }

    public int height_Current() {
        return height_PaneResult;
    }

    public void setSearchWord(String searchWord) {
        search_word = searchWord;
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

        System.out.println(str_Tempt);

        return str_Tempt;
    }

    private String addStringToHTML(String str) {
        String str_ = "";
        int lenght = str.length();
        int line_break = 50;
        for (int i_add = 0; i_add < lenght; i_add++) {
            if (i_add == lenght) {
                str_ += "</html>";
                break;
            }

            if (i_add == 0) {
                str_ += "<html>" + str.charAt(i_add);
                continue;
            }

            if (i_add >= line_break && str.charAt(i_add) == ' ') {
                str_ += "<br/>";
                line_break += 50;
                continue;
            }

            str_ += str.charAt(i_add);
        }

        return str_;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent eM) {
        // TODO Auto-generated method stub
        for (int i = 0; i < labelResult.size(); i++) {
            if (eM.getSource() == labelResult.get(i)) {
                Desktop desktop = java.awt.Desktop.getDesktop();
                try {
                    // specify the protocol along with the URL
                    URI oURL = new URI(
                            "https://www.google.com.vn/search?q="
                                    + convertTextToStringAccess(labelResult.get(i).getText())
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
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        int i = 0;
        int index_Enter = 0;
        int y_current = 0;

        while (i < labelResult.size()) {
            if (e.getSource() == labelResult.get(i)) {
                index_Enter = labelResult.get(i).getY();
            }
            i++;
        }

        i = 0;
        while (i < labelResult.size()) {
            y_current = labelResult.get(i).getY();

            if (y_current == index_Enter) {

                labelResult.get(i).setBackground(new ColorUIResource(r, g, b));

                labelResult.get(i).setBounds(labelResult.get(i).getX(),
                        labelResult.get(i).getY(), width_PanelResult,
                        labelResult.get(i).getHeight() + 100);

                labelResult.get(i).setText(wordShowed.get(i));
                labelResult.get(i).setVerticalAlignment(JLabel.TOP);

                str_show = addStringToHTML(meaningShowed.get(i));
                newLabel.setText(str_show);
                newLabel.setHorizontalAlignment(JLabel.CENTER);
                newLabel.setBounds(0, 50, width_PanelResult, 100);
                labelResult.get(i).add(newLabel);
                height_PaneResult += 100;
                setBounds(x_PanelResult, y_PanelResult, width_PanelResult,
                        height_PaneResult);

                index_Enter = labelResult.get(i).getY();
            } else if (y_current > index_Enter) {
                labelResult.get(i).setBounds(labelResult.get(i).getX(),
                        labelResult.get(i).getY() + 100,
                        width_PanelResult,
                        labelResult.get(i).getHeight());
            } else {
                ;
            }

            i++;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        int i = 0;
        int index_Enter = 0;
        int y_current = 0;

        while (i < labelResult.size()) {
            if (e.getSource() == labelResult.get(i)) {
                index_Enter = labelResult.get(i).getY();
            }
            i++;
        }

        i = 0;
        while (i < labelResult.size()) {
            y_current = labelResult.get(i).getY();

            if (y_current == index_Enter) {
                labelResult.get(i).setBackground(Color.WHITE);

                labelResult.get(i).setBounds(labelResult.get(i).getX(),
                        labelResult.get(i).getY(), width_PanelResult,
                        labelResult.get(i).getHeight() - 100);

                labelResult.get(i).setText(wordShowed.get(i));
                labelResult.get(i).setVerticalAlignment(JLabel.CENTER);

                newLabel.removeAll();

                height_PaneResult -= 100;
                setBounds(x_PanelResult, y_PanelResult, width_PanelResult,
                        height_PaneResult);

                index_Enter = labelResult.get(i).getY();
            } else if (y_current > index_Enter) {
                labelResult.get(i).setBounds(labelResult.get(i).getX(),
                        labelResult.get(i).getY() - 100,
                        width_PanelResult,
                        labelResult.get(i).getHeight());
            } else {
                ;
            }

            i++;
        }
    }

    private listWord list;
    private ArrayList<String> word_list = new ArrayList<>();
    private ArrayList<String> meaning_list = new ArrayList<>();
    private ArrayList<JLabel> labelResult = new ArrayList<>();
    private ArrayList<String> wordShowed = new ArrayList<>();
    private ArrayList<String> meaningShowed = new ArrayList<>();
    private String search_word;
    private JLabel btn_Result;
    private int y_btn_Result = 0;
    private int width_PanelResult;
    private int height_PaneResult;
    private int x_PanelResult;
    private int y_PanelResult;
    private int result_Find = 0;

    private int r = 204;
    private int g = 255;
    private int b = 209;

    private int height_FrameProject;
    private String str_show = "";
    private JLabel newLabel = new JLabel();
}
