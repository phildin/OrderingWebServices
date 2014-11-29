drop table partyaddress;
drop table party;
drop table address;
drop table country;
drop table currency;


create table country (
    id int primary key,
    isocode char(2) unique,
    name varchar(255),
    printablename varchar(255),
    iso3 char(3) unique,
    created timestamp,
    lastupdated timestamp
);

create table currency (
    code char(3) primary key,
    name varchar(255),
    created timestamp,
    lastupdated timestamp
);

create table address (
    id serial primary key,
    STREET1 varchar(255), 
    STREET2 varchar(255), 
    STATE varchar(32),  
    ZIPCODE varchar(32), 
    created timestamp,
    lastupdated timestamp,
    COUNTRYID int references country(id)
);

create table party (
    id serial primary key,
    name varchar(255),
    phone varchar(32),
    fax varchar(32),
    email varchar(255)
);


create table partyaddress (
    addressid bigint references address(id),
    partyid bigint references party(id),
    primary key (addressid, partyid)
);


