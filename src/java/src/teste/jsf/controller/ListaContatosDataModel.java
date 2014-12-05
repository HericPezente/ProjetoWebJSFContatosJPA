/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.teste.jsf.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import jdbc.dao.ContatoDao;
import jdbc.modelo.Contato;
import jdbc.model.ContatoRepository;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;


/**
 *
 * @author MEUS DOCUMENTOS
 */

public class ListaContatosDataModel extends LazyDataModel<Contato> {
    /**
     * Método que carrega a listagem de contatos de acordo com o parametros first (offset) e 
     * pageSize(limit)
     */
    
    
    @Override
    public List<Contato> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
            
            //EntityManager em=getEntityManager();
            //ContatoRepository repository= new ContatoRepository(em);
            //List<jdbc.model.Contato> contatos=repository.buscaTodos();
                   
            ContatoDao dao=new ContatoDao();
            setRowCount(dao.getTotal());
            //setRowCount(contatos.size());
            setPageSize(pageSize);
    return dao.getLista(first, pageSize);
        
    }
    
    
    private EntityManager getEntityManager(){
        FacesContext fc=FacesContext.getCurrentInstance();
        ExternalContext ec=fc.getExternalContext();
        HttpServletRequest request=(HttpServletRequest)ec.getRequest();
        EntityManager em=(EntityManager)request.getAttribute("EntityManager");
        
        
        return em;
    }  
    
}
