/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.blade.workflow.basic.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services.
 *
 * @author Inácio Nery
 * @generated
 */
@ProviderType
public class BazSoap implements Serializable {

	public static BazSoap toSoapModel(Baz model) {
		BazSoap soapModel = new BazSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setBazId(model.getBazId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static BazSoap[] toSoapModels(Baz[] models) {
		BazSoap[] soapModels = new BazSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BazSoap[][] toSoapModels(Baz[][] models) {
		BazSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BazSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BazSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BazSoap[] toSoapModels(List<Baz> models) {
		List<BazSoap> soapModels = new ArrayList<BazSoap>(models.size());

		for (Baz model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BazSoap[soapModels.size()]);
	}

	public BazSoap() {
	}

	public long getPrimaryKey() {
		return _bazId;
	}

	public void setPrimaryKey(long pk) {
		setBazId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getBazId() {
		return _bazId;
	}

	public void setBazId(long bazId) {
		_bazId = bazId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private String _uuid;
	private long _bazId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;

}