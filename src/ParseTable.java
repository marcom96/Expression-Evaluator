/**
 @author       Marco Martinez
 @fileName     ParseTable.java
 @version      1.0
 @description  Controls and manipulates the intermediate forms between infix and prefix through Q1 and S2.
 @date         3/26/2018

 Program Change Log
 ==========================
 Name     Date     Description
 Marco    3/26     Create baseline for ParseTable.
 */
 
public class ParseTable 
{

    // INSTANCE VARIABLE DECLARATION
    private Queue Q1;
    private Stack S2;
    
    // CLASS CONSTRUCTORS
    // (+) ParseTable()
    public ParseTable() 
    {
        Q1 = new Queue();
        S2 = new Stack();
    }
    
    // (+) ParseTable(Queue newQueue)
    public ParseTable(Queue newQueue) 
    {
        Q1 = new Queue(newQueue);
        S2 = new Stack();
    }
    
    // (+) ParseTable(ParseTable copy)
    public ParseTable(ParseTable copy) 
    {
        Q1 = copy.getQ1();
        S2 = copy.getS2();
    }
    
    // CHANGE STATE SERVICES
    // (+) void doQ1(GenericSlot token)
    public void doQ1(GenericSlot token) { this.Q1.enQueue(token); }
    
    // (+) void doS2(GenericSlot token)
    public void doS2(GenericSlot token) { this.S2.push(token); }
    
    // (+) void doER()
    public void doER() { throw new Error("Error with inputs."); }
    
    // (+) void doU1()
    public void doU1() 
    {
        this.Q1.enQueue(this.S2.pop());
    }
    
    // (+) void doUC()
    public void doUC() 
    {
        while (((GenericSlot)(this.S2).showTop()).determineIndex() != 4) {
            this.Q1.enQueue(this.S2.pop());
        }
            
        this.S2.pop();
    }
    
    // (+) void doU2()
    public void doU2() 
    {
        for (int i = 0; i < this.getCountOfS2(); i++)
            this.Q1.enQueue(this.S2.pop());
    }
    
    // (+) GenericItemType deQueueFromQ1()
    public GenericItemType deQueueFromQ1() { return this.Q1.deQueue(); }
    
    // (+) GenericItemType showTopOfS2()
    public GenericItemType showTopOfS2() { return this.S2.showTop(); }
    
    // (+) int getCountOfS2()
    public int getCountOfS2() { return this.S2.getCount(); }
    
    // (+) int getCountOfQ1()
    public int getCountOfQ1() { return this.Q1.getCount(); }
    
    // READ STATE SERVICES
    // (+) String getContents()
    public String getContents() 
    {
        String temp = "";
        this.Q1.Iterator_initialize();
        while (this.Q1.Iterator_hasNext()) 
        {
            temp += this.Q1.Iterator_iterate().toString() + "\n";
        }
        
        return temp;
    }
    
    // (+) Queue getQ1()
    public Queue getQ1() { return this.Q1; }
    
    // (+) Stack getS2()
    public Stack getS2() { return this.S2; }
 }