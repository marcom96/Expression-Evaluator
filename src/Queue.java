/**
 @author       Marco Martinez
 @fileName     Queue.java
 @version      1.0
 @description  This is an extension of JList to allow for Queue operations (enQueue,deQueue).
 @date         2/27/2018

 Program Change Log
 ==========================
 Name     Date     Description
 Marco    2/27    Create baseline for Queue.
 */

public class Queue extends JList {
    // CLASS CONSTRUCTORS
    // (+) Queue()
    public Queue() { super(); }

    // (+) Queue(GenericItemType git)
    public Queue(GenericItemType git) { super(git); }

    // (+) Queue(ListEntry le)
    public Queue(ListEntry le) { super(le); }

    // (+) Queue(JList jl)
    public Queue(JList jl) { super(jl); }

    // (+) Queue(Stack s)
    public Queue(Stack s) { super(s); }

    // (+) Queue(Queue q)
    public Queue(Queue q) {
        super(q);
    }

    // (+) Queue(PriorityQueue q)
    public Queue(PriorityQueue q) { super(q); }

    // CHANGE STATE SERVICES
    // (+) void enQueue(GenericItemType git)
    public void enQueue(GenericItemType git) { this.add_fromHead(git); }

    // (+) GenericItemType deQueue()
    public GenericItemType deQueue() {
        GenericItemType temp = this.tail.getData();
        this.tail = this.tail.getPrev();
        if (this.tail != null)
            this.tail.setNext(null);
        this.totalCount--;
        return temp;
    }

    // (+) GenericItemType showLast()
    public GenericItemType showLast() {
        return this.tail.getData();
    }
}
