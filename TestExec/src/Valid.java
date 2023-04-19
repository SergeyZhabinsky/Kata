import java.util.*;

public class Valid {

    final private Throwable T=null;

    // Содержит арифметическую строку разбитую на лексемы
    private String []arr;

    // Содержит набор арифметических символов
    private Set<String> arithmeticSymbols = new HashSet<>();

    // арифм. знак
    private String sign = "";

    // содержит true в случае успешного решения
    private String iResult = "";


    private boolean isArabicSymbol;
//=====================================================================


    Valid(){

        // Создаём набор арифметических символов
        arithmeticSymbols.add("+");
        arithmeticSymbols.add("-");
        arithmeticSymbols.add("*");
        arithmeticSymbols.add("/");
    }
//=====================================================================




    // Запускаем работу класса
    boolean start(String x){
        boolean bResult = false;
        isArabicSymbol = true;

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

        // count - количество арифм. знаков в строке
        int count = 0;

        // проходим по строке проверяя каждый символ
        for (char smb:x.toCharArray()) {
            if( arithmeticSymbols.contains( String.valueOf(smb) ) ) {
                sign = String.valueOf(smb);
                count += 1;
            }
        }

        // Провыряем результат. Должен быть один арифм. знак и он не должен стоять первым и последним
        try {
            if(count == 1 && x.indexOf(sign) > 0 && x.indexOf(sign) < x.length()-1){
                arr = triming(x, "\\" + sign);
                return true;
            }else{
//                throw new Except("Выражение должно содержать один арифметический знак и иметь числовые значения слева и справа от знака.", T);
                throw new Except("The expression must contain one arithmetic sign and have numeric values to the left and right of the sign", T);
            }
        }catch (Except e){}


        // Если арифм. действие не удовлетворяет условию
        return false;
    }
//=====================================================================



//    private boolean isDigitValid(String... x) throws Except{
    private boolean isDigitValid(String... x) throws Except{

        Map<String, Integer> digit = new HashMap<String, Integer>(){{
            put("0", 0);
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
            put("I",   1);
            put("II",  2);
            put("III", 3);
            put("IV",  4);
            put("V",   5);
            put("VI",  6);
            put("VII", 7);
            put("VIII",8);
            put("IX",  9);

            put("X",     10);
            put("XI",    11);
            put("XII",   12);
            put("XIII",  13);
            put("XIV",   14);
            put("XV",    15);
            put("XVI",   16);
            put("XVII",  17);
            put("XVIII", 18);
        }};

        int num1, num2 = -1;
        final String VALUE = "0-10";


        try {
            num1 = digit.get(x[0]);
            num2 = digit.get(x[1]);

            // Чтобы латинская цифра была не больше 10
            if(num1>10){
                throw new Except("An invalid value was entered. The digit is outside the range '" + VALUE +
                        "' or is not a Latin numeral in the same range: 1) '" + x[0] + "' 2) '" + x[1] + "'!", T);
            }


            // Проверяем одинаковый языковой ввод или нет
            if(isDigit(x[0]) != isDigit(x[1])) {
//                throw new Except("В выражении должны присутствовать, или только арабские цифры, или только латинские цифры.", T);
                throw new Except("The expression must contain either only Arabic numerals or only Latin numerals.", T);
            }


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

                // если латинские цифры, то выполняем условие задания
                if(!isArabicSymbol){
                    if(Integer.valueOf(iResult)<1){
//                        throw new Except("Значение меньше или равно '0'.", T);
                        throw new Except("Value less than or equal to '0'", T);
                    }
                    for(String key : digit.keySet()) {
                        String value = String.valueOf( digit.get(key) );
                        if(iResult.equals(value)){
                            iResult = key;
                            if(!isDigit(key)) break;
                        }
                    }
                }


            }catch(ArithmeticException e){
//                    throw new Except("Ошибка арифметической операции.", e);
                    throw new Except("Arithmetic operation error.", e);
            }


        }catch (NullPointerException e){
//            throw new Except("Введено неправильное значение. Цифра находится вне диапазона '" + VALUE +
//                    "' или не является латинской цифрой в том же диапазоне: 1)' " + x[0] + "' 2)' " + x[1] + "'!", e);
            throw new Except("An invalid value was entered. The digit is outside the range '" + VALUE +
                    "' or is not a Latin numeral in the same range: 1) '" + x[0] + "' 2) '" + x[1] + "'!", e);
        }
        return true;
    }
//=====================================================================



    private boolean isDigit(String str){
        try{
            Integer.valueOf(str);
        }catch (NumberFormatException e){
            isArabicSymbol=false;
            return false;
        }

        return true;
    }

}
