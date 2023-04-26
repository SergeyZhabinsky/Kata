import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestExec {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String s;
        Valid v = new Valid();
        while (true){
//            Введите разделёнными пробелами два числа с арифметическим знаком между ними.
//            Или букву "Q" для выхода.
//            После ввода нажмите кнопку "ENTER".
            System.out.println("\n\nCalculator.\n" +
                    "Enter two numbers separated by spaces with an arithmetic sign between them.\n" +
                    "Or the letter \"Q\" to exit.\n" +
                    "After entering, press the \"ENTER\" button.");
            s = scan.nextLine();

            if(s.toUpperCase().equals("Q")) break;

            if(v.start(s))
                System.out.println("Answer: " + v.getResult());
//                System.out.println("Ответ: " + v.getResult());
            else    break;

        }
    }
//=====================================================================



    public static String calc(String input){
        String result = "";
        return result;
    }

}
