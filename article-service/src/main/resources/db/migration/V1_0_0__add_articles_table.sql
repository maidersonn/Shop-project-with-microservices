create table if not exists article (
    id serial NOT NULL PRIMARY KEY,
    type varchar(20),
    size_ int,
    material varchar(20),
    brand varchar(20),
    price int
)