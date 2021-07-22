/**
 @author       Marco Martinez
 @fileName     OperatorToken.java
 @version      1.0
 @description  Used to evaluate expressions with operators.
 @date         3/26/2018

 Program Change Log
 ==========================
 Name     Date     Description
 Marco    3/26     Create baseline for OperatorToken.
 */

public class OperatorToken extends GenericSlot
{

    // CONSTANT DEFINITIONS
    private int PRIO_MAX = 5;

    // INSTANCE VARIABLE DECLARATIONS
    private String token;
    private int prio;

    // CLASS CONSTRUCTORS
    // (+) OperatorToken()
    public OperatorToken()
    {
        this.token = null;
        this.prio = -1;
    }

    // (+) OperatorToken(String newToken)
    public OperatorToken(String newToken)
    {
        this.token = newToken;
        if (this.determineIndex() == -1)
        {
            this.token = null;
            this.prio = -1;
        }

        else
        {
            this.prio = this.determineIndex();
        }
    }

    // (+) OperatorToken(OperatorToken copy)
    public OperatorToken(OperatorToken copy)
    {
        if (copy.getToken() != null)
        {
            this.token = copy.getToken();
            this.prio = copy.getPrio();
        }

        else
        {
            this.token = null;
            this.prio = -1;
        }
    }

    // CHANGE STATE SERVICES
    // (+) void setToken(String newToken)
    public void setToken(String newToken)
    {
        if (newToken != null)
            this.token = newToken;
        else
            this.token = null;
    }

    // (+) void setPrio(int newPrio)
    public void setPrio(int newPrio)
    {
        if (newPrio >= 0 && newPrio < PRIO_MAX)
            this.prio = newPrio;
    }

    // READ STATE SERVICES
    // (+) boolean isLess(GenericItemType git)
    public boolean isLess(GenericItemType git) { return this.token.compareTo(((OperatorToken) git).getToken()) < 0; }

    // (+) boolean isEqual(GenericItemType git)
    public boolean isEqual(GenericItemType git) { return this.token.compareTo(((OperatorToken) git).getToken()) == 0; }

    // (+) boolean isGreater(GenericItemType git)
    public boolean isGreater(GenericItemType git) { return this.token.compareTo(((OperatorToken) git).getToken()) > 0; }

    // (+) int determineIndex()
    public int determineIndex()
    {
        if (this.token != null)
        {
            switch (this.token)
            {
                case "=":
                    return 1;
                case "+":
                    return 2;
                case "-":
                    return 2;
                case "/":
                    return 3;
                case "*":
                    return 3;
                case "(":
                    return 4;
                case ")":
                    return 5;
                default:
                    return -1;
            }
        }
        return -1;
    }

    // (+) String getToken()
    public String getToken() { return this.token; }

    // (+) int getPrio()
    public int getPrio() { return this.prio; }

    // (+) String toString()
    public String toString() { return this.token + " has a priority of " + this.prio; }
}
