SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE socio;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO socio (id, dni, nombre, referido_por) VALUES
 (1001, '1111111A', 'socio1001', NULL)
,(1002, '2222222A', 'socio1002', 1001)
,(1003, '3333333A', 'socio1003', 1001);