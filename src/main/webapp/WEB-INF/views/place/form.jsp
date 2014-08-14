<c:import url="/WEB-INF/views/common/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="place FORM" />
    <c:param name="body">
        <spring:hasBindErrors name="place">
            <script type="text/javascript">
                $(document).ready(function() {
                    $("div.control-group>div.controls>.error").parent().parent().addClass("error");
                });
            </script>
        </spring:hasBindErrors>
        <form:form method="post" action="." modelAttribute="place"
            cssClass="form-horizontal">
            <fieldset>
                <legend>Place</legend>
                <div class="control-group">
                    <label class="control-label" for="name">name</label>
                    <div class="controls">
                        <form:input path="name" cssClass="span5"
                            cssErrorClass="error" />
                        <form:errors path="name"
                            cssClass="error help-inline inline"
                            element="span" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="description">description</label>
                    <div class="controls">
                        <form:input path="description" cssClass="span3"
                            cssErrorClass="error" />
                        <form:errors path="description"
                            cssClass="error help-inline inline"
                            element="span" />
                    </div>
                </div>
                <form:hidden path="id" />
                <div class="form-actions">
                    <input type="submit" class="btn btn-primary"
                        value="Submit">&nbsp;
                    <button type="reset" class="btn">Cancel</button>
                </div>
            </fieldset>
        </form:form>
        <hr>
        <a href='${pageContext.request.contextPath}/place/list' class="btn">list</a>
    </c:param>
</c:import>

