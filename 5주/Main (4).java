/*
Scanner 클래스와 Math 클래스:

Scanner 클래스는 사용자가 입력한 값을 받아오는 기능을 합니다. Math 클래스는 자바의 내장 수학 라이브러리로, 다양한 수학 연산을 제공합니다. 여기서는 Math.cos()를 사용하여 코사인 값을 구합니다.
Taylor 클래스:

테일러 급수를 사용하여 코사인 값을 직접 계산합니다. 이는 직접 구현한 방식으로, 자바 패키지(Math 클래스)를 사용하지 않고도 코사인 값을 계산할 수 있습니다.
calt 클래스:

기본적인 곱셈 기능을 제공하는 cal 클래스를 상속받아, 제곱 계산과 팩토리얼 계산 기능을 추가한 클래스입니다.
난수 생성:

Math.random()을 사용하여 패키지를 이용한 난수 생성기를 구현하였으며, 별도로 직접 난수 생성 로직을 구현하여 두 방식의 난수 생성 결과를 비교하는 프로그램을 작성했습니다.
*/

// 자바 패키지와 직접 구현한 코사인 함수 프로그램
// 자바 패키지(Math)를 이용하여 코사인값을 계산하고, 테일러 급수를 사용하여 코사인값을 직접 계산하는 프로그램

import java.util.Scanner;   // 사용자 입력을 받기 위한 Scanner 클래스
import java.lang.Math;      // 자바 패키지의 수학 라이브러리를 사용하기 위해 Math 클래스 사용

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);  // 사용자 입력을 위한 Scanner 객체 생성
        Taylor p = new Taylor();               // 직접 구현한 Taylor 클래스 객체 생성

        // 사용자로부터 실수 입력 받음 (각도 값)
        System.out.print("각도를 입력하세요: ");
        float x = inp.nextFloat();

        // 직접 구현한 코사인 함수 값 출력
        System.out.println("테일러 급수로 계산한 코사인값: " + p.cos(x));

        // Math 패키지를 사용하여 코사인 값 출력 (각도 값이 라디안으로 변환되어야 함)
        System.out.println("패키지로 계산한 코사인값: " + Math.cos(p.rad(x)));
    }
}

class Taylor extends calt {
    // 직접 구현한 코사인 함수 (테일러 급수 사용)
    float cos(float x) {
        float r = rad(x);  // 각도를 라디안으로 변환
        float s = 1;       // 테일러 급수 초기값 설정

        // 테일러 급수를 사용하여 코사인 값 계산 (10번 반복)
        for (int k = 1; k <= 10; k++) {
            // 테일러 급수 공식에 따라 항을 계산하고 더함
            s = add(s, power(-1, k) * power(r, 2 * k) / fac(2 * k));
        }
        return s;  // 계산된 값 반환
    }

    // 각도를 라디안 값으로 변환하는 함수
    float rad(float x) {
        return x * (float) Math.PI / 180;
    }
}

// Cal 클래스를 상속받아 연산 기능을 확장한 calt 클래스
class calt extends cal {
    // 제곱 계산 함수
    float power(float a, float b) {
        float p = 1;
        // b번 반복하여 a를 곱함
        for (int k = 1; k <= b; k++) {
            p = mul(p, a);  // 곱셈 함수 사용
        }
        return p;
    }

    // 팩토리얼 계산 함수
    float fac(int n) {
        float f = 1;
        // 1부터 n까지의 곱을 구함
        for (int k = 1; k <= n; k++) {
            f = mul(f, k);  // 곱셈 함수 사용
        }
        return f;
    }
}

// 기본적인 덧셈과 곱셈 기능을 제공하는 cal 클래스
class cal {
    // 두 수의 덧셈 함수
    float add(float a, float b) {
        return a + b;
    }

    // 두 수의 곱셈 함수
    float mul(float a, float b) {
        return a * b;
    }
}

// 패키지를 이용한 난수 생성기와 직접 구현한 난수 생성기 프로그램
// 패키지의 Math.random() 함수와 직접 구현한 난수 생성 함수 비교
class RandomExample {
    public static void main(String[] args) {
        // Math.random()을 사용한 난수 생성
        System.out.println("패키지 난수: " + Math.random());

        // 직접 구현한 난수 생성기 (0과 1 사이의 실수 반환)
        double randomValue = customRandom();
        System.out.println("직접 구현한 난수: " + randomValue);
    }

    // 간단한 난수 생성 함수 (0과 1 사이의 실수를 생성)
    static double customRandom() {
        // 시간에 따른 시드값을 이용하여 난수를 생성하는 방식
        long seed = System.currentTimeMillis();
        return (seed % 100) / 100.0;
    }
}
