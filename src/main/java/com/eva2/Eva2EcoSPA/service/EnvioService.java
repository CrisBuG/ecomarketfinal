package com.eva2.Eva2EcoSPA.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eva2.Eva2EcoSPA.Repository.EnvioRepository;
import com.eva2.Eva2EcoSPA.model.Envio;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class EnvioService{
    @Autowired
    private EnvioRepository envioRepository;
    
    public List<Envio> listarEnvios(){
        return envioRepository.findAll();
    }

    public Envio buscarPorId(int id){
        return envioRepository.findById(id).get();
    }

    public Envio guardarEnvio(Envio envio){
        return envioRepository.save(envio);
    }

    public void deleteById(int id){
        envioRepository.deleteById(id);
    }

    public Envio updateEnvio(Envio envioActualizado) {
        return envioRepository.save(envioActualizado);
    }

}
