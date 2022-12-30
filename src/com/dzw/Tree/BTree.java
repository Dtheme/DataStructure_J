package com.dzw.Tree;
import com.dzw.LinkedList.LinkedList;

/**
 * BTree
 * BTree是完全平衡多叉查找树，所以这里不能继承BST；
 * 并且BTree是混合多叉树，因此不宜继承MyAbstractTree或实现MyTree接口
 * 当然，为了解耦性在此单独定义类
 * B树的定义(以m阶B树为例，通常m为大于3的偶数，因此B树的度数t(即m/2)为大于等于2的自然数)：
 * 1.每个节点x有下面属性：
 * a x.n表示当前存储在节点x的元素个数
 * b x.n个元素本身以非降序存放(即元素可重复，一般就是严格升序存放)
 * c x.leaf表示节点x是否叶节点(布尔类型)
 * 2.每个内部节点x(有子节点的节点)还包含(x.n+1)个指向其子节点的指针p(i)(i为1...n+1)，叶节点的p(i)为null；
 * (也就是说内部节点x必须有(x.n+1)个子节点)
 * 3.元素x.element(i)(i为1...n)对存储在各子节点中的元素范围加以分割：
 * 第i个子节点的元素总是大于(等于)其父节点的第i-1个元素而小于(等于)其父节点的第i个元素
 * 4.每个叶节点具有相同的深度，即树的高度h；
 * 5.每个节点的元素个数有上界和下界；
 * a 除根节点外每个节点(包括叶节点)至少m/2-1(向上取整)个元素；如果树非空，则根节点至少1个元素；
 * b 每个节点至多m-1个元素(即至多m个子节点)
 */
public class BTree<E extends Comparable<E>> {

    private int degree = 2;
    private int order = 2 * degree;// 阶数，通常取偶数
    private int max = order - 1;// 元素个数上限
    private int min = (int) Math.ceil(order / 2.0) - 1; // 元素个数下界，因为阶数是偶数所以其实就是degree-1
    private BTreeNode<E> root = new BTreeNode<>(); // 根节点。树都是由1个根节点构成，所有其它节点都直接或间接被根节点指向
    private int size; // 树的大小(即元素个数)

