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
      <input type="text" name="city" id="city" /><br/>
      <label>State</label>
      <input type="text" name="state" id="state" /><br/>
      <label>Stars</label>
      <input type="text" name="stars" id="stars" /><br/>
      <label>Min Review Count</label>
      <input type="text" name="review_count" id="review_count"/>
      <button type="button"  id="button" name="button" >Get Businesses</button>
    </form>

    <div id="divBody" ></div>

  </body>
</html>