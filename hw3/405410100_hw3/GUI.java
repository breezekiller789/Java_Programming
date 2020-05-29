//  Reference :
//  https://github.com/mlovic/cs2410_TypingTutor
//  Java程式設計作業三      資工    黃晉威  405410100

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.lang.*;

public class GUI extends JFrame implements KeyListener, ItemListener {
    private JTextArea chapterBox, typeBox;
    private JLabel wrongLabel, errorLabel, accuracyLabel;
    private JRadioButton chapter1, chapter2, chapter3, chapter4, chapter5;
    private JCheckBox checkBox;
    private ButtonGroup buttongroup;
    private java.util.List<JButton> buttons;
    private String qwerty, chapter, typedText;
    private String chapters[];
    private double accuracy;
    private int currentChar, rowBreak1, rowBreak2, errorCnt, promptNum, 
                numKeysTyped;
    //  因為我有Derive JFrame，所以要加入下面這一行，才不會有warning
    private static final long serialVersionUID = 11111111;

    //  Default constructor，把一堆按鈕，按鍵，還有打字空間，鍵盤等等的建立起來
    //  再給他適當的大小跟位置。
    public GUI() {
        this.setLayout(null);
        setVisible(true);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        //  鍵盤上的案件，spec裡面沒有說要full size keyboard，所以就決定用簡單
        //  的就好。
        qwerty = "qwertyuiopasdfghjkl;'zxcvbnm,./ ";
        
        //  五篇文章的內容。
        chapters = new String[] { 
            "#include <stdio.h>\n#define MaxLine 1024\nint main(int argc, char **argv){\nchar line[MaxLine], Copystring[MaxLine], *ptr, *tmp, *cut;\nint i;\nreturn 0;",
            "Trump's power play Thursday revealed one of his many preoccupations a day after the US death toll from the coronavirus pandemic, a disaster unfolding on his watch, passed 100,000.",
            "apple is sweet",
            "It quickly spawned a sequel that became China's highest grossing film at the time when it was released in 2017. 'Wolf Warrior 2' was based around a squad of People's Liberation Army soldiers sent into an African country to rescue Chinese civilians. The film's tagline was, Even though a thousand miles away, anyone who affronts China will pay.",
            // "banana is also good bro...",
            "I love Java Programming!!!"
        };
//
        promptNum = 0;
        chapter = chapters[promptNum];
        typedText = "> ";
        rowBreak1 = 10;     //  這個值是待會要建立鍵盤圖形的時候要用到的index
        rowBreak2 = 21;     //  這也是一樣，只是不一樣的行。
        errorCnt = 0;      //  代表打錯了幾個字，待會要算出打字準確率要用的。
        currentChar = 0;    //  這是待會要打入文章的時候的索引值，是用來代表現在打到哪一個字元。
        numKeysTyped = 0;   //  到現在總共打了幾個字元。
        chapter1 = new JRadioButton("文章 1");
        chapter2 = new JRadioButton("文章 2");
        chapter3 = new JRadioButton("文章 3");
        chapter4 = new JRadioButton("文章 4");
        chapter5 = new JRadioButton("文章 5");
        buttongroup = new ButtonGroup();
        checkBox = new JCheckBox("自動換下一篇文章");
        chapterBox = new JTextArea(chapter);
        typeBox = new JTextArea(typedText);
        buttons = new ArrayList<JButton>();
        wrongLabel = new JLabel("輸入錯誤!");
        errorLabel = new JLabel("錯誤: 0");
        accuracyLabel = new JLabel("Accuracy: ");

        //  設置放文章要用的空間大小，位置。
        chapterBox.setSize(400, 100);
        chapterBox.setLocation(200, 100);
        chapterBox.setFocusable(false);
        chapterBox.setBackground(null);
        chapterBox.setLineWrap(true);
        chapterBox.setWrapStyleWord(true);

        //  設置打字框的空間大小，位置。
        typeBox.setSize(400, 100);
        typeBox.setLocation(200, 200);
        typeBox.setFocusable(false);
        typeBox.setLineWrap(true);
        typeBox.setWrapStyleWord(true);

        //  這個label是當使用者打錯字的時候要跳出來的。
        wrongLabel.setSize(100, 50);
        wrongLabel.setLocation(50, 50);
        wrongLabel.setVisible(false);

        //  顯示目前總共打錯了幾個字元。
        errorLabel.setSize(100, 50);
        errorLabel.setLocation(500, 50);
        errorLabel.setVisible(true);

        //  準確率的label，這是要全部文章打完才會顯示的。
        accuracyLabel.setSize(200, 50);
        accuracyLabel.setLocation(200, 150);
        accuracyLabel.setVisible(false);

        chapter1.setSize(100, 50);
        chapter1.setLocation(50, 100);
        chapter1.addItemListener(this);
        chapter1.setSelected(true);
        chapter1.setFocusable(false);

        chapter2.setSize(100, 50);
        chapter2.setLocation(50, 150);
        chapter2.addItemListener(this);
        chapter2.setFocusable(false);

        chapter3.setSize(100, 50);
        chapter3.setLocation(50, 200);
        chapter3.addItemListener(this);
        chapter3.setFocusable(false);

        chapter4.setSize(100, 50);
        chapter4.setLocation(50, 250);
        chapter4.addItemListener(this);
        chapter4.setFocusable(false);

        chapter5.setSize(100, 50);
        chapter5.setLocation(50, 300);
        chapter5.addItemListener(this);
        chapter5.setFocusable(false);

        checkBox.setSize(400, 50);
        checkBox.setLocation(200, 300);
        checkBox.setFocusable(false);
        checkBox.setSelected(true);

        //  把剛剛的Label都加進去Container裡面。
        Container pane = getContentPane();
        pane.add(checkBox);
        pane.add(chapterBox);
        pane.add(typeBox);
        pane.add(wrongLabel);
        pane.add(errorLabel);
        pane.add(accuracyLabel);
        pane.add(chapter1);
        pane.add(chapter2);
        pane.add(chapter3);
        pane.add(chapter4);
        pane.add(chapter5);
        
        //  加入鍵盤
        addButtons();
        //  加入空白鍵
        addSpacebar();

        //  把文章的五個按鍵加進去ButtonGroup裡面
        buttongroup.add(chapter1);
        buttongroup.add(chapter2);
        buttongroup.add(chapter3);
        buttongroup.add(chapter4);
        buttongroup.add(chapter5);
    }

