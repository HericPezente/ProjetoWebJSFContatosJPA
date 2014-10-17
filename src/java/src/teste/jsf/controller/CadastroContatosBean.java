/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.teste.jsf.controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import jdbc.dao.ContatoDao;
import jdbc.modelo.Contato;

/**
 *
 * @author MEUS DOCUMENTOS
 */
@ManagedBean
public class CadastroContatosBean{
    private Contato contato=new Contato();

    public Contato getContato() {
        System.out.println("getContato");
        return contato;
    }

    public void setContato(Contato contato) {
        System.out.println("setContato");
        this.contato = contato;
    }
    
    
    public void grava(){
            System.out.println("Gravando");
            ContatoDao dao= new ContatoDao();
            
            dao.adiciona(contato);
            
            contato.setNome("");
            contato.setEndereco("");
            contato.setDtNascimento(null);
    }
    
}
