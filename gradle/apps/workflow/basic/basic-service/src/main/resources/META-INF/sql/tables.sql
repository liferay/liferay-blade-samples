create table Workflow_Baz (
	uuid_ VARCHAR(75) null,
	bazId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);