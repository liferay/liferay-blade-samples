/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.blade.samples.gogo;

import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Liferay
 */
@Component(
	property = {"osgi.command.function=usercount", "osgi.command.scope=blade"},
	service = Object.class
)
public class UserCountCommand {

	public UserLocalService getUserLocalService() {
		return _userLocalService;
	}

	public void usercount() {
		System.out.println(
			"# of users: " + getUserLocalService().getUsersCount());
	}

	@Reference
	private volatile UserLocalService _userLocalService;

}