package model;

/**
 * definisce il possibile stato del volo:
 * OPEN: � possibile prenotare posti sul volo
 * CLOSED: Non � possibile prenotare il volo ma non � ancora stata decisa la strategia di imbarco
 * BOARDING: Non � possibile prenotare il volo ed � gi� stata calcolata la strategia di imbarco
 */
public enum StatoVolo {
	OPEN, CLOSED, BOARDING;
}
