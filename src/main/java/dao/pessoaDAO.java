/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import models.Pessoa;

/**
 *
 * @author mikaela.begotti
 */
public class pessoaDAO {
    public void save(Pessoa pessoa){
    String sql = "INSERT INTO pessoa(nome, cpf,dataNascimento) VALUES (?,?,?)";
    Connection conn = null;
    PreparedStatement pstm = null; 
        
        try{
    
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,pessoa.getNomePessoa());
            pstm.setString(2,pessoa.getCpf());
            pstm.setString(3,pessoa.getDataNascimento());

            
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
        public void delete(Pessoa pessoa){
        String sql = "DELETE FROM crud.pessoa WHERE idpessoa=?";
        Connection conn = null;
        PreparedStatement pstm = null; 
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1,pessoa.getIdPessoa());
            
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
    public LinkedList<Pessoa> getPessoas(){
		
		String sql = "SELECT * FROM crud.pessoa";
		
		LinkedList<Pessoa> pessoas = new LinkedList<Pessoa>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(rset.getInt("idpessoa"));
				pessoa.setNomePessoa(rset.getString("nome"));
                                pessoa.setCpf(rset.getString("cpf"));
                                pessoa.setDataNascimento(rset.getString("dataNascimento"));

				pessoas.add(pessoa);
				
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
                        System.out.println(pessoas);
			return pessoas;
	}
    
    public Pessoa getPessoaById(int id){
		
		String sql = "SELECT * FROM crud.pessoa where idpessoa=?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		Pessoa pessoa = new Pessoa();
		try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {

                pessoa.setNomePessoa(rset.getString("nome"));
                pessoa.setCpf(rset.getString("cpf"));
                pessoa.setDataNascimento(rset.getString("dataNascimento"));

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
        return pessoa;
    }
    
    public void update(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome=?, cpf=?, dataNascimento=? WHERE idpessoa=?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, pessoa.getNomePessoa());
            pstm.setString(2, pessoa.getCpf());
            pstm.setString(3, pessoa.getDataNascimento());
            pstm.setInt(4, pessoa.getIdPessoa());

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
