package com.ll.domain.quotes;

import com.ll.base.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuotesController {
    private Scanner scanner;
    private int lastQuotesId;
    private List<Quotes> quotesList;

    public QuotesController(Scanner scanner) {
        this.scanner = scanner;
        lastQuotesId = 0;
        quotesList = new ArrayList<>();

        initTestData();


    }

     private void initTestData() { // 테스트 데이터
        for (int i = 0; i < 10; i++) {
            write("명언" + i, "작가" + i);
        }

    }

    public void create() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();

        Quotes quotes = write(content, author);

        System.out.printf("%d번 명언이 등록되었습니다.\n", quotes.getId());
    }

    public void listView() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        if (quotesList.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        }

        for (int i = quotesList.size() - 1; i >= 0; i--) {
            Quotes quotes = quotesList.get(i);
            System.out.printf("%d / %s / %s\n", quotes.getId(), quotes.getAuthor(), quotes.getContent());
        }
    }

    public void delete(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수 종료
        }

        int index = findIndexOfQuotesById(id);

        try {
            quotesList.remove(index);
            System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
        } catch (Exception e) {
            System.out.printf("%d번 명언이 존재하지 않습니다.\n", id);
            return;
        }
    }

    private Quotes write(String content, String authorName) {
        lastQuotesId++;
        int id = lastQuotesId;

        Quotes quotes = new Quotes(id, content, authorName);
        quotesList.add(quotes);

        return quotes;
    }

    public void modify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수 종료
        }

        int index = findIndexOfQuotesById(id);

        Quotes quotes = quotesList.get(index);

        System.out.printf("명언(기존) : %s\n", quotes.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.printf("작가(기존) : %s\n", quotes.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        quotes.setContent(content);
        quotes.setAuthor(author);
        System.out.printf("%d번 명언을 수정합니다.\n", id);
    }

    private int findIndexOfQuotesById(int id) {
        for (int i = 0; i < quotesList.size(); i++) {
            Quotes quotes = quotesList.get(i);
            if (quotes.getId() == id) {
                return i;
            }
        }

        return -1;
    }
}


