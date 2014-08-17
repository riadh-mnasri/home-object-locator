<c:import url="/WEB-INF/views/common/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="place LIST" />
    <c:param name="body">
        <p>
            <a href='${pageContext.request.contextPath}/place/form/' class="btn btn-info">CREATE</a>
        </p>
        <table
            class="table table-striped table-bordered table-condensed">
            <tr>
                <th>ID</th>
                <th>OBJECT NAME</th>
                <th>PLACE NAME</th>
                <th>PLACE DESCRIPTION</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${page.content}" var="place">
                <tr>
                    <td>${f:h(place.id)}</td>
                    <td>${f:h(place.objectName)}</td>
                     <td>${f:h(place.placeName)}</td>
                    <td>${f:h(place.placeDescription)}</td>
                    <td><a href='${pageContext.request.contextPath}/place/edit/${place.id}'
                        class="btn btn-primary">edit</a>
                        <a href='${pageContext.request.contextPath}/place/delete/${place.id}'
                        class="btn">delete</a></td>
                </tr>
            </c:forEach>
        </table>

        <p>${f:h(page.number + 1)}/${f:h(page.totalPages)}</p>
        <div class="pagination">
            <ul>
                <li><c:if test="${!page.isFirstPage()}">
                        <a href='<c:url value="?page=0" />'>&laquo;
                            first</a>
                    </c:if></li>
                <li><c:if test="${page.hasPreviousPage()}">
                        <a
                            href='<c:url value="?page=${f:h(page.number - 1)}" />'>&lt;
                            prev</a>
                    </c:if></li>
                <li><c:if test="${page.hasNextPage()}">
                        <a
                            href='<c:url value="?page=${f:h(page.number + 1)}" />'>next
                            &gt;</a>
                    </c:if></li>
                <li><c:if test="${!page.isLastPage()}">
                        <a
                            href='<c:url value="?page=${f:h(page.totalPages - 1)}" />'>last
                            &raquo;</a>
                    </c:if></li>
            </ul>
        </div>
    </c:param>
</c:import>

