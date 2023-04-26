import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Valid {

    final private Throwable T=null;

    // Содержит арифметическую строку разбитую на лексемы
    private String []arr;

    // Содержит набор арифметических символов
    private Set<String> arithmeticSymbols = new HashSet<>();

    // Все возможные арифм. знаки
    private String allArphmtcSigns = "+-*/";


    // арифм. знак
    private String sign = "";

    // содержит true в случае успешного решения
    private String iResult = "";


//=====================================================================


    Valid(){

        // Создаём набор арифметических символов
        for (char smb:allArphmtcSigns.toCharArray()) {
            arithmeticSymbols.add(String.valueOf(smb));
        }
    }
//=====================================================================




    // Запускаем работу класса
    boolean start(String x){
        boolean bResult = false;

        x = x.toUpperCase();

        if(parseArithmeticProblem(x)){
            try {

                if (isDigitValid(arr)) {
                    bResult = true;
                }
            }catch (Except e){}
        }
        return bResult;
    }
//=====================================================================



    // получаем результат вычисления
    String getResult(){    return iResult; }


    // Дробим строку на числовые символы и арифмет. знак
    private String[] triming(String str, String sign){
        return str.split(sign);
    }
//=====================================================================



    // проверяем строку на возможность арифметического действия с учётом ТЗ
    private boolean parseArithmeticProblem(String x){
        boolean retValue = false;

        try {
            if(isMatchesRuleTemplate(x)){
                arr = triming(x, " ");
                sign = String.valueOf(arr[1]);
                arr = triming(x, "\\" + sign);
                for(int i=0; i<arr.length; ++i){
                    arr[i] = arr[i].trim();
                }
                retValue = true;
            }else{
    //                throw new Except("Неверный формат записи.", T);
                throw new Except("Invalid record format", T);
            }
        }catch (Except e){}


        // Если арифм. действие не удовлетворяет условию
        return retValue;
    }
//=====================================================================



    private boolean isDigitValid(String... x) throws Except{

        Map<String, Integer> digit = new HashMap<String, Integer>(){{
//            put("0", 0);
            put("1", 1);
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("10", 10);
        }};

        int num1, num2 = -1;
        final String VALUE = "1-10";


        try {
            num1 = digit.get(x[0]);
            num2 = digit.get(x[1]);

            try {
                switch (sign){
                    case "+": iResult = String.valueOf(num1+num2);  break;
                    case "-": iResult = String.valueOf(num1-num2);  break;
                    case "*": iResult = String.valueOf(num1*num2);  break;
                    case "/":
//                        if(num2 == 0)   System.out.println("Деление на нуль не допустимо!");
                        if(num2 == 0)   System.out.println("Division by zero is not allowed!");
                        else            iResult = String.valueOf(num1 / num2);
                        break;
                }
            }catch(ArithmeticException e){
//                    throw new Except("Ошибка арифметической операции.", e);
                    throw new Except("Arithmetic operation error.", e);
            }


        }catch (NullPointerException e){
//            throw new Except("Введено неправильное значение. Цифра находится вне диапазона '" + VALUE +
//                    ": 1)' " + x[0] + "' 2)' " + x[1] + "'!", e);
            throw new Except("An invalid value was entered. The digit is outside the range '" + VALUE +
                    "': 1) '" + x[0] + "' 2) '" + x[1] + "'!", e);
        }
        return true;
    }
//=====================================================================



    private boolean isDigit(String str){
        try{
            Integer.valueOf(str);
        }catch (NumberFormatException e){
            return false;
        }

        return true;
    }
//=====================================================================



    private boolean isMatchesRuleTemplate(String str){
        boolean retValue = false;

        Pattern pattern = Pattern.compile("[0-9]+\\s[+-/*]\\s[0-9]+");
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()){
            retValue = true;
        }
        return retValue;
    }

}


