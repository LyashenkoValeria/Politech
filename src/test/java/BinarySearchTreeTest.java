import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @org.junit.Test
    public void search() {
        BinarySearchTree BST = new BinarySearchTree();
        BST.add(8);
        BST.add(3);
        BST.add(1);
        BST.add(6);
        BST.add(4);
        BST.add(7);
        BST.add(10);
        BST.add(14);
        BST.add(13);
        assertTrue(BST.search(10));
        assertFalse(BST.search(12));
    }

    @org.junit.Test
    public void add() {
        BinarySearchTree BST = new BinarySearchTree();
        BST.add(8);
        BST.add(3);
        BST.add(1);
        BST.add(6);
        BST.add(4);
        BST.add(7);
        BST.add(10);
        BST.add(14);
        BST.add(13);
    }

    @org.junit.Test
    public void neighbors() {
        BinarySearchTree BST = new BinarySearchTree();
        BST.add(8);
        BST.add(3);
        BST.add(1);
        BST.add(6);
        BST.add(4);
        BST.add(7);
        BST.add(10);
        BST.add(14);
        BST.add(13);
        BST.neighbors(7);
        BST.neighbors(1);
    }

    @org.junit.Test
    public void remove() {
        BinarySearchTree BST = new BinarySearchTree();
        BST.add(8);
        BST.add(3);
        BST.add(1);
        BST.add(6);
        BST.add(4);
        BST.add(7);
        BST.add(10);
        BST.add(14);
        BST.add(13);
        BST.remove(13);
        BST.add(12);
        BST.remove(14);
        BST.remove(6);
    }

    @org.junit.Test
    public void nextElem() {
    }
}