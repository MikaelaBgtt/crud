/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import dao.pessoaDAO;
import java.util.LinkedList;
import models.Pessoa;

/**
 *
 * @author mikaela.begotti
 */
public class teste {
    public static void main(String[] args) {
        pessoaDAO pessoadao = new pessoaDAO();
        Pessoa pessoa = new Pessoa();
        LinkedList<String> pess =  new LinkedList<String>();
        //pess.clear();
        for (Pessoa pessoa1 : pessoadao.getPessoas()) {
            pess.add(pessoa1.getNomePessoa());
            System.out.println(pessoa1.getNomePessoa());
        }
    }
    
}
