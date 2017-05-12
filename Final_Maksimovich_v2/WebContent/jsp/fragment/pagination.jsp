<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="count" value="${requestScope.count}" />

<c:if test="${count > 1}">


	<c:set var="pageNumber" value="${requestScope.pageNumber}" />
	<c:set var="command" value="${requestScope.command}" />

	<div class="text-center paggination_container">
		<div class="pagination">
			<div id="child">

				<c:forEach var="i" begin="1" end="${count}">

					<c:choose>
						<c:when test="${i eq pageNumber }">
							<a class="active"
								href="controller?command=${command}&pageNumber=${i}">${i}</a>
						</c:when>
						<c:otherwise>
							<a href="controller?command=${command}&pageNumber=${i}">${i}</a>
						</c:otherwise>
					</c:choose>

				</c:forEach>

			</div>
		</div>
	</div>
</c:if>