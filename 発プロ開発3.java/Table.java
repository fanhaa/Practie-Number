import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.util.Random;

public class Table {
    JLabel timerLabel;
    Timer timer;
    long startTime;
    JTable table1;

    //左に表示されるランダムなデータを右で入力する
    //タイムアタック制

    public void perform() {
        //ウィンドウを生成
        JFrame frame = new JFrame("Practice Number");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);
        //tanbleの生成
        table1 = new JTable(5, 5);
        JTable table2 = new JTable(5, 5);
        //tableの大きさ変更した時のためJScrollPaneを使う
        JScrollPane Pane1 = new JScrollPane(table1);
        JScrollPane Pane2 = new JScrollPane(table2);
        //tablePanellの生成 tablePanelを1：2
        JPanel tablePanel = new JPanel(new GridLayout(1,2));
        tablePanel.add(Pane1);
        tablePanel.add(Pane2);


        //frameにtablePanelを入れる 場所はセンターに指定
        frame.add(tablePanel, BorderLayout.CENTER);


        JPanel timerPanel = new JPanel(new BorderLayout());

        timerLabel = new JLabel("00:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        timerPanel.add(timerLabel, BorderLayout.CENTER);
        //stopwatchPanlを追加 場所は上に来てほしいのでNORTH
        frame.add(timerPanel, BorderLayout.NORTH);
        //ボタンPanel生成
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //スタートボタンの生成
        JButton startButton = new JButton("Start");
        Font stfont = new Font("Comic Sans MS", Font.BOLD, 12);
        startButton.setFont(stfont);
        startButton.addActionListener(e -> startTimer());
        //ボタンPanelにスタートボタンを追加
        buttonPanel.add(startButton);
        //ストップボタンの生成
        JButton stopButton = new JButton("Stop");
        Font stofont = new Font("Comic Sans MS", Font.BOLD, 12); 
        stopButton.setFont(stofont);
        stopButton.addActionListener(e -> stopTimer());
        //ボタンPanelにストップボタンを追加
        buttonPanel.add(stopButton);
        //リセットボタンの生成
        JButton resetButton = new JButton("Reset");
        Font refont = new Font("Comic Sans MS", Font.BOLD, 12); 
        resetButton.setFont(refont);
        resetButton.addActionListener(e -> resetTimer());
        //ボタンPanelにリセットボタンを追加
        buttonPanel.add(resetButton);
        //frameにボタンPanelを追加
        frame.add(buttonPanel, BorderLayout.SOUTH);
        //RandomNumberメソッドで生成した数値をtable1にセットする
        RandomNumber((DefaultTableModel) table1.getModel());
        //JTableのセルの高さを変更
        table1.setRowHeight(45);
        table2.setRowHeight(45);

        //frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
    //1000までのランダムな数値を生成するメソッド
    public void RandomNumber(DefaultTableModel model) {
        Random random = new Random();
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int column = 0; column < model.getColumnCount(); column++) {
                int randomNumber = random.nextInt(1000);
                model.setValueAt(randomNumber, row, column);
            }
        }
    }
    //startボタンをクリックした時の操作
    public void startTimer() {
        startTime = System.currentTimeMillis();
        timer = new Timer(1000, e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            updateTimerLabel(elapsedTime);
        });
        timer.start();
    }
    //stopボタンをクリックした時の操作
    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }
    //resetボタンをクリックした時の操作
    public void resetTimer() {
        stopTimer();
        timerLabel.setText("00:00");
    }
    //timerlabelの更新
    public void updateTimerLabel(long elapsedTime) {
        long seconds = elapsedTime / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        String time = String.format("%02d:%02d", minutes, seconds);
        timerLabel.setText(time);
    }
}