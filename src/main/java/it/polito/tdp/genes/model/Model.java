package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private Graph<String, DefaultWeightedEdge> grafo;
	private GenesDao dao;
	private List<String> listaVertici;
	private List<Arco> listaArchi;
	
	public Model() {
		
		dao = new GenesDao();
		
	}
	
	public void creaGrafo() {
		
		listaVertici = new ArrayList<String>();
		listaArchi = new ArrayList<Arco>();
		grafo = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		this.dao.creaVertici(listaVertici);
		this.dao.creaArchi(listaArchi);
		
		Graphs.addAllVertices(this.grafo, listaVertici);
		
		for (Arco a : listaArchi) {
			Graphs.addEdgeWithVertices(this.grafo, a.getLoc1(), a.getLoc2(), a.getPeso());
		}
		
	}
	
	public List<String> listaLocalizzazioni() {
		return listaVertici;
	}
	
	public String localizzazioniConnesse(String loc) {
		
		String result = "";
		List<String> vicini = Graphs.neighborListOf(this.grafo, loc);
		
		Collections.sort(vicini);
		
		for (String vicino : vicini) {
			int peso = (int)this.grafo.getEdgeWeight(this.grafo.getEdge(loc, vicino));
			result = result + vicino + " --> peso: " + peso + "\n";
		}
		
		return result;
		
	}
	
	public int numeroVertici() {
		return this.grafo.vertexSet().size();
		}
	
		 public int numeroArchi() {
		return this.grafo.edgeSet().size();
		}


}