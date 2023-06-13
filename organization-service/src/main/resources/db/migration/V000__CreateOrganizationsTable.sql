CREATE TABLE organizations(
    organization_id uuid PRIMARY KEY,
    "name" varchar(32) NOT NULL,
    contact_name varchar(32) NOT NULL,
    contact_email varchar(320) NOT NULL,
    contact_phone varchar(16) NOT NULL 
);