/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.jsf.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import teste.modelo.Contato;
import teste.modelo.ContatoRepository;

/**
 *
 * @author MEUS DOCUMENTOS
 */
@ManagedBean
public class ListaContatosBean {

    private ListaContatosDataModel dataModel = new ListaContatosDataModel();
    
    public ListaContatosDataModel getContatos() {
        return dataModel;
    }

    public void remove(Contato contato) {
        EntityManager em = getEntityManager();
        ContatoRepository repository = new ContatoRepository(em);
        
        repository.remove(contato);
    }

    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }

}
