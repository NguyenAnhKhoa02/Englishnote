package englishnote;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

public class frameEdit extends frameOptionCommon implements KeyListener, MouseListener, ActionListener {
    frameEdit(listWord listWord) {
        new frameOptionCommon();
        super.setBounds(x_FrameOptionCommon, 0, 500, width_Screen);
        if (this.listWord == null) {
            this.listWord = listWord;
        }

        // create new arrlist from listWord
        list = listWord.getArrList();
        list_Word = listWord.getArrayListWord();
        list_Meaning = listWord.getArrayListMeaning();

        setTitle("Edit");

        // create wrap searchBox and btn "..."
        pane_WrapTextSearch = new JPanel();
        pane_WrapTextSearch.setBounds(0, 0, 500, 100);
        pane_WrapTextSearch.setLayout(null);

        // create txt_Search box
        txt_Search = new JTextField();
        txt_Search.setBounds(pane_WrapTextSearch.getWidth() / 2 - width_Search / 2, 30, width_Search, 25);
        txt_Search.addKeyListener(this);

        // create label search
        lb_Search = new JLabel("Search: ");
        lb_Search.setBounds(txt_Search.getX() - height_lb_Search, txt_Search.getY() / 2, width_lb_Search,
                height_lb_Search);

        // add txt_Search and lb_Search to pane_WrapTextSearch
        pane_WrapTextSearch.add(lb_Search);
        pane_WrapTextSearch.add(txt_Search);

        // add pane_WrapTextSearch to frameEdit
        add(pane_WrapTextSearch);

        // create pane_WrapEdit cover table
        pane_WrapEdit = new JPanel();
        pane_WrapEdit.setLayout(null);
        pane_WrapEdit.setBounds(0, 100, 500, height_Screen);

        addDataFromArrayListToArray();
        makeTable();
        formEditData();

        add(pane_WrapEdit);
        setVisible(true);
    }

    private void searchData(String str) {
        result_SearchWord = new ArrayList<>();
        result_SearchMeaning = new ArrayList<>();

        if (str == "") {
            result_SearchMeaning = null;
            result_SearchWord = null;
            return;
        }

        for (int i = 0; i < listWord.getArrList().size(); i++) {
            if (listWord.getArrList().get(i).toLowerCase().startsWith(str.toLowerCase())) {
                result_SearchWord.add(list_Word.get(i));
                result_SearchMeaning.add(list_Meaning.get(i));
            }
        }

    }

