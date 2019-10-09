package app.entities;

import java.util.Arrays;
import java.util.Collections;


/** Класс для реализации игры  */

public class Game {
    private String number="";
    final private Integer[] numbers = {0,1,2,3,4,5,6,7,8,9};

    /** Генерация числа для пользователя */

    public String getNumber(){

        Collections.shuffle(Arrays.asList(numbers));
        number+=numbers[0];
        number+=numbers[1];
        number+=numbers[2];
        number+=numbers[3];

        return number;
    }

    /** Проверка числа пользователя */

    public String checkNumber(String userNumber, String inputNumber){
        int bull=0;
        int cows=0;

        for (int i=0;i<4;i++){

            if(String.valueOf(userNumber.charAt(i)).equals(String.valueOf(inputNumber.charAt(i)))){
                bull++;
            }else{
                String symbol = String.valueOf(inputNumber.charAt(i));
                if(userNumber.contains(symbol)){
                    cows++;
                }
            }
        }

        return inputNumber+" -- " + bull+"Б" +cows+"К";
    }


}
