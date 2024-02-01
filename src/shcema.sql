CREATE TABLE pokemon (
    nombre character varying(255),
    numero integer,
    tipo_primario character varying(255),
    tipo_secundario character varying(255),
    habilidad character varying(255),
    habilidad_oculta character varying(255),
    objeto_equipado character varying(255),
    hp integer,
    ataque integer,
    defensa integer,
    velocidad integer,
    ataque_especial integer,
    defensa_especial integer,
    movimiento1 character varying(255),
    movimiento2 character varying(255),
    movimiento3 character varying(255),
    movimiento4 character varying(255),
    CONSTRAINT numberone UNIQUE (numero),
    CONSTRAINT pokemon_movimiento1_fkey FOREIGN KEY (movimiento1) REFERENCES movimiento(nombre),
    CONSTRAINT pokemon_movimiento2_fkey FOREIGN KEY (movimiento2) REFERENCES movimiento(nombre),
    CONSTRAINT pokemon_movimiento3_fkey FOREIGN KEY (movimiento3) REFERENCES movimiento(nombre),
    CONSTRAINT pokemon_movimiento4_fkey FOREIGN KEY (movimiento4) REFERENCES movimiento(nombre),
    CONSTRAINT pokemon_objeto_equipado_fkey FOREIGN KEY (objeto_equipado) REFERENCES objeto(nombre)
);

CREATE TABLE objeto (
    nombre character varying(255) PRIMARY KEY,
    generacion character varying(255),
    precio_compra character varying(255),
    precio_venta character varying(255),
    tipo character varying(255)
);

CREATE TABLE IF movimiento (
    nombre character varying(255) PRIMARY KEY,
    tipo character varying(255),
    categoria character varying(255),
    poder integer,
    pp integer,
    precision character varying(255),
    descripcion character varying(255)
);


