/**
 @author       Marco Martinez
 @fileName     Parser.java
 @version      1.0
 @description  Preps and translates expressions for evaluator.
 @date         3/26/2018

 Program Change Log
 ==========================
 Name     Date     Description
 Marco    3/26     Create baseline for Parser.
 */

public class Parser
{

    // CONSTANT DEFINITIONS
    private int COLUMNS = 7;
    private int ROWS = 5;

    // INSTANCE VARIABLE DECLARATIONS
    private String infix;
    private Queue postfix;
    private ParseTable intermediate;
    private String[][] nextstateTable;

    // CLASS CONSTRUCTORS
    // (+) Parser()
    public Parser()
    {
        this.infix = null;
        this.intermediate = new ParseTable();
        this.postfix = new Queue();
        this.nextstateTable = new String[ROWS][COLUMNS];
    }

    // (+) Parser(String newInfix)
    public Parser(String newInfix)
    {
        this.infix = newInfix;
        this.intermediate = new ParseTable();
        this.postfix = new Queue();
        this.nextstateTable = new String[ROWS][COLUMNS];
    }

    // (+) Parser(String newInfix, String[][] table)
    public Parser(String newInfix, String[][] table)
    {
        if (newInfix != null)
            this.infix = newInfix;
        else
            this.infix = null;

        if (table != null)
            this.nextstateTable = table;
        else
            this.nextstateTable = new String[ROWS][COLUMNS];

        this.intermediate = new ParseTable();
    }

    // (+) Parser(Parser copy)
    public Parser(Parser copy)
    {
        this.infix = copy.getInfix();
        this.intermediate = copy.getIntermediate();
        this.postfix = copy.getPostfix();
        if (copy.getNextstateTable() != null)
            this.nextstateTable = copy.getNextstateTable();
        else
            this.nextstateTable = new String[ROWS][COLUMNS];
    }

    // CHANGE STATE SERVICES
    // (+) void assignNextstateTable(String[][] table)
    public void assignNextstateTable(String[][] table)
    {
        if (table != null)
            this.nextstateTable = table;
        else
            this.nextstateTable = new String[ROWS][COLUMNS];
    }

    // (+) void translateInfixToPostfix()
    public void translateInfixToPostfix()
    {
        if (this.infix != null)
        {
            for (int i = 0; i < this.infix.length(); i++)
            {
                int currentCol;
                String temp = "";
                String c ="";
                String action;

                if (i < this.infix.length())
                    c = Character.toString(infix.charAt(i));

                if (c != null)
                {
                    if (!c.equals(" ") && (new OperatorToken(c)).getToken() == null && i < this.infix.length())
                    {
                        while (!c.equals(" ") && (new OperatorToken(c)).getToken() == null && i < this.infix.length())
                        {
                            temp += Character.toString(infix.charAt(i));
                            i++;

                            if (i < this.infix.length())
                                c = Character.toString(infix.charAt(i));
                        }
                        i--;
                        currentCol = 0;
                        c = null;
                    }

                    else if (!c.equals(" ") && i < this.infix.length())
                    {
                        temp = c;
                        currentCol = (new OperatorToken(temp)).determineIndex();
                    }

                    else
                    {
                        temp = null;
                        currentCol = COLUMNS - 1;
                    }

                    if (temp != null) {
                        action = this.nextstateTable[this.determineRow()][currentCol];
                        this.doAction(action,temp, currentCol);
                    }
                }
            }

            for (int j = 0; j <= this.intermediate.getCountOfS2(); j++)
                this.intermediate.doU2();

        }
        this.postfix = this.intermediate.getQ1();
    }

    // (+) void doAction(String action, String temp, int currentCol)
    public void doAction(String action, String temp, int currentCol)
    {
        switch (action)
        {
            case "S1":
                this.intermediate.doQ1(new OperandToken(temp));
                break;

            case "S2":
                this.intermediate.doS2(new OperatorToken(temp));
                break;

            case "ER":
                this.intermediate.doER();
                break;

            case "U1":
                while (action.equals("U1"))
                {
                    this.intermediate.doU1();
                    action = this.nextstateTable[this.determineRow()][currentCol];
                    this.doAction(action,temp,currentCol);
                }
                break;

            case "UC":
                this.intermediate.doUC();
                break;
        }
    }

    // (+) int determineRow()
    public int determineRow()
    {
        if (this.intermediate.showTopOfS2() != null)
            return ((GenericSlot)this.intermediate.showTopOfS2()).determineIndex();
        else
            return 0;
    }

    // READ STATE SERVICES
    // (+) String[][] getNextstateTable()
    public String[][] getNextstateTable() { return this.nextstateTable; }

    // (+) String getInfix()
    public String getInfix() { return (new String(this.infix)); }

    // (+) Queue getPostfix()
    public Queue getPostfix() { return this.postfix; }

    // (+) int getInfixLength()
    public int getInfixLength() { return this.infix.length(); }

    // (+) ParseTable getIntermediate()
    public ParseTable getIntermediate() { return (new ParseTable(this.intermediate)); }
 }
