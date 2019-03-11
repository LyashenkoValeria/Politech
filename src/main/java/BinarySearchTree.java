import java.sql.Array;
import java.util.*;

public class BinarySearchTree {
        public class Node {  //Узел дерева. Хранит информацию о предке, левом и правом потомках
            int key;
            Node parent;
            Node left;
            Node right;

            Node (int key) {
                this.key = key;
                this.parent = null;
                this.left = null;
                this.right = null;
            }
        }


        Node root = null;  //Корень дерева

       ArrayList<Integer> treeAsList= new ArrayList();

        public boolean search( int data ) {  //Поиск в дереве
            Node find = root;
            boolean check = false;
            while (find != null) {
                if (find.key == data) {
                    check = true;
                    break;
                }
                else if (data < find.key)  find = find.left;
                else find = find.right;
            }
            return check;
        }



        public void add(int insert) {  //Добавление в дерево
            if (search(insert) == true) throw new IllegalArgumentException(); //Пытаемся добавить в дерево существующий элемент
            Node insertNode = new Node(insert);

            treeAsList.add(insert);

            if (root == null) {  //Если дерево пустое
                root = insertNode;
            }
            else {
                Node rootSubtree = root;
                while (rootSubtree != null) {  //Проверяем наличие значения в корне
                    if (insertNode.key < rootSubtree.key)  //Если не корень поддерева не пустой, идём в левое поддерево
                        if (rootSubtree.left != null) rootSubtree = rootSubtree.left;
                        else {
                            insertNode.parent = rootSubtree;
                            rootSubtree.left = insertNode;
                            break;
                        }
                    else if (insertNode.key > rootSubtree.key)  //Если левый ребенок не пустой, идём в правое поддерево
                        if (rootSubtree.right != null) rootSubtree = rootSubtree.right;
                        else {
                            insertNode.parent = rootSubtree;
                            rootSubtree.right = insertNode;
                            break;
                        }
                }
            }
        }


        public ArrayList<Integer> neighbors (int elementKey) { //Выводит информацию о предке и потомках
            if (search(elementKey) == false) throw new IllegalArgumentException(); //Пытаемся посмотреть соседей у несуществующего элемента

            Node element = null;
            Node r = root;
            while (element == null) {
                if (elementKey == r.key) element = r;
                else if (elementKey < r.key) r = r.left;
                else r = r.right;
            }

            ArrayList<Integer> parentAndChildren = new ArrayList<Integer>();

            if (element.parent != null) parentAndChildren.add(element.parent.key);
            if (element.left != null) parentAndChildren.add(element.left.key);
            if (element.right != null) parentAndChildren.add(element.right.key);
            return parentAndChildren;
        }


        public void remove (int deleteKey){ //Удаление элемента
            if (search(deleteKey) == false) throw new IllegalArgumentException(); //Пытаемся удалить из дерева не существующий элемент

            int number = 0;
            for (int i = 0; i < treeAsList.size(); i++){
                if (treeAsList.get(i) == deleteKey) number = i;
            }

            treeAsList.remove(number);

            Node delete = null;
            Node r = root;
            while (delete == null) {
                if (deleteKey == r.key) delete = r;
                else if (deleteKey < r.key) r = r.left;
                     else r = r.right;
            }

            Node parentDelete = delete.parent;           //Случай первый. Удаляемый элемент не имеет потомков
            if (delete.left == null && delete.right == null)
                if (parentDelete.left == delete) {
                    parentDelete.left = null;
                }
                else parentDelete.right = null;


            else if  (delete.left == null || delete.right == null)  //Случай второй. Удаляемый элемент имеет одного потомка
                if (delete.left != null) {              //Если есть левый потомок
                    delete.left.parent = parentDelete;  //Родитель удаляемого элемента становится родителем левлго потомка элемента
                    if (delete == parentDelete.left)  parentDelete.left = delete.left; //Удаляемый элемент был слева
                    else parentDelete.right = delete.left; //Удаляемый элемент был справа
                }
                else {                                  //Если есть правый потомок
                    delete.right.parent = parentDelete;  //Родитель удаляемого становится родителем правого потомка
                    if (delete == parentDelete.right)  parentDelete.left = delete.right; //Удаляемый элемент был справа
                    else parentDelete.right = delete.right;           //Удаляемый элемент был слева
                }


            else if (delete.left != null && delete.right != null) { //Случай третий. Удаляемый элемент имеет двух потомков
                Node change = nextElem(delete); //Находим элемент идущий по большенству следом за удаляемым (Следующий)
                delete.key = change.key;        //Заменяем удаляемый элемент Следующим
                if (change.parent.left == change) change.parent.left = null; //"Открепляем" Следующего от его старого родителя, чтобы избежать повторения
                else change.parent.right = null;
            }


        }


        public Node nextElem(Node elem) { //Поиск следеющего элемента. Нужен для третьего случая удаления
            Node currentRoot = root;
            Node next = null;
            while (currentRoot != null) {
                next = currentRoot;
                if (currentRoot.key > elem.key) currentRoot = currentRoot.left;
                else currentRoot = currentRoot.right;
            }
            return next;
        }

}
