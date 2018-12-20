use default;
drop table aztest;
create table aztest(id int,name string) row format delimited fields terminated by ',';
load data inpath '/azdata/user.txt' into table aztest;
create table azres as select * from aztest;
insert overwrite directory '/azdata/userout' select count(1) from aztest;