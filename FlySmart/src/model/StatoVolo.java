package model;

/**
 * definisce il possibile stato del volo:
 * OPEN: è possibile prenotare posti sul volo
 * CLOSED: Non è possibile prenotare il volo ma non è ancora stata decisa la strategia di imbarco
 * BOARDING: Non è possibile prenotare il volo ed è già stata calcolata la strategia di imbarco
 */
public enum StatoVolo {
	OPEN, CLOSED, BOARDING;
}
