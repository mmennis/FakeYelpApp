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
        var h3 = document.createElement("h1");
        h3.appendChild(document.createTextNode("Working ..."));
        $('#divBody').html(h3);
        var URL = "${createLink(controller: 'business', action: 'getBusinesses')}";
        var start = performance.now();
        $.ajax({
          url: URL,
          data: $( '#lookupForm' ).serialize(),
          success: function(resp) {
            $('#divBody').html(resp);
            var queryTime = performance.now() - startTimer;
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
      <label>Business Name</label>
      <input name="bizname" id="bizname"/><br/>
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

      <input type="radio" name="dbtype" id="use_index" value="not_indexed"/>Use HBase<br/>
      <input type="radio" name="dbtype" id="use_index" value="indexed"/>Use MapR DB<br/>

      <button type="button"  id="button" name="button" >Get Rating</button>
    </form>


    <div id="query_time"></div>

    <div id="divBody" ></div>

  </body>
</html>