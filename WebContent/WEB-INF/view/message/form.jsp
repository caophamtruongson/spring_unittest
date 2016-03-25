<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<sf:errors path="message.*" cssClass="alert alert-danger" element="div" />
<hr>
<form class="form-horizontal" action="${url}" accept-charset="utf-8" method="post">
  <fieldset>
    <div class="form-group">
      <label class="control-label" for="form_name">Name</label>
        <input class="col-md-4 form-control" placeholder="Name" name="name" value="${message.name}" type="text" id="form_name" />
    </div>
    <div class="form-group">
      <label class="control-label" for="form_message">Message</label>
        <textarea class="col-md-8 form-control" rows="8" placeholder="Message" name="message" id="form_message">${message.message}</textarea>
    </div>
    <div class="form-group">
      <label class='control-label'>&nbsp;</label>
      <input class="btn btn-primary" name="submit" value="Save" type="submit" id="form_submit" />   </div>
  </fieldset>
</form>

<p><a href="/messages">Back</a></p>