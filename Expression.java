package DataStructureBasic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AvlTree<T extends Comparable> {

    private Node<T> root;

    /**
     * ����˳���ӡ�������������
     */
    public void printByOrder(){
        printByOrder(root);

        System.out.println("");
    }

    /**
     * ��˳���ӡ���������������ʵ������
     * @param root
     */
    private void printByOrder(Node<T> root){
        if(root == null)    return;

        printByOrder(root.left);

        System.out.println(root.value+"("+(root.left == null?"��":root.left.value)+","+(root.right == null?"��":root.right.value)+")");

        printByOrder(root.right);
    }

    public void delete(T value){
        this.root = delete(this.root,value);
    }

    private Node<T> delete(Node<T> parent,T value){
        if(parent == null)  return null;

        System.out.println("ɾ�����뵽��"+parent.value);

        //�ҵ��˲���Ľڵ�
        if(parent.value.compareTo(value) == 0){
            //������Ϊ��
            if(parent.left == null){
                return parent.right;
            }

            //������Ϊ��
            if(parent.right == null){
                return parent.left;
            }

            //����������������Ϊ�գ��ҵ�����������Ľڵ㣬Ȼ��ɾ���ýڵ�
            Node<T> maxLeftNode = parent.left;
            Node<T> newDeletedNode = maxLeftNode;
            while(maxLeftNode != null){
                maxLeftNode = maxLeftNode.right;
                if(maxLeftNode != null)     newDeletedNode = maxLeftNode;
            }

            //�ҵ�������Ҫɾ���Ľڵ�newDeletedNode���Ƚ��ýڵ��ֵ����parent��Ȼ��ɾ������Ҫɾ���Ľڵ�
            parent.value = newDeletedNode.value;

            //�ݹ�ɾ�����µĽڵ�
            parent.left = delete(parent.left,newDeletedNode.value);

            return parent;
        }

        //������ڵ����Ҫɾ���Ľڵ㣬���뵽���ڵ��������
        if(parent.value.compareTo(value) > 0){

            parent.left = delete(parent.left,value);

        }else{//������ڵ�С��Ҫɾ���Ľڵ㣬���뵽���ڵ��������

            parent.right = delete(parent.right,value);

        }

        parent = rotate(parent);

        return parent;
    }

    public void insert(T value){
        System.out.println("��ʼ���룺"+value);
        //���û�и��ڵ�
        if(root == null){
            root = new Node(value);
            return;
        }

        root = insertNode(root,value);
    }

    /**
     * ����ڵ��ʵ������
     * @param parent ���ڵ�
     * @param value ��Ҫ�����ֵ
     * @return ���ز�����ɵ���
     */
    private Node<T> insertNode(Node<T> parent,T value){
        //������ڵ�Ϊ�գ�˵���Ѿ�����ײ���value�½ڵ�ΪҶ�ӽڵ�
        if(parent == null){
            Node<T> newNode = new Node<T>(value);
            return newNode;
        }

        if(parent.value.compareTo(value) == 0)  return parent;

        if(parent.value.compareTo(value) > 0){
            parent.left = insertNode(parent.left,value);
        }else{
            parent.right = insertNode(parent.right,value);
        }

        //���Ŷ���������ת
        parent = rotate(parent);

        System.out.println(parent.value+"  >>>  �����߶ȣ�"+parent.leftHeight()+"   �����߶ȣ�"+parent.rightHeight());

        return parent;
    }

    /**
     * ��ת���������������������������
     * @param parent
     * @return ������ת��ﵽƽ����µ���
     */
    private Node<T> rotate(Node<T> parent){

        int leftHeight = parent.leftHeight();
        int rightHeight = parent.rightHeight();

        //�������ĸ߶ȱ���������2
        if((leftHeight >= rightHeight && (leftHeight - rightHeight) >= 2)){
            Node<T> leftChild = parent.left;

            //��ڵ�����������������ߣ������ͣ���Ҫһ������
            if((leftChild.leftHeight() > leftChild.rightHeight())){

                parent = leftRotate(parent,leftChild);

            }else{//�����ͣ���Ҫһ��������һ������

                parent.left = rightRotate(parent.left,parent.left.right);
                parent = leftRotate(parent,parent.left);
            }
            //�������ĸ߶ȱ���������2
        }else if((rightHeight > leftHeight && (rightHeight - leftHeight) >=2 )){
            Node<T> rightChild = parent.right;

            //�ҽڵ�����������������ߣ������ͣ���Ҫһ������
            if(rightChild.rightHeight() > rightChild.leftHeight()){

                parent = rightRotate(parent,rightChild);

            }else{

                parent.right = leftRotate(parent.right,parent.right.left);
                parent = rightRotate(parent,parent.right);
            }
        }

        return parent;
    }

    private Node<T> leftRotate(Node<T> p1,Node<T> p2){
        Node<T> p2Right = p2.right;

        p2.right = p1;
        p1.left = p2Right;

        return p2;
    }

    private Node<T> rightRotate(Node<T> p1,Node<T> p2){
        Node<T> p2Left = p2.left;

        p2.left = p1;
        p1.right = p2Left;

        return p2;
    }

    private class Node<T extends Comparable>{
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;

        //����
        private T value;

        public Node(T value){
            this.value = value;
        }

        public int leftHeight(){
            return calculateHeight(left);
        }

        public int rightHeight(){
            return calculateHeight(right);
        }

        /**
         * ��������ĸ߶�
         * @return
         */
        public int calculateHeight(){
            return calculateHeight(this);
        }

        /**
         * �������ĸ߶�
         * @param tree
         * @return
         */
        private int calculateHeight(Node<T> tree){
            if(tree == null)    return -1;

            int leftHeight = calculateHeight(tree.left);
            int rightHeight = calculateHeight(tree.right);

            return 1+(leftHeight > rightHeight?leftHeight:rightHeight);
        }
    }

    public static void main(String[] args){
        int max = 20;
        List<Integer> values = new ArrayList<>();
        for(int i = 1; i <= max; i++){
            values.add(i);
        }
        Collections.shuffle(values);

        Integer[] testValues = {5,4,10,7,11,9,13,24,1,8};
        values = Arrays.asList(testValues);

        for(Integer value:values)
            System.out.print(value+"    ");

        System.out.println("");

        AvlTree<Integer> tree = new AvlTree<Integer>();
        for(Integer value:values)
            tree.insert(value);

        tree.printByOrder();

        System.out.println(System.currentTimeMillis()+"    "+new Date().getTime());

        System.out.println("ɾ���ڵ�");

        tree.delete(4);

        tree.printByOrder();

        tree.delete(5);

        tree.printByOrder();
    }
}