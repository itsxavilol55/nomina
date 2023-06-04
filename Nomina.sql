Create database empresa
use empresa
CREATE TABLE Puesto (
	Id INT PRIMARY KEY IDENTITY(1,1),
    Nombre VARCHAR(50) NOT NULL,
    Descripcion VARCHAR(200),
    TasaSalario FLOAT,
    OtrosDatos VARCHAR(100)
);
CREATE TABLE Empleado (
	Id INT PRIMARY KEY IDENTITY(1,1),
    Nombre VARCHAR(120) NOT NULL,
    FechaContratacion DATE NOT NULL,
    Celular CHAR(10),
    Domicilio VARCHAR(200),
	IdPuesto INT,
    TipoContrato VARCHAR(50),
    SalarioBase FLOAT,
    Estado VARCHAR(20),
	SemanasVacaciones varchar(30),
    CorreoElectronico VARCHAR(100),
	FOREIGN KEY (Idpuesto) REFERENCES Puesto(Id)
)

select e.id,e.Nombre, FechaContratacion, Celular, Domicilio, p.Nombre, TipoContrato, TasaSalario,
SalarioBase, Estado, SemanasVacaciones, CorreoElectronico from empleado e inner join puesto p on e.idPuesto = p.id where e.id = 10
select * from puesto
select * from Empleado
INSERT INTO Puesto (Nombre, Descripcion, TasaSalario, OtrosDatos)
VALUES ('Gerente', 'Responsable de la gestión del equipo y toma de decisiones', 50.0, 'Experiencia mínima de 5 años');

INSERT INTO Puesto (Nombre, Descripcion, TasaSalario, OtrosDatos)
VALUES ('Analista de Datos', 'Encargado de analizar y extraer información de grandes conjuntos de datos', 40.0, 'Conocimientos avanzados de SQL y estadística');

INSERT INTO Puesto (Nombre, Descripcion, TasaSalario, OtrosDatos)
VALUES ('Diseñador Gráfico', 'Responsable de crear y diseñar elementos visuales', 30.0, 'Dominio de herramientas como Adobe Photoshop e Illustrator');

INSERT INTO Puesto (Nombre, Descripcion, TasaSalario, OtrosDatos)
VALUES ('Ejecutivo de Ventas', 'Encargado de gestionar y cerrar ventas', 35.0, 'Experiencia en el sector de ventas y habilidades de negociación');

INSERT INTO Puesto (Nombre, Descripcion, TasaSalario, OtrosDatos)
VALUES ('Ingeniero de Software', 'Responsable del desarrollo y mantenimiento de software', 45.0, 'Conocimientos en lenguajes de programación como Java, C++ y experiencia en metodologías ágiles');

INSERT INTO Puesto (Nombre, Descripcion, TasaSalario, OtrosDatos)
VALUES ('Asistente de Recursos Humanos', 'Apoyo en tareas administrativas y gestión del talento', 25.0, 'Conocimientos en legislación laboral y manejo de sistemas de gestión de recursos humanos');

INSERT INTO Puesto (Nombre, Descripcion, TasaSalario, OtrosDatos)
VALUES ('Técnico de Soporte', 'Encargado de brindar asistencia técnica a usuarios', 28.0, 'Conocimientos en sistemas operativos y resolución de problemas técnicos');

INSERT INTO Puesto (Nombre, Descripcion, TasaSalario, OtrosDatos)
VALUES ('Contador', 'Responsable de la gestión contable y financiera', 38.0, 'Experiencia en análisis de estados financieros y dominio de herramientas contables');

INSERT INTO Puesto (Nombre, Descripcion, TasaSalario, OtrosDatos)
VALUES ('Ingeniero de Sistemas', 'Encargado del diseño y mantenimiento de sistemas informáticos', 42.0, 'Conocimientos en redes, seguridad informática y administración de servidores');

INSERT INTO Puesto (Nombre, Descripcion, TasaSalario, OtrosDatos)
VALUES ('Asesor Financiero', 'Brinda asesoría en inversiones y planificación financiera', 32.0, 'Experiencia en el sector financiero y conocimientos en productos financieros');
--trabajadores
INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('Juan Perez', '2022-01-15', '1234567890', 'Calle 123, Ciudad', 1, 'Tiempo completo', 3000.0, 'Activo', '2,3,35,36', 'juan.perez@example.com');

INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('María López', '2021-08-20', '9876543210', 'Avenida Principal, Ciudad', 2, 'Medio tiempo', 2500.0, 'Activo', '10,11,42,43', 'maria.lopez@example.com');

INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('Carlos Gómez', '2023-02-10', '5555555555', 'Calle Secundaria, Ciudad', 3, 'Tiempo completo', 3500.0, 'Activo', '20,22,35', 'carlos.gomez@example.com');

INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('Ana Torres', '2022-11-05', '9999999999', 'Avenida Central, Ciudad', 2, 'Medio tiempo', 2300.0, 'Activo', '3,4,47,48', 'ana.torres@example.com');

INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('Pedro Rodríguez', '2023-04-01', '1111111111', 'Calle Principal, Ciudad', 4, 'Tiempo completo', 4000.0, 'Activo', '5,6,34,35', 'pedro.rodriguez@example.com');

INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('Laura García', '2021-06-12', '2222222222', 'Avenida Secundaria, Ciudad', 2, 'Tiempo completo', 2800.0, 'Activo', '22,23,47,48', 'laura.garcia@example.com');

INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('Miguel Martínez', '2022-03-25', '3333333333', 'Calle 456, Ciudad', 1, 'Tiempo completo', 3200.0, 'Activo', '3,4,5', 'miguel@example.com')

INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('Paola Sánchez', '2023-01-10', '4444444444', 'Avenida 789, Ciudad', 3, 'Tiempo completo', 3800.0, 'Activo', '10,11', 'paola.sanchez@example.com');

INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('Luis Morales', '2022-09-08', '6666666666', 'Calle 789, Ciudad', 4, 'Tiempo completo', 4200.0, 'Activo', '16,17', 'luis.morales@example.com');

INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('Carolina Vargas', '2023-03-15', '7777777777', 'Avenida 123, Ciudad', 2, 'Medio tiempo', 2400.0, 'Activo', '33,34', 'carolina.vargas@example.com');

INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('miles Morales', '2020-09-08', '6612366666', 'Calle 79, Ciudad', 4, 'Tiempo completo', 4700.0, 'Activo', '20,21', 'miles.morales@example.com');

INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('maria Sánchez', '2019-01-10', '4454444444', 'Avenida 87789, Ciudad 4', 3, 'Tiempo completo', 3800.0, 'Activo', '21', 'paola.sanchez@example.com');


INSERT INTO Empleado (Nombre, FechaContratacion, Celular, Domicilio, IdPuesto, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico)
VALUES ('jose ozuna', '2019-05-10', '7454444444', 'Avenida benito, Ciudad 7', 7, 'Tiempo completo', 4000.0, 'Activo', '', 'paola.sanchez@example.com');

