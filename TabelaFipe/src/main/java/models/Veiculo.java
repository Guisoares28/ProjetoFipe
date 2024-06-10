package models;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Veiculo {

    @JsonAlias("TipoVeiculo")
    private Integer tipoVeiculo;
    @JsonAlias("Valor")
    private String valor;
    @JsonAlias("Marca")
    private String marca;
    @JsonAlias("Modelo")
    private String modelo;
    @JsonAlias("AnoModelo")
    private Integer anoModelo;
    @JsonAlias("Combustivel")
    private String combustivel;
    @JsonAlias("CodigoFipe")
    private String codigoFipe;
    @JsonAlias("MesReferencia")
    private String mesReferencia;

    public Veiculo(){}

    public Integer getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(Integer tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        this.codigoFipe = codigoFipe;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "tipoVeiculo:" + tipoVeiculo +
                " valor: " + valor + '\'' +
                " marca: " + marca + '\'' +
                " modelo: " + modelo + '\'' +
                " anoModelo: " + anoModelo +
                " combustivel: " + combustivel + '\'' +
                " codigoFipe: " + codigoFipe + '\'' +
                " mesReferencia: " + mesReferencia + '\'' +
                '}';
    }
}
