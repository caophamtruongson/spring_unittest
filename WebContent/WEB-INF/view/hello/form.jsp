<form class="form-horizontal" action="http://localhost:8000/messages/create" accept-charset="utf-8" method="post">
  <fieldset>
    <div class="form-group">
      <label class="control-label" for="form_name">Name</label>
        <input class="col-md-4 form-control" placeholder="Name" name="name" value="" type="text" id="form_name" />
    </div>
    <div class="form-group">
      <label class="control-label" for="form_message">Message</label>
        <textarea class="col-md-8 form-control" rows="8" placeholder="Message" name="message" id="form_message"></textarea>
    </div>
    <div class="form-group">
      <label class='control-label'>&nbsp;</label>
      <input class="btn btn-primary" name="submit" value="Save" type="submit" id="form_submit" />   </div>
  </fieldset>
</form>

<p><a href="/hello">Back</a></p>