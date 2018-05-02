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

package com.liferay.blade.reluctant.vs.greedy.service;

import com.liferay.blade.reluctant.vs.greedy.portlet.api.SomeService;

import org.osgi.service.component.annotations.Component;

/**
 * @author James Hinkey
 */
@Component(
	immediate = true, property = "service.ranking:Integer=100",
	service = SomeService.class
)
public class HigherRankedService implements SomeService {

	@Override
	public String doSomething() {
		return "I am better, use me!";
	}

}