/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import models.Lugar;

/**
 *
 * @author mikaela.begotti
 */
public class lugarDAO {
      public void save(Lugar lugar){
    String sql = "INSERT INTO crud.lugar(pais, estado, cidade) VALUES (?,?,?)";
    Connection conn = null;
    PreparedStatement pstm = null; 
        
        try{
    
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,lugar.getPais());
            pstm.setString(2,lugar.getEstado());
            pstm.setString(3,lugar.getCidade());
     
            pstm.execute();
            System.out.println("Salvo");
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
    }
        public void delete(Lugar lugar){
        String sql = "DELETE FROM crud.lugar WHERE idlugar=?";
        Connection conn = null;
        PreparedStatement pstm = null; 
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1,lugar.getIdLugar());
            
            pstm.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
    }
    public LinkedList<Lugar> getLugares(){
		
		String sql = "SELECT * FROM crud.lugar";
		
		LinkedList<Lugar> lugars = new LinkedList<Lugar>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Lugar lugar = new Lugar();
				lugar.setIdLugar(rset.getInt("idlugar"));
				lugar.setPais(rset.getString("pais"));
                                lugar.setEstado(rset.getString("estado"));
                                lugar.setCidade(rset.getString("cidade"));

				lugars.add(lugar);
				
			}
		}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rset!=null) {
						rset.close();
					}
					
					if(pstm!=null) {
						pstm.close();
					}
					
					if(conn!=null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
                        System.out.println(lugars);
			return lugars;
	}
    
    public Lugar getLugarById(int id){
		
		String sql = "SELECT * FROM crud.lugar where idlugar=?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		Lugar lugar = new Lugar();
		try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {

                lugar.setPais(rset.getString("pais"));
                lugar.setEstado(rset.getString("estado"));
                lugar.setCidade(rset.getString("cidade"));

            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lugar;
    }
    
    public void update(Lugar lugar) {
        String sql = "UPDATE lugar SET pais=?, estado=?, cidade=? WHERE idlugar=?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, lugar.getPais());
            pstm.setString(2, lugar.getEstado());
            pstm.setString(3, lugar.getCidade());
            pstm.setInt(4, lugar.getIdLugar());

            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



    
