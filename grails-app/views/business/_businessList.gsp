
<table>
  <thead>
    <tr>
      <th>Name</th>
      <th>City</th>
      <th>State</th>
      <th>Stars</th>
      <th>Reviews</th>
    </tr>
  </thead>
  <tbody>
    <g:each in="${businesses}" var="business">
      <tr>
        <td>${business.name}</td>
        <td>${business.city}</td>
        <td>${business.state}</td>
        <td>${business.stars}</td>
        <td>${business.review_count}</td>
      </tr>
    </g:each>
  </tbody>
</table>