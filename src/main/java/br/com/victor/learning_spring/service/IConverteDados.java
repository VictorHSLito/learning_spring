package br.com.victor.learning_spring.service;
public interface IConverteDados {
    public <T> T obterDados(String json, Class<T> classe);
}
