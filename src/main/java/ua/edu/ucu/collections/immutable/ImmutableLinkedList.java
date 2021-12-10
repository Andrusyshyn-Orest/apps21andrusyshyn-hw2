package ua.edu.ucu.collections.immutable;

public final class ImmutableLinkedList implements ImmutableList {
    private final Node head;
    private final Node tail;
    private final int length;

    public ImmutableLinkedList(Object[] elements) {
        this.length = elements.length;
        if (elements.length == 0) {
            this.head = null;
            this.tail = null;
            return;
        }

        Node myHead;
        Node myTail;
        if (elements.length == 1) {
            myHead = new Node();
            myHead.setValue(elements[0]);
            myTail = myHead;
        }
        else {
            myHead = new Node();
            myHead.setValue(elements[0]);
            myHead.setNext(new Node());
            Node tempNode = myHead;
            Node currNode = myHead.getNext();
            for (int i = 1; i < this.length - 1; i++) {
                currNode.setValue(elements[i]);
                currNode.setPrevious(tempNode);
                currNode.setNext(new Node());
                tempNode = currNode;
                currNode = currNode.getNext();
            }

            currNode.setPrevious(tempNode);
            currNode.setValue(elements[this.length-1]);
            myTail = currNode;
        }
        this.head = myHead;
        this.tail = myTail;
    }

    public ImmutableLinkedList(Node node) {
        StartFinish startFinish = this.copyChain(node);
        this.head = startFinish.getStart();
        this.tail = startFinish.getFinish();
        this.length = startFinish.getLength();
//        Node currNode = node;
//        this.head = new Node();
//        this.length= 0;
//        if (node.getNext() == null){
//            this.head.setValue(currNode.getValue());
//            this.length = 1;
//            this.tail = this.head;
//        }
//        else{
//            Node actualNode = this.head;
//            Node tempNode = this.head;
//            while (currNode.getNext() != null){
//                this.length++;
//                actualNode.setValue(currNode.getValue());
//                actualNode.setNext(new Node());
//
//                if (currNode.getPrevious() != null){
//                    actualNode.setPrevious(tempNode);
//                    tempNode = actualNode;
//                }
//
//                actualNode = actualNode.getNext();
//                currNode = currNode.getNext();
//            }
//            actualNode.setPrevious(tempNode);
//            actualNode.setValue(currNode.getValue());
//            this.tail = actualNode;
//            this.length++;
//        }
    }

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    private StartFinish copyChain(Node node) {
        Node currNode = node;
        Node chainHead = new Node();
        Node chainTail;                               // new Node();
        int chainLength = 0;
        if (node.getNext() == null) {
            chainHead.setValue(currNode.getValue());
            chainTail = chainHead;
            chainLength = 1;
            return new StartFinish(chainHead, chainTail, chainLength);

        } else {
            Node actualNode = chainHead;
            Node tempNode = chainHead;
            while (currNode.getNext() != null) {
                chainLength++;
                actualNode.setValue(currNode.getValue());
                actualNode.setNext(new Node());

                if (currNode.getPrevious() != null) {
                    actualNode.setPrevious(tempNode);
                    tempNode = actualNode;
                }

                actualNode = actualNode.getNext();
                currNode = currNode.getNext();
            }
            actualNode.setPrevious(tempNode);
            actualNode.setValue(currNode.getValue());
            chainTail = actualNode;
            chainLength++;
            return new StartFinish(chainHead, chainTail, chainLength);
        }
    }



    @Override
    public ImmutableLinkedList add(Object e) {
        if (this.isEmpty()) {
            Node newHead = new Node();
            newHead.setValue(e);
            return new ImmutableLinkedList(newHead);
        }

        StartFinish startFinish = copyChain(this.head);
        Node newHead = startFinish.getStart();
        Node newTail = startFinish.getFinish();
        newTail.setNext(new Node());
        Node newLast = newTail.getNext();
        newLast.setValue(e);
        newLast.setPrevious(newTail);
        return new ImmutableLinkedList(newHead);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        if (!((0 <= index) && (index <= this.length))) {
            throw new IllegalArgumentException();
        }

        if (index == 0) {
            return this.addFirst(e);
        }
        if (index == length) {
            return this.addLast(e);
        }
        StartFinish startFinish = copyChain(this.head);
        Node newHead = startFinish.getStart();
        Node currNode = findByIndex(index, newHead);
        Node newNode = new Node();
        newNode.setValue(e);
        Node previousNode = currNode.getPrevious();
        previousNode.setNext(newNode);
        currNode.setPrevious(newNode);
        newNode.setNext(currNode);
        newNode.setPrevious(previousNode);
        return new ImmutableLinkedList(newHead);
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        if (this.isEmpty()) {
            return new ImmutableLinkedList(c);
        }

        StartFinish startFinish = copyChain(this.head);
        Node newHead = startFinish.getStart();
        Node newTail = startFinish.getFinish();
        Node currNode = newTail;
        Node tempNode;
        for (int i = 0; i < c.length; i++) {
            currNode.setNext(new Node());
            tempNode = currNode;
            currNode = currNode.getNext();
            currNode.setValue(c[i]);
            currNode.setPrevious(tempNode);
        }

        return new ImmutableLinkedList(newHead);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        if (!((0 <= index) && (index <= this.length))) {
            throw new IllegalArgumentException();
        }

        if (c.length == 0) {
            if (this.isEmpty()) {
                return new ImmutableLinkedList();
            }
            else {
                return new ImmutableLinkedList(this.head);
            }
        }

        if (index == length) {
            return this.addAll(c);
        }

        StartFinish startFinish = copyChain(this.head);
        boolean zeroIndexFlag = false;
        Node newHead = startFinish.getStart();
        Node currNode = findByIndex(index, newHead);
        Node previousNode;
        if (currNode.getPrevious() == null) {
            zeroIndexFlag = true;
            previousNode = new Node();
        }
        else {
            previousNode = currNode.getPrevious();
        }
        ImmutableLinkedList insertChain = new ImmutableLinkedList(c);
        Node insertHead = insertChain.getHead();
        previousNode.setNext(insertHead);
        insertHead.setPrevious(previousNode);

        Node insertTail = findTail(insertHead);
        currNode.setPrevious(insertTail);
        insertTail.setNext(currNode);

        if (zeroIndexFlag) {
            newHead = insertHead;
            previousNode.setNext(null);
            insertHead.setPrevious(null);
        }
        return new ImmutableLinkedList(newHead);
    }

