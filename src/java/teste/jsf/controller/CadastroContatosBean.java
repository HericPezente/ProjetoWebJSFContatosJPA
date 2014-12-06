/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.jsf.controller;

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
import teste.modelo.Contato;
import teste.modelo.ContatoRepository;

/**
 *
 * @author MEUS DOCUMENTOS
 */
@ManagedBean
public class CadastroContatosBean {

    public Contato contato = new Contato();

    @ManagedProperty("#{param.id}")
    private Long id;

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void init() {
        if (id != null) {
            EntityManager em = getEntityManager();
            this.contato = (Contato) new ContatoRepository(em).buscaPorId(id);
        }
    }

    public EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
        return manager;
    }

    public String grava() {
        EntityManager em = getEntityManager();
        ContatoRepository repository = new ContatoRepository(em);

        repository.adiciona(contato);

        return "lista-contatos-template?faces-redirect=true";
    }

    public void emailChanged(ValueChangeEvent event) {
        String oldEmailValue = (String) event.getOldValue();
        String newEmailValue = (String) event.getNewValue();

        if (newEmailValue != null && !newEmailValue.equals(oldEmailValue)) {
            EntityManager manager = getEntityManager();
            Contato contato = new ContatoRepository(manager).buscaPorEmail(newEmailValue);
            Long id = (Long) ((UIInput) event.getComponent().findComponent("id")).getValue();

            if (contato != null && !contato.getId().equals(id) && newEmailValue.equals(contato.getEmail())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, String.format("Email já cadastrado para o contato %s.", contato.getNome()), null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }
}
