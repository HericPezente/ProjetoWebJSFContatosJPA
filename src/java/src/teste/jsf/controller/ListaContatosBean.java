/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.teste.jsf.controller;

import jdbc.dao.ContatoDao;
import jdbc.modelo.Contato;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author MEUS DOCUMENTOS
 */
@ManagedBean
public class ListaContatosBean {

    
    public List<Contato> getContatos(){
        ContatoDao dao= new ContatoDao();
        return dao.getLista();
        
    }
    
    public void remove(Contato contato){
        new ContatoDao().remove(contato);
    }
}
