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
import models.Veiculo;

/**
 *
 * @author mikaela.begotti
 */
public class veiculoDAO {
      public void save(Veiculo veiculo){
    String sql = "INSERT INTO crud.veiculo(nome, cor, ano) VALUES (?,?,?)";
    Connection conn = null;
    PreparedStatement pstm = null; 
        
        try{
    
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,veiculo.getNomeVeiculo());
            pstm.setString(2,veiculo.getCorVeiculo());
            pstm.setInt(3,veiculo.getAno());

            
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
        public void delete(Veiculo veiculo){
        String sql = "DELETE FROM crud.veiculo WHERE idveiculo=?";
        Connection conn = null;
        PreparedStatement pstm = null; 
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1,veiculo.getIdVeiculo());
            
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
    public LinkedList<Veiculo> getVeiculos(){
		
		String sql = "SELECT * FROM crud.veiculo";
		
		LinkedList<Veiculo> veiculos = new LinkedList<Veiculo>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Veiculo veiculo = new Veiculo();
				veiculo.setIdVeiculo(rset.getInt("idveiculo"));
				veiculo.setNomeVeiculo(rset.getString("nome"));
                                veiculo.setCorVeiculo(rset.getString("cor"));
                                veiculo.setAno(rset.getInt("ano"));

				veiculos.add(veiculo);
				
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
                        System.out.println(veiculos);
			return veiculos;
	}
    
    public Veiculo getVeiculoById(int id){
		
		String sql = "SELECT * FROM crud.veiculo where idveiculo=?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		Veiculo veiculo = new Veiculo();
		try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {

                veiculo.setNomeVeiculo(rset.getString("nome"));
                veiculo.setCorVeiculo(rset.getString("cor"));
                veiculo.setAno(rset.getInt("ano"));

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
        return veiculo;
    }
    
    public void update(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET nome=?, cor=?, ano=? WHERE idveiculo=?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, veiculo.getNomeVeiculo());
            pstm.setString(2, veiculo.getCorVeiculo());
            pstm.setInt(3, veiculo.getAno());
            pstm.setInt(4, veiculo.getIdVeiculo());

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


