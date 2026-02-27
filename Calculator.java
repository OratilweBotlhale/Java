import java.util.Scanner;

public static void main(String args[])
{
    int flag = 0, option;
    double val1, val2;
    Scanner reader = new Scanner(System.in);
    System.out.println("Basic Java Calculator");

    do{
        System.out.println("---Menu Options--");
        System.out.println(" ");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Divison");
        System.out.println("5. Exit");
        System.out.println(" ");
        System.out.println("Enter number that represents your menu choice");
        option = reader.nextInt();

        if(option ==5)
        {
            flag = 1;
        }
        else {
            switch (option)
            {
                case 1: System.out.println("Addition");
                break;
                case 2: System.out.println("Subtraction");
                break;
                case 3: System.out.println("Multiplication");
                break;
                case 4: System.out.println("Division");
                break;
            }
            System.out.println();
        }
    } while (flag == 0);
    }
