<%@ page contentType="text/html;charset=UTF-8"  %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <meta name="layout" content="main" />

    <title>Lookup Business Reviews</title>

    <asset:javascript src="jquery-2.2.0.min.js" />

    <script type="text/javascript">

    $(document).ready(function() {
      $('#button').click(function(event){

        var URL = "${createLink(controller: 'business', action: 'getBusinesses')}";

        $.ajax({
          url: URL,
          data: $( '#lookupForm' ).serialize(),
          success: function(resp) {
            $('#divBody').html(resp);
          },
          onError: function(resp) {
            alert("Error: " + resp.toJSON());
            return;
          }
        });
      });
    });


    </script>
  </head>
  <body>

    <h2>Look up reviewed businesses</h2>

    <form id="lookupForm" >
      <label>City</label>
      <select name="city" id="city">
        <g:each in="${cities}" var="city">
          <option value="${city}">${city}</option>
        </g:each>

      </select><br/>
      <label>State</label>
      <select name="state" id="state" >
        <g:each in="${states}" var="${state}">
          <option value="${state}">${state}</option>
        </g:each>
      </select><br/>
      <label>Stars</label>
      <select name="stars" id="stars">
        <g:each in="${stars}" var="${star}">
          <option value="${star}">${star}</option>
        </g:each>
      </select><br/>
      <label>Min Review Count</label>
      <input type="text" name="review_count" id="review_count"/>
      <label>Use Index</label>
      <input type="checkbox" name="use_index" id="use_index"/>
      <button type="button"  id="button" name="button" >Get Businesses</button>
    </form>

    <div id="divBody" ></div>

  </body>
</html>