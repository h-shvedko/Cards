-- we don't know how to generate schema main (class Schema) :(
create table CARDS
(
	id integer not null
		primary key
		unique,
	example varchar(255),
	foreign_example varchar(255),
	foreign_name varchar(255) not null,
	is_perfect_with_haben integer,
	is_reflexiv_verb integer,
	is_regular_verb integer,
	is_trembare_prefix_verb integer,
	is_visible integer not null,
	kind_of_noun integer,
	name varchar(255) not null,
	plural_endung varchar(255),
	preposition_akk varchar(255),
	preposition_dat varchar(255),
	preposition_gen varchar(255),
	category_id integer not null,
	type_id integer not null,
	user_id INTEGER default 1,
	name_voice VARCHAR(255),
	foreign_name_voice VARCHAR(255),
	example_voice VARCHAR(255),
	foreign_example_voice VARCHAR(255),
	foreign_value_presense_voice VARCHAR(255),
	foreign_value_preteritum_voice VARCHAR(255),
	foreign_value_perfect_voice VARCHAR(255),
	foreign_value_plural_voice VARCHAR(255),
	foreign_name_preteritum VARCHAR(255),
	foreign_name_perfect VARCHAR(255),
	foreign_nama_infinitive VARCHAR(255),
	level_id integer not null,
	check (is_visible>=1)
)
;

create index CARDS_category_id_index
	on CARDS (category_id)
;

create index CARDS_type_id_index
	on CARDS (type_id)
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
	is_visible integer not null,
	name varchar(255) not null,
	user_id integer not null,
	category_id INTEGER,
	topic_id INTEGER,
	is_anchor INTEGER,
	is_favorite INTEGER,
	trembare_prefix INT default 0,
	regelmassig INT default 0,
	reflexive INT default 0,
	perfect INT default 0,
	preposition_akkusative INT default 0,
	preposition_dative INT default 0,
	preposition_genetive INT default 0,
	level_id integer not null
)
;

create table DECKS_VALUES
(
	id integer not null
		primary key
		unique,
	cards_id integer not null,
	deck_id integer not null,
	is_favorite INT default 0 not null,
	is_anchor INT default 0 not null,
	is_ready INT default 0 not null,
	date_ready DATE,
	order_in_card INT default 0,
	count_of_appearence INT default 0
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
		primary key
		unique,
	example varchar(255),
	foreign_example varchar(255),
	foreign_name varchar(255) not null,
	is_perfect_with_haben integer,
	is_reflexiv_verb integer,
	is_regular_verb integer,
	is_trembare_prefix_verb integer,
	kind_of_noun integer,
	name varchar(255) not null,
	plural_endung varchar(255),
	preposition_akk varchar(255),
	preposition_dat varchar(255),
	preposition_gen varchar(255),
	category_id integer not null,
	type_id integer not null,
	foreign_name_preteritum VARCHAR(255),
	foreign_name_perfect VARCHAR(255),
	foreign_nama_infinitive VARCHAR(255),
	proceed INT default 1
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
	is_visible integer not null,
	name varchar(255),
	password varchar(255),
	first_name varchar(255),
	last_name varchar(255)
)
;

create table hibernate_sequence
(
	next_val bigint
)
;

