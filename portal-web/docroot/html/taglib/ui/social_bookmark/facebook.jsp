<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/html/taglib/ui/social_bookmark/init.jsp" %>

<%
String facebookDisplayStyle = "button_count";

if (displayStyle.equals("simple")) {
	facebookDisplayStyle = "standard";
}
else if (displayStyle.equals("vertical")) {
	facebookDisplayStyle = "box_count";
}
%>

<liferay-util:html-bottom outputKey="taglib_ui_social_bookmark_facebook">
	<script src="<%= HttpUtil.getProtocol(request) %>://connect.facebook.net/<%= locale.getLanguage() %>_<%= locale.getCountry() %>/all.js#xfbml=1"></script>
</liferay-util:html-bottom>

<div id="fb-root"></div>

<fb:like
	font=""
	height="<%= (facebookDisplayStyle.equals("standard") || facebookDisplayStyle.equals("button_count")) ? 20 : StringPool.BLANK %>"
	href="<%= url %>"
	layout="<%= facebookDisplayStyle%>"
	send="false"
	show_faces="true"
>
</fb:like>