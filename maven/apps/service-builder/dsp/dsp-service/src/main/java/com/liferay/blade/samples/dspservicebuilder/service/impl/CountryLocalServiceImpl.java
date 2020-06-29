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

package com.liferay.blade.samples.dspservicebuilder.service.impl;

import com.liferay.blade.samples.dspservicebuilder.service.base.CountryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the country local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.blade.samples.dspservicebuilder.service.CountryLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CountryLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.blade.samples.dspservicebuilder.model.Country",
	service = AopService.class
)
public class CountryLocalServiceImpl extends CountryLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.liferay.blade.samples.dspservicebuilder.service.CountryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.blade.samples.dspservicebuilder.service.CountryLocalServiceUtil</code>.
	 */
	public void useDSP() {
		try {
			DataSource dataSource = countryPersistence.getDataSource();

			Connection connection = dataSource.getConnection();

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(
				"select id, name from country");

			while (resultSet.next()) {
				if (_log.isInfoEnabled()) {
					_log.info("Record from external database:");
				}

				String id = resultSet.getString("id");

				if (_log.isInfoEnabled()) {
					_log.info("ID: " + id);
				}

				String name = resultSet.getString("name");

				if (_log.isInfoEnabled()) {
					_log.info("Name: " + name + System.lineSeparator());
				}
			}

			connection.close();
		}
		catch (SQLException sqle) {
			_log.error("Failed to retrieve data from external database!", sqle);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CountryLocalServiceImpl.class);
}