/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.teste.jsf.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author MEUS DOCUMENTOS
 */

@ManagedBean
@SessionScoped
public class LoginBean {

    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        
        this.senha = senha;
    }

    public String login() {
        if (this.usuario.equals("")) {
            FacesMessage message = new FacesMessage("O usuário deve ser definido.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }

        if ("admin".equals(usuario) && !"123".equals(senha)) {
            //Mensagen de validação
            FacesMessage message = new FacesMessage("O usuário não existe ou a senha é invalida.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
        
        //return "lista-contatos-template?faces-redirect=true";
        return "lista-contatos-css?faces-redirect=true";

    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true"; 
    }
    
}
