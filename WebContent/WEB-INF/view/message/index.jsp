<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4"></div>
  <div class="col-md-4"></div>
</div>
<br>
<h3>Listing <span class='muted'>Messages</span></h3>
<br>
<c:choose> 
	<c:when test="${not empty requestScope.hotMessages}">
		<div class="row">
		  <c:forEach var="hotMessage" items="${requestScope.hotMessages}" varStatus="loopCounter">
			  <div class="col-md-4"><a href="/messages/view/${hotMessage.id}">${hotMessage.name}</a></div>
		  </c:forEach>
		</div>
	</c:when>
	<c:otherwise>
	  <p>No Messages.</p>
	</c:otherwise>
</c:choose>
<br>
<table class="table table-striped">
  <thead>
    <tr>
      <th>Name</th>
      <th>Message</th>
      <th>Created at</th>
      <th>Views</th>
      <th>&nbsp;</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="message" items="${requestScope.messages}" varStatus="loopCounter">
	    <tr>
	      <td>${message.name}</td>
	      <td>${message.message}</td>
	      <td>${message.createdAt}</td>
	      <td>${message.views}</td>
	      <td>
	        <div class="btn-toolbar">
	          <div class="btn-group">
	            <a class="btn btn-default btn-sm" href="/messages/clone/${message.id}"><i class="icon-eye-open"></i> Clone</a>            
	            <a class="btn btn-default btn-sm" href="/messages/view/${message.id}"><i class="icon-eye-open"></i> View</a>            
	            <a class="btn btn-default btn-sm" href="/messages/edit/${message.id}"><i class="icon-wrench"></i> Edit</a>            
	            <a class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')" href="/messages/delete/${message.id}"><i class="icon-trash icon-white"></i> Delete</a>         
            </div>
	        </div>
	
	      </td>
	    </tr>
    </c:forEach>
  </tbody>
</table>

<p>
  <a class="btn btn-success" href="/messages/new">Add new Message</a>
</p>