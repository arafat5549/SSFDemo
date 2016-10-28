insert into Student select 1,N'刘一',18,N'男' union all
select 2,N'钱二',19,N'女' union all
select 3,N'张三',17,N'男' union all
select 4,N'李四',18,N'女' union all
select 5,N'王五',17,N'男' union all
select 6,N'赵六',19,N'女'; 
 
insert into Teacher select 1,N'叶平' union all
select 2,N'贺高' union all
select 3,N'杨艳' union all
select 4,N'周磊';
 
insert into Course select 1,N'语文',1 union all
select 2,N'数学',2 union all
select 3,N'英语',3 union all
select 4,N'物理',4;
 
insert into SC 
select 1,1,56 union all 
select 1,2,78 union all 
select 1,3,67 union all 
select 1,4,58 union all 
select 2,1,79 union all 
select 2,2,81 union all 
select 2,3,92 union all 
select 2,4,68 union all 
select 3,1,91 union all 
select 3,2,47 union all 
select 3,3,88 union all 
select 3,4,56 union all 
select 4,2,88 union all 
select 4,3,90 union all 
select 4,4,93 union all 
select 5,1,46 union all 
select 5,3,78 union all 
select 5,4,53 union all 
select 6,1,35 union all 
select 6,2,68 union all 
select 6,4,71;