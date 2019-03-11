import org.junit.Before;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.Array;
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

    public boolean compare (ArrayList<Integer> tree1, List<Integer> tree2){
        int count = 0;
        if (tree1.size() != tree2.size()) return false;
        else {
            for (int i = 0; i < tree1.size(); i++) {
                if (tree1.get(i) == tree2.get(i)) count++;
            }
            if (count == tree1.size()) return true;
            else return false;
        }
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

        BinarySearchTree BST2 = new BinarySearchTree();
        BST2.add(3);
        BST2.add(8);
        BST2.add(1);
        BST2.add(6);
        BST2.add(4);
        BST2.add(7);
        BST2.add(10);
        BST2.add(14);
        BST2.add(13);
        BST2.add(16);

        BST.add(16);
        assertThrows(IllegalArgumentException.class, new Executable() {
            public void execute() throws Throwable {
                BST.add(1);
            }
        });
        assertTrue(compare(BST1.treeAsList, BST.treeAsList));
        assertFalse(compare(BST2.treeAsList, BST.treeAsList));
    }

    @org.junit.Test
    public void neighbors() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            public void execute() throws Throwable {
                BST.neighbors(20);
            }
        });
        ArrayList<Integer> check = new ArrayList<Integer>();
        check.add(3);
        assertTrue(compare(BST.neighbors(1), check));
        assertFalse(compare(BST.neighbors(8), check));
    }

    @org.junit.Test
    public void remove() {
        BinarySearchTree BSTWithRemove = new BinarySearchTree();
        BSTWithRemove.add(8);
        BSTWithRemove.add(3);
        BSTWithRemove.add(10);
        BSTWithRemove.add(14);
        BSTWithRemove.add(1);
        BSTWithRemove.add(6);
        BSTWithRemove.add(4);
        BSTWithRemove.add(7);
        BST.remove(13);
        BST.add(12);
        BST.remove(14);
        BST.remove(6);
        BST.add(15);

        assertTrue(compare(BSTWithRemove.treeAsList, BST.treeAsList));

        BinarySearchTree otherBST = new BinarySearchTree();
        otherBST.add(8);
        assertFalse(compare(otherBST.treeAsList, BST.treeAsList));
    }
}