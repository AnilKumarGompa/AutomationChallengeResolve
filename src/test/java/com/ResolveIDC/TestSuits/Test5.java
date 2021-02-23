package com.ResolveIDC.TestSuits;

public class Test5 {
    public static void main(String[] args){
        printMultiplesOfThreeAndFive();
    }

    private static void printMultiplesOfThreeAndFive(){
        for(int i=1;i<=100;i++){
            if(i%3==0&&i%5==0){
                System.out.println("Resolve");
            }
            else if(i%3==0||i%5==0){
                System.out.println("M"+convertToWords(i));
            }
            else{
                System.out.println(i);
            }
        }
    }
    private static String convertToWords(int number)
    {
        // Get number of digits
        // in given number
        char[] num = String.valueOf(number).toCharArray();
        int len = num.length;

        // Base cases
        if (len == 0) {
            return "Empty String";
        }


        String[] singleDigits = new String[] {
                "Zero", "One", "Two", "Three", "Four",
                "Five", "Six", "Seven", "Eight", "Nine"
        };

        /* To make indexing simple, the first string is not used*/
        String[] two_digits = new String[] {
                "",          "Ten",      "Eleven",  "Twelve",
                "Thirteen",  "Fourteen", "Fifteen", "Sixteen",
                "Seventeen", "Eighteen", "Nineteen"
        };

        /* To make indexing simple, the first two string are not used*/
        String[] tens_multiple = new String[] {
                "",      "",      "Twenty",  "Thirty", "Forty",
                "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
        };

        String[] tens_power = new String[] { "Hundred", "Thousand" };

        /* For single digit number */
        if (len == 1) {
            return singleDigits[num[0] - '0'];
        }

        /* Iterate while num
            is not '\0' */
        int x = 0;
        String numWord = "";
        while (x < num.length) {

            /* Code path for first 2 digits */
            if (len >= 3) {
                if (num[x] - '0' != 0) {
                    numWord+=singleDigits[num[x] - '0'] + " ";
                    numWord+=tens_power[len - 3]+ " ";
                    // here len can be 3 or 4
                }
                --len;
            }

            /* Code path for last 2 digits */
            else {
                /* Need to explicitly handle
                10-19. Sum of the two digits
                is used as index of "two_digits"
                array of strings */
                if (num[x] - '0' == 1) {
                    int sum = num[x] - '0' + num[x + 1] - '0';
                    return two_digits[sum];
                }

                /* Need to explicitely handle 20 */
                else if (num[x] - '0' == 2 && num[x + 1] - '0' == 0) {
                    return "Twenty";
                }

                /* Rest of the two digit
                numbers i.e., 21 to 99 */
                else {
                    int i = (num[x] - '0');
                    if (i > 0)
                        numWord+= tens_multiple[i] + " ";
                    else
                        numWord+= "";
                    ++x;
                    if (num[x] - '0' != 0)
                        numWord+=singleDigits[num[x] - '0'];
                }
            }
            ++x;
        }
        return numWord;
    }
}
