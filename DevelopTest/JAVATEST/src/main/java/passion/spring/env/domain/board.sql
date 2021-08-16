create sequence seq_board increment by 1 start with 1;

create table board
(
    board_id number(11) not null primary key,
    title    varchar(100),
    content  varchar(2000)
);