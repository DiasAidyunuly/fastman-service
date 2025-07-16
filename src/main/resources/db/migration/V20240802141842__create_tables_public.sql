CREATE TABLE IF NOT EXISTS filials
(
    code_fil     int4 primary key,
    code_site_TZ int4,
    name_site_TZ varchar(50),
    code_site_SP int4,
    name_site_SP varchar(50),
    "format"     varchar(50),
    name_fil     varchar(50),
    city         varchar(50),
    address_fil  varchar(100)
);

CREATE TABLE IF NOT EXISTS structure_od
(
    id_str_od  SERIAL primary key,
    code_fil   int4,
    region     varchar(50),
    division   varchar(50),
    regdir     varchar(50),
    tabRegDir  varchar(100),
    terdir     varchar(50),
    tabTerDir  varchar(100),
    dirfill    varchar(50),
    tabDirFil  varchar(100),
    "open"     int2,
    date_open  timestamptz,
    date_close timestamptz,
    CONSTRAINT structure_od_code_fil_fkey FOREIGN KEY (code_fil) REFERENCES filials (code_fil)
);