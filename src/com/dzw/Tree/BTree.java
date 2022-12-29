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
@SuppressWarnings("unused")
public class BTree<E extends Comparable<E>> {

    private int degree = 2;
    // 阶数，通常取偶数
    private final int order = 2 * degree;
    // 元素个数上限
    private final int max = order - 1;
    private final int min = (int) Math.ceil(order / 2.0) - 1; // 元素个数下界，因为阶数是偶数所以其实就是degree-1
    private final BTreeNode<E> root = new BTreeNode<>(); // 根节点。树都是由1个根节点构成，所有其它节点都直接或间接被根节点指向
    private int size; // 树的整体大小(即元素个数)

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
     * 插入
     * @param element 插入新元素
     * @return
     */
    public boolean insert(E element) {
        return true;
    }

    private boolean insert(BTreeNode<E> root, E element) {
        return true;
    }

    private boolean insertWithoutFull(BTreeNode<E> root, E element) {
        return true;
    }


    private void split(BTreeNode<E> target, int i) {

    }

    public boolean delete(E element) {
        return true;
    }

    private void delete(BTreeNode<E> root, E element) {
    }

    public void preOrder() {
    }

    /**
     * 前序遍历
     * 深度优先遍历与前序遍历相同，深度遍历
     * @param root
     */
    private void preOrder(BTreeNode<E> root) {

    }

    public void inOrder() {

    }
    /**
     * 中序遍历
     * 中序遍历可以排序
     * @param root 根节点
     */
    private void inOrder(BTreeNode<E> root) {

    }

    public void postOrder() {
    }

    /**
     * 后序遍历
     * @param root 根节点
     */
    private void postOrder(BTreeNode<E> root) {

    }

    /**
     * 表示元素位置的类
     * 类是public权限但构造方法私有化并且仅提供公有getter方法，是为让其它类仅仅只可以获取该类的属性
     * @param <U>
     */
    public static class Result<U extends Comparable<U>> {

    }

    /**
     * BTree的节点
     * 同样只对外公开element 节点不开放给外部使用
     * @param <E> 节点元素
     */
    public static class BTreeNode<E extends Comparable<E>> {
        private int n; // 元素个数
        private LinkedList<E> elementsList = new LinkedList<>(); // 元素列表
        private LinkedList<BTreeNode<E>> nodeList = new LinkedList<>(); // 子节点列表

        public boolean isLeaf() {
            // 是否叶子节点
            boolean leaf = true;
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
