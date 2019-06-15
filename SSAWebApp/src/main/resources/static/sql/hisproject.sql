drop table USER_MASTER;
drop sequence SSN_SEQ;

drop table state_master;
drop sequence state_id_seq;
create table state_master(
	state_id number primary key,
	state_code varchar2(10) unique,
	state_name varchar2(50) 
);
create sequence state_id_seq;

CREATE OR REPLACE Function stateid return number is
l_excp_seq_num NUMBER;
BEGIN
SELECT state_id_seq.nextval
INTO l_excp_seq_num
FROM DUAL;
RETURN l_excp_seq_num;
End;
/

insert all
into state_master values (stateid,'AL', 'Alabama')
into state_master values (stateid,'AK', 'Alaska')
into state_master values (stateid,'AZ','Arizona')
into state_master values (stateid,'AR', 'Arkansas')
into state_master values (stateid,'CA', 'California')
into state_master values (stateid,'CO', 'Colorado')
into state_master values (stateid,'CT', 'Connecticut')
into state_master values (stateid,'DE', 'Delaware')
into state_master values (stateid,'DC', 'District of Columbia')
into state_master values (stateid,'FL', 'Florida')
into state_master values (stateid,'GA', 'Georgia')
into state_master values (stateid,'HI', 'Hawaii')
into state_master values (stateid,'ID', 'Idaho')
into state_master values (stateid,'IL', 'Illinois')
into state_master values (stateid,'IN', 'Indiana')
into state_master values (stateid,'IA', 'Iowa')
into state_master values (stateid,'KS', 'Kansas')
into state_master values (stateid,'KY', 'Kentucky')
into state_master values (stateid,'LA', 'Louisiana')
into state_master values (stateid,'ME', 'Maine')
into state_master values (stateid,'MD', 'Maryland')
into state_master values (stateid,'MA', 'Massachusetts')
into state_master values (stateid,'MI', 'Michigan')
into state_master values (stateid,'MN', 'Minnesota')
into state_master values (stateid,'MS', 'Mississippi')
into state_master values (stateid,'MO', 'Missouri')
into state_master values (stateid,'MT', 'Montana')
into state_master values (stateid,'NE', 'Nebraska')
into state_master values (stateid,'NV', 'Nevada')
into state_master values (stateid,'NH', 'New Hampshire')
into state_master values (stateid,'NJ', 'New Jersey')
into state_master values (stateid,'NM', 'New Mexico')
into state_master values (stateid,'NY', 'New York')
into state_master values (stateid,'NC', 'North Carolina')
into state_master values (stateid,'ND', 'North Dakota')
into state_master values (stateid,'OH', 'Ohio')
into state_master values (stateid,'OK', 'Oklahoma')
into state_master values (stateid,'OR', 'Oregon')
into state_master values (stateid,'PA', 'Pennsylvania')
into state_master values (stateid,'PR', 'Puerto Rico')
into state_master values (stateid,'RI', 'Rhode Island')
into state_master values (stateid,'SC', 'South Carolina')
into state_master values (stateid,'SD', 'South Dakota')
into state_master values (stateid,'TN', 'Tennessee')
into state_master values (stateid,'TX', 'Texas')
into state_master values (stateid,'UT', 'Utah')
into state_master values (stateid,'VT', 'Vermont')
into state_master values (stateid,'VA', 'Virginia')
into state_master values (stateid,'WA', 'Washington')
into state_master values (stateid,'WV', 'West Virginia')
into state_master values (stateid,'WI', 'Wisconsin')
into state_master values (stateid,'WY', 'Wyoming')
select * from dual;
