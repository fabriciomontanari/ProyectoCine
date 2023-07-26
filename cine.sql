/*Se crea la base de datos */
drop schema if exists cine;
drop user if exists usuario_prueba;
CREATE SCHEMA cine ;

/*Se crea un usuario para la base de datos llamado "usuario_prueba" y tiene la contraseña "Usuario_Clave."*/
create user 'usuario_prueba'@'%' identified by 'Usuar1o_Clave.';

/*Se asignan los prvilegios sobr ela base de datos TechShop al usuario creado */
grant all privileges on cine.* to 'usuario_prueba'@'%';
flush privileges;

/* la tabla de categoria contiene categorias de productos*/
create table cine.categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_categoria))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table cine.pelicula (
  pelicula VARCHAR(60),
  id_categoria INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,  
  detalle VARCHAR(1600) NOT NULL, 
  precio double,
  existencias int,  
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (pelicula),
  foreign key fk_pelicula_categoria (id_categoria) references categoria(id_categoria)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*Se crea la tabla de usuarios llamada usuario... igual que la clase Usuario*/
CREATE TABLE cine.usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(512) NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  apellidos VARCHAR(30) NOT NULL,
  correo VARCHAR(25) NULL,
  telefono VARCHAR(15) NULL,
  ruta_imagen varchar(1024),
  activo boolean,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*Se crea la tabla de roles de usuarios llamada rol... igual que la clase Rol*/
create table cine.rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre varchar(20),
  id_usuario int,
  PRIMARY KEY (id_rol),
  foreign key fk_rol_usuario (id_usuario) references usuario(id_usuario)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table cine.factura (
  id_factura INT NOT NULL AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  fecha date,  
  total double,
  estado int,
  PRIMARY KEY (id_factura),
  foreign key fk_factura_usuario (id_usuario) references usuario(id_usuario)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table cine.venta (
  id_venta INT NOT NULL AUTO_INCREMENT,
  id_factura INT NOT NULL,
  pelicula VARCHAR(60),
  precio double, 
  cantidad int,
  PRIMARY KEY (id_venta),
  foreign key fk_venta_factura (id_factura) references factura(id_factura),
  foreign key fk_venta_pelicula (pelicula) references pelicula(pelicula) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*Se insertan 3 registros en la tabla cliente como ejemplo */
INSERT INTO cine.usuario (id_usuario, username,password,nombre, apellidos, correo, telefono,ruta_imagen,activo) VALUES 
(1,'juan','$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.','Juan', 'Castro Mora',    'jcastro@gmail.com',    '4556-8978', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Juan_Diego_Madrigal.jpg/250px-Juan_Diego_Madrigal.jpg',true);

/*Se insertan 3 categorias de productos como ejemplo */
INSERT INTO cine.categoria (id_categoria,descripcion,ruta_imagen,activo) VALUES 
(1,"Terror","https://hips.hearstapps.com/hmg-prod/images/poster-peliculas-terror-2019-annabelle-3-1578395572.jpg",true);

/*Se insertan 4 productos por categoria */
INSERT INTO cine.pelicula (pelicula,id_categoria,descripcion,detalle,precio,existencias,ruta_imagen,activo) VALUES
("Anabelle",1,"Terror","John Form encuentra el regalo perfecto para Mia, su mujer embarazada: una preciosa muñeca 'vintage' llamada Annabelle. Una noche, una secta satánica les ataca brutalmente. Además, provocan que un ente maligno se apodere de Annabelle.",1500.00,50,"https://hips.hearstapps.com/hmg-prod/images/poster-peliculas-terror-2019-annabelle-3-1578395572.jpg",true);
/*Se crean 6 facturas */   /*'Activa','Pagada','Anulada')*/
INSERT INTO cine.factura (id_factura,id_usuario,fecha,total,estado) VALUES
(1,1,'2023-07-2',3000,1);

INSERT INTO cine.venta (id_venta,id_factura,pelicula,precio,cantidad) values
(1,1,"Anabelle",3000,2);


insert into cine.rol (id_rol, nombre, id_usuario) values
 (1,'ROLE_ADMIN',1), (2,'ROLE_VENDEDOR',1), (3,'ROLE_USER',1);