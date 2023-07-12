package it.polito.tdp.genes.model;

public class Arco {
	
	String loc1;
	String loc2;
	int peso;
	public Arco(String loc1, String loc2, int peso) {
		super();
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.peso = peso;
	}
	public String getLoc1() {
		return loc1;
	}
	public void setLoc1(String loc1) {
		this.loc1 = loc1;
	}
	public String getLoc2() {
		return loc2;
	}
	public void setLoc2(String loc2) {
		this.loc2 = loc2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	

}
