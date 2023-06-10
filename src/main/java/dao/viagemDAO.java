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
import models.Viagem;

/**
 *
 * @author mikaela.begotti
 */
public class viagemDAO {
      public void save(Viagem viagem){
    String sql = "INSERT INTO crud.viagem(pessoa, veiculo, lugar) VALUES (?,?,?)";
    Connection conn = null;
    PreparedStatement pstm = null; 
        
        try{
    
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,viagem.getPessoa());
            pstm.setString(2,viagem.getVeiculo());
            pstm.setString(3,viagem.getLugar());

            
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
        public void delete(Viagem viagem){
        String sql = "DELETE FROM crud.viagem WHERE idviagem=?";
        Connection conn = null;
        PreparedStatement pstm = null; 
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1,viagem.getIdViagem());
            
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
    public LinkedList<Viagem> getViagens(){
		
		String sql = "SELECT * FROM crud.viagem";
		
		LinkedList<Viagem> viagems = new LinkedList<Viagem>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Viagem viagem = new Viagem();
				viagem.setIdViagem(rset.getInt("idviagem"));
				viagem.setPessoa(rset.getString("pessoa"));
                                viagem.setVeiculo(rset.getString("veiculo"));
                                viagem.setLugar(rset.getString("lugar"));

				viagems.add(viagem);
				
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
                        System.out.println(viagems);
			return viagems;
	}
    
    public Viagem getViagemById(int id){
		
		String sql = "SELECT * FROM crud.viagem where idviagem=?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		Viagem viagem = new Viagem();
		try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {

                viagem.setPessoa(rset.getString("pessoa"));
                viagem.setVeiculo(rset.getString("veiculo"));
                viagem.setLugar(rset.getString("lugar"));

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
        return viagem;
    }
    
    public void update(Viagem viagem) {
        String sql = "UPDATE viagem SET pessoa=?, veiculo=?, lugar=? WHERE idviagem=?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, viagem.getPessoa());
            pstm.setString(2, viagem.getVeiculo());
            pstm.setString(3, viagem.getLugar());
            pstm.setInt(4, viagem.getIdViagem());

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

