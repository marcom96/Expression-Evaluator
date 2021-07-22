/**
 @author Marco Martinez
 @fileName Main.java
 @version 1.0
 @description Tests JList, ListEntry, Stack, Queue, PriorityQueue.
 @date 2/27/2018

 Program Change Log
 ==========================
 Name   Date    Description
 Marco  2/27    Create baseline for Main.
 */

// LIBRARIES
import static java.lang.Math.*;

public class Evaluator
{
    // CONSTANT DEFINITIONS
    private int BUCKET_MAX = 26;

    // INSTANCE VARIABLE DECLARATIONS
    private Queue postfix;
    private Stack pending;
    private OperandToken result;
    private HashTable symboltable;

    // CLASS CONSTRUCTORS
    // (+) Evaluator()
    public Evaluator()
    {
        this.postfix = new Queue();
        this.pending = new Stack();
        this.result = new OperandToken();
        this.symboltable = new HashTable(BUCKET_MAX);
    }

    // (+) Evaluator(Queue newPostfix)
    public Evaluator(Queue newPostfix)
    {
        this.symboltable = new HashTable(BUCKET_MAX);
        this.postfix = new Queue(newPostfix);
        this.pending = new Stack();
        this.result = new OperandToken();
    }

    // (+) Evaluator(Queue newPostfix, HashTable newSymboltable)
    public Evaluator(Queue newPostfix, HashTable newSymboltable)
    {
        this.symboltable = new HashTable(newSymboltable);
        this.postfix = new Queue(newPostfix);
        this.pending = new Stack();
        this.result = new OperandToken();
    }

    // (+) Evaluator(Evaluator copy)
    public Evaluator(Evaluator copy)
    {
        this.postfix = new Queue(copy.getPostfix());
        this.pending = new Stack();
        this.result = new OperandToken(copy.getResult());
        this.symboltable = new HashTable(copy.getSymboltable());
    }

    // CHANGE STATE SERVICES
    // (+) void addSymbolToTable(OperandToken symbol)
    public void addSymbolToTable(OperandToken symbol) { this.symboltable.insertIntoHT(symbol); }

    // (+) OperandToken determineValue()
    public OperandToken determineValue() {
        OperandToken temp = (OperandToken)this.postfix.deQueue();

        try {
            if (((OperatorToken)this.postfix.showLast()).getToken().equals("-")) {
                this.postfix.deQueue();
                return this.assignValue(temp, true);
            }
            else
                return this.assignValue(temp,false);

        } catch (ClassCastException e) {
            return this.assignValue(temp,false);
        }
    }

    // (+) OperandToken assignValue(OperandToken token,boolean isNegative)
    public OperandToken assignValue(OperandToken token,boolean isNegative)
        {
        if (isNumeric(token.getToken()) && isNegative == false)
            token.setValue(Double.parseDouble(token.getToken()));
        else if (isNumeric(token.getToken()) && isNegative == true)
            token.setValue(-Double.parseDouble(token.getToken()));
        else if (this.symboltable.searchHT(token) != null)
            token.setValue(((OperandToken) this.symboltable.searchHT(token)).getValue());

        return token;
    }

    // (+) void determineFunction(OperandToken function)
    public void determineFunction(OperandToken function) {
        OperandToken temp;

        switch (function.getToken().toLowerCase()) {
            case "sqrt":
                this.postfix.deQueue();
                temp = this.determineValue();
                temp.setValue(sqrt(temp.getValue()));
                this.pending.push(temp);
                break;
            case "abs":
                this.postfix.deQueue();
                temp = this.determineValue();
                temp.setValue(abs(temp.getValue()));
                this.pending.push(temp);
                break;
            case "sin":
                this.postfix.deQueue();
                temp = this.determineValue();
                temp.setValue(sin(temp.getValue()));
                this.pending.push(temp);
                break;
            case "cos":
                this.postfix.deQueue();
                temp = this.determineValue();
                temp.setValue(cos(temp.getValue()));
                this.pending.push(temp);
                break;
            default:
                this.pending.push(this.assignValue((OperandToken)this.postfix.deQueue(),false));
                break;
        }
    }

    // (+) void evaluatePostfix()
    public void evaluatePostfix() {
        if (this.postfix.getCount() > 1) {
            this.result = new OperandToken((OperandToken) this.postfix.showLast());
            this.pending.push(this.postfix.deQueue());
            OperandToken test;
            boolean active = true;

            if (this.postfix.getCount() != 2) {
                while (active) {
                    try {
                        for (int i = 0; i < 20; i++) {
                            this.determineFunction((OperandToken)this.postfix.showLast());
                        }
                    } catch (ClassCastException e) {
                    }

                    if (!((OperatorToken) this.postfix.showLast()).getToken().equals("="))
                        this.determineOperation((OperandToken)this.pending.pop(),(OperandToken)this.pending.pop(),(OperatorToken)this.postfix.deQueue());
                    else
                        active = false;
                }
                this.result.setValue(((OperandToken) this.pending.pop()).getValue());
            } else {
                this.result = this.assignValue(this.result,false);
            }
        }
    }

    // (+) void determineOperation(OperandToken b, OperandToken a, OperatorToken operator)
    public void determineOperation(OperandToken b, OperandToken a, OperatorToken operator) {
        switch (operator.getToken()) {
            case "+":
                this.pending.push(new OperandToken("temp",(a.getValue() + b.getValue())));
                break;
            case "-":
                this.pending.push(new OperandToken("temp",(a.getValue() - b.getValue())));
                break;
            case "*":
                this.pending.push(new OperandToken("temp",(a.getValue() * b.getValue())));
                break;
            case "/":
                if (b.getValue() != 0)
                    this.pending.push(new OperandToken("temp",(a.getValue() / b.getValue())));
                else
                    throw new Error("Undefined error.");
                break;
            default:
                break;
        }
        return;
    }

    // READ STATE SERVICES
    // (+) Queue getPostfix()
    public Queue getPostfix() { return this.postfix; }

    // (+) OperandToken getResult()
    public OperandToken getResult() { return this.result; }

    // (+) HashTable getSymboltable()
    public HashTable getSymboltable() { return this.symboltable; }

    // (+) boolean isNumeric(String token)
    public boolean isNumeric(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
