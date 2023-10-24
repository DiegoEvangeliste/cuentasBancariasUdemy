package com.example.cuentas.controller;

import com.example.cuentas.model.Account;
import com.example.cuentas.service.impl.AccountService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Component
@Data
@ViewScoped
public class IndexController {

    @Autowired(required = true)
    AccountService accountService;

    private List<Account> accounts;

    private Account accountSelected;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init() {
        loadData();
    }

    private void loadData() {
        this.accounts = accountService.findAllAccounts();
        accounts.forEach((cuenta) -> logger.info(cuenta.toString()));
    }

    public void addCuenta() {
        logger.info("Se crea objeto cuentaSeleccionada para el caso de agregar");
        this.accountSelected = new Account();
    }

    public void saveCuenta() {
        logger.info("Cuenta a guardar: " + this.accountSelected);

        // Agregamos la cuenta si el ID no existe
        if (this.accountSelected.getIdCuenta() == null) {
            this.accountService.saveAccount(this.accountSelected);
            this.accounts.add(this.accountSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta agregada"));
        } else { //Si el ID existe, modificamos la cuenta (update)
            this.accountService.saveAccount(this.accountSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta actualizada"));
        }

        // Reseteamos el objeto seleccionado de la tabla
        this.accountSelected = null;

        // Ocultamos la ventana de "Agregar cuenta"
        PrimeFaces.current().executeScript("PF('ventanaModalCuenta').hide()");

        //Actualizamos la tabla de las cuentas
        PrimeFaces.current().ajax().update("forma-cuentas:mensajes", "forma-cuentas:cuentas-tabla");
    }

    public void updateCuenta() {
        // ?????
    }

    public void deleteCuenta() {
        logger.info("Cuenta a eliminar: " + this.accountSelected);
        this.accountService.deleteAccount(this.accountSelected);

        // Eliminamos el registro de la lista de cuentas
        this.accounts.remove(this.accountSelected);

        // Reseteamos el objeto seleccionado de la tabla
        this.accountSelected = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta eliminada"));

        //Actualizamos la tabla de las cuentas
        PrimeFaces.current().ajax().update("forma-cuentas:mensajes", "forma-cuentas:cuentas-tabla");
    }
}
