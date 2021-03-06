package com.bitbucket.mathiasj33.gad.blatt03;

/**
 * Die Klasse RingQueue soll eine zirkuläre Warteschlange auf Basis der Klasse
 * {@link DynamicArray} implementieren.
 */
public class RingQueue {
	private DynamicArray dynArr;

	private int size;

	private int from;

	private int to;

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Dieser Konstruktor erzeugt eine neue Ringschlange. Ein leere Ringschlange
	 * habe stets eine Größe von 0, sowie auf 0 gesetzte Objektvariablen to und
	 * from.
	 * 
	 * @param growthFactor
	 *            der Wachstumsfaktor des zugrundeliegenden dynamischen Feldes
	 * @param maxOverhead
	 *            der maximale Overhead des zugrundeliegenden dynamischen Feldes
	 */
	public RingQueue(int growthFactor, int maxOverhead) {
		dynArr = new DynamicArray(growthFactor, maxOverhead);
		size = 0;
		from = 0;
		to = 0;
	}

	/**
	 * Diese Methode reiht ein Element in die Schlange ein.
	 * 
	 * @param value
	 *            der einzufügende Wert
	 */
	public void enqueue(int value) {
		Interval interval = isEmpty() ? new EmptyInterval() : new NonEmptyInterval(from, to);
		size++;
		Interval newInterval = dynArr.reportUsage(interval, size);
		if(newInterval instanceof EmptyInterval) {
			from = to = 0;
			dynArr.set(to, value);
			return;
		}
		from = newInterval.getFrom();
		to = (newInterval.getTo() + 1) % dynArr.getInnerLength();
		dynArr.set(to, value);
	}

	/**
	 * Diese Methode entfernt ein Element aus der Warteschlange.
	 * 
	 * @return das entfernte Element
	 */
	public int dequeue() {
		Interval interval = isEmpty() ? new EmptyInterval() : new NonEmptyInterval(from, to);
		size--;
		int value = dynArr.get(from);
		
		Interval newInterval = dynArr.reportUsage(interval, size);
		if(newInterval instanceof EmptyInterval) {
			from = to = 0;
			return value;
		}
		from = (newInterval.getFrom() + 1) % dynArr.getInnerLength();
		to = newInterval.getTo();
		return value;
	}
}
