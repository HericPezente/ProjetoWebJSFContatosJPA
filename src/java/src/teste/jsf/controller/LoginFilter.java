/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.teste.jsf.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MEUS DOCUMENTOS
 */


//@WebFilter(urlPatterns = {"/lista-contatos-template","/cadastro-contatos-template"})
@WebFilter (urlPatterns = {"/lista-contatos-css.xhtml","/cadastro-contatos-css.xhtml"})
public class LoginFilter  implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       
        HttpSession session= ((HttpServletRequest)request).getSession(false);
        if(session==null|| session.getAttribute("usuario")==null){
            
            ((HttpServletResponse) response).sendRedirect("login_css.xhtml");
        return;
        }
        
        chain.doFilter(request, response);
        
        
    }

    @Override
    public void destroy() {
        
    }
    
    
}
