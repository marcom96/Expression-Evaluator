/**
 @author       Marco Martinez
 @fileName     PriorityQueue.java
 @version      1.0
 @description  This is an extension of JList to allow for PriorityQueue for sorting6.
 @date         2/27/2018

 Program Change Log
 ==========================
 Name     Date     Description
 Marco    2/27    Create baseline for PriorityQueue.
 */

public class PriorityQueue extends Queue {
    // CLASS CONSTRUCTORS
    // (+) PriorityQueue()
    public PriorityQueue() { super(); }

    // (+) PriorityQueue(GenericItemType git)
    public PriorityQueue(GenericItemType git) { super(git); }

    // (+) PriorityQueue(ListEntry le)
    public PriorityQueue(ListEntry le) { super(le); }

    // (+) PriorityQueue(JList jl)
    public PriorityQueue(JList jl) { super(jl); }

    // (+) PriorityQueue(Stack s)
    public PriorityQueue(Stack s) { super(s); }

    // (+) PriorityQueue(Queue q)
    public PriorityQueue(Queue q) {
        super(q);
    }

    // (+) PriorityQueue(PriorityQueue q)
    public PriorityQueue(PriorityQueue q) {
        super(q);
    }

    // CHANGE STATE SERVICES
    // (+) void SortAscending()
    public void SortAscending() { this.bubbleSort_ascending(); }

    // (+) void SortDescending()
    public void SortDescending() { this.bubbleSort_descending(); }
}
