/*
 * 프로그램 설명
 * 이 Java 프로그램은 AWT(추상 창 도구 키트)를 사용해서 GUI(그래픽 사용자 인터페이스)를 제공하는 계산기입니다
 * 사용자에게 두 개의 난수를 제공하고 사칙연산(덧셈, 뺄셈, 곱셈, 나눗셈) 지수승 조합을 계산하는 기능을 제공합니다
 * 사칙연산 결과는 자동으로 생성된 두 개의 난수에 대해 계산되며 지수승과 조합 계산은 버튼 클릭을 통해 수행됩니다
 *
 * 학습 내용
 * 1. **AWT 기본 구성**
 *   - `Frame` 클래스는 윈도우 창을 생성하고 구성하는데 사용되며 프로그램 창을 관리합니다
 *   - `Panel` 클래스는 여러 GUI 구성 요소를 그룹화해서 레이아웃을 구성합니다
 *   - `TextField`, `Button`, `Label`은 사용자 입력을 받거나 정보를 표시하는 데 사용되는 기본 AWT 구성 요소입니다
 *
 * 2. **레이아웃 관리**
 *   - `GridLayout`을 사용해서 GUI의 전체 레이아웃을 7행 1열로 설정했습니다
 *   - 각 계산 결과 및 텍스트 필드를 `Panel`을 통해 그룹화하고 패널을 `Frame`에 추가해서 정렬했습니다
 *
 * 3. **이벤트 처리**
 *   - `ActionListener`를 사용해서 버튼 클릭 시 이벤트를 처리합니다 버튼을 누를 때마다 난수를 새로 생성하거나 지수승과 조합을 계산해서 출력합니다
 *   - `WindowAdapter`를 사용해서 창을 닫을 때 프로그램이 정상적으로 종료되도록 처리합니다
 *
 * 4. **난수 생성**
 *   - `Random` 클래스를 사용해서 1과 100 사이의 난수를 생성하고 이를 이용해 계산을 수행합니다
 *
 * 5. **사칙연산**
 *   - 두 개의 난수를 가지고 덧셈 뺄셈 곱셈 나눗셈을 수행하며 나눗셈 시 0으로 나누는 경우를 방지합니다
 *
 * 6. **지수승 계산**
 *   - `Math.pow()` 메서드를 사용해서 첫 번째 난수(a)를 밑으로 두 번째 난수(b)를 지수로 하여 a^b를 계산합니다
 *
 * 7. **조합 계산**
 *   - `combination()` 메서드를 사용해서 조합 C(a, b)를 계산합니다 팩토리얼 연산을 루프로 처리하여 큰 수의 조합 계산에서도 정확성을 유지합니다
 *   - 팩토리얼 계산은 조합 공식을 이용해 분자와 분모를 각각 계산한 후 나누는 방식으로 구현했습니다
 *
 * 8. **자료형**
 *   - `long` 자료형을 사용해서 큰 숫자를 처리할 때 오버플로우를 방지합니다 특히 지수승이나 조합 계산은 큰 수가 발생할 수 있기 때문에 `long`으로 처리했습니다
 *
 * 9. **프로그램 종료**
 *   - 창을 닫으면 `WindowAdapter`의 `windowClosing()` 메서드를 통해 프로그램이 정상적으로 종료됩니다
 *
 * 이 프로그램을 통해 AWT를 사용한 GUI 구성, 이벤트 처리, 연산 기능을 통합한 간단한 계산기를 구현하는 방법을 학습할 수 있습니다
 */


import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Cal41 인스턴스를 생성하여 프로그램 시작
        new Cal41();
    }
}

class Cal41 extends Frame {
    int a, b;  // 난수로 사용할 변수 a, b
    Panel p1, p2, p3, p4, p5, p6, p7;  // 각 패널을 저장할 변수
    Button b1, b2;  // 버튼 변수
    TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;  // 입력 및 출력 텍스트 필드
    Random r = new Random();  // 난수 생성 객체

