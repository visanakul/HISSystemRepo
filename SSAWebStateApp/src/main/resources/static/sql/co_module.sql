drop table co_triggers;

create table co_triggers
(
	trg_id number primary key,
	case_num number,
	create_dt date,
	trg_status varchar2(10),
	update_dt date

);

DECLARE
i number:=1;
trg_id number:=100;
case_no number:=10000;
BEGIN
WHILE i <=5000 LOOP
    INSERT INTO CO_TRIGGERS(TRG_ID,CASE_NUM,CREATE_DT,TRG_STATUS,UPDATE_DT)VALUES(trg_id,case_no,sysdate,'P',null);
    i:= i + 1;
    trg_id:=trg_id +1;
    case_no:=case_no+1;
  END LOOP;
END;
/

create table batch_run_dtls
(
	batch_id number(10) primary key,
	batch_name varchar2(50),
	start_date date,
        end_date date,
	batch_status char(1)
);
select trg_id from co_triggers where ora_hash(trg_id,4)=0;
create table co_pdf
(
   case_num number,
   pdf_file varchar2(50)
);