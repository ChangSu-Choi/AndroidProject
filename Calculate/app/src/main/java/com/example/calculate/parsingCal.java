package com.example.calculate;

import java.util.ArrayList;

public class parsingCal {


    public String calulation(ArrayList<String> splitedString){
        double num1 = Double.parseDouble(splitedString.get(0));
        double num2;
        double temp = 0;
        String operator = "", result = "";

//        입력이 제대로 들어왔는지 확인 하기 위함
        for(int i = 0; i < splitedString.size();i++){
            System.out.println("["+i+"]"+splitedString.get(i));
        }

//        입렵값을 루프로 도는데
        for(int i = 1; i < splitedString.size(); i++){
            if(i % 2 == 1){
//                홀수 기준으로 연산자가 들어옴, 들어온 연산자를 저장 후 뒤에 연산자에 따라 비교함
                operator = splitedString.get(i);
                continue;
            }

            num2 = Integer.parseInt(splitedString.get(i));
            switch (operator) {
                case "+":
                    temp = num1 + num2;
                    System.out.println("num1 += " + num1 + num2);
                    break;
                case "-":
                    temp = num1 - num2;
                    System.out.println("num1 -= " + num1+ num2);
                    break;
                case "*":
                    temp = num1 * num2;
                    System.out.println("num1 *= " + num1+ num2);
                    break;
                case "/":
                    System.out.println("num1 /= " + num1+ num2);
                    temp = num1 / num2;
                    break;
            }

            num1 = temp;
        }
        // 소수점 반올림
        return String.format("%.2f",num1);
    }

}
