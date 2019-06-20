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

package com.liferay.blade.workflow.asset.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Inácio Nery
 */
@ProviderType
public class NoSuchQuxException extends NoSuchModelException {

	public NoSuchQuxException() {
	}

	public NoSuchQuxException(String msg) {
		super(msg);
	}

	public NoSuchQuxException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchQuxException(Throwable cause) {
		super(cause);
	}

}