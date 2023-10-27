package com.ll.base;


import com.ll.domain.quotes.Quotes;
import com.ll.domain.quotes.QuotesController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {

    private String command;
    private int textNumber;
    private Scanner scanner;
    private List<Quotes> quotesList;

    public App(){
        textNumber = 0;
        scanner = new Scanner(System.in);
        quotesList = new ArrayList<>();


    }

    public void run() {
        QuotesController quotesController = new QuotesController(scanner);
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");

            command = scanner.nextLine();

            Rq rq = new Rq(command);

            switch (rq.getAction()) {
                case "종료":
                    return;
                case "등록":
                    quotesController.create();
                    break;
                case "목록":
                    quotesController.listView();
                    break;
                case "삭제":
                    quotesController.delete(rq);
                    break;
                case "수정":
                    quotesController.modify(rq);
                    break;
            }
        }
    }
}