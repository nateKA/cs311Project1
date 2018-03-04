import org.junit.Assert;
import org.junit.Test;

public class HashTableUnitTests {
    @Test
    public void bucket1(){
        HashTable ht = new HashTable(10);
        ht.add(new Tuple(6,"one"));
        ht.add(new Tuple(6,"two"));
        ht.add(new Tuple(6,"three"));

        ht.add(new Tuple(13,"one"));
        ht.add(new Tuple(13,"two"));

        Assert.assertEquals(3,ht.maxLoad());
        Assert.assertEquals(2,ht.getNumBuckets());
        Assert.assertEquals(5,ht.getNumElements());
    }

    @Test
    public void bucket2(){
        HashTable ht = new HashTable(10);
        ht.add(new Tuple(6,"one"));
        ht.add(new Tuple(6,"two"));
        ht.add(new Tuple(6,"three"));

        ht.add(new Tuple(13,"one"));
        ht.add(new Tuple(13,"two"));
        ht.add(new Tuple(13,"three"));
        ht.add(new Tuple(2,"four"));

        Assert.assertEquals(4,ht.maxLoad());
        Assert.assertEquals(2,ht.getNumBuckets());
        Assert.assertEquals(7,ht.getNumElements());
    }

    @Test
    public void bucket3(){
        HashTable ht = new HashTable(10);
        ht.add(new Tuple(6,"one"));
        ht.add(new Tuple(6,"two"));
        ht.add(new Tuple(6,"three"));

        ht.add(new Tuple(13,"one"));
        ht.add(new Tuple(13,"two"));
        ht.add(new Tuple(13,"three"));
        ht.add(new Tuple(2,"four"));

        ht.remove(new Tuple(13,"two"));

        Assert.assertEquals(3,ht.maxLoad());
        Assert.assertEquals(2,ht.getNumBuckets());
        Assert.assertEquals(6,ht.getNumElements());
    }

    @Test
    public void bucket4(){
        HashTable ht = new HashTable(10);
        ht.add(new Tuple(6,"one"));
        ht.add(new Tuple(6,"two"));

        ht.add(new Tuple(13,"one"));
        ht.add(new Tuple(13,"two"));
        ht.add(new Tuple(13,"three"));
        ht.add(new Tuple(2,"four"));

        ht.remove(new Tuple(13,"two"));
        ht.remove(new Tuple(2,"four"));
        ht.remove(new Tuple(13,"one"));

        Assert.assertEquals(2,ht.maxLoad());
        Assert.assertEquals(2,ht.getNumBuckets());
        Assert.assertEquals(3,ht.getNumElements());
    }

    @Test
    public void bucket5(){
        HashTable ht = new HashTable(10);
        ht.add(new Tuple(6,"one"));
        ht.add(new Tuple(6,"two"));

        ht.add(new Tuple(13,"one"));
        ht.add(new Tuple(13,"two"));
        ht.add(new Tuple(13,"three"));
        ht.add(new Tuple(2,"four"));

        ht.remove(new Tuple(13,"two"));
        ht.remove(new Tuple(2,"four"));
        ht.remove(new Tuple(13,"one"));
        ht.remove(new Tuple(13,"three"));

        Assert.assertEquals(2,ht.maxLoad());
        Assert.assertEquals(1,ht.getNumBuckets());
        Assert.assertEquals(2,ht.getNumElements());
    }

    @Test
    public void bucket6(){
        HashTable ht = new HashTable(10);
        ht.add(new Tuple(6,"one"));
        ht.add(new Tuple(6,"two"));

        ht.add(new Tuple(13,"one"));
        ht.add(new Tuple(13,"two"));
        ht.add(new Tuple(13,"three"));
        ht.add(new Tuple(2,"four"));


        ht.add(new Tuple(11,"one"));
        ht.add(new Tuple(1,"one"));
        ht.add(new Tuple(3,"one"));
        ht.add(new Tuple(4,"one"));
        ht.add(new Tuple(5,"one"));
        ht.add(new Tuple(7,"one"));


        Assert.assertEquals(3,ht.maxLoad());
        Assert.assertEquals(9,ht.getNumBuckets());
        Assert.assertEquals(12,ht.getNumElements());
    }

    @Test
    public void bucket7(){
        HashTable ht = new HashTable(10);
        ht.add(new Tuple(6,"one"));
        ht.add(new Tuple(6,"two"));

        ht.add(new Tuple(13,"one"));
        ht.add(new Tuple(13,"two"));
        ht.add(new Tuple(13,"three"));
        ht.add(new Tuple(2,"four"));


        ht.add(new Tuple(11,"one"));
        ht.add(new Tuple(1,"one"));
        ht.add(new Tuple(3,"one"));
        ht.add(new Tuple(4,"one"));
        ht.add(new Tuple(5,"one"));
        ht.add(new Tuple(7,"one"));
        ht.add(new Tuple(24,"one"));


        Assert.assertEquals(3,ht.maxLoad());
        Assert.assertEquals(9,ht.getNumBuckets());
        Assert.assertEquals(13,ht.getNumElements());
    }
}
