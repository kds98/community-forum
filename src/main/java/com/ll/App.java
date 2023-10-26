package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    String command;
    int textNumber = 0;
    Scanner scanner = new Scanner(System.in);
    List<Quotes> quotes = new ArrayList<>();

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            command = scanner.nextLine();
            if (command.equals("종료")) {
                break;
            } else if (command.equals("등록")) {
                create();
            } else if (command.equals("목록")) {
                listView();
            } else if (command.startsWith("삭제?")) {
                delete();
            } else if (command.startsWith("수정?")) {
                modify();
            }
        }
        System.exit(0);
    }

    void create() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();
        textNumber++;
        Quotes q = new Quotes(textNumber, content, author);
        System.out.printf("%d번 명언이 등록되었습니다.\n", textNumber);
        quotes.add(q);
    }

    void listView() {
        if (quotes.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        }
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = quotes.size() - 1; i >= 0; i--) {
            Quotes q = quotes.get(i);
            System.out.printf("%d / %s / %s\n", q.id, q.author, q.content);
        }
    }

    void delete() {
        int id = getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수 종료
        }
        try {
            quotes.remove(id - 1);
            System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
        } catch (Exception e) {
            System.out.printf("%d번 명언이 존재하지 않습니다.\n", id);
            return;
        }
    }

    void modify() {
        int id = getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수 종료
        }
        System.out.printf("%d번 명언을 수정합니다.\n", id);
    }

    int getParamAsInt(String paramName, int defaultValue) {
        String[] commandBits = command.split("\\?", 2);
        String queryString = commandBits[1];

        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];

            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String _paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            if (_paramName.equals(paramName)) {
                try {
                    // 문제 없는 경우
                    return Integer.parseInt(paramValue);
                } catch (NumberFormatException e) {
                    // 문제가 생긴 경우
                    return defaultValue;
                }
            }
        }
        return defaultValue;
    }
}