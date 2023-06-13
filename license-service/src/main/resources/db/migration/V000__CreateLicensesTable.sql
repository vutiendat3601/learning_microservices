CREATE TABLE licenses(
    license_id uuid NOT NULL,
    "description" varchar(255) NOT NULL,
    organization_id uuid NOT NULL, 
    product_name varchar(64) NOT NULL,
    license_type varchar(64) NOT NULL,
    comment varchar(255) NOT NULL
);