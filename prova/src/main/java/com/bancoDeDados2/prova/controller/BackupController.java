package com.bancoDeDados2.prova.controller;

import com.bancoDeDados2.prova.service.BackupService;
import org.springframework.stereotype.Controller;

@Controller
public class BackupController {

    private final BackupService service;

    public BackupController(BackupService service) {
        this.service = service;
    }

    public void povoarBancoDeDados() {
        service.povoarBancoDeDados();
    }

    public void gerarBackup() {
        service.gerarBackup();
    }

    public void removerDados() {
        service.removerDados();
    }

    public void restaurarBackup() {
        service.restaurarBackup();
    }
}
