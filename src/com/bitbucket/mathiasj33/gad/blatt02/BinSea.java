package com.bitbucket.mathiasj33.gad.blatt02;

/**
 * Die Klasse BinSea stellt Methoden bereit, in sortierten Feldern binär
 * nach Wertebereichen zu suchen.
 */
class BinSea {
  /**
   * Diese Methode sucht nach einem Wert in einem einem sortierten Feld. Sie
   * soll dabei dazu verwendet werden können, den ersten bzw. letzten Index in
   * einem Intervall zu finden. Wird kein passender Index gefunden, wird
   * -1 zurückgegeben.
   * 
   * Beispiel:
   * 
   *             0 <-----------------------> 5
   * sortedData: [-10, 33, 50, 99, 123, 4242 ]
   * value: 80             ^   ^
   *                       |   |
   *                       |   `- Ergebnis (3) für ersten Index im Intervall, da 99
   *                       |      als erster Wert im Feld größer als 80 ist
   *                       |
   *                       `- Ergebnis (2) für letzten Index im Intervall, da 50
   *                          als letzter Wert kleiner als 80 ist
   * 
   * @param sortedData das sortierte Feld, in dem gesucht wird
   * @param value der Wert der Intervallbegrenzung, der dem gesucht wird
   * @param lower true für untere Intervallbegrenzung, false für obere Intervallbegrenzung
   * @return der passende Index, -1 wenn dieser nicht gefunden werden kann
   */
  public static int search (int[] sortedData, int value, boolean lower) {  //As stated in Piazza, sortedData does not contain double values and the bounds are inclusive
	  int length = sortedData.length;
	  int lowerBound = 0;
	  int upperBound = length - 1;
	  int index = length / 2;
	  int found = sortedData[index];
	  while(found != value) {
		  if(lowerBound == upperBound && found != value) {
			  if(found < value) {
				  if(!lower && lowerBound == length - 1) return -1;
				  return lower ? lowerBound : upperBound+1;
			  }
			  else if(found > value) {
				  if(lower && lowerBound == 0) return -1;
				  return lower ? lowerBound-1 : upperBound;
			  }
		  }
		  if(found < value) {
			  lowerBound = index + 1;
		  }
		  else if (found > value) {
			  upperBound = index - 1;
		  }
		  index = (lowerBound + upperBound) / 2;
		  found = sortedData[index];
	  }
	  return index;
  }

  /**
   * Diese Methode sucht ein Intervall von Indices eines sortierten Feldes, deren Werte
   * in einem Wertebereich liegen. Es soll dabei binäre Suche verwendet werden.
   * 
   * Beispiel:
   *                     0 <-----------------------> 5
   * sortedData:         [-10, 33, 50, 99, 123, 4242 ]
   * valueRange: [80;700]              ^   ^
   *                                   |   |
   *                                   |   `- Ergebnis (4) für obere Intervallbegrenzung,
   *                                   |      da 123 als letzter Wert kleiner oder gleich 700 ist
   *                                   |
   *                                   `- Ergebnis (3) für untere Intervallbegrenzung,
   *                                      da 99 als erster Wert größer oder gleich 80 ist
   * 
   * @param sortedData das sortierte Feld, in dem gesucht wird
   * @param valueRange der Wertebereich, zu dem Indices gesucht werden
   */
  public static Interval search (int[] sortedData, Interval valueRange) {
	  int lowerIndex = search(sortedData, valueRange.getFrom(), false);
	  int upperIndex = search(sortedData, valueRange.getTo(), true);
	  return Interval.fromArrayIndices(lowerIndex, upperIndex);
  }

}
