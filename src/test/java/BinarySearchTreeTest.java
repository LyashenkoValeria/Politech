import org.junit.Before;
import org.junit.jupiter.api.function.Executable;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.*;


public class BinarySearchTreeTest {
    BinarySearchTree BST = new BinarySearchTree();
    @Before
    public void Tree(){
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
    public void search() {
        assertTrue(BST.search(10));
        assertFalse(BST.search(12));
    }

    @org.junit.Test
    public void add() {
        BinarySearchTree BST1 = new BinarySearchTree();
        BST1.add(8);
        BST1.add(3);
        BST1.add(1);
        BST1.add(6);
        BST1.add(4);
        BST1.add(7);
        BST1.add(10);
        BST1.add(14);
        BST1.add(13);
        BST1.add(16);

        BST.add(16);
        assertThrows(IllegalArgumentException.class, new Executable() {
            public void execute() throws Throwable {
                BST.add(1);
            }
        });
        assertEquals(BST1.treeAsList(BST1.root), BST.treeAsList(BST.root));
    }

    @org.junit.Test
    public void neighbors() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            public void execute() throws Throwable {
                BST.neighbors(20);
            }
        });
        List<Integer> check = new ArrayList<Integer>();
        check.add(3);
        assertEquals(BST.neighbors(1), check);
    }

    @org.junit.Test
    public void remove() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            public void execute() throws Throwable {
                BST.remove(20);
            }
        });

        BinarySearchTree BSTWithRemove = new BinarySearchTree();
        BSTWithRemove.add(8);
        BSTWithRemove.add(3);
        BSTWithRemove.add(1);
        BSTWithRemove.add(6);
        BSTWithRemove.add(4);
        BSTWithRemove.add(7);
        BSTWithRemove.add(10);
        BSTWithRemove.add(14);
        BST.remove(13);
        assertEquals(BSTWithRemove.treeAsList(BSTWithRemove.root), BST.treeAsList(BST.root));

        BinarySearchTree BSTWithRemove1 = new BinarySearchTree();
        BSTWithRemove1.add(8);
        BSTWithRemove1.add(3);
        BSTWithRemove1.add(1);
        BSTWithRemove1.add(6);
        BSTWithRemove1.add(4);
        BSTWithRemove1.add(7);
        BSTWithRemove1.add(10);
        BSTWithRemove1.add(12);
        BST.add(12);
        BST.remove(14);
        assertEquals(BSTWithRemove1.treeAsList(BSTWithRemove.root), BST.treeAsList(BST.root));

        BinarySearchTree BSTWithRemove2 = new BinarySearchTree();
        BSTWithRemove2.add(8);
        BSTWithRemove2.add(3);
        BSTWithRemove2.add(1);
        BSTWithRemove2.add(7);
        BSTWithRemove2.add(4);
        BSTWithRemove2.add(10);
        BSTWithRemove2.add(12);
        BST.remove(6);
        assertEquals(BSTWithRemove2.treeAsList(BSTWithRemove.root), BST.treeAsList(BST.root));
    }
}