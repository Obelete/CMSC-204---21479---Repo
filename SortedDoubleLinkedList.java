
/**
 * @author Orion Belete
 * SortedDoubleLinkedList generic class which extends the BasicDoubleLinkedList generic class.
 */

import java.util.Comparator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	private Comparator<T> comparator;
	private BasicDoubleLinkedList<T>.DoubleIterator theIterator;

	SortedDoubleLinkedList(Comparator<T> comparator2) {
		super();
		this.comparator = comparator2;
	}

	/**
	 * add the list in sorted manner
	 * 
	 * @param data
	 * @return reference to current object
	 */
	public SortedDoubleLinkedList<T> add(T data) {

		theIterator = iterator();
		Node node;

		if (size <= 0) {

			size = size + 1;
			firstNode = new Node(data);
			endNode = firstNode;
			return this;
		} else {

			if (size == 1) {

				size = size + 1;
				if (comparator.compare(data, firstNode.getData()) > 0) {
					node = new Node(data, firstNode, null);
					firstNode.setNext(node);
					endNode = node;

				} else {
					node = new Node(data, null, firstNode);
					firstNode.setPrevious(node);
					firstNode = node;

				}
				return this;

			}

			theIterator.next();

			while (theIterator.current != null) {

				if (comparator.compare(data, theIterator.current.getData()) <= 0) {

					if (!theIterator.current.hasPrevious()) {
						size = size + 1;
						node = new Node(data, null, theIterator.current);
						firstNode = node;

					} else {

						size = size + 1;
						node = new Node(data, theIterator.current.getPrevious(), theIterator.current);
						theIterator.current.getPrevious().setNext(node);

					}

					theIterator.current.setPrevious(node);
					return this;

				}

				if (theIterator.hasNext() == false) {
					size = size +1;
					Node placeHolder = new Node(data, endNode, null);
					endNode.setNext(placeHolder);
					endNode = placeHolder;
					return this;

				}

				theIterator.next();

			}
		}

		return this;

	}

	/**
	 * Unsupported operation
	 */
	@Override
	SortedDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unsupported operation
	 */
	@Override
	SortedDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * a method to remove passed data
	 * @param data
	 * @param comarator
	 */
	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		super.remove(data, comparator);
		return this;
	}

}
