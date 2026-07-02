package com.exemplo.garagem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Permite que o seu HTML acesse o Java sem erros de CORS
public class CarroController {

    // Classe interna para estruturar os dados do carro
    public static class Carro {
        public int id;
        public String marca;
        public String nome;
        public String imagem;

        public Carro(int id, String marca, String nome, String imagem) {
            this.id = id;
            this.marca = marca;
            this.nome = nome;
            this.imagem = imagem;
        }
    }

    @GetMapping("/api/carros")
    public List<Carro> get200Carros() {
        List<Carro> listaDeCarros = new ArrayList<>();
        
        // Base de marcas e modelos para o loop gerar 200 variações
        String[][] dadosBase = {
            {"Porsche", "911 GT3 RS"}, {"Porsche", "918 Spyder"}, {"Porsche", "Cayman GT4"},
            {"Ferrari", "LaFerrari"}, {"Ferrari", "488 Pista"}, {"Ferrari", "SF90 Stradale"},
            {"Lamborghini", "Aventador SVJ"}, {"Lamborghini", "Huracán STO"}, {"Lamborghini", "Revuelto"},
            {"McLaren", "P1"}, {"McLaren", "720S"}, {"McLaren", "Senna"},
            {"Nissan", "GT-R Nismo"}, {"Toyota", "Supra MK4"}, {"Chevrolet", "Corvette Z06"},
            {"Ford", "Ford GT"}, {"Dodge", "Viper ACR"}, {"Audi", "R8 V10"}
        };

        String imagemPadrao = "https://images.unsplash.com/photo-1614162692292-7ac56d7f7f1e?w=300";
        int contadorId = 1;

        // Loop inteligente que roda até preencher exatamente 200 carros dinamicamente
        while (listaDeCarros.size() < 200) {
            for (String[] carroBase : dadosBase) {
                if (listaDeCarros.size() < 200) {
                    String marca = carroBase[0];
                    // Adiciona uma numeração para diferenciar os 200 modelos (ex: Spec v1, Spec v2...)
                    int versao = (contadorId / dadosBase.length) + 1;
                    String nome = carroBase[1] + (versao > 1 ? " (Spec v" + versao + ")" : "");
                    
                    listaDeCarros.add(new Carro(contadorId, marca, nome, imagemPadrao));
                    contadorId++;
                }
            }
        }

        return listaDeCarros;
    }
}