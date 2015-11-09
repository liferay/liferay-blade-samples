create index IX_99833ED8 on Foo (field2);
create index IX_2545A5E0 on Foo (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D5BE2462 on Foo (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DEC05F3B on SSB_Foo (field2);
create index IX_ED0BE683 on SSB_Foo (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_725231C5 on SSB_Foo (uuid_[$COLUMN_LENGTH:75$], groupId);