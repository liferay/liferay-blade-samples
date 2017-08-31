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

package com.liferay.blade.samples.jndiservicebuilder.model;

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
public class RegionSoap implements Serializable {
	public static RegionSoap toSoapModel(Region model) {
		RegionSoap soapModel = new RegionSoap();

		soapModel.setRegionId(model.getRegionId());
		soapModel.setRegionName(model.getRegionName());

		return soapModel;
	}

	public static RegionSoap[] toSoapModels(Region[] models) {
		RegionSoap[] soapModels = new RegionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RegionSoap[][] toSoapModels(Region[][] models) {
		RegionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RegionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RegionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RegionSoap[] toSoapModels(List<Region> models) {
		List<RegionSoap> soapModels = new ArrayList<RegionSoap>(models.size());

		for (Region model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RegionSoap[soapModels.size()]);
	}

	public RegionSoap() {
	}

	public long getPrimaryKey() {
		return _regionId;
	}

	public void setPrimaryKey(long pk) {
		setRegionId(pk);
	}

	public long getRegionId() {
		return _regionId;
	}

	public void setRegionId(long regionId) {
		_regionId = regionId;
	}

	public String getRegionName() {
		return _regionName;
	}

	public void setRegionName(String regionName) {
		_regionName = regionName;
	}

	private long _regionId;
	private String _regionName;
}