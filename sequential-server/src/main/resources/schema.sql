drop table if exists vocabulary;
drop table if exists vocabulary_lists;


create table vocabulary_lists
(
    id int not null
        primary key,
    name varchar(255) null
);

create table vocabulary
(
    id int not null
        primary key,
    eng varchar(255) null,
    tr varchar(255) charset utf8 not null,
    vocabulary_list_id int null,
    constraint FK37d972jqngydaiug3nl4gvooy
        foreign key (vocabulary_list_id) references vocabulary_lists (id)
);

ALTER TABLE vocabulary MODIFY COLUMN tr VARCHAR(255)
    CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;
