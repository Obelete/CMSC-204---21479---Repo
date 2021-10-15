/**
 * BasicDoubleLinkedList generic class which extends Object
 * @author Orion Belete
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;




public class BasicDoubleLinkedList<T> extends Object {

	protected Node firstNode;
	protected Node endNode;
	protected int size;
	private DoubleIterator doubleIterator;

	/**
	 * This is the constructor
	 */

	BasicDoubleLinkedList() {
		firstNode = null;
		endNode = null;
		size = 0;
	}

	/**
	 * Get the size of the list.
	 * @return the size
	 */

	public int getSize() {
		return size;
	}

	/**
	 * Add an element to the end of the list.
	 * @param data
	 * @return the reference to current object
	 */

	BasicDoubleLinkedList<T> addToEnd(T data) {

		size = size + 1;
		if (endNode != null) {
			Node placeHolder = new Node(data, endNode, null);
			endNode.setNext(placeHolder);
			endNode = placeHolder;
		} else {
			firstNode = new Node(data);
			endNode = firstNode;
		}

		return this;

	}

	/**
	 * Add an element to the front of the list.
	 * @param data
	 * @return the reference to current object
	 */

	BasicDoubleLinkedList<T> addToFront(T data) {

		size = size + 1;
		if (firstNode != null) {
			Node placeHolder = new Node(data, null, firstNode);
			firstNode.setPrevious(placeHolder);
			firstNode = placeHolder;
		} else {
			firstNode = new Node(data);
			endNode = firstNode;
		}

		return this;

	}

	/**
	 * method to get the first node of the list.
	 * @return data element
	 * @throw NoSuchElementException when there is no first element
	 */

	public T getFirst() throws NoSuchElementException {
		if (firstNode != null) {
			return firstNode.getData();
		} else {
			return null; // throw new NoSuchElementException()
		}
	}

	/**
	 * method to get the last node of the list.
	 * @return data element
	 */

	public T getLast() {
		if (firstNode != null) {
			return endNode.getData();
		} else {
			return null; // throw new NoSuchElementException()
		}
	}

	/**
	 * required inner iterator class that implements ListIterator
	 */

	public class DoubleIterator implements ListIterator<T> {

		protected BasicDoubleLinkedList<T>.Node current;
		private boolean isTail = false;
		private boolean isHead = false;

		DoubleIterator() {
			current = null;
		}

		/**
		 * method to check if list has next element
		 * @return true or false
		 */

		@Override
		public boolean hasNext() {
			if (current != endNode && !isTail) {
				return true;
			}
			else {
				return false;
			}
		}

		/**
		 * method to iterate to the next element
		 * @return next data element
		 */

		@Override
		public T next() throws NoSuchElementException {

			if (size == 0) { // The list is empty
				throw new NoSuchElementException();
			}

			else if (isTail) {
				throw new NoSuchElementException();
			}

			else if (current == null) { // Before first (null)
				current = firstNode;
				return current.getData();
			}

			else if (current.hasNext()) { // Before another element
				current = current.getNext();
				return current.getData();
			} else {
				throw new NoSuchElementException();
			}

		}

		/**
		 * method to check if list has previous element
		 * @return true or false
		 */

		@Override
		public boolean hasPrevious() {
			return !(current == null && !isTail);
		}

		/**
		 * Iterate to the iterator's previous value.
		 * @return previous data element
		 */

		@Override
		public T previous() throws NoSuchElementException {

			T placeHolder;

			if (size == 0) {
				throw new NoSuchElementException();
			}

			else if (current == null && isTail) {
				current = endNode;
				return current.getData();
			} else if (current == null && !isTail) {
				throw new NoSuchElementException();
			}

			if (current.hasPrevious()) {
				isTail = false;
				current = current.getPrevious();
				return current.getNext().getData();
			} else {
				if (current != null) {
					placeHolder = current.getData();
					current = null;
					return placeHolder;
				}
			}

			throw new RuntimeException();
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(Object arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}

	/**
	 * method to call inner iterator class above
	 * @return DoubleIterator
	 */

	public DoubleIterator iterator() throws NoSuchElementException, UnsupportedOperationException {
		return new DoubleIterator();
	}


	/**
	 * Removes the first node of targetData.
	 * 
	 * @param targetData
	 * @param comparator
	 * @return data element
	 */

	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {

		Node placeHolder;

		if (size == 1) {
			if (comparator.compare(firstNode.getData(), targetData) == 0) {
				firstNode = null;
				endNode = null;
				size = size - 1;
			}
			return this;
		} else if (size == 0) {
			return this;
		}

		placeHolder = firstNode;

		do {

			if (comparator.compare(placeHolder.getData(), targetData) == 0) {

				size = size - 1;
				if (size == 1) {
					size = 0;
					firstNode = null;
					endNode = null;
					size = size - 1;
				}

				if (placeHolder.equals(endNode)) {
					endNode = endNode.getPrevious();
					endNode.setNext(null);
				} else if (placeHolder.equals(firstNode)) {

					firstNode = firstNode.getNext();
					firstNode.setPrevious(null);
				} else {
					placeHolder.getPrevious().setNext(placeHolder.getNext());
					placeHolder.getNext().setPrevious(placeHolder.getPrevious());
				}

			}

			placeHolder = placeHolder.getNext();

		} while (placeHolder != null);

		return this;

	}

	/**
	 * method to remove and return the first element from the list
	 * @return data element
	 */

	public T retrieveFirstElement() {

		T dataElement;
		if (firstNode == null) {
			dataElement = null;
		} else {
			dataElement = firstNode.getData();
		}

		if (size <= 0) {
			firstNode = null;
			endNode = null;

		} else {
			firstNode = firstNode.getNext();
			firstNode.setPrevious(null);
		}

		if (size == 0) {
			size = 0;
		} else {
			size = size - 1;
		}

		return dataElement;

	}

	/**
	 * method to remove and return last element
	 * @return last element
	 **/

	public T retrieveLastElement() {

		T dataElement;
		if (endNode == null) {
			dataElement = null;
		} else {
			dataElement = endNode.getData();
		}
		endNode = endNode.getPrevious();
		if (size == 0) {
			size = 0;
		} else {
			size = size - 1;
		}

		return dataElement;

	}

	/**
	 * method to convert into an arraylist.
	 * @return the arraylist
	 */

	public ArrayList<T> toArrayList() {

		ArrayList<T> list = new ArrayList<T>();
		doubleIterator = iterator();

		do {
			doubleIterator.next();
			list.add(doubleIterator.current.getData());
		} while (doubleIterator.hasNext());

		return list;

	}

	class Node {
		private Node previous;
		private Node next;
		private T data;
		

		/**
		 * Constructor for linked node passed
		 * @param paramData        data of the node
		 * @param previous reference to previous node
		 * @param next     reference to next node
		 */

		Node(T paramData, Node previous, Node next) {
			this.data = paramData;
			this.previous = previous;
			this.next = next;
		}

		/**
		 * Constructor for an unlinked node.
		 * @param paramData data of the node
		 */

		Node(T paramData) {
			this.data = paramData;
			this.previous = null;
			this.next = null;
		}


		/**
		 * node data setter method
		 */

		public void setData(T paramData) {
			this.data = paramData;
		}

		/**
		 * node data getter method
		 * @return data data of node
		 */

		public T getData() {
			return data;
		}


		/**
		 * point reference to next node
		 */

		public void setNext(Node node) {
			this.next = node;
		}

		/**
		 * Get the next node pointer.
		 * @return next pointer
		 */

		public Node getNext() {
			return next;
		}

		/**
		 * check if the node has next
		 * @return true or false
		 */

		public boolean hasNext() {
			if (next == null) {
				return false; 
			}
			else{ 
				return true;
			}
		}

		// Previous operations

		/**
		 * set pointer to the previous node.
		 */

		public void setPrevious(Node node) {
			this.previous = node;
		}

		/**
		 * Get the the previous node pointer
		 * @return previous 
		 */

		public Node getPrevious() {
			return previous;
		}

		/**
		 * Check if the node has previous
		 * @return true or false
		 */

		public boolean hasPrevious() {
			if (previous == null) {
				return false;
			}
			else {
				return true;
			}
		}

	}

}