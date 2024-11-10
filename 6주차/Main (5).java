
// 이 프로그램은 두 가지 주요 기능을 포함하고 있습니다:
// 1. 사용자로부터 입력받은 숫자를 m진수에서 n진수로 변환.
// 2. 배열을 이용하여 큰 수의 팩토리얼을 계산하고 출력.

// 주요 기능 설명:
// - 진수 변환: 입력받은 숫자를 m진수에서 n진수로 변환하여 출력합니다.
// - 팩토리얼 계산: 배열을 사용하여 큰 수의 팩토리얼을 계산하고 결과를 출력합니다.

// 주요 클래스와 함수 설명:
// - Main 클래스: 사용자로부터 입력을 받아 진수 변환 및 팩토리얼 계산을 수행합니다.
// - Radix 클래스: 진수 변환과 관련된 계산을 수행합니다.
// - factorial 함수: 배열을 사용하여 큰 수의 팩토리얼을 계산하고 출력합니다.
// - multiply 함수: 한 자리 숫자와 배열에 저장된 큰 수를 곱하는 과정에서 자리올림을 처리합니다.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();  // 원래 진수
        int n = sc.nextInt();  // 변환할 진수
        Radix radix = new Radix(m, n);

        String num = sc.next();  // 입력받은 숫자

        // 입력받은 숫자를 m진수로 변환 후, 계산 및 출력
        System.out.println(Integer.parseInt(num, m));
        System.out.println(radix.conv(num));
        System.out.println(radix.conv2(radix.conv(num)));

        // 배열을 이용한 큰 수의 팩토리얼 계산
        int factNum = sc.nextInt();  // 팩토리얼을 구할 숫자 입력
        factorial(factNum);  // 팩토리얼 결과 출력
    }

    // 배열을 사용한 간결한 큰 팩토리얼 계산
    public static void factorial(int num) {
        int[] res = new int[10000];  // 큰 수를 저장할 배열
        res[0] = 1;  // 초기값: 0! = 1
        int resSize = 1;  // 현재 자릿수

        // 2부터 num까지 곱하는 과정
        for (int i = 2; i <= num; i++) {
            resSize = multiply(i, res, resSize);  // i와 곱하고 결과를 배열에 저장
        }

        // 결과 출력 (배열의 뒷자리부터 출력)
        System.out.print("Factorial of " + num + " is: ");
        for (int i = resSize - 1; i >= 0; i--) {
            System.out.print(res[i]);
        }
        System.out.println();
    }

    // 한 자리 숫자 i와 배열에 저장된 큰 수를 곱하는 함수
    public static int multiply(int x, int[] res, int resSize) {
        int carry = 0;  // 자리올림 값

        // 배열에 저장된 각 자리에 x를 곱하고 자리올림 처리
        for (int i = 0; i < resSize; i++) {
            int prod = res[i] * x + carry;
            res[i] = prod % 10;  // 현재 자리의 값
            carry = prod / 10;  // 자리올림 값 저장
        }

        // 자리올림 값이 있을 때는 for문으로 처리
        for (int i = resSize; carry > 0; i++) {
            res[i] = carry % 10;
            carry = carry / 10;
            resSize++;
        }

        return resSize;
    }
}

class Radix {
    int m, n;

    // 생성자에서 진수 변환값 초기화
    Radix(int mParam, int nParam) {
        m = mParam;
        n = nParam;
    }

    // m^n을 구하는 메소드
    int exp(int base, int exp){
        int res = 1;
        for (int i = 1; i <= exp; i++) {
            res *= base;
        }
        return res;
    }

    // m진수 숫자를 n진수로 변환
    String conv2(int num){
        String result = "";
        for (int i = 0; num != 0; i++, num /= n) {
            result = String.format("%x", num % n) + result;
        }
        return result;
    }

    // m진수 문자열을 10진수로 변환
    int conv(String num){
        int sum = 0;
        for (int i = 0; i < num.length(); i++) {
            int digit;
            if (num.charAt(i) >= 'a' && num.charAt(i) <= 'f') {
                digit = num.charAt(i) - 'a' + 10;
            } else {
                digit = num.charAt(i) - '0';
            }
            sum += digit * exp(m, num.length() - i - 1);
        }
        return sum;
    }
}
