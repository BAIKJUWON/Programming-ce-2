/*
 * 이 프로그램은 입력 문자열을 분석하여 각 토큰을
 * 키워드, 식별자, 숫자로 분류하는 간단한 Java 프로그램입니다.
 *
 * 프로그램의 흐름은 다음과 같습니다:
 * 1. 사용자가 콘솔에 입력을 합니다.
 * 2. 입력된 문자열은 지정된 구분자(예: 공백, 괄호, 기호 등)를 기준으로
 *    토큰으로 분리됩니다.
 * 3. 각 토큰에 대해 다음과 같은 검사가 이루어집니다:
 *    - 키워드 검사: 토큰이 미리 정의된 키워드 목록에 있는지 확인합니다.
 *    - 식별자 검사: 토큰이 Java의 식별자 규칙에 따라 유효한지 확인합니다.
 *    - 숫자 검사: 토큰이 숫자로 변환 가능한지 확인합니다.
 * 4. 각 토큰의 분류 결과에 따라 콘솔에 출력합니다.
 * 5. 사용자가 "quit"를 입력하면 프로그램이 종료됩니다.
 *
 * 주요 클래스:
 * - Main: 프로그램의 진입점과 입력 처리 로직을 포함합니다.
 * - Keyw: 키워드와 식별자를 검사하는 메소드를 포함하는 클래스입니다.
 *
 * 이 프로그램은 간단한 렉서(어휘 분석기)의 역할을 하며,
 * 프로그래밍 언어의 기본 구조를 이해하는 데 도움을 줍니다.
 */



package new2021;


import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

            Scanner inp = new Scanner(System.in);

            Keyw p = new Keyw();



            while (true) {

                String stra = inp.nextLine();

                if (stra.equals("quit")) break; // "quit" 입력 시 종료

                String tmp;

                StringTokenizer st = new StringTokenizer(stra, " {} [] ().=:;,+\"", true);



                while (st.hasMoreTokens()) {

                    tmp = st.nextToken();

                    if (tmp.equals(" ")) continue; // 공백은 무시



                    // 키워드 검사

                    if (p.iskey(tmp)) {

                        System.out.println("키워드 : " + tmp);

                    }

                    // 식별자 검사

                    else if (p.sik(tmp)) {

                        System.out.println("식별자 : " + tmp);

                    }

                    // 숫자 검사

                    else if (p.Number1(tmp)) {

                        System.out.println("숫자 : " + tmp);

                    }

                    else {

                        System.out.println(tmp);

                    }

                }

            }

        }

    }



    class Keyw {

        String[] key = {"double", "int", "float", "for", "void",

                "while", "if", "break", "static", "extern",

                "else", "char", "continue", "return"};



        // 키워드 검사

        boolean iskey(String a) {

            for (String k : key) {

                if (a.equals(k)) return true;

            }

            return false;

        }



        // 식별자 검사

        boolean sik(String a) {

            if (a.isEmpty()) return false;

            char firstChar = a.charAt(0);

            if (!Character.isLetter(firstChar) && firstChar != '_') return false; // 첫 문자가 문자 또는 밑줄이 아니면 false

            for (int i = 1; i < a.length(); i++) {

                char c = a.charAt(i);

                if (!Character.isLetterOrDigit(c) && c != '_') return false; // 문자, 숫자, 밑줄 외의 문자가 있으면 false

            }

            return true;

        }



        // 숫자 검사

        boolean Number1(String a) {

            try {

                Double.parseDouble(a); // 문자열을 숫자로 변환 시도

                return true; // 변환 성공 시 true 반환

            } catch (NumberFormatException e) {

                return false; // 변환 실패 시 false 반환

            }

        }

    }