    public BTree(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

    public BTreeNode<E> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    /**
     * 查找
     *
     * @param element 查找元素
     * @return
     */
    public Result<E> search(E element) {
        return search(root, element);
    }

    private Result<E> search(BTreeNode<E> root, E element) {
        if (root == null || element == null) {
            return null;
        }
        int i = 0;
        while (i < root.n && element.compareTo(root.getElement(i)) > 0) { // 注意：数组下标从0开始
            i++;
        }
        if (i < root.n && element.compareTo(root.getElement(i)) == 0) { // 在节点中找到元素
            return new Result<>(root, i);
        } else if (root.leaf) { // 遍历完毕未找到元素
            return null;
        } else { // 在子节点中查找元素
            // 注意：BTree的实际应用中，此处是需要先将子节点元素从磁盘读取到主存
            return search(root.getNode(i), element);
        }
    }

    /**
     * 插入
     * @param element 插入新元素
     * @return
     */
    public boolean insert(E element) {
        boolean result = insert(root, element);
        if (result) {
            size++;
        }
        return result;
    }

    private boolean insert(BTreeNode<E> root, E element) {
        if (root == null || element == null) {
            return false;
        }
        if (root.n == max) { // 根节点为满而需要分裂的特殊情形
            BTreeNode<E> newNode = new BTreeNode<>(); // 新的根节点，高度增加
            this.root = newNode;
            newNode.addNode(root);
            newNode.leaf = false; // 因为子节点列表nodeList有元素，所以不是叶子节点
            split(newNode, 0);
            return insertWithoutFull(newNode, element);
        } else {
            return insertWithoutFull(root, element);
        }
    }

    /**
     * 这个方法是指向以非满节点参数root为根的树插入元素ele
     * 并不是指参数root为根的树其所有节点都非满
     *
     * @param root 根节点
     * @param element 元素
     * @return
     */
    private boolean insertWithoutFull(BTreeNode<E> root, E element) {
        if (root == null || element == null) {
            return false;
        }
        int i = root.n - 1; // 元素下标
        if (root.leaf) { // 注意：存在子节点为满的节点必须先分裂，所以必然是插入到分解后的叶子节点
            while (i >= 0 && element.compareTo(root.getElement(i)) < 0) {
                i--;
            }
            if (i < root.n - 1) {
                root.addElement(i + 1, element);
            } else {
                root.addElement(element);
            }
            root.n++;
            // 注意：BTree的实际应用中，此处需要将root的元素从主存写入到磁盘
            return true;
        } else {
            while (i >= 0 && element.compareTo(root.getElement(i)) < 0) {
                i--;
            }
            i++; // 因为子节点个数比元素个数多1
            // 注意：BTree的实际应用中，此处需要将root的下标为i的子节点的元素从磁盘读取到主存
            if (root.getNode(i).n == max) { // root节点需要分裂，root节点的该子节点是满的，并不是指root是满的
                split(root, i);
                if (element.compareTo(root.getElement(i)) > 0) { // 此步不要遗漏
                    i++;
                }
            }
            return insertWithoutFull(root.getNode(i), element);
        }
    }

    /**
     * 分裂（节点的分裂）
     * 分裂其实有不同算法，自顶而下和自底而上；
     * 本方法采用自顶而下算法，即每次插入新元素时，沿着树向下查找所属位置，
     * 依次分裂沿途每个满节点(包括叶子节点)，然后插入该元素；
     * 自底而上算法稍微复杂点，它是在找到新元素所属位置(叶子节点)后分裂该叶子节点(满的)并插入元素，
     * 因为每次分裂后其父节点元素数量就会加1，因此继续分裂父节点直至首个非满节点为止；
     * 分裂是BTree长高的唯一途径(对根节点分裂是增加BTree高度的唯一途径)
     *
     * @param target 需要分裂的节点
     * @param i 分裂位置，即需要分裂的节点的子节点下标，即该子节点是满的
     */
    private void split(BTreeNode<E> target, int i) { // 注意：总是 i <= target.n
        BTreeNode<E> leftNode = target.getNode(i);
        BTreeNode<E> rightNode = new BTreeNode<>();
        rightNode.leaf = leftNode.leaf;
        rightNode.n = min; // 分裂后的两个子节点的元素个数其实就是BTree允许的最小元素个数

        // 处理分裂后的新增子节点以及原子节点
        for (int j = 0; j < min; j++) { // 通常 min = degree - 1
            // 注意：这里不能使用add(i, e)方法添加元素
            rightNode.addElement(leftNode.getElement(j + min + 1));
        }
        if (!leftNode.leaf) {
            for (int j = 0; j < min + 1; j++) {
                rightNode.addNode(leftNode.getNode(j + min + 1));
            }
        }

        // 处理需要分裂的节点
        if (i == target.n) { // target的下标target.n的子节点(即最右边子节点)是满的
            // 元素及子节点不需要移位
            target.addElement(leftNode.getElement(min));
            target.addNode(rightNode);
        } else { // target的下标i(0,1,2,...,target.n-1)的子节点是满的
            // 元素及子节点需要移位
            target.addElement(i, leftNode.getElement(min));
            target.addNode(i + 1, rightNode);
        }
        target.n++;

        // 处理分裂后的原子节点
        for (int j = 0; j < min + 1; j++) { // 提升到父节点的元素也会被删除
            leftNode.removeLastElement();
        }
        if (!leftNode.leaf) {
            for (int j = 0; j < min + 1; j++) {
                leftNode.removeLastNode();
            }
        }
        leftNode.n = min;
        // 注意：BTree的实际应用中，此处需要将target、leftNode、rightNode的元素从主存写入
    }

    /**
     * 删除
     *
     * @param element
     * @return
     */
    public boolean delete(E element) {
        Result<E> result = search(element);
        if (result == null) {
            return false;
        }
        delete(root, element);
        size--;
        return true;
    }

    /**
     * 在参数root为根节点代表的树中删除元素element(element在树中)
     *
     * @param root
     * @param element
     * @return
     */
    private void delete(BTreeNode<E> root, E element) {
        int i = 0;
        while (i < root.n && element.compareTo(root.getElement(i)) > 0) { // 注意：数组下标从0开始
            i++;
        }
        if (i < root.n && element.compareTo(root.getElement(i)) == 0) { // element在当前节点中(即在当前节点的元素列表中)
            if (root.leaf) { // 情形1
                root.elementsList.remove(i);
                root.n--;
            } else { // 情形2
                BTreeNode<E> leftNode = root.getNode(i);
                BTreeNode<E> rightNode = root.getNode(i + 1);
                if (leftNode.n > min) { // 情形2a
                    E newElement = leftNode.elementsList.getLast();
                    root.elementsList.remove(i);
                    root.addElement(newElement);
                    delete(leftNode, newElement);
                } else if (rightNode.n > min) { // 情形2b
                    // 情形2b与情形2a是对称情形
                    E newElement = rightNode.elementsList.getFirst();
                    root.elementsList.remove(i);
                    root.addElement(newElement);
                    delete(rightNode, newElement);
                } else { // 情形2c
                    leftNode.addElement(element);
                    leftNode.n++;
                    for (int j = 0; j < min; j++) {
                        leftNode.addElement(rightNode.getElement(j));
                        leftNode.n++;
                    }
                    if (!leftNode.leaf) {
                        for (int j = 0; j < min + 1; j++) {
                            leftNode.addNode(rightNode.getNode(j));
                        }
                    }
                    root.elementsList.remove(i);
                    root.nodeList.remove(i + 1);
                    root.n--;
                    if (root.n == 0) { // 说明入参root是根节点
                        // 树的高度缩减1
                        this.root = leftNode;
                    }
                    delete(leftNode, element);
                }
            }
        } else { // 元素在当前节点下标为i的子树中
            BTreeNode<E> childNode = root.getNode(i);
            if (childNode.n == min) { // 情形3
                // 不论元素是在childNode中还是在childNode的子树中，总是保证本次迭代后childNode至少min+1个元素
                if (i > 0 && root.getNode(i - 1).n > min) { // 情形3a
                    // childNode的左邻兄弟节点有可借元素
                    BTreeNode<E> leftNode = root.getNode(i - 1);
                    childNode.elementsList.addFirst(root.getElement(i - 1));
                    childNode.n++;
                    root.elementsList.set(i - 1, leftNode.elementsList.removeLast());
                    leftNode.n--;
                    if (!childNode.leaf) { // 左邻兄弟节点的最后子节点也要借过去
                        childNode.nodeList.addFirst(leftNode.nodeList.removeLast());
                    }
                    delete(childNode, element);
                } else if (i < root.n && root.getNode(i + 1).n > min) { // 情形3b
                    // childNode的右邻兄弟节点有可借元素

                    // 情形3b情形3a是对称情形
                    BTreeNode<E> rightNode = root.getNode(i + 1);
                    childNode.elementsList.addLast(root.getElement(i));
                    childNode.n++;
                    root.elementsList.set(i, rightNode.elementsList.removeFirst());
                    rightNode.n--;
                    if (!childNode.leaf) {// 右邻兄弟节点的第一个子节点也要借过去
                        childNode.nodeList.addLast(rightNode.nodeList.removeFirst());
                    }
                    delete(childNode, element);
                } else { // 情形3c
                    // childNode的相邻兄弟节点无可借元素(注意：肯定有相邻兄弟节点)
                    // 当前节点要么是根节点，要么元素个数必然大于min
                    // 当前节点不可能是元素个数为min的非根节点的内部节点，因为前面已经保证了元素个数至少min+1
                    if (i > 0) { // childNode的左邻兄弟节点无可借元素
                        BTreeNode<E> leftNode = root.getNode(i - 1);
                        childNode.elementsList.addFirst(root.elementsList.remove(i - 1));
                        root.n--;
                        childNode.n++;
                        for (int j = min - 1; j >= 0; j--) {
                            childNode.elementsList.addFirst(leftNode.elementsList.get(j));
                            childNode.n++;
                        }
                        if (!childNode.leaf) {
                            for (int j = min; j >= 0; j--) {
                                childNode.nodeList.addFirst(leftNode.nodeList.get(j));
                            }
                        }
                        root.nodeList.remove(i - 1);
                    } else if (i < root.n) {  // childNode的右邻兄弟节点无可借元素
                        BTreeNode<E> rightNode = root.getNode(i + 1);
                        childNode.elementsList.addLast(root.elementsList.remove(i));
                        root.n--;
                        childNode.n++;
                        for (int j = 0; j <= min - 1; j++) {
                            childNode.elementsList.addLast(rightNode.elementsList.get(j));
                            childNode.n++;
                        }
                        if (!childNode.leaf) {
                            for (int j = 0; j <= min; j++) {
                                childNode.nodeList.addLast(rightNode.nodeList.get(j));
                            }
                        }
                        root.nodeList.remove(i + 1);
                    }
                    if (root.n == 0) { // 说明入参root是根节点
                        // 树的高度缩减1
                        this.root = childNode;
                    }
                    delete(childNode, element);
                }
            } else {
                delete(childNode, element);
            }
        }
    }

    public void widthOrder() {
        widthOrder(root);
    }

    /**
     * 广度优先遍历（层序遍历）
     *
     * @param root
     */
    private void widthOrder(BTreeNode<E> root) {
        throw new UnsupportedOperationException("要想支持广度遍历需要增加节点属性");
    }

    public void preOrder() {
        System.out.println("===前序遍历：===");
        System.out.print("\t");
        preOrder(root);
        System.out.println("\n=====完毕=====");
    }

    /**
     * 前序遍历
     * 深度遍历与前序遍历相同，亦即深度遍历
     *
     * @param root
     */
    private void preOrder(BTreeNode<E> root) {
        for (int i = 0; i < root.n; i++) {
            System.out.print(root.getElement(i) + " ");
        }
        if (!root.leaf) {
            for (int i = 0; i <= root.n; i++) {
                preOrder(root.getNode(i));
            }
        }
    }

    public void inOrder() {
        System.out.println("===中序遍历：===");
        System.out.print("\t");
        inOrder(root);
        System.out.println("\n=====完毕=====");
    }

    /**
     * 中序遍历
     * 中序遍历可以排序
     *
     * @param root
     */
    private void inOrder(BTreeNode<E> root) {
        boolean isNotLeaf = !root.leaf;
        if (isNotLeaf) {
            inOrder(root.getNode(0));
        }
        for (int i = 0; i < root.n; i++) {
            System.out.print(root.getElement(i) + " ");
            if (isNotLeaf) {
                inOrder(root.getNode(i + 1));
            }
        }
    }

    public void postOrder() {
        System.out.println("===后序遍历：===");
        System.out.print("\t");
        postOrder(root);
        System.out.println("\n=====完毕=====");
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    private void postOrder(BTreeNode<E> root) {
        boolean isNotLeaf = !root.leaf;
        if (isNotLeaf) {
            postOrder(root.getNode(0));
        }
        for (int i = 0; i < root.n; i++) {
            if (isNotLeaf) {
                postOrder(root.getNode(i + 1));
            }
            System.out.print(root.getElement(i) + " ");
        }
    }

    /**
     * 表示元素位置的类
     * 类是public权限但构造方法私有化并且仅提供公有getter方法，是为让其它类仅仅只可以获取该类的属性
     *
     * @param <E>
     */
    public static class Result<E extends Comparable<E>> {
        private BTreeNode<E> node;
        private int index;

        private Result(BTreeNode<E> node, int index) {
            this.node = node;
            this.index = index;
        }

        public BTreeNode<E> getNode() {
            return node;
        }

        public int getIndex() {
            return index;
        }
    }

    /**
     * BTree的节点类
     * 类是public权限但构造方法私有化并且仅提供公有getter方法，是为让其它类仅仅只可以获取该类的属性
     *
     * @param <E>
     */
    public static class BTreeNode<E extends Comparable<E>> {
        private int n; // 元素个数
        private boolean leaf = true; // 是否叶子节点
        private LinkedList<E> elementsList = new LinkedList<>(); // 元素列表
        private LinkedList<BTreeNode<E>> nodeList = new LinkedList<>(); // 子节点列表

        private BTreeNode() {
        }

        public boolean isLeaf() {
            return leaf;
        }

        public LinkedList<E> getElementsList() {
            return elementsList;
        }

        public LinkedList<BTreeNode<E>> getNodeList() {
            return nodeList;
        }

        public void addElement(E element) {
            elementsList.add(element);
        }

        public void addElement(int index, E element) {
            elementsList.add(index, element);
        }

        public void addNode(BTreeNode<E> node) {
            nodeList.add(node);
        }

        public void addNode(int index, BTreeNode<E> node) {
            nodeList.add(index, node);
        }

        public E getElement(int index) {
            return elementsList.get(index);
        }

        public BTreeNode<E> getNode(int index) {
            return nodeList.get(index);
        }

        public void removeLastElement() {
            elementsList.removeLast();
        }

        public void removeLastNode() {
            nodeList.removeLast();
        }
    }
}