    // add list_Word and list_Meaning to data[][]
    private void addDataFromArrayListToArray() {

        int lenght = 0;

        // if the result equal "" create new
        if (result_SearchWord == null && result_SearchMeaning == null) {
            data = new String[list.size()][2];
            lenght = list.size();
        } else {
            data = new String[result_SearchWord.size()][2];
            lenght = result_SearchWord.size();
        }

        // add list_Word and list_Meaning to data[][]
        for (int i = 0; i < lenght; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    if (result_SearchWord == null) {
                        data[i][j] = list_Word.get(i);
                    } else {
                        data[i][j] = result_SearchWord.get(i);
                    }
                } else {
                    if (result_SearchWord == null) {
                        data[i][j] = list_Meaning.get(i);
                    } else {
                        data[i][j] = result_SearchMeaning.get(i);
                    }

                }
            }
        }
    }

    // make table with data[][]
    private void makeTable() {
        String[] columnNames = { "Word", "Meaning" };

        table = new JTable(data, columnNames);
        table.setBounds(0, 0, 500, 470);
        table.setRowHeight(20);
        table.addMouseListener(this);

        scroll = new JScrollPane(table);
        scroll.setBounds(0, 110, 500, 470);

        pane_WrapEdit.add(scroll, BorderLayout.NORTH);
    }

    // make fromEditData
    private void formEditData() {

        // create txt_EditWord
        txt_EditWord = new JTextField();
        txt_EditWord.setBounds(30, 25, 200, 25);

        // create lb_Word with text "Word"
        lb_Word = new JLabel("Word");
        lb_Word.setBounds(100, 0, 50, 25);
        txt_EditWord.setEditable(false);
        txt_EditWord.addKeyListener(this);

        // create txt_EditMeaning
        txt_EditMeaning = new JTextField();
        txt_EditMeaning.setBounds(260, 25, 200, 25);

        // create lb_Meaning with text "Meaning"
        lb_Meaning = new JLabel("Meaning");
        lb_Meaning.setBounds(335, 0, 50, 25);
        txt_EditMeaning.setEditable(false);
        txt_EditMeaning.addKeyListener(this);

        // create btn_edit to edit the new to data
        btn_Edit = new JButton("Sửa");
        btn_Edit.setBounds(150, 70, 80, 25);
        btn_Edit.setEnabled(false);
        btn_Edit.setFocusable(false);
        btn_Edit.addActionListener(this);

        // create btn_Clear to remove data from table
        btn_Clear = new JButton("Xóa");
        btn_Clear.setBounds(260, 70, 80, 25);
        btn_Clear.setEnabled(false);
        btn_Clear.setFocusable(false);
        btn_Clear.addActionListener(this);

        pane_WrapEdit.add(txt_EditMeaning);
        pane_WrapEdit.add(txt_EditWord);

        pane_WrapEdit.add(lb_Word);
        pane_WrapEdit.add(lb_Meaning);

        pane_WrapEdit.add(btn_Edit);
        pane_WrapEdit.add(btn_Clear);
    }

    // upload new data when edited
    private void uploadData(int index) {
        String str_upload = txt_EditWord.getText() + "@" + txt_EditMeaning.getText();
        list.set(index, str_upload);
        System.out.println(list.get(index));
    }

    // get real pos compare with list_Word
    private int realPosition(String str_TableWord) {
        for (int pos = 0; pos < list_Word.size(); pos++) {
            if (str_TableWord.equals(list_Word.get(pos)))
                return pos;
        }

        return 0;
    }

    private listWord listWord;
    private JPanel pane_WrapEdit;
    private JTable table;
    private JScrollPane scroll;

    private JPanel pane_WrapTextSearch;
    private JTextField txt_Search;
    private final int width_Search = 200;

    private JLabel lb_Search;
    private final int width_lb_Search = 100;
    private final int height_lb_Search = 50;

    private ArrayList<String> result_SearchWord;
    private ArrayList<String> result_SearchMeaning;
    private ArrayList<String> list_Word = new ArrayList<>();
    private ArrayList<String> list_Meaning = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();

    private String[][] data;

    private String str_Search = "";
    private String tempt_Str = "";

    private JTextField txt_EditWord;
    private JTextField txt_EditMeaning;

    private JLabel lb_Word;
    private JLabel lb_Meaning;

    private JButton btn_Edit;
    private JButton btn_Clear;
    private String str_EditWord = "";
    private String str_EditMeaning = "";
    private boolean save_file = false;

    // KeyListener*********************************************************************************
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == txt_Search) {
            if (e.getKeyChar() >= 97 && e.getKeyChar() <= 122 || e.getKeyChar() >= 65 &&
                    e.getKeyChar() <= 90 || e.getKeyChar() == 32) {
                str_Search += e.getKeyChar();
            }

            if (e.getKeyChar() == 8) {
                tempt_Str = str_Search;
                int i = 0;
                int lenght_Search = tempt_Str.length() - 1;
                str_Search = "";
                while (i < lenght_Search) {
                    str_Search += tempt_Str.charAt(i);
                    i++;
                }
            }

            // if str_Search unequal "", filter from table to search for result
            if (!str_Search.equals("")) {
                pane_WrapEdit.remove(scroll);
                searchData(str_Search);
                addDataFromArrayListToArray();

                makeTable();
                pane_WrapEdit.repaint();
            }

            // if str_Seach equal "", show all data from list
            if (str_Search.equals("")) {
                pane_WrapEdit.remove(scroll);
                searchData("");
                addDataFromArrayListToArray();

                makeTable();
                pane_WrapEdit.repaint();
            }
        }

        if (e.getSource() == txt_EditWord) {
            if (e.getKeyChar() >= 97 && e.getKeyChar() <= 122 || e.getKeyChar() >= 65 &&
                    e.getKeyChar() <= 90 || e.getKeyChar() == 32) {
                str_EditWord += e.getKeyChar();
            }

            if (e.getKeyChar() == 8) {
                tempt_Str = str_EditWord;
                int i = 0;
                int lenght_EditWord = tempt_Str.length() - 1;
                str_EditWord = "";
                while (i < lenght_EditWord) {
                    str_EditWord += tempt_Str.charAt(i);
                    i++;
                }
            }
        }

        if (e.getSource() == txt_EditMeaning) {
            if (e.getKeyChar() >= 97 && e.getKeyChar() <= 122 || e.getKeyChar() >= 65 &&
                    e.getKeyChar() <= 90 || e.getKeyChar() == 32) {
                str_EditMeaning += e.getKeyChar();
            }

            if (e.getKeyChar() == 8) {
                tempt_Str = str_EditMeaning;
                int i = 0;
                int lenght_EditMeaning = tempt_Str.length() - 1;
                str_EditMeaning = "";
                while (i < lenght_EditMeaning) {
                    str_EditMeaning += tempt_Str.charAt(i);
                    i++;
                }
            }
        }

        // enable button if row is selected
        if (table.getSelectedRow() != -1)
            if (!str_EditWord.equals(table.getValueAt(table.getSelectedRow(), 0))
                    || !str_EditMeaning.equals(table.getValueAt(table.getSelectedRow(), 1))) {
                btn_Edit.setEnabled(true);
            } else {
                btn_Edit.setEnabled(false);
            }

        if (str_EditWord.equals("")) {
            btn_Edit.setEnabled(false);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    // MouseListener******************************************************************************************
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // add text to txt_EditWord and txt_EditMeaning and enable all button when a row
        // is selected
        if (table.getSelectedRow() > -1) {
            txt_EditWord.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
            txt_EditMeaning.setText(table.getValueAt(table.getSelectedRow(), 1).toString());

            str_EditWord = txt_EditWord.getText();
            str_EditMeaning = txt_EditMeaning.getText();

            btn_Clear.setEnabled(true);
            txt_EditWord.setEditable(true);
            txt_EditMeaning.setEditable(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    // ActionListener
    // ***************************************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {

        int real_Pos = realPosition(table.getValueAt(table.getSelectedRow(), 0).toString());

        // remove the row was selected from list
        if (e.getSource() == btn_Clear) {
            save_file = true;

            list.remove(real_Pos);

            txt_EditWord.setText("");
            txt_EditWord.setEditable(false);

            txt_EditMeaning.setText("");
            txt_EditMeaning.setEditable(false);

            btn_Clear.setEnabled(false);

            String[] option = { "OK" };

            ImageIcon image = new ImageIcon("..\\englishnote\\src\\main\\java\\Icons\\nezuko strike.png");

            JOptionPane.showOptionDialog(this, "Data has be clear", "", 0,
                    JOptionPane.INFORMATION_MESSAGE, image, option, 0);

            pane_WrapEdit.remove(scroll);
            searchData(str_Search);
            addDataFromArrayListToArray();
            makeTable();

            pane_WrapEdit.repaint();
        }

        // edit the new data and replace the data to list
        if (e.getSource() == btn_Edit) {
            save_file = true;
            list_Word.set(real_Pos, txt_EditWord.getText());
            list_Meaning.set(real_Pos, txt_EditMeaning.getText());
            uploadData(real_Pos);

            txt_EditWord.setText("");
            txt_EditMeaning.setText("");

            txt_EditWord.setEditable(false);
            txt_EditMeaning.setEditable(false);

            btn_Edit.setEnabled(false);

            pane_WrapEdit.remove(scroll);
            searchData(str_Search);
            addDataFromArrayListToArray();
            makeTable();

            pane_WrapEdit.repaint();
        }
    }

    // WindowListener***************************************************************************
    // write data when closing window
    @Override
    public void windowClosing(WindowEvent e) {
        if (save_file) {
            listWord.setArrList();
        }
    }

}
