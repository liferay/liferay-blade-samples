create index IX_D808EC33 on ADQ_Bar (field1[$COLUMN_LENGTH:75$]);
create index IX_D808EFF4 on ADQ_Bar (field2);
create index IX_2A25A3FC on ADQ_Bar (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_477EF97E on ADQ_Bar (uuid_[$COLUMN_LENGTH:75$], groupId);