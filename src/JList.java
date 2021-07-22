/**
 @author       Marco Martinez
 @fileName     JList.java
 @version      1.0
 @description  Used as pointer based container with "standard" functionality.
 @date         2/20/2018

 Program Change Log
 ==========================
 Name     Date     Description
 Marco    2/20    Create baseline for JList.
 */

public class JList {
    // INSTANCE VARIABLE DECLARATIONS
    ListEntry   head,
                tail,
                currentIteration;
    int         totalCount,
                currentCount;

    // CLASS CONSTRUCTORS
    // (+) JList()
    public JList() {
        this.head = this.tail = this.currentIteration = null;
        this.currentCount = this.totalCount = 0;
    }

    // (+) JList(GenericItemType data)
    public JList(GenericItemType data) {
        if (data != null) {
            this.head = new ListEntry(data);
            this.head.setNext(null);
            this.head.setPrev(null);
            this.currentIteration = null;
            this.tail = this.head;
            this.totalCount = 1;
            this.currentCount = 0;

        } else {
            this.head = this.tail = this.currentIteration = null;
            this.currentCount = this.totalCount = 0;
        }
    }

    // (+) JList(ListEntry le)
    public JList(ListEntry le) {
        if (le.getData() != null) {
            this.totalCount = 1;
            this.currentCount = 0;
            this.head = this.tail = this.currentIteration = le;
            while (this.currentIteration.getNext() != null) {
                this.currentIteration = this.currentIteration.getNext();
                this.totalCount++;
            }
            this.tail = this.currentIteration;
        } else {
            this.head = this.tail = this.currentIteration = null;
            this.currentCount = this.totalCount = 0;
        }
    }

    // (+) JList(JList l)
    public JList(JList l) {
        this.head = l.getStart();
        this.tail = l.tail;
        this.totalCount = l.getCount();
    }

    // (+) JList(Stack s)
    public JList(Stack s) {
        this.head = s.getStart();
        this.tail = s.tail;
        this.totalCount = s.getCount();
    }

    // (+) JList(Queue q)
    public JList(Queue q) {
        this.head = q.getStart();
        this.tail = q.tail;
        this.totalCount = q.getCount();
    }

    // (+) JList(PriorityQueue q)
    public JList(PriorityQueue q) {
        this.head = q.getStart();
        this.tail = q.tail;
        this.totalCount = q.getCount();
    }

    // CHANGE STATE SERVICES
    // (+) void init()
    public void init() {
        this.head = this.tail = this.currentIteration = null;
        this.currentCount = this.totalCount = 0;
    }

    // (+) void add_fromHead(GenericItemType git)
    public void add_fromHead(GenericItemType git) {
        if (this.isFull())
            return;

        if (git != null) {
            if (!this.isEmpty()) {
                this.head.setPrev(new ListEntry(git));
                this.head.getPrev().setNext(this.head);
                this.head = this.head.getPrev();
            } else {
                this.head = this.tail = new ListEntry(git);
                this.head.setPrev(null);
                this.head.setNext(null);
            }
            this.totalCount++;
        }
    }

    // (+) void add_fromMid(GenericItemType git)
    public void add_fromMid(GenericItemType git) {
        if (this.isFull())
            return;

        if (git != null) {
            if (!this.isEmpty()) {
                int mid = this.totalCount / 2;
                this.currentIteration = head;
                for (int i = 0; i < mid-1; i++) {
                    this.currentIteration = this.currentIteration.getNext();
                }
                ListEntry temp = this.currentIteration;
                this.currentIteration = new ListEntry(git);
                this.currentIteration.setPrev(temp);
                this.currentIteration.setNext(temp.getNext());
                temp.setNext(this.currentIteration);
                temp = this.currentIteration.getNext();
                temp.setPrev(this.currentIteration);
            } else {
                this.head = this.tail = new ListEntry(git);
                this.head.setNext(null);
                this.head.setPrev(null);
            }
            this.totalCount++;
        }

    }

    // (+) void add_fromTail(GenericItemType git)
    public void add_fromTail(GenericItemType git) {
        if (this.isFull())
            return;
            
        if (git != null) {
            if (!this.isEmpty()) {
                this.tail.setNext(new ListEntry(git));
                this.tail.getNext().setPrev(this.tail);
                this.tail = this.tail.getNext();
            } else {
                this.head = this.tail = new ListEntry(git);
                this.head.setPrev(null);
                this.head.setNext(null);
            }
            this.totalCount++;
        }
    }

    // (+) void add_fromHead(ListEntry le)
    public void add_fromHead(ListEntry le) {
        if (this.isFull())
            return;

        if (le.getData() != null) {
            if (!this.isEmpty()) {
                this.head.setPrev(new ListEntry(le.getData()));
                this.head.getPrev().setNext(this.head);
                this.head = this.head.getPrev();
            } else {
                this.head = this.tail =  new ListEntry(le.getData());
            }
            this.totalCount++;
        }
    }

    // (+) void add_fromMid(ListEntry le)
    public void add_fromMid(ListEntry le) {
        if (this.isFull())
            return;

        if (le.getData() != null) {
            if (!this.isEmpty()) {
                int mid = this.totalCount / 2;
                this.currentIteration = head;
                for (int i = 0; i < mid-1; i++) {
                    this.currentIteration = this.currentIteration.getNext();
                }
                ListEntry temp = this.currentIteration.getNext();
                this.currentIteration.setNext(new ListEntry(le.getData()));
                this.currentIteration.getNext().setPrev(this.currentIteration);
                temp.setPrev(this.currentIteration.getNext());
                temp.getPrev().setNext(temp);

            } else {
                this.head = this.tail =  new ListEntry(le.getData());
            }
            this.totalCount++;
        }

    }