    private Node findTail(Node chainHead) {
        Node curr = chainHead;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        return curr;
    }

    private Node findByIndex(int index, Node startNode) {
        Node currNode = startNode;
        for (int i = 0; i < index; i++) {
            currNode = currNode.getNext();
        }
        return currNode;
    }

    @Override
    public Object get(int index) {
        if (!((0 <= index) && (index < this.length))) {
            throw new IllegalArgumentException();
        }
        Node currNode = findByIndex(index, this.head);
        return currNode.getValue();
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        if (!((0 <= index) && (index < this.length))) {
            throw new IllegalArgumentException();
        }

        if (index == 0) {
            return this.removeFirst();
        }

        if (index == this.length-1) {
            return this.removeLast();
        }

        StartFinish startFinish = copyChain(this.head);
        Node newHead = startFinish.getStart();
        Node currNode = findByIndex(index, newHead);
        Node previousNode = currNode.getPrevious();
        Node nextNode = currNode.getNext();
        previousNode.setNext(nextNode);
        nextNode.setPrevious(previousNode);
        currNode.setNext(null);
        currNode.setNext(null);
        return new ImmutableLinkedList(newHead);
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        if (!((0 <= index) && (index < this.length))) {
            throw new IllegalArgumentException();
        }
        StartFinish startFinish = copyChain(this.head);
        Node newHead = startFinish.getStart();
        Node currNode = findByIndex(index, newHead);
        currNode.setValue(e);
        return new ImmutableLinkedList(newHead);
    }

    @Override
    public int indexOf(Object e) {
        Node currNode = this.head;
        for (int i = 0; i < this.length; i++) {
            if (currNode.getValue().equals(e)) {
                return i;
            }
            currNode = currNode.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return (this.length == 0);
    }

    @Override
    public Object[] toArray() {
        Node currNode = this.head;
        int ind = 0;
        Object [] array = new Object[this.length];
        while (currNode != null) {
            array[ind] = currNode.getValue();
            ind++;
            currNode = currNode.getNext();
        }
        return array;
    }

    public ImmutableLinkedList addFirst(Object e) {
        if (this.isEmpty()) {
            Node newHead = new Node();
            newHead.setValue(e);
            return new ImmutableLinkedList(newHead);
        }
        StartFinish startFinish = copyChain(this.head);
        Node newHead = startFinish.getStart();
        newHead.setPrevious(new Node());
        Node newFirst = newHead.getPrevious();
        newFirst.setValue(e);
        newFirst.setNext(newHead);
        return new ImmutableLinkedList(newFirst);
    }

    public ImmutableLinkedList addLast(Object e) {
        return this.add(e);
    }

    public Node getHead() {
        if (isEmpty()) {
            return null;
        }
        StartFinish startFinish = copyChain(this.head);
        return startFinish.getStart();
    }

    public Node getTail() {
        if (isEmpty()) {
            return null;
        }
        StartFinish startFinish = copyChain(this.head);
        return startFinish.getFinish();
    }

    public Object getFirst() {
        if (isEmpty()) {
            return null;
        }
        return this.head.getValue();
    }

    public Object getLast() {
        if (isEmpty()) {
            return null;
        }
        return this.tail.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        if (this.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (length == 1) {
            return new ImmutableLinkedList();
        }
        StartFinish startFinish = copyChain(this.head);
        Node oldHead = startFinish.getStart();
        Node newHead = oldHead.getNext();
        newHead.setPrevious(null);
        oldHead.setNext(null);
        return new ImmutableLinkedList(newHead);
    }

    public ImmutableLinkedList removeLast() {
        if (this.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (length == 1) {
            return new ImmutableLinkedList();
        }
        StartFinish startFinish = copyChain(this.head);
        Node oldTail = startFinish.getFinish();
        Node newTail = oldTail.getPrevious();
        newTail.setNext(null);
        oldTail.setPrevious(null);
        Node newHead = startFinish.getStart();
        return new ImmutableLinkedList(newHead);
    }

    private static class StartFinish {
        private Node start;
        private Node finish;
        private int length;
        StartFinish(Node start, Node finish, int length) {
            this.start = start;
            this.finish = finish;
            this.length = length;
        }

        public Node getStart() {
            return start;
        }

        public Node getFinish() {
            return finish;
        }

        public int getLength() {
            return length;
        }
    }
}
