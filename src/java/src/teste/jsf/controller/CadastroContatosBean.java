/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.teste.jsf.controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import jdbc.dao.ContatoDao;
import jdbc.model.ContatoRepository;
import jdbc.modelo.Contato;

/**
 *
 * @author MEUS DOCUMENTOS
 */
@ManagedBean
public class CadastroContatosBean {

    public Contato contato = new Contato();

    @ManagedProperty("#{param.id}")
    private Long id;
    
    public CadastroContatosBean(){
        System.out.println("Construtor");
    }

    public Contato getContato() {

        return contato;
    }

    public void setContato(Contato contato) {

        this.contato = contato;
    }

    public Long getId() {
        System.out.println("getId");
        return id;
    }

    public void setId(Long id) {
        System.out.println("setId:"+id);
        this.id = id;
    }

    
    @PostConstruct
    public void init() {
        if (id != null) {
            try {
                //JPA EntityManager em=getEntityManager();
                //JPA this.contato= new ContatoRepository(em).buscaPorId(id);
                this.contato = new ContatoDao().buscaPorId(id);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    /* JPA public EntityManager getEntityManager(){
        FacesContext fc=FacesContext.getCurrentInstance();
        ExternalContext ec=fc.getExternalContext();
        HttpServletRequest request=(HttpServletRequest)ec.getRequest();
        EntityManager manager=(EntityManager)request.getAttribute("EntityManager");
        return manager;
    }*/
    public String grava() {
        //JPA EntityManager manager = getEntityManager();
        //JPA  new ContatoRepository(manager).adiciona(contato);
        if (contato.getNome()== null) {
            
            FacesMessage message = new FacesMessage("O nome deve ser definido.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        if (contato.getDtNascimento() == null) {
            
            FacesMessage message = new FacesMessage("A data de nascimento deve ser definida.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        if (contato.getNome() == null || contato.getDtNascimento() == null) {
            
            return null;

        }

        if (contato.getId() == null) {

            new ContatoDao().adiciona(contato);

        } else {
            new ContatoDao().altera(contato);
        }

        //contato.setNome("");
        //contato.setEndereco("");
        //contato.setDtNascimento(null);
        //return "lista-contatos-template";
        return "lista-contatos-template?faces-redirect=true";
    }

    public void emailChanged(ValueChangeEvent event) {
        
        String oldEmailValue = (String) event.getOldValue();
        String newEmailValue = (String) event.getNewValue();
        

        if (newEmailValue != null && !newEmailValue.equals(oldEmailValue)) {
            try {
                //JPA EntityManager manager = getEntityManager();
                //JPA Contato contato = new
                //JPA ContatoRepository(manager).buscaPorEmail(newEmailValue);  
                Contato contato = new ContatoDao().buscaPorEmail(newEmailValue);
                if(contato != null){
         
                }
                Long id = (Long) ((UIInput) event.getComponent().findComponent("id")).getValue();
                if (contato != null && !contato.getId().equals(id) && newEmailValue.equals(contato.getEmail())) {
                    System.out.println("Passou por aqui");
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, String.format("Email ja cadastrado para o contato %s", contato.getNome()), null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