    //  新增虛擬的鍵盤。
    public void addButtons() {
        for (int i = 0; i < qwerty.length()-1; ++i) {
            buttons.add(new JButton(Character.toString(qwerty.charAt(i))));
            buttons.get(i).setSize(50, 50);
            //  呼叫setPosition來幫我算位置。
            setPosition(i);
            this.getContentPane().add(buttons.get(i));
            buttons.get(i).setFocusable(false);
        }
    }
        
    //  因為空白鍵的大小不一樣，所以要另外做
    public void addSpacebar() {
        JButton spacebar = new JButton();
        buttons.add(spacebar);
        //  佔去比較大的空間，看起來比較像正常的空白鍵。
        spacebar.setSize(250, 50);
        spacebar.setLocation(200, 500);
        //  把空白鍵加進Container裡面。
        this.getContentPane().add(spacebar);
    }

    //  設置鍵盤的位置。
    public void setPosition(int i) {
        //  第一排，qwertyuiop，總共十個按鍵
        if (i <= 9) {
            buttons.get(i).setLocation(150 + 50*i, 350);
        }
        //  第二排，asdfghjkl;' ，總共11個按鍵。
        else if (i >= 10 && i < rowBreak2) {
            buttons.get(i).setLocation(170 + 50*(i-rowBreak1), 400);
        }
        //  第三排，zxcvbnm,./，十個按鍵。
        else if (i >= rowBreak2) {
            buttons.get(i).setLocation(190 + 50*(i-rowBreak2), 450);
        }
        //  正常來講應該不會進去這裡。
        else {
            System.out.println("error\n");
        }
    }

