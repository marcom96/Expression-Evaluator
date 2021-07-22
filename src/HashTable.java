/**
    @author       Marco Martinez
    @fileName     HashTable.java
    @version      2.0
    @description  Complete redesign with JList reuse.
    @date         2/1/2019

    Program Change Log
    ==========================
    Name     Date     Description
    Marco    2/1      Create baseline for HashTable.java
 */

public class HashTable {
    // CONSTANT DEFINITIONS
    public final int MAXBUCKETS = 1000;

    // INSTANCE VARIABLE DECLARATIONS
    private Bucket[] ht = new Bucket[MAXBUCKETS];
    private int index,
                max;

    // CLASS CONSTRUCTORS
    // (+) HashTable()
    public HashTable() {
        this.max = MAXBUCKETS;
        for (int i = 0; i < max; i++)
            this.ht[i] = new Bucket();
    }

    // (+) HashTable(int newMax)
    public HashTable(int newMax) {
        if (newMax >= 0)
            this.max = newMax;
        else
            this.max = MAXBUCKETS;
        for (int i = 0; i < max; i++)
            this.ht[i] = new Bucket();
    }

    // (+) HashTable(GenericSlot gs)
    public HashTable(GenericSlot gs) {
        this.max = MAXBUCKETS;
        for (int i = 0; i < max; i++)
            this.ht[i] = new Bucket();
        this.ht[gs.determineIndex()] = new Bucket(gs);
    }

    // (+) HashTable(GenericSlot gs, int newMax)
    public HashTable(GenericSlot gs, int newMax) {
        if (newMax >= 0)
            this.max = newMax;
        else
            this.max = MAXBUCKETS;
        for (int i = 0; i < max; i++)
            this.ht[i] = new Bucket();
        this.ht[gs.determineIndex()] = new Bucket(gs);
    }

    // (+) HashTable(HashTable ht)
    public HashTable(HashTable ht) {
        this.ht = ht.getHashTable();
        this.max = ht.getMax();
    }

    // CHANGE STATE SERVICES
    // (+) void initialize()
    public void initialize() {
        for (int i = 0; i < max; i++)
            this.ht[i] = new Bucket();
        this.index = 0;
    }

    // (+) void insertIntoHT(GenericSlot data)
    public void insertIntoHT(GenericSlot data) {
        int hashIndex = data.determineIndex();
        if (hashIndex < max)
            this.ht[hashIndex].add_fromTail(data);
    }

    // (+) GenericItemType searchHT(GenericSlot data)
    public GenericItemType searchHT(GenericSlot data) {
        int hashIndex = data.determineIndex();
        if (hashIndex < max)
            return ht[hashIndex].linearSearch(data);
        return null;
    }

    // (+) void deleteFromHT(GenericSlot data)
    public void deleteFromHT(GenericSlot data) {
        int hashIndex = data.determineIndex();
        if (hashIndex < max)
            this.ht[hashIndex].remove(data);
    }

    // READ STATE SERVICES
    // (+) void Iterator_initialize()
    public void Iterator_initialize() {
      this.index = 0;
   }

    // (+) boolean Iterator_hasNext()
    public boolean Iterator_hasNext() {
      return this.index < max;
   }

    // (+) Bucket Iterator_iterate()
    public Bucket Iterator_iterate() {
      return new Bucket(this.ht[this.index++]);
   }

    // (+) int findLocation(GenericSlot key)
    public int findLocation(GenericSlot key) {
        if (ht[key.determineIndex()].linearSearch(key) != null)
            return this.ht[key.determineIndex()].searchLocation(key);
        return -1;
    }

    // (+) int getIndex()
    public int getIndex() {
      return this.index;
   }

    // (+) Bucket[] getHashTable()
    public Bucket[] getHashTable() {
      return this.ht;
   }

    // (+) Bucket getHashTable(int index)
    public Bucket getHashTable(int index) {
        if (index < MAXBUCKETS)
            return this.ht[index];
        return new Bucket();
    }

    // (+) int getMax()
    public int getMax() {
      return this.max;
   }
}
