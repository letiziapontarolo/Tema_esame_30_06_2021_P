package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Arco;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	public void creaVertici(List<String> listaVertici) {
		
		String sql = "SELECT distinct c.Localization "
				+ "FROM classification c";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				listaVertici.add(res.getString("Localization"));
			}
			res.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
		
	}
	
	public void creaArchi(List<Arco> listaArchi) {
		
		String sql = "SELECT c1.Localization AS loc1, c2.Localization AS loc2, COUNT(distinct i.`Type`) AS peso "
				+ "FROM classification c1, classification c2, interactions i "
				+ "WHERE c1.GeneID = i.GeneID1 AND c2.GeneID = i.GeneID2 OR (c1.GeneID = i.GeneID2 AND c2.GeneID = i.GeneID1) "
				+ "GROUP BY c2.Localization, c1.Localization "
				+ "HAVING loc1 > loc2";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				Arco a = new Arco(res.getString("loc1"), 
						res.getString("loc2"), 
						res.getInt("peso"));
				
				listaArchi.add(a);

				
			}
			res.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
		
	}
	
		
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	


	
}
