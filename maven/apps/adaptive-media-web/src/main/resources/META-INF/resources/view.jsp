<%--
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
--%>

<%@ include file="/init.jsp" %>

<div class="container">

<%
String[] mimeTypes = {"image/bmp", "image/gif", "image/jpeg", "image/pjpeg", "image/png", "image/tiff", "image/x-citrix-jpeg", "image/x-citrix-png", "image/x-ms-bmp", "image/x-png", "image/x-tiff"};

List<FileEntry> fileEntries = DLAppServiceUtil.getFileEntries(scopeGroupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, mimeTypes);

int columns = 0;

for (FileEntry fileEntry : fileEntries) {
%>

	<c:if test="<%= (columns % 2) == 0 %>">
		<c:if test="<%= columns != 0 %>">
			</div>
		</c:if>

		<div class="row">
	</c:if>

	<div class="col-md-6">
		<liferay-adaptive-media:img class="img-fluid" fileVersion="<%= fileEntry.getFileVersion() %>" />
	</div>

	<%
		columns++;
	}
	%>

</div>