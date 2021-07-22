/**
    @author       Marco Martinez
    @fileName     Bucket.java
    @version      2.0
    @description  This is a record of Bucket.
    @date         2/1/2019

    Program Change Log
    =========================
    Name       Date      Description
    Marco      2/1       Create baseline for Bucket.java
    Marco      2/7       Finalize Bucket.java
    Marco      3/7       Redesign for reuse
 */


public class Bucket extends JList {
    // CLASS CONSTRUCTORS
    // (+) Bucket()
    public Bucket() {
        super();
    }

    // (+) Bucket(GenericItemType data)
    public Bucket(GenericItemType data) {
        super(data);
    }

    // (+) Bucket(ListEntry le)
    public Bucket(ListEntry le) {
        super(le);
    }

    // (+) Bucket(JList l)
    public Bucket(JList l) {
        super(l);
    }

    // (+) Bucket(Bucket b)
    public Bucket(Bucket b) {
        super(b);
    }

    // READ STATE SERVICES
    // (+) int searchLocation(GenericSlot key)
    public int searchLocation(GenericSlot key) {
        return keyLocation(key);
    }

    // (+) int searchLocation(GenericItemType key)
    public int searchLocation(GenericItemType key) {
        return keyLocation(key);
    }

    // (+) int searchLocation(ListEntry key)
    public int searchLocation(ListEntry key) {
        return keyLocation(key.getData());
    }

    //  (-) int keyLocation(GenericItemType key)
    private int keyLocation(GenericItemType key) {
        this.currentCount = 0;
        this.currentIteration = this.head;
        for (int i = 0; i < this.totalCount; i++) {
            if (this.currentIteration.getData().isEqual(key)) {
                return i;
            }
            this.currentIteration = this.currentIteration.getNext();
            this.currentCount++;
        }
        this.currentCount = 0;
        return -1;

    }
}
