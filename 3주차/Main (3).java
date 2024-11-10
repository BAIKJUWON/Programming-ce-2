import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Stdv();
    }
}

class Stdv extends Frame implements ActionListener, KeyListener {
    Stand s;
    Panel p1, p2, p3, p4, p5;
    Button b1, b2, b3;
    TextField tf1, tf2, tf3, tf4, tf5;
    TextArea ta;

    Stdv() {
        s = new Stand(100);
        setLayout(new GridLayout(6, 1));
        p1 = new Panel();
        p2 = new Panel();
        p3 = new Panel();
        p4 = new Panel();
        p5 = new Panel();
        tf1 = new TextField(5);
        tf2 = new TextField(5);
        tf3 = new TextField(5);
        tf4 = new TextField(10);
        tf5 = new TextField(10);
        ta = new TextArea(5, 30);

        b1 = new Button("입력");
        b2 = new Button("표준편차");
        b3 = new Button("학생 정보 보기");

        p1.add(new Label("표준편차"));
        p2.add(new Label("학번 이름 성적"));
        p2.add(tf1);
        p2.add(tf2);
        p2.add(tf3);
        p3.add(b1);
        p4.add(new Label("결과: "));
        p4.add(tf5);
        p5.add(b2);
        p5.add(b3);

        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(ta);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        tf3.addKeyListener(this);

        addWindowListener(new WindowHandler());
        setSize(400, 300);
        setVisible(true);
        tf1.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            int id = Integer.parseInt(tf1.getText());
            String name = tf2.getText();
            int score = Integer.parseInt(tf3.getText());
            s.add(new Std(id, name, score));
            tf4.setText(s.n + "명 입력되었습니다.");
            tf1.setText(""); tf2.setText(""); tf3.setText("");
            tf1.requestFocus();
        } else if (e.getSource() == b2) {
            tf5.setText("" + s.stdv());
            tf1.requestFocus();
        } else if (e.getSource() == b3) {
            ta.setText("");  // 기존 내용 지우기
            ta.append("학번\t이름\t성적\n");
            for (int i = 0; i < s.n; i++) {
                ta.append(s.a[i].id + "\t" + s.a[i].name + "\t" + s.a[i].score + "\n");
            }
        }
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            int id = Integer.parseInt(tf1.getText());
            String name = tf2.getText();
            int score = Integer.parseInt(tf3.getText());
            s.add(new Std(id, name, score));
            tf1.setText(""); tf2.setText(""); tf3.setText("");
            tf1.requestFocus();
        }
    }
}

class WindowHandler extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        Window w = e.getWindow();
        w.setVisible(false);
        w.dispose();
        System.exit(0);
    }
}

class Stand extends Avg {
    Stand(int n) {
        super(n);
    }

    float stdv() {
        float s = 0;
        for (int k = 0; k < n; k++) {
            s = s + (a[k].score - avg()) * (a[k].score - avg());
        }
        s = s / (n - 1);
        return (float) Math.sqrt(s);
    }
}

class Avg {
    Std[] a;
    int n;

    Avg(int n) {
        a = new Std[n];
        this.n = 0;
    }

    void add(Std b) {
        a[n++] = b;
    }

    float avg() {
        float s = 0;
        for (int k = 0; k < n; k++) {
            s = s + a[k].score;
        }
        return s / n;
    }
}

class Std {
    int id, score;
    String name;

    Std(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    void pr() {
        System.out.println(id + " " + name + " " + score);
    }
}
