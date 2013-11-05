package model;

/**
 * definisce il possibile stato del volo:
 * OPEN: e' possibile prenotare posti sul volo
 * CLOSED: Non e' possibile prenotare il volo ma non e' ancora stata decisa la strategia di imbarco
 * BOARDING: Non e' possibile prenotare il volo ed è già stata calcolata la strategia di imbarco
 */
public enum StatoVolo {
	OPEN, CLOSED, BOARDING;
}
