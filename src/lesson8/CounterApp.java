package lesson8;

import javax.swing.*;
import javax.swing.text.TextAction;
import java.awt.*;
import java.awt.event.*;

public class CounterApp extends JFrame {
    private int initialValue = 0;
    private int step = 1;

    public CounterApp() {
        setBounds(500, 500, 500, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Counter");
        setAlwaysOnTop(true);
        setVisible(true);
        Font font = new Font("Arial", Font.BOLD, 32);

        JPanel stepPanel = new JPanel();
        add(stepPanel, BorderLayout.NORTH);
        stepPanel.setLayout(new BorderLayout());

        createStepControls(stepPanel);

        JLabel counterLabel = new JLabel(String.valueOf(initialValue),SwingConstants.CENTER);
        counterLabel.setFont(font);
        add(counterLabel, BorderLayout.CENTER);

        JButton incCounterBtn = new JButton(">");
        initIncButton(font, counterLabel, incCounterBtn);

        JButton decCounterBtn = new JButton("<");
        intiDecButton(font, counterLabel, decCounterBtn);

        JButton resetCounterBtn = new JButton("Сбросить");
        stepPanel.add(resetCounterBtn, BorderLayout.SOUTH);
        resetCounterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialValue = 0;
                counterLabel.setText("0");
            }
        });
    }

    private void createStepControls(JPanel stepPanel) {
        JTextField stepValText = new JTextField();
        initStepEdit(stepPanel, stepValText);

        JLabel stepLabel = new JLabel("Шаг счетчика:");
        stepLabel.setLabelFor(stepValText);
        stepPanel.add(stepLabel, BorderLayout.WEST);
    }

    private void initStepEdit(JPanel stepPanel, JTextField stepValText) {
        stepValText.setToolTipText("Введите значение шага счетчика (целое число)");
        stepValText.setText(String.valueOf(step));

        stepValText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    step = Integer.parseInt(stepValText.getText());
                }
                catch (NumberFormatException numberFormatException)
                {
                    stepValText.setText(String.valueOf(step));
                }
            }
        });
         stepPanel.add(stepValText,BorderLayout.CENTER);
    }

    private void intiDecButton(Font font, JLabel label, JButton decCounterBtn) {
        decCounterBtn.setFont(font);
        add(decCounterBtn, BorderLayout.WEST);
        decCounterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(String.valueOf(initialValue -= step));
            }
        });
    }

    private void initIncButton(Font font, JLabel label, JButton incCounterBtn) {
        incCounterBtn.setFont(font);
        add(incCounterBtn, BorderLayout.EAST);
        incCounterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(String.valueOf(initialValue+=step));
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CounterApp();
            }
        });
    }
}