    // Cal41 생성자
    Cal41() {
        // GridLayout을 사용하여 레이아웃을 7행 1열로 설정
        setLayout(new GridLayout(7, 1));

        // 패널 초기화
        p1 = new Panel();
        p2 = new Panel();
        p3 = new Panel();
        p4 = new Panel();
        p5 = new Panel();
        p6 = new Panel();
        p7 = new Panel();

        // 텍스트 필드 초기화
        tf1 = new TextField(5);
        tf2 = new TextField(5);
        tf3 = new TextField(5);
        tf4 = new TextField(5);
        tf5 = new TextField(5);
        tf6 = new TextField(5);
        tf7 = new TextField(10);  // 지수승 결과 출력용
        tf8 = new TextField(10);  // 조합 결과 출력용
        b1 = new Button("확인");  // 사칙연산 버튼
        b2 = new Button("지수승 & 조합 계산");  // 지수승 및 조합 계산 버튼

        // 1과 100 사이의 난수를 생성하여 a와 b에 할당
        a = r.nextInt(100) + 1;
        b = r.nextInt(100) + 1;

        // 사칙연산 결과를 텍스트 필드에 설정
        tf1.setText("" + a);  // 첫 번째 난수
        tf2.setText("" + b);  // 두 번째 난수
        tf3.setText("" + (a + b));  // 덧셈 결과
        tf4.setText("" + (a - b));  // 뺄셈 결과
        tf5.setText("" + (a * b));  // 곱셈 결과
        tf6.setText("" + ((b != 0) ? a / b : "0"));  // 나눗셈 결과 (0으로 나누기 방지)

        // 창 크기 설정 및 보이도록 설정
        setSize(400, 300);
        setVisible(true);

        // 각 패널에 구성 요소 추가
        p1.add(new Label("사칙연산"));  // 첫 번째 패널: 설명 라벨
        p2.add(tf1);  // 두 번째 패널: 첫 번째 난수
        p2.add(new Label("연산"));  // 라벨
        p2.add(tf2);  // 두 번째 난수
        p3.add(b1);  // 세 번째 패널: 사칙연산 버튼
        p4.add(new Label("연산 : + - * /"));  // 네 번째 패널: 설명 라벨
        p5.add(tf3);  // 다섯 번째 패널: 덧셈 결과
        p5.add(tf4);  // 뺄셈 결과
        p5.add(tf5);  // 곱셈 결과
        p5.add(tf6);  // 나눗셈 결과

        // 지수승과 조합 계산을 위한 패널 추가
        p6.add(new Label("지수승 (a^b):"));  // 지수승 설명 라벨
        p6.add(tf7);  // 지수승 결과

        p7.add(new Label("조합 (C(a, b)):"));  // 조합 설명 라벨
        p7.add(tf8);  // 조합 결과
        p7.add(b2);  // 지수승 및 조합 계산 버튼

        // 프레임에 패널 추가
        add(p1); add(p2); add(p3); add(p4); add(p5); add(p6); add(p7);

        // '확인' 버튼 클릭 시 사칙연산 결과 업데이트
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 새로운 난수 생성
                a = r.nextInt(100) + 1;
                b = r.nextInt(100) + 1;

                // 각 사칙연산 결과를 텍스트 필드에 업데이트
                tf1.setText("" + a);
                tf2.setText("" + b);
                tf3.setText("" + (a + b));
                tf4.setText("" + (a - b));
                tf5.setText("" + (a * b));
                tf6.setText("" + ((b != 0) ? a / b : "0"));  // 0으로 나누기 방지
            }
        });

        // '지수승 & 조합 계산' 버튼 클릭 시 지수승 및 조합 계산 수행
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 지수승 계산 결과를 텍스트 필드에 설정
                tf7.setText("" + (long)Math.pow(a, b));

                // 조합 계산 결과를 텍스트 필드에 설정
                if (b <= a) {
                    tf8.setText("" + combination(a, b));
                } else {
                    tf8.setText("계산 불가 (b > a)");  // b가 a보다 클 때 조합 계산 불가
                }
            }
        });

        // 창 닫기 이벤트 처리
        addWindowListener(new WindowHandler());
    }

    // 조합 계산 함수
    long combination(int n, int r) {
        if (r == 0 || r == n) {
            return 1;  // 조합 계산에서 r이 0이거나 n과 같으면 결과는 1
        }

        long numerator = 1;  // 분자
        long denominator = 1;  // 분모

        // C(n, r) = n! / (r! * (n-r)!)
        for (int i = 0; i < r; i++) {
            numerator *= (n - i);  // 분자는 n * (n-1) * ... * (n-r+1)
            denominator *= (i + 1);  // 분모는 r!
        }

        return numerator / denominator;  // 최종 조합 값 반환
    }
}

// 창 닫기 이벤트 핸들러 클래스
class WindowHandler extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        Window w = e.getWindow();
        w.setVisible(false);  // 창을 보이지 않게 함
        w.dispose();  // 창 자원을 해제
        System.exit(0);  // 프로그램 종료
    }
}
