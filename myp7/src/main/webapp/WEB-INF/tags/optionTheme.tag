<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@ attribute name="theme" required="true" %>

<label>
	<div id="option-theme-click">
		<div id="option-theme-box">
			<div id="option-theme-header" class="theme-${theme}"></div>
			<div id="option-theme-menu"></div>
			<div id="option-theme-content"></div>
		</div>
		<div id="option-theme-footer" class="theme-${theme}"></div>
		<input type="radio" 
			   name="tema" 
			   id="theme-${theme}" 
			   value="${theme}" 
			   onclick="onClickTheme('${theme}')" />&nbsp;${theme}
	</div>
</label>