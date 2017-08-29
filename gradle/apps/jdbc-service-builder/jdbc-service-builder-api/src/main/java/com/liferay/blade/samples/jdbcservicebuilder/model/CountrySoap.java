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

package com.liferay.blade.samples.jdbcservicebuilder.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CountrySoap implements Serializable {
	public static CountrySoap toSoapModel(Country model) {
		CountrySoap soapModel = new CountrySoap();

		soapModel.setCountryId(model.getCountryId());
		soapModel.setCountryName(model.getCountryName());

		return soapModel;
	}

	public static CountrySoap[] toSoapModels(Country[] models) {
		CountrySoap[] soapModels = new CountrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CountrySoap[][] toSoapModels(Country[][] models) {
		CountrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CountrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CountrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CountrySoap[] toSoapModels(List<Country> models) {
		List<CountrySoap> soapModels = new ArrayList<CountrySoap>(models.size());

		for (Country model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CountrySoap[soapModels.size()]);
	}

	public CountrySoap() {
	}

	public long getPrimaryKey() {
		return _countryId;
	}

	public void setPrimaryKey(long pk) {
		setCountryId(pk);
	}

	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	public String getCountryName() {
		return _countryName;
	}

	public void setCountryName(String countryName) {
		_countryName = countryName;
	}

	private long _countryId;
	private String _countryName;
}