/**
 @author Marco Martinez
 @fileName AppDriver.java
 @version 1.0
 @description Evaluates expressions.
 @date 2/27/2018

 Program Change Log
 ==========================
 Name   Date    Description
 Marco  2/27    Create baseline for AppDriver.
 */

// LIBRARIES
import java.io.*;
import java.util.Scanner;

public class AppDriver {
    // CONSTANT DEFINITIONS
    private static int COLUMNS = 7;
    private static int ROWS = 5;
    private static int BUCKET_MAX = 26;

    public static void main(String[] args)
    {
        // VARIABLE DECLARATIONS
        Scanner scan = new Scanner(System.in);
        Parser myParser;
        Evaluator myEval;
        String infix,
                yesNo = "y";
        Queue postfix;
        OperandToken result;


        while((yesNo.toLowerCase()).equals("y")) {
            infix = getInfix(scan);
            myParser = new Parser(infix, generateNextstateTable());
            try {
                myParser.translateInfixToPostfix();
                postfix = myParser.getPostfix();
                ParseTable test = new ParseTable(postfix);
                System.out.println(test.getContents());
                myEval = new Evaluator(postfix, generateSymboltable());
                determineAdditionalVariables(myEval, scan);
                try {
                    myEval.evaluatePostfix();
                    result = myEval.getResult();
                    displayResults(result);
                } catch (java.lang.Error e) {
                    System.out.println("Divide by zero error.");
                }
            } catch (java.lang.Error e) {
                System.out.println("Error with inputs.");
            }
            
            yesNo = getYesNo(scan);
        }
    }

    // (+) static void displayResults(OperandToken result)
    public static void displayResults(OperandToken result)
    {
        System.out.println(result.getToken() + " = " + result.getValue());
    }

    // (+) static String getInfix(Scanner scan)
    public static String getInfix(Scanner scan)
    {
        String infix;
        System.out.print("Please enter an expression: ");
        infix = scan.nextLine();
        scan.reset();
        return infix;
    }

    // (+) static void determineAdditionalVariables(Evaluator myEval, Scanner scan)
    public static void determineAdditionalVariables(Evaluator myEval, Scanner scan)
    {
        String yesNo;
        String varName;
        Double varValue;

        System.out.print("Would you like to manually enter another symbol for expression evaluation? (y/n) ");
        yesNo = scan.nextLine();
        scan.reset();
        while (!(yesNo.toLowerCase()).equals("y") && !(yesNo.toLowerCase()).equals("n"))
        {
            System.out.print("Please enter either y or n: ");
            yesNo = scan.nextLine();
            scan.reset();
        }
        while ((yesNo.toLowerCase()).equals("y"))
        {
            System.out.print("Please enter the name of the variable: ");
            varName = scan.nextLine();

            System.out.print("Please enter the value of the variable: ");
            varValue = scan.nextDouble();

            myEval.addSymbolToTable(new OperandToken(varName,varValue));

            System.out.print("Would you like to manually enter another symbol for expression evaluation? (y/n)");
            yesNo = scan.nextLine();
            scan.reset();
            while (!(yesNo.toLowerCase()).equals("y") && !(yesNo.toLowerCase()).equals("n"))
            {
                System.out.print("Please enter either y or n: ");
                yesNo = scan.nextLine();
                scan.reset();
            }
        }
    }

    // (+) static String getYesNo(Scanner scan)
    public static String getYesNo(Scanner scan)
    {
        String yesNo;
        String varName;
        Double varValue;

        System.out.print("Would you like to evaluate another expression? (y/n) ");
        yesNo = scan.nextLine();
        scan.reset();
        while (!(yesNo.toLowerCase()).equals("y") && !(yesNo.toLowerCase()).equals("n"))
        {
            System.out.print("Please enter either y or n: ");
            yesNo = scan.nextLine();
            scan.reset();
        }
        return yesNo;
    }

    // (+) static HashTable generateSymboltable()
    public static HashTable generateSymboltable()
    {
        HashTable tempTable = new HashTable(BUCKET_MAX);
        try
        {
            InputStream in = new FileInputStream("SYMBOLTABLE.dat");
            byte [] data = new byte[4096];
            String file;
            String[] lines;
            in.read(data);
            file = new String(data, "UTF-8");
            lines = file.split("\\r?\\n");
            String c;
            String temp;
            int spot;

            for (int i = 0; i < lines.length-1; i++)
            {
                temp = "";
                c = "";
                spot = 0;
                c = Character.toString(lines[i].charAt(spot));
                while (!c.equals(" "))
                {
                    temp += c;
                    spot++;
                    c = Character.toString(lines[i].charAt(spot));
                }
                tempTable.insertIntoHT(new OperandToken(temp,Double.valueOf(lines[i].substring((spot),lines[i].length()))));
            }
            return tempTable;
        }

        catch (IOException e) { return tempTable; }
    }

    // (+) static String[][] generateNextstateTable()
    public static String[][] generateNextstateTable()
    {
        try
        {
            InputStream in = new FileInputStream("TABLE.dat");
            byte [] data = new byte[4096];
            String file;
            String[] lines;
            in.read(data);
            file = new String(data, "UTF-8");
            lines = file.split("\\r?\\n");
            String table[][] = new String[ROWS][COLUMNS];

            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLUMNS; j++)
                {
                    table[i][j] = lines[i].substring(j*3,j*3+2);
                }
            }
            return table;
        }

        catch (IOException e)
        {
            return null;
        }
    }
}
