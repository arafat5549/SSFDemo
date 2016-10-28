 
drop database if exists sqltest;
create database sqltest
CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';
use sqltest;

drop TABLE if exists Student;
 CREATE TABLE Student 
 ( 
    SNO    INT, 
    Sname nvarchar(32), 
    Sage  INT, 
    Ssex  nvarchar(8) 
 ) ;
drop TABLE if exists Course;
CREATE TABLE Course 
( 
    CNO    INT, 
    Cname nvarchar(32), 
    TNO    INT 
) ;
drop TABLE if exists Sc;
 CREATE TABLE Sc 
 ( 
    SNO    INT, 
    CNO    INT, 
    score INT 
 ) ;

drop TABLE if exists Teacher;
CREATE TABLE Teacher 
( 
    TNO    INT, 
    Tname nvarchar(16) 
);