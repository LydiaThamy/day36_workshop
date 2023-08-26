use railway;

create table cities (
	city_id	int	not null	auto_increment,
    city		varchar(256)	not null,
    constraint	pk_city_id	primary key(city_id)
);