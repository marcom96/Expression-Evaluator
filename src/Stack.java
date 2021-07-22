/**
 @author       Marco Martinez
 @fileName     Stack.java
 @version      1.0
 @description  This is an extension of JList to allow for Stack operations (push, pop, show top).
 @date         2/27/2018

 Program Change Log
 ==========================
 Name     Date     Description
 Marco    2/27    Create baseline for Stack.
 */

public class Stack extends JList {
    // CLASS CONSTRUCTORS
    // (+) Stack()
    public Stack() { super(); }

    // (+) Stack(GenericItemType git)
    public Stack(GenericItemType git) { super(git); }

    // (+) Stack(ListEntry le)
    public Stack(ListEntry le) { super(le); }

    // (+) Stack(JList jl)
    public Stack(JList jl) { super(jl); }

    // (+) Stack(Stack s)
    public Stack(Stack s) { super(s); }

    // (+) Stack(Queue q)
    public Stack(Queue q) { super(q); }

    // (+) Stack(PriorityQueue q)
    public Stack(PriorityQueue q) { super(q); }

    // CHANGE STATE SERVICES
    // (+) void push(GenericItemType git)
    public void push(GenericItemType git) { this.add_fromHead(git); }

    // (+) GenericItemType pop()
    public GenericItemType pop() {
        GenericItemType temp = this.head.getData();
        this.head = this.head.getNext();
        if (this.head != null)
            this.head.setPrev(null);
        this.totalCount--;
        return temp;
    }

    // READ STATE SERVICES
    // (+) GenericItemType showTop()
    public GenericItemType showTop() {
        if (head != null)
            return this.head.getData();
        return null;
    }
}
