/**
 * 
 */
package smart;

/**
 * definisce i metodi da implementare per le classi che realizzano
 * gli algoritmi spatr di imbarco
 *
 */
public interface SmartAlgorithm {
	
	/**
	 * calcola la posizione dei pallet e dei passeggeri sull'aereo
	 * @return CheckinReport repost contenente le informazioni finali sul volo
	 */
	public CheckinReport calcolaCheckin();


}
