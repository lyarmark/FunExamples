package FunExamples.FunExamples;
public class StringToInt {
    public static void main(String args[]) {
           String str = "-123.321";

           int len = str.length();
           double num = 0, factor = 1;
           boolean neg = false;
           int j = 0;
           if (str.charAt(0) == '-') {
                  neg = true;
                  j = 1;
           }

           // left of deciman
           for (int i = j; i < len; i++) {
                  if (str.charAt(i) == '.') {
                        j = i;
                        break;
                  }
                  num *= 10;
                  num += str.charAt(i) - '0';
           }

           double num2 = 0;
           // right of decimal
           for (int i = len - 1; i > j; i--) {
                  num2 += str.charAt(i) - '0';
                  num2 *= .10;
           }

           num += num2;
           if (neg) {
                  num = -num;
           }
           System.out.println(num);
    }
}