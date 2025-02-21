INSERT INTO movements_types (movement_type_id,movement_type) VALUES
	 (1,'Recepción'),
	 (2,'Despacho'),
	 (3,'Merma'),
	 (4,'Inv. inicial');

INSERT INTO movements_types (movement_type_id,movement_type) VALUES
	 (1,'Recepción'),
	 (2,'Despacho'),
	 (3,'Merma'),
	 (4,'Inv. inicial');

INSERT INTO product_category (category_name) VALUES
	 ('test');

INSERT INTO products_presentations (presentation_name) VALUES
	 ('test');

INSERT INTO public.products (presentation_type_id,product_category_id,product_code,product_description,product_name,product_price,product_unit_messure_id) VALUES
	 (1,1,'1','Nutrichicha 250 g.','Nutrichicha 250 g',100.0,1),
	 (1,1,'11','Nutrichicha 500g Comercial','Nutrichicha 500g Comercial',100.0,1),
	 (1,1,'111','Nutrichicha 500g Social','Nutrichicha 500g Social',100.0,1),
	 (1,1,'1111','Nutrichicha 1kg','Nutrichicha 1kg',100.0,1),
	 (1,1,'11111','Nutrichicha Granel','Nutrichicha Granel',100.0,1),
	 (1,1,'111111','Nutricereal 2Cereales 500g','Nutricereal 2Cereales 500g',100.0,1),
	 (1,1,'2','Nutricereal 2Cereales Granel','Nutricereal 2Cereales Granel',100.0,1),
	 (1,1,'22','Crema de Arroz 500g','Crema de Arroz 500g',100.0,1),
	 (1,1,'222','Crema de Arroz 1Kg','Crema de Arroz 1Kg',100.0,1),
	 (1,1,'2222','Crema de Arroz Granel','Crema de Arroz Granel',100.0,1);
INSERT INTO public.products (presentation_type_id,product_category_id,product_code,product_description,product_name,product_price,product_unit_messure_id) VALUES
	 (1,1,'22222','Nutrifororo 450g','Nutrifororo 450g',100.0,1),
	 (1,1,'222222','Nutrifororo 1kg','Nutrifororo 1kg',100.0,1),
	 (1,1,'2222222','Nutrifororo Granel','Nutrifororo Granel',100.0,1),
	 (1,1,'3','Papelon 200g','Papelon 200g',100.0,1),
	 (1,1,'33','Harina de Maiz','Harina de Maiz',100.0,1),
	 (1,1,'333','Azucar Refinada','Azucar Refinada',100.0,1);


INSERT INTO movements (quantity,entity_id,lot_id,movement_date,movement_type_id,product_id,registration_datetime,sunagro_guide_id,warehouse_id,action_description,delivery_note,observations,purchase_order,responsible_user) VALUES
	 (50,1,1,'2025-02-17 01:00:00',1,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL),
	 (10,1,1,'2025-02-17 02:00:00',2,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL),
	 (100,1,1,'2025-02-17 00:00:00',4,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL),
	 (5,1,1,'2025-02-17 03:00:00',3,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL),
	 (1,1,1,'2025-02-17 04:00:00',1,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL),
	 (1,1,1,'2025-02-17 05:00:00',1,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL),
	 (2,1,1,'2025-02-17 06:00:00',2,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL),
	 (1,1,1,'2025-02-17 07:00:00',3,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL),
	 (2,1,1,'2025-02-17 08:00:00',4,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL);
