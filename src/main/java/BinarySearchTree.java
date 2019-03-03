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
            Node insertNode = new Node(insert);
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


        public void neighbors (int elementKey) { //Выводит информацию о предке и потомках
            Node element = new Node(elementKey);
            if (element.parent != null) System.out.println("Parent = "  + element.parent);
            if (element.left != null) System.out.println("Left child = " + element.left);
            if (element.right != null) System.out.println("Right child = " + element.right);
        }


        public void remove (int deleteKey){ //Удаление элемента
            Node delete = new Node(deleteKey);
            Node parentDelete = delete.parent;           //Случай первый. Удаляемый элемент не имеет потомков
            if (delete.left == null && delete.right == null)
                if (parentDelete.left == delete) parentDelete.left = null;
                else parentDelete.right = null;


            if  (delete.left == null || delete.right == null)  //Случай второй. Удаляемый элемент имеет одного потомка
                if (delete.left != null) {              //Если есть левый потомок
                    delete.left.parent = parentDelete;  //Родитель удаляемого элемента становится родителем левлго потомка элемента
                    if (delete == parentDelete.left)  parentDelete.left = delete.left; //Удаляемый элемент был слева
                    else parentDelete.right = delete.left; //Удаляемый элемент был справа
                }
                else {                                  //Если есть правый потомок
                    delete.right.parent = parentDelete;//Родитель удаляемого становится родителем правого потомка
                    if (delete == parentDelete.right)  parentDelete.left = delete.right; //Удаляемый элемент был справа
                    else parentDelete.right = delete.right;           //Удаляемый элемент был слева
                }


            if (delete.left != null && delete.right != null) { //Случай третий. Удаляемый элемент имеет двух потомков
                Node change = nextElem(delete); //Находим элемент идущий по большенству следом за удаляемым (Следующий)
                delete.key = change.key;        //Заменяем удаляемый элемент Следующим
                if (change.parent.left == change) change.parent.left = null; //"Открепляем" Следующего от его старого родителя, чтобы избежать повторения
                else change.parent.right = null;
            }
        }


        public Node nextElem(Node elem) { //Поиск следеющего эелемента. Нужен для третьего случая удаления
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
