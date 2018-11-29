-- we don't know how to generate schema main (class Schema) :(
create table CARDS
(
	id integer not null
		primary key
		unique,
	example varchar(255),
	example_voice varchar(255),
	foreign_example varchar(255),
	foreign_example_voice varchar(255),
	foreign_name varchar(255) not null,
	foreign_nama_infinitive varchar(255),
	foreign_name_perfect varchar(255),
	foreign_name_preteritum varchar(255),
	foreign_name_voice varchar(255) not null,
	foreign_value_perfect_voice varchar(255),
	foreign_value_plural_voice varchar(255),
	foreign_value_presense_voice varchar(255),
	foreign_value_preteritum_voice varchar(255),
	is_perfect_with_haben integer,
	is_reflexiv_verb integer,
	is_regular_verb integer,
	is_trembare_prefix_verb integer,
	is_visible integer not null,
	kind_of_noun integer,
	name varchar(255) not null,
	name_voice varchar(255) not null,
	plural_endung varchar(255),
	preposition_gen varchar(255),
	category_id integer not null,
	level_id integer not null,
	preposition_akk integer not null,
	preposition_dat integer not null,
	type_id integer not null,
	user_id integer not null
)
;

create table CATEGORIES
(
	id integer not null
		primary key
		unique,
	is_visible integer not null,
	name varchar(255) not null
		unique
)
;

create table DECKS
(
	id integer not null
		primary key
		unique,
	is_anchor integer,
	is_favorite integer,
	is_visible integer,
	name varchar(255) not null
		unique,
	perfect integer,
	preposition_akkusative integer,
	preposition_dative integer,
	preposition_genetive integer,
	reflexive integer,
	regelmassig integer,
	trembare_prefix integer,
	category_id integer not null,
	level_id integer not null,
	topic_id integer not null,
	user_id integer not null
)
;

create table DECKS_VALUES
(
	id integer not null
		primary key
		unique,
	count_of_appearence integer not null,
	date_ready varchar(255),
	is_anchor integer,
	is_favorite integer,
	is_ready integer,
	order_in_card integer not null,
	cards_id integer not null,
	deck_id integer not null
)
;

create table FILES
(
	id integer not null
		primary key
		unique,
	is_visible integer not null,
	label varchar(255) not null
		unique,
	name varchar(255) not null
		unique,
	table_name varchar(255) not null
		unique
)
;

create table FILES_DE
(
	id integer not null
		primary key
		unique,
	card_id integer not null,
	is_visible integer not null,
	lang_id integer not null,
	path_to_example_file varchar(255) not null
		unique,
	path_to_value_file varchar(255) not null
		unique,
	path_to_verb_infinitive_file varchar(255) not null
		unique,
	path_to_verb_perfect_file varchar(255) not null
		unique,
	path_to_verb_presance_file varchar(255) not null
		unique,
	path_to_verb_preteritum_file varchar(255) not null
		unique
)
;

create table LANGUAGES
(
	id integer not null
		primary key
		unique,
	alias varchar(255) not null
		unique,
	is_visible integer not null,
	label varchar(255) not null
		unique,
	name varchar(255) not null
		unique,
	table_name varchar(255) not null
		unique
)
;

create table LANGUAGE_DE
(
	id integer not null
		primary key
		unique,
	card_id integer not null,
	file_id integer,
	is_visible integer,
	value varchar(255) not null
)
;

create table LEVELS
(
	id integer not null
		primary key
		unique,
	is_visible integer not null,
	name varchar(255) not null
		unique
)
;

create table PREPOSITION_AKKUSATIV
(
	id integer not null
		primary key
		unique,
	is_visible integer not null,
	name varchar(255) not null,
	check (is_visible>=1)
)
;

create table PREPOSITION_DATIV
(
	id integer not null
		primary key
		unique,
	is_visible integer not null,
	name varchar(255) not null,
	check (is_visible>=1)
)
;

create table SYSTEM_CONFIGS
(
	id integer not null
		primary key
		unique,
	is_visible integer not null,
	name varchar(255) not null
		unique
)
;

create table TMP_CARDS
(
	id integer not null
		primary key,
	example varchar(255),
	foreign_example varchar(255),
	foreign_name varchar(255) not null,
	foreign_nama_infinitive varchar(255),
	foreign_name_perfect varchar(255),
	foreign_name_preteritum varchar(255),
	is_perfect_with_haben integer,
	is_reflexiv_verb integer,
	is_regular_verb integer,
	is_trembare_prefix_verb integer,
	kind_of_noun integer,
	name varchar(255) not null,
	plural_endung varchar(255),
	preposition_gen varchar(255),
	proceed boolean,
	category_id integer not null,
	preposition_akk integer,
	preposition_dat integer,
	type_id integer not null
)
;

create table TYPES
(
	id integer not null
		primary key
		unique,
	is_visible integer not null,
	name varchar(255) not null
		unique
)
;

create table USERS
(
	id integer not null
		primary key
		unique,
	first_name varchar(255),
	is_visible integer not null,
	last_name varchar(255),
	name varchar(255),
	password varchar(255)
)
;

create table hibernate_sequence
(
	next_val bigint
)
;

