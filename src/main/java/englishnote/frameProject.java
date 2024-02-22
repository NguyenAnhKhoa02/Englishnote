package englishnote;

import java.awt.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.plaf.FileChooserUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class frameProject extends JFrame implements KeyListener, ActionListener, WindowListener {
    frameProject() {
        setBounds(x_Frame, y_Frame, width_Frame, height_Frame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        this.setResizable(false);
        // setIconImage for title bar
        image_Icon = Toolkit.getDefaultToolkit().getImage("..\\englishnote\\src\\main\\java\\Icons\\icon_titleBar.png");
        setIconImage(image_Icon);

        list_Word = new listWord();

        panel_Search = new panelSearchWord(width_Frame, height_Frame);
        panel_Search.getTextSearch().addKeyListener(this);
        panel_Search.setBackground(new Color(0, 0, 0, 0));

        int width_PanelSearch = panel_Search.getWidth();
        int height_PanelSearch = panel_Search.getHeight();
        panel_Option = new panelOption(width_Frame, height_Frame, width_PanelSearch, height_PanelSearch);
        panel_Option.setBackground(new Color(0, 0, 0, 0));
        panel_Option.setListWord(list_Word);

        ImageIcon imgIco_changeBkg = new ImageIcon(path_changeBkgIcon);
        btn_changeBkg = new JButton(imgIco_changeBkg);
        btn_changeBkg.setBounds(10, height_Frame - 80, 25, 25);
        btn_changeBkg.setFocusable(false);
        btn_changeBkg.addActionListener(this);
        add(btn_changeBkg);

        layerPane = new JLayeredPane();

        managePathFile = new managePathFile();
        if (managePathFile.getPathImage().size() > 0)
            str_pic = managePathFile.getPathFile().get(0);

        manageFileImage = new manageFileImage();
        if (manageFileImage.getDirFile().size() > 0)
            str_dir = manageFileImage.getDirFile().get(0);

        setImage(str_pic);
        addComponentToFrame();

        addWindowListener(this);
        setVisible(true);
    }

    private void setImage(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage().getScaledInstance(width_Frame, height_Frame, Image.SCALE_SMOOTH);

        lb_WrapPic = new JLabel(new ImageIcon(image));
        lb_WrapPic.setBounds(0, 0, width_Frame, height_Frame);
        layerPane.add(lb_WrapPic, JLayeredPane.DEFAULT_LAYER);
    }

    // add component to frameProject
    public void addComponentToFrame() {
        layerPane.setBounds(0, 0, width_Frame, height_Frame);
        layerPane.setLayout(null);
        layerPane.setOpaque(true);

        layerPane.add(panel_Search, JLayeredPane.POPUP_LAYER);
        layerPane.add(panel_Option, JLayeredPane.POPUP_LAYER);

        add(layerPane);
    }

    private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width_Screen = (int) size.getWidth();
    private final int height_Screen = (int) size.getHeight();
    private final int width_Frame = 500;
    private final int height_Frame = 500;
    private final int x_Frame = width_Screen / 2 - width_Frame / 2;
    private final int y_Frame = height_Screen / 2 - height_Frame / 2;
    private panelSearchWord panel_Search;
    private panelOption panel_Option;
    private listWord list_Word;
    private JLayeredPane layerPane;
    private String str_SearchWord = "";
    private String tempt_Search = "";
    private panelResultSearch panel_ResultSearchWord = new panelResultSearch();
    private Image image_Icon;

    boolean clicked = false;
    private JButton btn_changeBkg;
    private JLabel lb_WrapPic;
    private String str_dir = "";
    private String str_pic = "";
    private JFileChooser fileChooser;

    private managePathFile managePathFile;
    private manageFileImage manageFileImage;

    private String path_changeBkgIcon = "..\\englishnote\\src\\main\\java\\Icons\\setting.png";

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // make string when keypress from a to z
        if (e.getKeyChar() >= 97 && e.getKeyChar() <= 122 || e.getKeyChar() >= 65 &&
                e.getKeyChar() <= 90 || e.getKeyChar() == 32) {
            str_SearchWord += e.getKeyChar();
        }

        // remove a char from string
        if (e.getKeyChar() == 8) {
            tempt_Search = str_SearchWord;
            int i = 0;
            int lenght_Search = tempt_Search.length() - 1;
            str_SearchWord = "";
            while (i < lenght_Search) {
                str_SearchWord += tempt_Search.charAt(i);
                i++;
            }
        }
        // if string unequal "", create panel_ResultsearchWord and show it on frame
        if (!str_SearchWord.equals("")) {
            if (panel_ResultSearchWord != null) {
                panel_ResultSearchWord.setVisible(false);
            }
            panel_ResultSearchWord = new panelResultSearch(panel_Search.getTextSearch().getWidth(),
                    panel_Search.getTextSearch().getX(),
                    panel_Search.getTextSearch().getY() + panel_Search.getTextSearch().getHeight(), list_Word);

            panel_Search.getButtonSearch().setVisible(true);
            panel_ResultSearchWord.setHeightFrameProject(height_Frame);
            layerPane.add(panel_ResultSearchWord, JLayeredPane.DRAG_LAYER);
        }

        // if string equal "", visible the panel
        if (str_SearchWord.equals("")) {
            panel_ResultSearchWord.setVisible(false);
            panel_Search.getButtonSearch().setVisible(false);
        }

        if (panel_ResultSearchWord != null && !str_SearchWord.equals("")) {
            panel_ResultSearchWord.setSearchWord(str_SearchWord);
            panel_ResultSearchWord.showResult();
        }

        // set string to panel_SearchStringSearch() to acess browser search the string
        panel_Search.setNumberResult(panel_ResultSearchWord.getNumberResult());
        panel_Search.setStringSearch(str_SearchWord);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (str_dir.equals("")) {
            fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(this);
        } else {
            fileChooser = new JFileChooser(str_dir);
            fileChooser.showSaveDialog(this);
        }

        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
        str_dir = str_pic = file.getAbsolutePath();
        managePathFile.getPathFile().add(str_pic);
        manageFileImage.getDirFile().add(str_dir);
        layerPane.remove(lb_WrapPic);
        setImage(str_pic);

        repaint();
    }

    // WindowListener
    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        if (!str_pic.equals("")) {
            managePathFile.getPathFile().set(0, str_pic);
            managePathFile.setPathFile();
        }

        if (!str_dir.equals("")) {
            manageFileImage.getDirFile().set(0, str_dir);
            manageFileImage.setToFile();
        }
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
