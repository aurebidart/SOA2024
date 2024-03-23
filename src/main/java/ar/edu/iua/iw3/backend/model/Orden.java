package ar.edu.iua.iw3.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "ordenes")
@Getter
@Setter
public class Orden {

    // Recepcion externa | estado = 0 -> 1

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer estado;

    // cantidad de kilogramos que deben cargar en el camión, punto 1
    @Column(nullable = false)
    private Long preset;

    @Column(nullable = true)
    private Date fechaRecepcionExterno;

    @Column(nullable = false)
    private Date fechaCargaPrevista;

    // Camion arriba a la planta | estado = 1 -> 2

    @Column(nullable = true)
    private Double pesajeInicial;

    // 5 dígitos según el punto 2
    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private Date fechaPesajeInicial;

    // Comienza la carga del camion | estado = 2 -> 3

    @Column(nullable = true)
    private Date fechaInicioCarga;

    @Column(nullable = true)
    private Date fechaFinCarga;

    // Datos del ultimo detalle
    // Se almacenan en la orden por un tema de eficiencia en el acceso

    @Column(nullable = true)
    private Double masaAcumulada;

    @Column(nullable = true)
    private Double densidadProducto;

    @Column(nullable = true)
    private Double temperaturaProducto;

    @Column(nullable = true)
    private Double caudal;

    @Transient
    private Long tiempoRestanteEstimado;

    @Transient
    private Long tiempoTranscurrido;

    // Camion sale de la planta | estado = 3 -> 4

    @Column(nullable = true)
    private Double pesajeFinal;

    @Column(nullable = true)
    private Date fechaPesajeFinal;

    @PrePersist
    @PreUpdate
    private void calcularTiempos() {
        if (this.fechaInicioCarga != null && preset != null && this.masaAcumulada != null && this.caudal != null) {
            this.tiempoRestanteEstimado = (long) ((this.preset - this.masaAcumulada) / this.caudal);
        }
        if (this.fechaInicioCarga != null) {
            this.tiempoTranscurrido = (new Date().getTime() - this.fechaInicioCarga.getTime()) / 1000;
        }
    }
}