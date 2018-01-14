/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inmobile.demo.controller;

import cl.inmobile.demo.ejb.PersonaFacadeLocal;
import cl.inmobile.demo.model.Persona;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author rodo
 */
@Named
@ViewScoped
public class indexController implements Serializable{

    @EJB
    private PersonaFacadeLocal personaFacade;
    
    private Persona persona;

    private List<Persona> listaPersonas;
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    
    @PostConstruct
    public void init(){
      persona = new Persona();
      listaPersonas = personaFacade.findAll();
    }
    
    public String crear(){
        try {
            personaFacade.create(persona);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se a registrado de forma correcta "));
        } catch (Exception e) {
        }
        return "buscarForm";
    }
    
    public String eliminar(Persona per){
        try {
            personaFacade.remove(per);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se a eliminado de forma correcta "));
        } catch (Exception e) {
        }
        return "buscarForm";
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
    
    
}