    @Override
    public void keyPressed (KeyEvent e) {
        //  這是拿來debug用的，如果要看的話可以把註解拿掉。
        // System.out.println("You pressed " + e.getKeyChar());
    }


    @Override
    public void keyReleased (KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        numKeysTyped++;
        //  打對按鍵，就把錯誤的label關掉，然後同時要顯示在typebox裡面，最後把
        //  currentChar加一，繼續下一個字元。
        if (e.getKeyChar() == chapter.charAt(currentChar)) {
            wrongLabel.setVisible(false);
            typedText+= e.getKeyChar();
            typeBox.setText(typedText);
            ++currentChar;
        }
        //  打錯就會進這裡，會在左上角提示使用者打錯字，然後errorCnt加一，
        //  順便更新右上角的錯誤字數。
        else if (e.getKeyChar() != chapter.charAt(currentChar)) {
            wrongLabel.setVisible(true);
            ++errorCnt;
            errorLabel.setText("錯誤: " + errorCnt);
        }
        //  使用者把該文章打完，而且有點選自動換下一篇文章的選項的話，就會進
        //  這裏，主要就是檢查還有沒有下一篇文章可以打，有的話就顯示下一篇文章
        //  ，沒有的話就代表使用者打完了，就顯示使用者打字的準確率。
        if (currentChar == chapter.length() && checkBox.isSelected()) {
            ++promptNum;
            if (promptNum < 5) {
                selectRadioButton(promptNum);
            }
            else {
                //  如果三張都打完了，就顯示FinalScreen，主要是顯示打字準確率。
                displayFinalScreen();
            }
        }
    }

    //  如果五篇文章都打完了，這邊就會計算打字的準確率，將所有打錯的字元個數除
    //  上總共的字元個數，就是失誤率，再用100去扣掉，就是準確率了。
    public void displayFinalScreen() {
        //  先把文章視窗關掉。
        chapterBox.setVisible(false);
        //  顯示準確率視窗。
        accuracyLabel.setVisible(true);
        double accuracy = 100 - ((double)errorCnt * 100 / numKeysTyped);
        accuracyLabel.setText("Accuracy: " + new DecimalFormat("#0.00").format(accuracy) + "%");
    }

    //  更改文章，傳入的整數是想要轉到哪篇文章。
    public void changePrompt(int n) {
        promptNum = n;
        chapter = chapters[promptNum];
        //  重設文章的區塊文字。
        chapterBox.setText(chapter);
        //  目前要打入字元的位置也要重設。
        currentChar = 0;
        //  呼叫reset來重置typebox，不然會留下上次打的字，不直觀。
        resetTypeBox();
    }

    public void resetTypeBox() {
        typedText = "> ";
        //  把剛剛有打入的東西都清掉變成 "> "
        typeBox.setText(typedText);
    }

    //  看哪現在是選到哪一篇文章，就把那篇文章的選項改為true，待會下面
    //  itemStateChanged就會被叫，然後改為該篇文章來顯示。
    public void selectRadioButton(int num) {
        switch (num) {
            case 0: chapter1.setSelected(true);
                    break;
            case 1: chapter2.setSelected(true);
                    break;
            case 2: chapter3.setSelected(true);
                    break;
            case 3: chapter4.setSelected(true);
                    break;
            case 4: chapter5.setSelected(true);
                    break;
            default: break;
        }
    }

    //  看是哪一個文章的選項是被選起來的，就改為那一篇文章。
    public void itemStateChanged(ItemEvent e) {
        if (chapter1.isSelected())  {
            changePrompt(0);
        }
        else if (chapter2.isSelected())  {
            changePrompt(1);
        }
        else if (chapter3.isSelected())  {
            changePrompt(2);
        }
        else if (chapter4.isSelected())  {
            changePrompt(3);
        }
        else if (chapter5.isSelected())  {
            changePrompt(4);
        }
    }
}