    // (+) void add_fromTail(ListEntry le)
    public void add_fromTail(ListEntry le) {
        if (this.isFull())
            return;

        if (le.getData() != null) {
            if (!this.isEmpty()) {
                this.tail.setNext(new ListEntry(le.getData()));
                this.tail.getNext().setPrev(this.tail);
                this.tail = this.tail.getNext();
            } else {
                this.head = this.tail =  new ListEntry(le.getData());
            }
        }
        this.totalCount++;
    }

    // (+) void bubbleSort_ascending()
    public void bubbleSort_ascending() {
        this.currentIteration = this.head;

        for (int outer = 0; outer < this.totalCount; outer++) {
            for (int inner = 0; inner < this.totalCount-1; inner++) {
                if (this.currentIteration.getData().isGreater(this.currentIteration.getNext().getData())) {
                    GenericItemType temp = this.currentIteration.getData();
                    this.currentIteration.setData(this.currentIteration.getNext().getData());
                    this.currentIteration.getNext().setData(temp);
                }
                this.currentIteration = this.currentIteration.getNext();
            }
            this.currentIteration = this.head;
        }
    }

    // (+) void bubbleSort_descending()
    public void bubbleSort_descending() {
        this.currentIteration = this.head;

        for (int outer = 0; outer < this.totalCount; outer++) {
            for (int inner = 0; inner < this.totalCount-1; inner++) {
                if (this.currentIteration.getData().isLess(this.currentIteration.getNext().getData())) {
                    GenericItemType temp = this.currentIteration.getData();
                    this.currentIteration.setData(this.currentIteration.getNext().getData());
                    this.currentIteration.getNext().setData(temp);
                }
                this.currentIteration = this.currentIteration.getNext();
            }
            this.currentIteration = this.head;
        }
    }

    // (+) GenericItemType linearSearch(GenericItemType key)
    public GenericItemType linearSearch(GenericItemType key) { return new ListEntry(this.lSearch(key)).getData(); }

    // (+) GenericItemType linearSearch(ListEntry key)
    public GenericItemType linearSearch(ListEntry key) { return new ListEntry(this.lSearch(key.getData())).getData(); }

    //  (-) ListEntry lSearch(GenericItemType key)
    private ListEntry lSearch(GenericItemType key) {
        this.currentCount = 0;
        this.currentIteration = this.head;
        for (int i = 0; i < this.totalCount; i++) {
            if (this.currentIteration.getData().isEqual(key)) {
                return this.currentIteration;
            }
            this.currentIteration = this.currentIteration.getNext();
            this.currentCount++;
        }
        this.currentCount = 0;
        return new ListEntry();
    }

    // (+) void remove(GenericItemType key)
    public void remove(GenericItemType key) { this.delete(key); }

    // (+) void remove(ListEntry key)
    public void remove(ListEntry key) { this.delete(key.getData()); }

    //  (-) void delete(GenericItemType key)
    private void delete(GenericItemType key) {
        this.currentIteration = this.lSearch(key);
        if (this.currentIteration != null) {
            this.currentIteration.setData(this.tail.getData());
            this.tail = this.tail.getPrev();
            this.tail.setNext(null);
            this.totalCount--;
        }
        bubbleSort_ascending();
    }

    // (+) void reverseList()
    public void reverseList() {
        JList temp = new JList();
        this.currentIteration = this.tail;
        for (int i = 0; i < this.totalCount; i++) {
            temp.add_fromTail(this.currentIteration.getData());
            this.currentIteration = this.currentIteration.getPrev();
        }
        this.head = temp.getStart();
        this.tail = temp.getEnd();
        this.totalCount = temp.getCount();
    }

    // READ STATE SERVICES
    // (+) boolean isFull()
    public boolean isFull() {
        if (this.isEmpty())
            return false;

        try {
            this.tail.setNext(new ListEntry(this.tail));
            this.tail.getNext().setPrev(this.tail);
            this.tail = this.tail.getNext();
            this.tail = this.tail.getPrev();
            this.tail.setNext(null);
            return false;
        } catch (OutOfMemoryError e) {
            return true;
        }
    }

    // (+) boolean isEmpty()
    public boolean isEmpty() { return this.head == null; }

    // (+) int getCount()
    public int getCount() { return this.totalCount; }

    // (+) ListEntry getStart()
    public ListEntry getStart() { return this.head; }

    // (+) ListEntry getEnd()
    public ListEntry getEnd() { return this.tail; }

    // (+) void Iterator_initialize()
    public void Iterator_initialize() {
        this.currentCount = 0;
        this.currentIteration = this.head;
    }

    // (+) boolean Iterator_hasNext()
    public boolean Iterator_hasNext() {
        if (this.currentCount < this.totalCount)
            return true;
        return false;
    }

    // (+) GenericItemType Iterator_iterate()
    public GenericItemType Iterator_iterate() {
        if (this.currentCount < this.totalCount) {
            if (this.currentCount != 0)
                this.currentIteration = this.currentIteration.getNext();
            else {
                this.currentIteration = this.head;
            }
            this.currentCount++;
            return this.currentIteration.getData();
        }
        return new ListEntry().getData();
    }
}
