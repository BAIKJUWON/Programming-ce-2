import java.awt.*;
import java.awt.event.*;

public class ExpCombinationCalculator {
    public static void main(String[] args) {
        new CalculatorFrame();
    }
}

class CalculatorFrame extends Frame implements ActionListener {
    Panel p1, p2, p3, p4;
    TextField baseField, exponentField, combinationNField, combinationRField, resultField;
    Button calculateButton;

    CalculatorFrame() {
        setLayout(new GridLayout(5, 1));

        p1 = new Panel();
        p2 = new Panel();
        p3 = new Panel();
        p4 = new Panel();

        baseField = new TextField(5);
        exponentField = new TextField(5);
        combinationNField = new TextField(5);
        combinationRField = new TextField(5);
        resultField = new TextField(15);

        calculateButton = new Button("계산하기");
        calculateButton.addActionListener(this);

        p1.add(new Label("지수승 계산: 밑"));
        p1.add(baseField);
        p1.add(new Label("지수"));
        p1.add(exponentField);

        p2.add(new Label("조합 계산: n"));
        p2.add(combinationNField);
        p2.add(new Label("r"));
        p2.add(combinationRField);

        p3.add(calculateButton);

        p4.add(new Label("결과:"));
        p4.add(resultField);

        add(p1);
        add(p2);
        add(p3);
        add(p4);

        setSize(400, 200);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int base = Integer.parseInt(baseField.getText());
            int exponent = Integer.parseInt(exponentField.getText());

            // 지수승 계산 (반복문 사용)
            int resultExponent = 1;
            for (int i = 0; i < exponent; i++) {
                resultExponent *= base;
            }

            int n = Integer.parseInt(combinationNField.getText());
            int r = Integer.parseInt(combinationRField.getText());

            // 팩토리얼 계산 (반복문 사용, 조합 계산용)
            int factorialN = 1;
            for (int i = 1; i <= n; i++) {
                factorialN *= i;
            }

            int factorialR = 1;
            for (int i = 1; i <= r; i++) {
                factorialR *= i;
            }

            int factorialNMinusR = 1;
            for (int i = 1; i <= (n - r); i++) {
                factorialNMinusR *= i;
            }

            // 조합 계산: nCr = n! / (r! * (n - r)!)
            int resultCombination = factorialN / (factorialR * factorialNMinusR);

            resultField.setText("지수승: " + resultExponent + " , 조합: " + resultCombination);
        } catch (NumberFormatException ex) {
            resultField.setText("유효한 숫자를 입력하세요.");
        } catch (ArithmeticException ex) {
            resultField.setText("계산 중 오류가 발생했습니다.");
        }
    }
}
