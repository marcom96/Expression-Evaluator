/**
 @author       Marco Martinez
 @fileName     OperandToken.java
 @version      1.0
 @description  Used to evaluate expressions with operands.
 @date         3/26/2018

 Program Change Log
 ==========================
 Name     Date     Description
 Marco    3/26     Create baseline for OperandToken.
 */

 public class OperandToken extends GenericSlot {
    // INSTANCE VARIABLE DECLARATIONS
    private String token;
    private double value;

    // CLASS CONSTRUCTORS
    // (+) OperandToken()
    public OperandToken() {
        this.token = null;
        this.value = 0.00;
    }

    // (+) OperandToken(String newToken)
    public OperandToken(String newToken) {
        if (newToken != null) {
            this.token = newToken;
        } else {
            this.token = null;
        }
        this.value = 0.00;
    }

    // (+) OperandToken(String newToken, double newValue)
    public OperandToken(String newToken, double newValue) {
        if (newToken != null) {
            this.token = newToken;
            this.value = newValue;
        } else {
            this.token = null;
            this.value = newValue;
        }
    }

    // (+) OperandToken(String newToken, float newValue)
    public OperandToken(String newToken, float newValue) {
        if (newToken != null) {
            this.token = newToken;
            this.value = (double) newValue;
        } else {
            this.token = null;
            this.value = (double) newValue;
        }
    }

    // (+) OperandToken(String newToken, int newValue)
    public OperandToken(String newToken, int newValue) {
        if (newToken != null) {
            this.token = newToken;
            this.value = (double) newValue;
        } else {
            this.token = null;
            this.value = (double) newValue;
        }
    }

    // (+) OperandToken(OperandToken copy)
    public OperandToken(OperandToken copy) {
        if (copy.getToken() != null) {
            this.token = copy.getToken();
        } else {
            this.token = null;
        }
        this.value = copy.getValue();
    }

    // CHANGE STATE SERVICES
    // (+) void setToken(String newToken)
    public void setToken(String newToken) {
        if (newToken != null)
            this.token = newToken;
        else
            this.token = null;
    }

    // (+) void setValue(int newValue)
    public void setValue(int newValue) { this.value = (double) newValue; }

    // (+) void setValue(float newValue)
    public void setValue(float newValue) { this.value = (double) newValue; }

    // (+) void setValue(double newValue)
    public void setValue(double newValue) { this.value = newValue; }

    // READ STATE SERVICES
    // (+) boolean isLess(GenericItemType git)
    public boolean isLess(GenericItemType git) { return this.token.compareTo(((OperandToken) git).getToken()) < 0; }

    // (+) boolean isEqual(GenericItemType git)
    public boolean isEqual(GenericItemType git) { return this.token.compareTo(((OperandToken) git).getToken()) == 0; }

    // (+) boolean isGreater(GenericItemType git)
    public boolean isGreater(GenericItemType git) { return this.token.compareTo(((OperandToken) git).getToken()) > 0; }

    // (+) int determineIndex()
    public int determineIndex() { return (Character.getNumericValue((this.token.toLowerCase()).charAt(0))-10); }

    // (+) String getToken()
    public String getToken() { return this.token; }

    // (+) double getValue()
    public double getValue() { return this.value; }

    // (+) String toString()
    public String toString() { return this.token + " has a value of " + this.value; }
}
