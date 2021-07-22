/**
 @author       Marco Martinez
 @fileName     ListEntry.java
 @version      1.0
 @description  Used in List Container with references to next and previous for bidirectional.
 @date         2/20/2018

 Program Change Log
 ==========================
 Name     Date     Description
 Marco    2/20    Create baseline for ListEntry.
 */

public class ListEntry {
    // (+) INSTANCE VARIABLE DECLARATION
    GenericItemType data;
    ListEntry       next,
                    prev;

    // CLASS CONSTRUCTORS
    // (+) ListEntry()
    public ListEntry() {
        this.data = null;
        this.next = null;
        this.prev = null;
    }

    // (+) ListEntry(GenericItemType data)
    public ListEntry(GenericItemType data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    // (+) ListEntry(ListEntry le)
    public ListEntry(ListEntry le) {
        this.data = le.getData();
        this.next = le.getNext();
        this.prev = le.getPrev();
    }

    // CHANGE STATE SERVICES
    // (+) void setData(GenericItemType data)
    public void setData(GenericItemType data) {
        this.data = data;
    }

    // (+) void setNext(ListEntry next)
    public void setNext(ListEntry next) {
        if (next != null)
            this.next = next;
        else
            this.next = null;
    }

    // (+) void setPrev(ListEntry prev)
    public void setPrev(ListEntry prev) {
        if (prev != null)
            this.prev = prev;
        else
            this.prev = null;
    }

    // READ STATE SERVICES
    // (+) GenericItemType getData()
    public GenericItemType getData() {
        return this.data;
    }

    // (+) ListEntry getNext()
    public ListEntry getNext() {
        return this.next;
    }

    // (+) ListEntry getPrev()
    public ListEntry getPrev() {
        return this.prev;
    }
}
